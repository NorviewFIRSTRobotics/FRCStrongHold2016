package org.usfirst.frc.team1793.robot.state.drive;

import org.usfirst.frc.team1793.robot.Robot;
import org.usfirst.frc.team1793.robot.state.State;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turn extends State {
	private double rotation;
	public Turn(double rotation) {
		this.rotation=rotation;
	}
	@Override
	public void run() {
		SmartDashboard.putNumber("turning", rotation);
		Robot.drive.arcadeDrive(0, rotation);
	}
	
	@Override
	public State next() throws InvalidStateException {
		return next;
	}

}
