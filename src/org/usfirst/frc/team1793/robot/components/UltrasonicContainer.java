package org.usfirst.frc.team1793.robot.components;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.components.UltrasonicPair.SensorPosition;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UltrasonicContainer {
	private Ultrasonic u;
	private SensorPosition s;
	public UltrasonicContainer(SensorPosition s) {
		this(new Ultrasonic(s.ping,s.echo));
		this.s = s;
	}
	public UltrasonicContainer(Ultrasonic u) {
		this.u =u;
	}
	public double getRange() {
		u.ping();
		Timer.delay(Constants.ULTRA_SONIC_DELAY);
		return u.getRangeInches();
	}
	public void debug() {	
		SmartDashboard.putNumber(s.name(),getRange());
	}
}
 