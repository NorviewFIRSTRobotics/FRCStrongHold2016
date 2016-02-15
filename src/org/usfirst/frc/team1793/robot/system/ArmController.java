package org.usfirst.frc.team1793.robot.system;

import edu.wpi.first.wpilibj.SpeedController;

public class ArmController extends Controller {
	Arm arm;
	public ArmController(SpeedController motor) {
		super(2);
		arm = new Arm(motor);
	}
	public void lift(double speed) {
		executor.execute( () -> arm.lift(speed));
	}
	private static class Arm {
		SpeedController motor;
		public Arm(SpeedController motor) {
			this.motor = motor;
		}
		public void lift(double speed) {
			motor.set(speed);
		}
	}
}
