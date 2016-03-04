package org.usfirst.frc.team1793.robot.activities.breach;

import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.activities.IRobotActivity;

public abstract class Breach extends Activity implements IRobotActivity {
	public Breach(IRobotActivity robot) {
		super(robot);
	}

	public static enum BreachType {
		Lowbar, SimpleDefense, Portcullis, ChevalDeFries, None;
	}

}
