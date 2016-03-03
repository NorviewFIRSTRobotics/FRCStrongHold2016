package org.usfirst.frc.team1793.robot.system;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;

public class ShooterController extends Controller {
	private SpeedController motor;
	private boolean running;
	private DigitalInput limitSwitch = new DigitalInput(9);

	public ShooterController(SpeedController motor) {
		super(1);
		this.motor = motor;
	}

	public void shoot(double speed) {
		if (running) {
			return;
		}
		running = true;
		executor.execute(() -> {
			Timer timer = new Timer();
			timer.start();
			
			//Throw ball
			while (timer.get() < .25) {
				motor.set(speed);
			}
			//Return to store position 
			while (!inStorePosition()) {
				motor.set(speed / 4);
			}
			motor.set(0);
			running = false;
		});
	}

	public boolean inStorePosition() {
		return limitSwitch.get();
	}

}
