package org.usfirst.frc.team1793.robot.activities.breach.subactivities;

import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;

public class ExtendArm extends SubActivity {

	public ExtendArm(IRobotActivity activity, IRobotControllers controllers) {
		super(activity, controllers);
	}
	@Override
	public void initialize() {
		super.initialize();
		controllers.getArm().extend();
	}
	@Override
	public void update() {
		if(controllers.getArm().isFinished()) 
			isComplete = true;
	}

}
