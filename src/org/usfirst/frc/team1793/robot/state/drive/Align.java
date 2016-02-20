package org.usfirst.frc.team1793.robot.state.drive;

import org.usfirst.frc.team1793.robot.Constants.Axis;
import org.usfirst.frc.team1793.robot.Robot;
import org.usfirst.frc.team1793.robot.state.State;
import org.usfirst.frc.team1793.robot.system.DriveController;

public class Align extends State {

	public Axis axis;

	public Align(Axis axis) {
		this.axis = axis;
	}
	
	@Override
	public void run() {
		if(!DriveController.turnFinished) {
			Robot.drive.turn(axis.degree);
		} else {
			try {
				next();
			} catch (InvalidStateException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public State next() throws InvalidStateException {
		return next;
	}

}
