package org.usfirst.frc.team1793.robot.activities.breach.subactivities;

import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;

public class StowArm extends SubActivity {

	public StowArm(IRobotActivity activity, IRobotControllers controllers) {
		super(activity, controllers);
	}

	@Override
	public void update() {
		if(!controllers.getArm().isLatestOperationFinished()) {
			controllers.getArm().stow();
		} else {
			isComplete = true;
		}
	}

}
