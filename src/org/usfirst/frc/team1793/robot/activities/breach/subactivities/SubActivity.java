package org.usfirst.frc.team1793.robot.activities.breach.subactivities;

import org.usfirst.frc.team1793.robot.Robot;
import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.activities.IRobotActivity;

public abstract class SubActivity extends Activity {
	protected Robot robot = (Robot) this.robot;
	public SubActivity(IRobotActivity robot) {
		super(robot);
	}
	

}
