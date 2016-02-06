package org.usfirst.frc.team1793.robot.system;

import edu.wpi.first.wpilibj.SpeedController;

public class ArmController extends Controller {

	public ArmController(SpeedController motor) {
		super(new Arm(motor), 2);
	}
	public void lift(double speed) {
		executor.execute( () -> ((Arm)slave).lift(speed));
	}
	public static class Arm implements ISystem{
		SpeedController motor;
		public Arm(SpeedController motor) {
			this.motor = motor;
		}
		public void lift(double speed) {
			motor.set(speed);
		}
	}
}
