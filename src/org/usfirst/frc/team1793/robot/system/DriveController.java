package org.usfirst.frc.team1793.robot.system;

import static org.usfirst.frc.team1793.robot.Constants.TURN_SPEED;
import static org.usfirst.frc.team1793.robot.Constants.TURN_THRESHOLD;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.usfirst.frc.team1793.robot.Robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DriveController extends Controller {
	Drive drive;

	public DriveController(SpeedController leftMotor, SpeedController rightMotor) {
		super(2);
		drive = new Drive(leftMotor, rightMotor);
	}

	public void arcadeDrive(double move, double rotation) {
		executor.execute(() -> drive.arcadeDrive(move, rotation));
	}

	public void tankDrive(double leftY, double rightY) {
		executor.execute(() -> drive.tankDrive(leftY, rightY));
	}
	public void drive(double speed) {
		executor.execute(() -> drive.drive(speed));
	}
	public Future<Boolean> turn(double angle) {
		return executor.submit(() -> drive.turn(angle));
	}

	public static class Drive extends RobotDrive {
		

		public Drive(SpeedController leftMotor, SpeedController rightMotor) {
			super(leftMotor, rightMotor);
//			rightMotor.setInverted(true);
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
		public void drive(double speed) {
			setLeftRightMotorOutputs(speed, speed);
		}
		public boolean turn(double angle) {
			try {
				return turn(Robot.gyro.getAngle().get(), angle);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return false;
			}
		}

		public boolean turn(double currentangle, double endangle) throws InterruptedException, ExecutionException {
			double leftDir = currentangle > 0 ? -1 : 1;
			double rightDir = currentangle > 0 ? 1 : -1;
			if (Math.abs(currentangle - endangle) <= TURN_THRESHOLD) {
				setLeftRightMotorOutputs(leftDir * TURN_SPEED, rightDir * TURN_SPEED);
				return turn(Robot.gyro.getAngle().get(), endangle);
			} else {
				return true;
			}
		}

	}

}
