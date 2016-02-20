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
	public static volatile boolean turnFinished;

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

	public void turn(double angle) {

		executor.execute(() -> {
			turnFinished = drive.turn(angle);
		});

	}

	public static class Drive extends RobotDrive {
		public enum Turn {
			LEFT, RIGHT, DONE
		}

		public Drive(SpeedController leftMotor, SpeedController rightMotor) {
			super(leftMotor, rightMotor);
			leftMotor.setInverted(true);
		}

		@Override
		public void arcadeDrive(double moveValue, double rotateValue) {
			super.arcadeDrive(moveValue, rotateValue);
		}

		@Override
		public void tankDrive(double leftValue, double rightValue) {
			super.tankDrive(leftValue, rightValue);
		}

		public void drive(double speed) {
			setLeftRightMotorOutputs(speed, speed);
		}

		public boolean turn(double angle) {
			Turn turn = angleThreshold(angle);
			switch (turn) {
			case DONE:
				return true;
			case LEFT:
				setLeftRightMotorOutputs(-TURN_SPEED, TURN_SPEED);
				return false;
			case RIGHT:
				setLeftRightMotorOutputs(TURN_SPEED, -TURN_SPEED);
				return false;
			default:
				return false;
			}
		}

		public Turn angleThreshold(double angle) {
			double diff = angle - Robot.gyro.getAngle();
			boolean currentBigger = Robot.gyro.getAngle() > angle;
			if (Math.abs(diff) <= TURN_THRESHOLD) {
				return Turn.DONE;
			} else {
				if (currentBigger) {
					if (diff < 0) {
						return Turn.LEFT;

					} else {
						return Turn.RIGHT;
					}
				} else {
					if (diff < 0) {
						return Turn.RIGHT;

					} else {
						return Turn.LEFT;
					}
				}
			}

		}

		public boolean turn(double currentangle, double endangle) throws InterruptedException, ExecutionException {
			return false;
			// SmartDashboard.putNumber("current angle", currentangle);
			// SmartDashboard.putNumber("end angle", endangle);
			// double leftDir = currentangle > 0 ? -1 : 1;
			// double rightDir = currentangle > 0 ? 1 : -1;
			// if (Math.abs(currentangle - endangle) > TURN_THRESHOLD) {
			// setLeftRightMotorOutputs(leftDir * TURN_SPEED, rightDir *
			// TURN_SPEED);
			// return turn(Robot.gyro.getAngle(), endangle);
			// } else {
			// return true;
			// }
		}

	}

}
