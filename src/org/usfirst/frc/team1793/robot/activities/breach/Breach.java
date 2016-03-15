package org.usfirst.frc.team1793.robot.activities.breach;

import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;

public abstract class Breach extends Activity implements IRobotActivity {
	public Breach(IRobotActivity activity,IRobotControllers controllers) {
		super(activity,controllers);
	}

	public static enum BreachType {
		Lowbar, SimpleDefense, Portcullis, ChevalDeFries, None;
	}

}
