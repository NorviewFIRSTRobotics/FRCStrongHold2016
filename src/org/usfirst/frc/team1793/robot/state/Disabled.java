package org.usfirst.frc.team1793.robot.state;

import org.usfirst.frc.team1793.robot.Robot;

public class Disabled extends GameState {
	
	public Disabled(Robot robot) {
		super(robot);
		robot.setActivity(robot.getDefaultActivity());
	}

	@Override
	public void run() {}
	
}
