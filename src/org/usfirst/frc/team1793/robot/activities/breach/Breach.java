package org.usfirst.frc.team1793.robot.activities.breach;

import org.usfirst.frc.team1793.robot.activities.Activity;

public abstract class Breach extends Activity {
	public static enum BreachType {
		Lowbar,
		SimpleDefense,
		Portcullis,
		ChevalDeFries,
		None;
	}

	
	public Breach() {
	}
	
	
}
