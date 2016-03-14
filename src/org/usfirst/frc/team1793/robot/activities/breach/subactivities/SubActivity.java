package org.usfirst.frc.team1793.robot.activities.breach.subactivities;

import org.usfirst.frc.team1793.robot.IRobotControllers;
import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.activities.IRobotActivity;

public abstract class SubActivity extends Activity {
	public SubActivity(IRobotActivity activity,IRobotControllers controllers) {
		super(activity,controllers);
	}
	

}
