package org.usfirst.frc.team1793.robot.activities.defaults;

import org.usfirst.frc.team1793.robot.IRobotControllers;
import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.activities.IRobotActivity;

public class Idle extends Activity {

	public Idle(IRobotActivity activity, IRobotControllers controllers) {
		super(activity,controllers);
	}
	@Override
	public void initialize() {}

	@Override
	public void update() {

	}

	@Override
	public boolean isComplete() {
		return false;
	}

}
