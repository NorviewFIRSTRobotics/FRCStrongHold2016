package org.usfirst.frc.team1793.robot.state.drive;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.Constants.Relative;
import org.usfirst.frc.team1793.robot.Robot;
import org.usfirst.frc.team1793.robot.state.State;

import edu.wpi.first.wpilibj.Timer;

public class Turn90 extends State {
	private Timer timer;
	public Relative axis;

	public Turn90(Relative axis) {
		timer = new Timer();
		this.axis = axis;
		System.out.println("starting turning state " + axis);
	}

	@Override
	public void run() {
		if(timer.get() == -1) {
			timer.start();
		}
		System.out.println(this.getClass()+":"+timer.get());
		if (timer.get() >= axis.time) {
			finished = true;
			System.out.println("done turning");
		} else {
			Robot.drive.turnSpeed(axis.sign * Constants.TURN_SPEED);
			System.out.println("turning!");
		}
	}

	@Override
	public State next() {
		return null;
	}

}
