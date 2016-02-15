package org.usfirst.frc.team1793.robot;

import edu.wpi.first.wpilibj.Ultrasonic;

public class Sensors {	
	public static Ultrasonic leftFront = new Ultrasonic(0,0);
	public static Ultrasonic rightFront = new Ultrasonic(0,0);
	public static Ultrasonic leftBack = new Ultrasonic(0,0);
	public static Ultrasonic rightBack = new Ultrasonic(0,0);
	public static Ultrasonic front = new Ultrasonic(0,0);
	public static Ultrasonic back = new Ultrasonic(0,0);

	public static double getDistance(Ultrasonic sensor) {
		return sensor.getRangeInches();
	}
	public static double getDistanceSum(Ultrasonic left,Ultrasonic right) {
		return getDistance(left)+getDistance(right);
	}
	public static double getDelta(Ultrasonic left, Ultrasonic right) {
		return getDistance(left)-getDistance(right);
	}
	
	
}
