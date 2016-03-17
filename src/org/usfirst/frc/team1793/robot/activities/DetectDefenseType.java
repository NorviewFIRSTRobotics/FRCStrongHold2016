package org.usfirst.frc.team1793.robot.activities;

import org.usfirst.frc.team1793.robot.activities.breach.Breach.BreachType;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;

public class DetectDefenseType extends Activity {

	public DetectDefenseType(IRobotActivity activity,IRobotControllers controllers) {
		super(activity,controllers);
	}

	public static BreachType detectBreachType() {

		// TODO implement breach detection

		return BreachType.None;
	}

	@Override
	public void update() {}



}
