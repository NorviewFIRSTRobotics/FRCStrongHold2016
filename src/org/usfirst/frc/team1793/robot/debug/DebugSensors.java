package org.usfirst.frc.team1793.robot.debug;

import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;

public class DebugSensors extends Activity {

	public DebugSensors(IRobotActivity activity, IRobotControllers controllers) {
		super(activity, controllers);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		controllers.getFrontSides().debug();
		controllers.getBackSides().debug();
	}

}
