package org.usfirst.frc.team1793.robot.activities.breach.subactivities;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;

public class ApproachArmDefense extends SubActivity {

	public ApproachArmDefense(IRobotActivity activity, IRobotControllers controllers) {
		super(activity, controllers);
	}
	@Override
	public void initialize() {
		
		super.initialize();
	}
	@Override
	public void update() {
		if(controllers.getFace().isCloseEnough()) {
			isComplete = true;
		} else {
			controllers.getDrive().drive(Constants.DRIVE_SPEED);
		}
	}
	
	
}