package org.usfirst.frc.team1793.robot.activities.breach;

import org.usfirst.frc.team1793.robot.IRobotControllers;
import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.activities.IRobotActivity;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.MoveForward;

public class BreachLowbar extends Breach {

	MoveForward _forward = new MoveForward(activity, controllers, 2);
	Activity currentActivity;
	public BreachLowbar(IRobotActivity activity, IRobotControllers controllers) {
		super(activity,controllers);
	}

	@Override
	public void initialize() {
		setActivity(getDefaultActivity());
	}

	@Override
	public void update() {
		currentActivity.update();
	}

	@Override
	public Activity getDefaultActivity() {
		return _forward;
	}

	@Override
	public Activity getDetectDefenseActivity() {
		return null;
	}

	@Override
	public void setActivity(Activity activity) {
		this.currentActivity = activity; 
	}

}
