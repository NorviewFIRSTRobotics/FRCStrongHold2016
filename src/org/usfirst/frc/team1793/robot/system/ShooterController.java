package org.usfirst.frc.team1793.robot.system;

import edu.wpi.first.wpilibj.SpeedController;

public class ShooterController extends Controller {
	
	public ShooterController(SpeedController motor) {
		super(new Shooter(motor), 2);
	}
	public void shoot(double motor) {
		executor.execute(() -> ((Shooter)slave).shoot(motor));
	}
	public static class Shooter implements ISystem {
		private SpeedController motorMotor;
		public Shooter(SpeedController motor) {
			motorMotor = motor;
			
		}
		public void shoot(double motorSpeed) {
			motorMotor.set(motorSpeed);
			
		}
	}

}
