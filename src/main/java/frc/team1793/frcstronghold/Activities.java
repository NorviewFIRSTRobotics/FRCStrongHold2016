package frc.team1793.frcstronghold;


import jaci.openrio.toast.core.Toast;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static frc.team1793.frcstronghold.RobotModule.*;

/**
 * Created by tyler on 10/28/16.
 */
public class Activities {
    private static volatile boolean abort = false;
    public static void setAbort(boolean abort) {
        Activities.abort = abort;
    }
    private static final double ARM_START = 10, ARM_END = 3030;
    /**
     * @param activity, Activity to invoke run within a new Executor Thread
     */
    public static void runActivity(Runnable activity) {
        if(RobotModule.isDebug)
            RobotModule.logger.info(String.format("Running Activity %s", activity.getClass().getTypeName()));
        executor.execute(activity);
    }


    /**
     * @param activity {@link Consumer} to run in a thread with the {@link java.util.concurrent.Executor}
     * @param t T instance passed to activity
     * @param <T> Type to pass to the activity
     */
    public static <T> void runConsumerActivity(Consumer<T> activity, T t) {
        if(RobotModule.isDebug)
        RobotModule.logger.info(String.format("Running Activity %s", activity.getClass().getTypeName()));
        executor.execute( () -> activity.accept(t));
    }


    /**
     * @param activity {@link BiConsumer} to run in a thread with the {@link java.util.concurrent.Executor}
     * @param t T instance passed to activity
     * @param u U instance passed to the activity
     * @param <T> Type to pass to the activity
     * @param <U> Type to pass to the activity
     */
    public static <T,U> void runBiConsumerActivity(BiConsumer<T,U> activity, T t, U u) {
        if(RobotModule.isDebug)
            RobotModule.logger.info(String.format("Running Activity %s", activity.getClass().getTypeName()));
        executor.execute( () -> activity.accept(t,u));
    }

    /**
     * @param forward {@link Double} value to pass to {@link edu.wpi.first.wpilibj.RobotDrive} threadDrive
     * @param turn {@link Double} value to pass to {@link edu.wpi.first.wpilibj.RobotDrive} threadDrive
     */
    public static BiConsumer<Double,Double> manualDrive = (forward, turn) -> {
        if(forward == 0 && turn == 0)
            treadDrive.stopMotor();
        else
            treadDrive.arcadeDrive(-forward, -turn);
    };

    /**
     * @param time {@link Double} value for the length of time the robot should go forward
     */
    public static Consumer<Double> autonomousDrive = (time) -> {
        if(abort)
            return;
        if (autonomousTimer.isStarted()) {
            logger.info("" + autonomousTimer.get());
            if (autonomousTimer.getLap() == 0 && !autonomousTimer.hasPeriodPassed(time)) {
                //Go forward for $time seconds for only the first lap
                treadDrive.drive(1, 0);
            } else {
                treadDrive.stopMotor();
                autonomousTimer.stop();
            }
        } else {
            autonomousTimer.start();
        }
    };



    private static boolean isShooting = false;
    public static Runnable bucket = () -> {
        if(abort)
            return;
        if (isShooting)
            return;
        isShooting = true;

        TrackableTimer timer = new TrackableTimer();
        timer.start();
        System.out.println(timer.get());
        while (!timer.hasPeriodPassed(.2, 1)) {
            if(abort)
                break;
            bucketDrive.set(-.5);
        }
        System.out.println("Shot");
        timer.stop();
        while (!limitSwitch.get()) {
            if(abort)
                break;
            bucketDrive.set(.5);
        }
        bucketDrive.stopMotor();
        System.out.println("Done");
        isShooting = false;
    };

    /**
     * Used to map a number range from [inputStart, inputEnd] to [outputStart, outputEnd]
     * @param value input to convert to the new range
     * @param inputStart first value of the initial range
     * @param inputEnd last value of the initial range
     * @param outputStart first value of the new range
     * @param outputEnd last value of the new range
     * @return returns the new value
     */
    public static double mapToRange(double value, double inputStart, double inputEnd, double outputStart, double outputEnd) {
        double inputRange = inputEnd-inputStart;
        double outputRange = outputEnd-outputStart;
        return (value-inputStart)*outputRange/inputRange+outputStart;
    }

    public static double getArmAngle() {
        double inputAngle = clampDouble(armRotaryEncoder.getValue(),ARM_START,ARM_START);
        if(Toast.isSimulation())
            inputAngle = ARM_END;
        return mapToRange(inputAngle,ARM_START,ARM_END, -1,1);
    }
    public static double clampDouble(double val, double min, double max) {
        return val > max ? max : val < min ? min : val;
    }
    public static Consumer<Double> arm = (v) -> { if(v < .2d)  armDrive.stopMotor(); else armDrive.set(-v); };

    //TODO this might work?
    public static BiConsumer<Double, Double> armToAngle = (theta, speed) -> {
        if(abort)
            return;
        //input read angle from the rotary encoder
        double currentAngle = getArmAngle();
        //set predicate if the intended angle is < or > than currentAngle
        Predicate<Double> reached = theta <= currentAngle ? (c) -> theta <= c : (c1) -> theta > c1;
        System.out.println(String.format("t %s, c %s,  t < c: %s",theta, currentAngle, theta <= currentAngle));
        //test if the arm has not reached the angle yet
        while(reached.test(currentAngle)) {
            if(abort)
                break;
            //move the arm towards the angle
            System.out.println("moving!");
            arm.accept(clampDouble(currentAngle-theta, -speed,speed));
        }
    };

}
