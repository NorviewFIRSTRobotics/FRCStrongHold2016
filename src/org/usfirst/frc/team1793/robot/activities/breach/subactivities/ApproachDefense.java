package org.usfirst.frc.team1793.robot.activities.breach.subactivities;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;
import org.usfirst.frc.team1793.robot.components.UltrasonicPair;

public class ApproachDefense extends SubActivity {
	UltrasonicPair sensor;

	// true goes forward, false goes reverse
	public ApproachDefense(IRobotActivity activity, IRobotControllers controllers) {
		super(activity, controllers);

	}

	@Override
	public void initialize() {
		isComplete = false;
		sensor = direction ? controllers.getFrontSides() : controllers.getBackSides();
		sensor.setRunning(true);
	}

	public void update() {
		if (sensor.getSum() > Constants.BREACH) {
			this.controllers.getDrive().drive(direction ? -1 : 1 * Constants.DRIVE_SPEED);
		} else {
			this.controllers.getDrive().drive(0);
			isComplete = true;
			sensor.setRunning(false);
			sensor = null;
		}
	}

}