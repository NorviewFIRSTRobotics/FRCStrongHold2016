package org.usfirst.frc.team1793.robot.state.drive;

import java.util.concurrent.ExecutionException;

import org.usfirst.frc.team1793.robot.Constants.Axis;
import org.usfirst.frc.team1793.robot.Robot;
import org.usfirst.frc.team1793.robot.state.State;

public class Align extends State {

	public Axis axis;
	public Align(Axis axis) {
		this.axis = axis;
	}
	@Override
	public void run() {
		double angle = axis.degree;
		try {
			if(Robot.drive.turn(angle).get())
				next();
		} catch (InterruptedException | ExecutionException | InvalidStateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public State next() throws InvalidStateException {
		return next;
	}

}
