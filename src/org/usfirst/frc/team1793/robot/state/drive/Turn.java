package org.usfirst.frc.team1793.robot.state.drive;

import org.usfirst.frc.team1793.robot.Robot;
import org.usfirst.frc.team1793.robot.state.State;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turn extends State {
	private double rotation;
	private double time;
	private Timer timer;
	public Turn(double rotation) {
		this.rotation=rotation;
		timer = new Timer();
	}
	public Turn(float time) {
		this.time = time;
	}
	@Override
	public void run() {
		if(timer.get() == 0) {
			timer.start();
		}
		
		if(time != 0) {
			if(timer.get() < time) { 
				SmartDashboard.putNumber("turning-time", time);
				Robot.drive.arcadeDrive(0, rotation);
			}
		} else if (rotation != 0) {
			SmartDashboard.putNumber("turning-rotation", rotation);
			Robot.drive.arcadeDrive(0, rotation);
		}
		
	}
	
	@Override
	public State next() throws InvalidStateException {
		return next;
	}

}
