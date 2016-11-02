package frc.team1793.frcstronghold;

import edu.wpi.first.wpilibj.*;
import jaci.openrio.toast.lib.log.Logger;
import jaci.openrio.toast.lib.module.ToastStateModule;
import jaci.openrio.toast.lib.state.RobotState;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static frc.team1793.frcstronghold.Activities.*;

public class RobotModule extends ToastStateModule {
    public static Logger logger;
    public static TrackableTimer autonomousTimer;
    public static RobotDrive treadDrive;
    public static SpeedController armDrive, bucketDrive;
    public static volatile DigitalInput limitSwitch;
    public static ExecutorService executor;
    public static AnalogInput armRotaryEncoder;
    public static Joystick left, right;


    private static boolean controller = false;

    /**
     * Used to detect if the current joystick setup is the single controller or two independent joysticks
     */
    private void initJoysticks() {
        Joystick l = new Joystick(0), r = new Joystick(1);

        if (l.getName().contains("Attack")) {
            left = l;
            right = r;
            logger.info(String.format("Initializing left:%s right:%s joysticks", left.hashCode(), right.hashCode()));
        } else {
            controller = true;
            left = right = new Joystick(2);
            logger.info(String.format("Initializing %s,%s joystick", left.hashCode(), right.hashCode()));
        }
    }

    private static int motorPID = 0;

    private static int nextPID() {
        return motorPID++;
    }

    @Override
    public void prestart() {
        logger = new Logger("FRCStrongHold", Logger.ATTR_DEFAULT);
        treadDrive = new RobotDrive(nextPID(), nextPID(), nextPID(), nextPID());
        bucketDrive = new Victor(nextPID());
        armDrive = new Victor(nextPID());
        limitSwitch = new DigitalInput(9);
        armRotaryEncoder = new AnalogInput(1);
        initJoysticks();
    }

    @Override
    public void start() {
        autonomousTimer = new TrackableTimer();
    }

    @Override
    public void tickState(RobotState state) {
        if (executor == null)
            createExecutor();
        switch (state) {
            case TELEOP:
                Activities.runBiConsumerActivity(manualDrive, right.getY(), right.getX());
                if (controller) {
                    Activities.runConsumerActivity(arm, left.getY());

                    //TODO test this; likely doesn't work
                    if(left.getRawButton(0))
                        Activities.runBiConsumerActivity(armToAngle,360.0,.5d);
                    if(left.getRawButton(1))
                        Activities.runBiConsumerActivity(armToAngle,0.0,.5d);
                }
                else Activities.runConsumerActivity(arm, left.getTwist());
                if (controller ? left.getRawButton(8) : left.getRawButton(1)) Activities.runActivity(bucket);
                break;
            case AUTONOMOUS:
                Activities.runConsumerActivity(autonomousDrive, 5d);
                break;
            case DISABLED:
                executor = null;
                break;
        }
    }

    private void createExecutor() {
        executor = Executors.newFixedThreadPool(3);
    }

    @Override
    public String getModuleName() {
        return "FRCStrongHold";
    }

    @Override
    public String getModuleVersion() {
        return "0.0.1";
    }
}
