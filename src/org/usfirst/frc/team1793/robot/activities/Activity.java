package org.usfirst.frc.team1793.robot.activities;

public abstract class Activity  {
	protected IRobotActivity robot; 
	public Activity(IRobotActivity robot) {
		this.robot = robot;
		
	}

//	called when an activity is selected as the current activity
	public abstract void initialize();
	//(called by the xxxPeriodic methods): this would be used to advance the internal state of each. object
	public abstract void update();
//	this would allow us to cancel an activity at any time (e.g. when switching to disabled mode)
	public abstract void cancel();
}
