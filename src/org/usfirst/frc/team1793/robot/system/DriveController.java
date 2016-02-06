package org.usfirst.frc.team1793.robot.system;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveController extends Controller {
	public DriveController(SpeedController leftMotor, SpeedController rightMotor) {
		super(new Drive(leftMotor, rightMotor),2);
	}
	public void arcadeDrive(double move, double rotation) {
		executor.execute(() -> ((Drive)slave).arcadeDrive(move,rotation));
		
	}
	public void tankDrive(double leftY, double rightY) {
		executor.execute(() -> ((Drive)slave).tankDrive(leftY, rightY));
	}
	public static class Drive extends RobotDrive implements ISystem {

		public Drive(SpeedController leftMotor, SpeedController rightMotor) {
			super(leftMotor, rightMotor);
			rightMotor.setInverted(true);
		}
		
		@Override
		public void arcadeDrive(double moveValue, double rotateValue) {
			super.arcadeDrive(moveValue, rotateValue);
			SmartDashboard.putString("2nd", Thread.currentThread().toString());
		}
		
		@Override
		public void tankDrive(double leftValue, double rightValue) {
			super.tankDrive(leftValue, rightValue);
			SmartDashboard.putString("2nd", Thread.currentThread().toString());
		}
	}

}
