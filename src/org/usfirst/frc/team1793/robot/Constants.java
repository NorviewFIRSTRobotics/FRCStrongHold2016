package org.usfirst.frc.team1793.robot;

public class Constants {
	public enum Progress {
		JUSTFRONT,
		ALL,
		JUSTBACK,
		NONE;
	}
	public enum Axis {
		NORTH(0),
		EAST(90),
		SOUTH(270),
		WEST(180);
		public int degree;
		Axis(int degree) {
			this.degree = degree;
		}
		
	}
	//TODO convert to inches
	public static final double FARENOUGH = 12;
	public static final double STRAIGHTENOUGH = 2.5;
	public static final double BREACH = 25;
	
	public static final double TURN_SPEED = 0.75d;
	public static final double TURN_THRESHOLD = 5d;
	public static final double DRIVE_SPEED = 0.1d;
	public static final int GYRO_PID = 0, RE_PID = 1; 
}
