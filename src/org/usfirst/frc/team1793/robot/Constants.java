package org.usfirst.frc.team1793.robot;

public class Constants {


	// Breach is the average distance between the two sides of the robot to the
	// shields on the defenses
	// The sum of the distance of a sensor on left and right is taken and
	// compared to this value to detect if within a defense
	public static final double BREACH = 30;

	// Speed a which the robot will turn automatically
	public static final double TURN_SPEED = 0.5d;

	// Angle threshold when turning automatically with the gyroscope
	public static final double TURN_THRESHOLD = 5d;

	// Speed used by all automatic drive motors
	public static final double DRIVE_SPEED = 0.75d;

	// Speed at which the arm rotations
	public static final double ARM_SPEED = 0.4;

	// Threshold for the arm reaching its intended angle
	public static final double ARM_THRESHOLD = 0.2;

	// Offset for arm angle
	public static final double ARM_OFFSET = -.2;

	// Voltage value for the arm angle when all the way extended
	public static final double ARM_EXTENDED_ANGLE = 1;

	// Voltage value for the arm angle when stowed in robot;
	public static final double ARM_STOWED_ANGLE = 0;

	// Speed at which the shooter rotations when throwing.
	// When returning to store position, speed is
	// SHOOT_SPEED/SHOOT_RETURN_FACTOR
	public static final double SHOOT_SPEED = .75;
	public static final double SHOOT_RETURN_FACTOR = 4;

	// Amount of time the motors are run for a shoot action
	public static final double SHOOT_TIME = .25;

	public static final int GYRO_PID = 0, RE_PID = 1, DRIVE_STICK_PID = 0, ARM_STICK_PID = 1, LIMIT_SWITCH_PID = 9;
	public static final int ARM_EXTEND_BUTTON = 2;
	public static final int ARM_STOW_BUTTON = 3;
	public static final int ARM_THROW_BUTTON = 4;
	
	public static final int GLOBAL_STICK_MODIFIER_BUTTON = 1; 
	public static final int DRIVE_SIMPLE_DEFENSE_BUTTON = 7, DRIVE_RESET_BUTTON = 2;

	public static final double ULTRA_SONIC_DELAY = 0.1;

}
