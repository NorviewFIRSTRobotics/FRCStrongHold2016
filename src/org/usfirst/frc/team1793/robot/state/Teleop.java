package org.usfirst.frc.team1793.robot.state;

import org.usfirst.frc.team1793.robot.Robot;

public class Teleop extends GameState{

	public Teleop(Robot robot) {
		super(robot);
		robot.setActivity(robot.getDefaultActivity());
	}

	@Override
	public void run() {
		robot.currentActivity.update();
		
	}

}
