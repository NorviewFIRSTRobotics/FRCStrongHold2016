package frc.team1793.frcstronghold;

import edu.wpi.first.wpilibj.*;
import jaci.openrio.toast.core.Toast;
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
    public static boolean isDebug = false;
    private static boolean controller = false;

    /**
     * Used to detect if the current joystick setup is the single controller or two independent joysticks
     */
    private void initJoysticks() {
        Joystick l = new Joystick(0), r = new Joystick(1);
        if (Toast.isSimulation() || l.getName().contains("Attack")) {
            left = l;
            right = r;
            info(String.format("Initializing left:%s right:%s joysticks", left.hashCode(), right.hashCode()));
        } else {
            controller = true;
            left = right = new Joystick(2);
            info("Initializing %s,%s joystick (controller:%s) ", left.hashCode(), right.hashCode(), controller);
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
        createExecutor();
    }

    public void info(String s, Object... o) {
        logger.info(String.format(s,o));
    }

    public boolean isPressed(Joystick stick, int button) {
        assert  button > 0;
        boolean p = stick.getRawButton(button);
        if(p)
            info("Pressing %s:%d (controller:%s)", Toast.isSimulation() ? stick.hashCode() : stick.getName(), button, controller);
        return p;
    }
    @Override
    public void tickState(RobotState state) {
        switch (state) {
            case TELEOP:
                Activities.runBiConsumerActivity(manualDrive, right.getY(), right.getX());
                //Called when controller is active
                if (controller) {
                    Activities.runConsumerActivity(arm, left.getTwist());
                    if(isPressed(left,8)) {
                        Activities.runActivity(bucket);
                    }
                }
                //Called when two joysticks are active
                else {
                    Activities.runConsumerActivity(arm, left.getY());
                    if(isPressed(left,1)) {
                        Activities.runActivity(bucket);
                    }
                }
                break;
            case AUTONOMOUS:
                Activities.setAbort(false);
                Activities.runConsumerActivity(autonomousDrive, 5d);
                break;
            case DISABLED:
                Activities.setAbort(true);
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
