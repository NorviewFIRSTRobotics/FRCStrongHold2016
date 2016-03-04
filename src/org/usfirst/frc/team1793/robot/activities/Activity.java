package org.usfirst.frc.team1793.robot.activities;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class Activity  {
	protected IRobotActivity robot;
	protected boolean isComplete;
	public Activity(IRobotActivity robot) {
		this.robot = robot;
	}

	// called when an activity is selected as the current activity
	public abstract void initialize();

	// (called by the xxxPeriodic methods): this would be used to advance the
	// internal state of each. object
	public abstract void update();

	// this would allow us to cancel an activity at any time (e.g. when
	// switching to disabled mode)
	public void cancel() {
		robot.setActivity(robot.getDefaultActivity());
	}

	public boolean isComplete() {
		if(isComplete)
			SmartDashboard.putString("Current Activity", this.getClass().getSimpleName()+":completed");
		return isComplete;
	}
}
