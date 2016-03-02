package org.usfirst.frc.team1793.robot.state.drive;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.Robot;
import org.usfirst.frc.team1793.robot.state.State;

import edu.wpi.first.wpilibj.Timer;

public class PeriodDrive extends State {
	private static Timer timer = new Timer();
	private double seconds;
	
	public PeriodDrive(double seconds) {
		this.seconds = seconds;
	
	}
	@Override
	public void run() {
		if(timer.get() == 0) {
			timer.start();
		}
		else {
			while(timer.get() < seconds) {
				Robot.drive.drive(Constants.DRIVE_SPEED);	
			}
		}
		
	}

	@Override
	public State next() {
		return null;
	}

}
