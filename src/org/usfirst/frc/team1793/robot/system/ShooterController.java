package org.usfirst.frc.team1793.robot.system;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;

public class ShooterController extends Controller {
	private SpeedController motor;
	private boolean running;
	private DigitalInput limitSwitch = new DigitalInput(Constants.LIMIT_SWITCH_PID);

	public ShooterController(SpeedController motor,Robot robot) {
		super(1,robot);
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

			// Throw ball
			while (timer.get() < Constants.SHOOT_TIME) {
				motor.set(speed);
			}
			// Return to store position
			while (!isInStorePosition()) {
				motor.set(speed / Constants.SHOOT_RETURN_FACTOR);
			}
			motor.set(0);
			running = false;
		});
	}

	public boolean isInStorePosition() {
		return limitSwitch.get();
	}
	public boolean isRunning() {
		return running;
	}

}
