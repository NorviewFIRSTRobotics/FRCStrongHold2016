package org.usfirst.frc.team1793.robot;

public class Utils {
	public static boolean withInAbsThreshold(double current, double intended, double threshold) {
		double abs = Math.abs(current);
		return (current - intended) <= threshold;
	}
}
