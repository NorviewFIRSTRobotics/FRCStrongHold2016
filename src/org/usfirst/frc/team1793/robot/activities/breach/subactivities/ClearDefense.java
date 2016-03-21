package org.usfirst.frc.team1793.robot.activities.breach.subactivities;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.Constants.Direction;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;

public class ClearDefense extends SensorActivity {
	public ClearDefense(IRobotActivity activity, IRobotControllers controllers) {
		super(activity,controllers);
	}

	@Override
	public void initialize() {
		isComplete = false;
		sensor = direction == Direction.FORWARD ? controllers.getBackSides() : controllers.getFrontSides();
	}

	@Override
	public void update() {			
	
		if(sensor.getSum() <= Constants.BREACH) {
			this.controllers.getDrive().drive(getDriveSpeed());
		} else {
			isComplete = true;
		}
	}
	
}
