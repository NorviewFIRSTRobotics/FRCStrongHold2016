package frc.team1793.frcstronghold;

import edu.wpi.first.wpilibj.*;
import jaci.openrio.toast.lib.log.Logger;
import jaci.openrio.toast.lib.module.ToastStateModule;
import jaci.openrio.toast.lib.state.RobotState;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RobotModule extends ToastStateModule {
    public static Logger logger;
    private static RobotDrive treadDrive;
    private static SpeedController armDrive,bucketDrive;
    private static Joystick left, right;
    private static DigitalInput limitSwitch1;
    private static ExecutorService executor;
    private static AnalogInput armRotaryEncoder;
    private static int motorPID = 0;
    private static int nextPID() {
        return motorPID++;
    }
    @Override
    public void prestart() {
        logger = new Logger("FRCStrongHold", Logger.ATTR_DEFAULT);
        treadDrive = new RobotDrive(nextPID(),nextPID(),nextPID(),nextPID());
        armDrive = new Victor(nextPID());
        bucketDrive = new Victor(nextPID());
        left = new Joystick(1);
        right = new Joystick(0);
        limitSwitch1 = new DigitalInput(9);
        armRotaryEncoder = new AnalogInput(1);
    }
    private TrackableTimer autonomousTimer;
    @Override
    public void start() {
        autonomousTimer = new TrackableTimer();
        executor = Executors.newFixedThreadPool(3);
    }
    private volatile boolean isShooting = false;
    @Override
    public void tickState(RobotState state) {
        switch(state) {
            case TELEOP:
                executor.execute(() -> treadDrive.arcadeDrive(right.getY(),right.getTwist()));
                executor.execute(()-> armDrive.set(left.getY()));
                if(!isShooting && right.getButton(Joystick.ButtonType.kTrigger)) {
                    executor.execute(() -> {
                        isShooting = true;
                        TrackableTimer bucketTimer = new TrackableTimer();
                        bucketTimer.start();
                        while(bucketTimer.get() < 2) {
                            logger.info("Throwing Ball!");
                            bucketDrive.set(.5);
                        }
                        autonomousTimer.stop();
                        autonomousTimer.start();
                        while(!limitSwitch1.get()) {
                            if(autonomousTimer.get() > 5)
                                break;
                            bucketDrive.set(-.5);
                            logger.info("Returning Bucket");
                        }
                        bucketDrive.stopMotor();
                        logger.info("Successful Return!");
                        isShooting = false;
                    });
                }
                break;
            case AUTONOMOUS:
                if(autonomousTimer.isStarted()) {
                    logger.info(""+ autonomousTimer.get());
                    if(autonomousTimer.getLap() == 0 && !autonomousTimer.hasPeriodPassed(10)) {
                        //Go forward for 10 seconds for only the first lap
                        treadDrive.drive(1, 0);
                    } else {
                        treadDrive.stopMotor();
                    }
                } else {
                    autonomousTimer.start();
                }
                break;
            case DISABLED:
                break;
        }
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
