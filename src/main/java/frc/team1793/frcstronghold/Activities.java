package frc.team1793.frcstronghold;


import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static frc.team1793.frcstronghold.RobotModule.*;

/**
 * Created by tyler on 10/28/16.
 */
public class Activities {
    private static final double ARM_START = 0, ARM_END = 360;
    /**
     * @param activity, Activity to invoke run within a new Executor Thread
     */
    public static void runActivity(Runnable activity) {
        executor.execute(activity);
    }



    /**
     * @param activity {@link Consumer} to run in a thread with the {@link java.util.concurrent.Executor}
     * @param t T instance passed to activity
     * @param <T> Type to pass to the activity
     */
    public static <T> void runConsumerActivity(Consumer<T> activity, T t) {
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
        executor.execute( () -> activity.accept(t,u));
    }

    /**
     * @param forward {@link Double} value to pass to {@link edu.wpi.first.wpilibj.RobotDrive} threadDrive
     * @param turn {@link Double} value to pass to {@link edu.wpi.first.wpilibj.RobotDrive} threadDrive
     */
    public static BiConsumer<Double,Double> manualDrive = (forward, turn) -> treadDrive.arcadeDrive(-forward, -turn);

    /**
     * @param time {@link Double} value for the length of time the robot should go forward
     */
    public static Consumer<Double> autonomousDrive = (time) -> {
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
        if (isShooting)
            return;
        isShooting = true;

        TrackableTimer timer = new TrackableTimer();
        timer.start();
        System.out.println(timer.get());
        while (!timer.hasPeriodPassed(.2, 1)) {
            bucketDrive.set(-.5);
        }
        System.out.println("Shot");
        timer.stop();
        while (!limitSwitch.get()) {
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
        return mapToRange(armRotaryEncoder.getValue(),ARM_START,ARM_END, 0,360);
    }
    public static double clampDouble(double val, double min, double max) {
        return val > max ? max : val < min ? min : val;
    }
    public static Consumer<Double> arm = (v) ->armDrive.set(-v);

    public static BiConsumer<Double, Double> armToAngle = (theta, speed) -> {
        double currentAngle = getArmAngle();
        while(theta > currentAngle || theta < currentAngle) {
            arm.accept(clampDouble(currentAngle-theta, -speed,speed));
        }
    };

}
