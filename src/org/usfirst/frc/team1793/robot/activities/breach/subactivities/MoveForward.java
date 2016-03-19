package org.usfirst.frc.team1793.robot.activities.breach.subactivities;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;

import edu.wpi.first.wpilibj.Timer;

public class MoveForward extends SubActivity {
	private double seconds;
	private Timer timer;
	public MoveForward(IRobotActivity activity, IRobotControllers controllers,double seconds) {
		super(activity,controllers);
		this.seconds = seconds;
	}

	@Override
	public void initialize() {
		timer = new Timer();
		timer.start();
	}

	@Override
	public void update() {
		System.out.println(timer.get());
		if(!timer.hasPeriodPassed(seconds)) {
			controllers.getDrive().drive(-Constants.DRIVE_SPEED);
		} else {
			controllers.getDrive().drive(0);
			isComplete = true;
		}
	}

}
