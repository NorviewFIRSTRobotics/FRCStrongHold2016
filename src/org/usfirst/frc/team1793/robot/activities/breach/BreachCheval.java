package org.usfirst.frc.team1793.robot.activities.breach;

import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.activities.IRobotActivity;

public class BreachCheval extends Breach {

	public BreachCheval(IRobotActivity robot) {
		super(robot);
	}

	@Override
	public void initialize() {}

	@Override
	public void update() {}
	
	@Override
	public Activity getDefaultActivity() {
		return null;
	}

	@Override
	public Activity getDetectDefenseActivity() {
		return null;
	}

	@Override
	public void setActivity(Activity activity) {}


}
