package org.usfirst.frc.team1793.robot.system;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterController extends Controller {
	private SpeedController motor;
	private volatile boolean running;
	private DigitalInput limitSwitch = new DigitalInput(Constants.LIMIT_SWITCH_PID);

	public ShooterController(SpeedController motor,Robot robot) {
		super(1,robot);
		this.motor = motor;
	}

	public void shoot(double speed) {
		if (running) {
			return;
		}
		Timer timer = new Timer();
		running = true;
		executor.execute(() -> {
			//SmartDashboard.putString("Shootball Progress", "Starting");
			
			timer.reset();
			timer.start();
			
			//SmartDashboard.putString("Shootball Progress", "Initializing Timer");
			// Throw ball
			//SmartDashboard.putString("Shootball Progress", "Throwing Ball");
			while (timer.get() < Constants.SHOOT_TIME) {
				motor.set(speed);
				
				//SmartDashboard.putString("Shootball Progress", "Throwing Ball for " + timer.get() + " sec");
			}
			motor.set(0);
			// Return to store position
			//SmartDashboard.putString("Shootball Progress", "Returning to store position");
			while (!isInStorePosition()) {
				motor.set(-speed/2);
			}
			//SmartDashboard.putString("Shootball Progress", "Done");
			motor.set(0);
			running = false;
//			Timer.delay(.1);
		});
	}

	public boolean isInStorePosition() {
		return limitSwitch.get();
	}
	public boolean isRunning() {
		return running;
	}

}
