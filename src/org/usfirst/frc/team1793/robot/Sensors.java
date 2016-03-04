package org.usfirst.frc.team1793.robot;

import edu.wpi.first.wpilibj.Ultrasonic;

public class Sensors {
	public static Ultrasonic leftFront;
	public static Ultrasonic rightFront;
	public static Ultrasonic leftBack;
	public static Ultrasonic rightBack;
	public static Ultrasonic front;
	public static Ultrasonic back;
	
	public static double getDistance(Ultrasonic sensor) {
		return sensor.getRangeInches();
	}

	public static double getDistanceSum(Ultrasonic left, Ultrasonic right) {
		return getDistance(left) + getDistance(right);
	}

	public static double getDelta(Ultrasonic left, Ultrasonic right) {
		return getDistance(left) - getDistance(right);
	}
/*
	public static void test() {
		Ultrasonic u1;
		double start = Timer.getMatchTime(), end;
		for (int i = 0; i < 100; i++) {
			u1 = new Ultrasonic(0, 1);
			u1.setAutomaticMode(true);
			Timer.delay(.1d);
			System.out.println(u1.getRangeInches());
			u1.free();
		}
		end = Timer.getMatchTime();
		System.out.println(String.format("start:%d, end:%d, diff:%d", start, end, (end - start)));
	}*/

}
