package org.usfirst.frc.team1793.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Sensors {
	public enum Ultrasonics {
		FRONTLEFT(0, 1),
		FRONTRIGHT(5,4),
		BACKLEFT(3,4),
		BACKRIGHT(2,1),
		FRONT(6,7),
		BACK(8,7);
		public int echo,ping;
		private Ultrasonics(int echo, int ping) {
			this.echo = echo;
			this.ping = ping;
		}
	}
	
	public static double getDistance(Ultrasonic sensor) {
		return sensor.getRangeInches();
	}

	public static double getDistanceSum(Ultrasonic left, Ultrasonic right) {
		return getDistance(left) + getDistance(right);
	}

	public static double getDelta(Ultrasonic left, Ultrasonic right) {
		return getDistance(left) - getDistance(right);
	}
	static Ultrasonic u = new Ultrasonic(1,2);
	
	
	
	
	
	
	public static void test() {
		u.ping();
		Timer.delay(.1);
		
		SmartDashboard.putNumber("sonic",u.getRangeInches());
	}

}
