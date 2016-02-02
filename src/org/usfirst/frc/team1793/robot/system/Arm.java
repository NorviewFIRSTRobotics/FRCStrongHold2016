package org.usfirst.frc.team1793.robot.system;

import edu.wpi.first.wpilibj.SpeedController;

public class Arm implements ISystem{
	SpeedController motor;
	public Arm(SpeedController motor) {
		this.motor = motor;
	}
	public void lift(double speed) {
		motor.set(speed);
	}
}
