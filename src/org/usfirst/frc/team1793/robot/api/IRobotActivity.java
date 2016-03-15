package org.usfirst.frc.team1793.robot.api;

import org.usfirst.frc.team1793.robot.activities.Activity;

public interface IRobotActivity extends IActivityHandler {

	public Activity getDefaultActivity();
	
	public Activity getDetectDefenseActivity();
		
}
