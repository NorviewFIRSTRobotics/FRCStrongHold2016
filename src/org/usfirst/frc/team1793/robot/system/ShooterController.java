package org.usfirst.frc.team1793.robot.system;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;

public class ShooterController extends Controller {
	Shooter shooter;
	private boolean running = false; 
	public DigitalInput in = new DigitalInput(9);
	public ShooterController(SpeedController motor) {
		super(1);
		shooter = new Shooter(motor);
	}
	public void shoot(double motor) {
		if(running) {
			return;
		}
		
		running = true;
		executor.execute(() -> 
		{
			Timer timer = new Timer();
			timer.start();
			while(timer.get() < .25) {
				System.out.println("Time is "+timer.get());
				shooter.shoot(-motor);
			} 
			while(!on()) {
				shooter.shoot(motor/4);
			}
			shooter.shoot(0);
			running = false;
		}
		);
	}
	public boolean on() {
		return in.get();
	}
	private static class Shooter {
		private SpeedController motor;
//		private double startTime;
//		private final double DURATION = 1.5;
		public Shooter(SpeedController motor) {
			this.motor = motor;
			
		}
		public void shoot(double motorSpeed) {
			motor.set(motorSpeed);			
		}		
	}
}