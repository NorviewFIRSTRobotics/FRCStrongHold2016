package org.usfirst.frc.team1793.robot.activities.breach.subactivities;

import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;
import org.usfirst.frc.team1793.robot.components.UltrasonicPair;

public abstract class SubActivity extends Activity {
	protected boolean direction;
	protected UltrasonicPair sensor;
	public SubActivity(IRobotActivity activity,IRobotControllers controllers) {
		super(activity,controllers);
	}
	public void setDir(boolean dir) {
		direction = dir;
	}

}
