package org.usfirst.frc.team1793.robot.activities.breach.subactivities;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class MoveForward extends SubActivity {
	private double seconds;
	private Timer timer;
	public MoveForward(Robot robot, double seconds) {
		super(robot);
		this.seconds = seconds;
	}

	@Override
	public void initialize() {
		timer = new Timer();
		timer.start();
	}

	@Override
	public void update() {
		if(timer.get() < seconds) {
			robot.drive.drive(Constants.DRIVE_SPEED);
		} else {
			robot.drive.drive(0);
			isComplete = true;
		}
	}

}
