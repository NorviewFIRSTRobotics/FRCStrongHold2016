package org.usfirst.frc.team1793.robot;

public class Constants {

	public enum BreachProgress {
		JUSTFRONT, ALL, JUSTBACK, NONE;
	}

//	public enum Axis {
//		NORTH(0), EAST(90), SOUTH(270), WEST(180);
//		public int degree;
//
//		Axis(int degree) {
//			this.degree = degree;
//		}
//
//	}
//
//	public enum Relative {
//		L90(1, -1), R90(1, 1);
//		public double time, sign;
//
//		private Relative(double time, double sign) {
//			this.time = time;
//			this.sign = sign;
//		}
//	}

	// TODO convert to inches
	public static final double FARENOUGH = 12;
	public static final double STRAIGHTENOUGH = 2.5;
	
	public static final double BREACH = 30;

	public static final double TURN_SPEED = 0.5d;
	public static final double TURN_THRESHOLD = 5d;

	public static final double DRIVE_SPEED = 0.75d;

	public static final double ARM_SPEED = 0.4;
	public static final double ARM_THRESHOLD = 0.2;

	public static final double SHOOT_SPEED = .75;
	
	
	public static final int GYRO_PID = 0, RE_PID = 1,DRIVE_STICK_PID=0, ARM_STICK_PID=1;
	public static final int ARM_THROW_BUTTON=1;
	public static final int DRIVE_SIMPLE_DEFENSE_BUTTON=1;
	public static final int RESET_BUTTON = 8;
	
	public static final int LIMIT_SWITCH_PID = 9;

}
