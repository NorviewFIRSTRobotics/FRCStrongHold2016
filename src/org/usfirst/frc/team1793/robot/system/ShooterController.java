package org.usfirst.frc.team1793.robot.system;

import edu.wpi.first.wpilibj.SpeedController;

public class ShooterController extends Controller {
	Shooter shooter;
	public ShooterController(SpeedController motor) {
		super(1);
		shooter = new Shooter(motor);
	}
	public void shoot(double motor) {
		executor.execute(() -> shooter.shoot(motor));
	}
	private static class Shooter {
		private SpeedController motorMotor;
		public Shooter(SpeedController motor) {
			motorMotor = motor;
			
		}
		public void shoot(double motorSpeed) {
			motorMotor.set(motorSpeed);
		}
	}

}
