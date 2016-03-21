package org.usfirst.frc.team1793.robot.activities.breach.subactivities;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.Constants.Direction;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;
import org.usfirst.frc.team1793.robot.components.UltrasonicContainer;

import edu.wpi.first.wpilibj.Timer;

public class MoveForward extends SubActivity {
	private Direction direction;
	private UltrasonicContainer sensor;
	private double seconds;
	private double start;
	public MoveForward(IRobotActivity activity, IRobotControllers controllers,double seconds) {
		super(activity,controllers);
		this.seconds = seconds;
	}

	@Override
	public void initialize() {
		start = Timer.getFPGATimestamp();
		sensor = controllers.getFace();
	}

	@Override
	public void update() {
		if(sensor.getRange() > Constants.FOOT) {
			isComplete = true;
		} 
		else if( (start-Timer.getFPGATimestamp()) < seconds) {
			controllers.getDrive().drive(getDriveSpeed());
		} else {
			controllers.getDrive().zero();
			isComplete = true;
		}
	}
	protected double getDriveSpeed() {
		return (direction == Direction.FORWARD ? 1 : -1) * Constants.DRIVE_SPEED; 
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
