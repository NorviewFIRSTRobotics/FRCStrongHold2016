package org.usfirst.frc.team1793.robot.activities.breach.subactivities;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.Constants.Direction;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;
import org.usfirst.frc.team1793.robot.components.UltrasonicPair;

public abstract class SensorActivity extends SubActivity {

	protected Direction direction;
	protected UltrasonicPair sensor;
	public SensorActivity(IRobotActivity activity,IRobotControllers controllers) {
		super(activity,controllers);
	}
	public void setDir(Direction dir) {
		direction = dir;
	}
	@Override
	public void update() {
	
		
	}
	protected double getDriveSpeed() {
		return (direction == Direction.FORWARD ? 1 : -1) * Constants.DRIVE_SPEED; 
	}

}
