package org.usfirst.frc.team1793.robot.activities;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;
import org.usfirst.frc.team1793.robot.system.DriveController;

public class Turn extends Activity {
	private double startingAngle,turnAngle, endAngle;
	public Turn(IRobotActivity activity,IRobotControllers controllers, double turnAngle) {
		super(activity,controllers);
		this.turnAngle = turnAngle;
	}

	@Override
	public void initialize() {
		startingAngle = normalizeAngle(controllers.getGyro().getAngle());
		endAngle = startingAngle+turnAngle;
	}

	@Override
	public void update() {
		DriveController drive = controllers.getDrive();
		double dir = getTurnDirection();
		double currentAngle = normalizeAngle(controllers.getGyro().getAngle());
		
		if(Math.abs(currentAngle - endAngle) > Constants.TURN_THRESHOLD) { 
			drive.turn(Constants.TURN_SPEED*dir);
		} else {
			isComplete = true;
		}
	}
	private double getTurnDirection() {
		return Math.pow(endAngle - startingAngle,0);
	}
	private double normalizeAngle(double angle) {
		return angle%360;
	}

}
