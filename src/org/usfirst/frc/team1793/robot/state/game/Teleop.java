package org.usfirst.frc.team1793.robot.state.game;

import org.usfirst.frc.team1793.robot.Robot;
import org.usfirst.frc.team1793.robot.state.State;

public class Teleop  extends GameState{

	@Override
	public State next() throws InvalidStateException {
		return new NoState();
	}

	@Override
	public void run() {
		Robot.drive.arcadeDrive(Robot.leftStick.getY(), Robot.leftStick.getZ());
	}

}
