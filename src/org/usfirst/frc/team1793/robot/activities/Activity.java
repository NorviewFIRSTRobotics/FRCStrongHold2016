package org.usfirst.frc.team1793.robot.activities;

import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class Activity  {
	protected IRobotActivity activity;
	protected IRobotControllers controllers;
	
	protected boolean isComplete;
	public Activity(IRobotActivity activity,IRobotControllers controllers) {
		this.activity = activity;
		this.controllers = controllers;
	}

	// called when an activity is selected as the current activity
	public void initialize(){
		isComplete = false;
	}

	// (called by the xxxPeriodic methods): this would be used to advance the
	// internal state of each. object
	public abstract void update();

	// this would allow us to cancel an activity at any time (e.g. when
	// switching to disabled mode)
	public void cancel() {
		activity.setActivity(activity.getDefaultActivity());
	}

	public boolean isComplete() {
		if(isComplete)
			SmartDashboard.putString(this.getClass().getSimpleName(), this.getClass().getSimpleName()+":completed");
		return isComplete;
	}
	
	
}
