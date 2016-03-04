package org.usfirst.frc.team1793.robot.state;

import org.usfirst.frc.team1793.robot.Robot;

public abstract class GameState {
	Robot robot;
	public GameState(Robot robot) {
		this.robot = robot;
	}
	public abstract void run();
}
