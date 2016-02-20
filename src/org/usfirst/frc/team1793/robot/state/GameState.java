package org.usfirst.frc.team1793.robot.state;

import org.usfirst.frc.team1793.robot.Robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class GameState extends State {
	public static JoystickButton resetGyro = new JoystickButton(Robot.leftStick, 1);
	@Override
	public void run() {
		if(resetGyro.get()) {
			Robot.gyro.calibrate();
		}
	}

	@Override
	public State next() throws InvalidStateException {
		return null;
	}
	
}
