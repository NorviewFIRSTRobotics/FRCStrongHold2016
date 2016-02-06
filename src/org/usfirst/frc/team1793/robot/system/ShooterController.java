package org.usfirst.frc.team1793.robot.system;

import edu.wpi.first.wpilibj.SpeedController;

public class ShooterController extends Controller {
	
	public ShooterController(SpeedController front, SpeedController back) {
		super(new Shooter(front, back), 2);
	}
	public void shoot(double frontSpeed, double backSpeed) {
		executor.execute(() -> ((Shooter)slave).shoot(frontSpeed, backSpeed));
	}
	public static class Shooter implements ISystem {
		private SpeedController frontMotor, backMotor;
		public Shooter(SpeedController front, SpeedController back) {
			frontMotor = front;
			backMotor = back;
		}
		public void shoot(double frontSpeed, double backSpeed) {
			frontMotor.set(frontSpeed);
			backMotor.set(backSpeed);
		}
	}

}
