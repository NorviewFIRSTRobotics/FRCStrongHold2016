package org.usfirst.frc.team1793.robot.activities.breach.subactivities;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;
import org.usfirst.frc.team1793.robot.components.UltrasonicPair;
import org.usfirst.frc.team1793.robot.components.UltrasonicPair.SensorPosition;

public class EnterDefense extends SubActivity {
	UltrasonicPair back;
	public EnterDefense(IRobotActivity activity, IRobotControllers controllers) {
		super(activity,controllers);
	}

	@Override
	public void initialize() {
		isComplete = false;
		back = controllers.getBackSides();
		back.setRunning(true);
	}

	@Override
	public void update() {			
		
		if(back.getSum() > Constants.BREACH) {
			this.controllers.getDrive().drive(Constants.DRIVE_SPEED);
		} else {
			this.controllers.getDrive().drive(0);
			isComplete = true;
			back.setRunning(false);
			back = null;
		}
	}
	
}
