package org.usfirst.frc.team1793.robot.state;

import org.usfirst.frc.team1793.robot.Robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class GameState extends State {
	public static JoystickButton resetGyro = new JoystickButton(Robot.driveStick, 1);
	@Override
	public void run() {
		if(resetGyro.get()) {
			Robot.gyro.calibrate();
		}
		if(Robot.INSTANCE.isEnabled()) {
			System.out.println("robot is enabled");
		} else {
			try {
				Thread.currentThread().join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public State next() throws InvalidStateException {
		return null;
	}
	
}
 