package org.usfirst.frc.team1793.robot.system;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

public class Drive extends RobotDrive implements ISystem {

	public Drive(SpeedController leftMotor, SpeedController rightMotor) {
		super(leftMotor, rightMotor);
	}
	
	@Override
	public void arcadeDrive(double moveValue, double rotateValue) {
		super.arcadeDrive(moveValue, rotateValue);
	}
	
	@Override
	public void tankDrive(double leftValue, double rightValue) {
		super.tankDrive(leftValue, rightValue);
	}
}
