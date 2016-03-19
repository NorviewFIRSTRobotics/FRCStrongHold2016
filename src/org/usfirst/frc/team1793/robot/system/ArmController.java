package org.usfirst.frc.team1793.robot.system;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.Utils;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmController extends Controller {

	private AnalogInput rotaryEncoder = new AnalogInput(Constants.RE_PID);
	private SpeedController motor;
	private volatile boolean finishedLatestOperation = false;
	private int zeroPoint;

	public ArmController(SpeedController motor, IRobotControllers robot) {
		super(2, robot);
		this.motor = motor;
		zeroPoint = (int) getAngle();
	}

	public void lift(double speed) {

		SmartDashboard.putNumber("Arm Motor", speed);
		executor.execute(() -> {
			// s
			// if(speed == 0) {
			// motor.set(speed);
			// return;
			// }
			//
			// boolean stowed = Utils.withInAbsThreshold(getAngle(),
			// zeroPoint,Constants.ARM_THRESHOLD);
			// boolean extended = Utils.withInAbsThreshold(-getAngle(),
			// -Constants.ARM_EXTENDED_ANGLE, Constants.ARM_THRESHOLD);
			// boolean canRun = (stowed && dir == 1) || (extended && dir == -1);
			// motor.set(canRun ? speed:0);
			
			//To extend arm speed goes to negative
			//To stow arm speed goes positive
			
	
			if (speed > 0 && getAngle() > Constants.ARM_EXTENDED_ANGLE) {
				motor.set(speed);
			} else if (speed < 0 && getAngle() < Constants.ARM_STOWED_ANGLE) {
				motor.set(speed);
			} else {
				motor.set(0);
			}
			//
			// if (speed < 0) {
			// if (getAngle() < 1)
			// motor.set(speed);
			// } else if (speed > 0) {
			// if (getAngle() > Constants.ARM_EXTENDED_ANGLE)
			// motor.set(speed);
			// } else {
			// motor.set(0);
			// }
		});
	}

	public void setArmPosition(double angle) {
		finishedLatestOperation = false;
		executor.execute(() -> {
			boolean finished = false;
			// TODO check which way is which and document it!!!! try to make -1
			// mean towards the store position!
		
			int direction = getAngle() > angle ? 1 : -1;
			while (!finished) {
				double speed = Constants.ARM_SPEED*direction;

				if (direction == 1  && getAngle() > angle) {
					motor.set(speed);
				} else if (direction == -1 && getAngle() < angle) {
					motor.set(speed);
				} else {
					motor.set(0);
					finished = true;
				}
			}
			finishedLatestOperation = true;
		});
	}

	public void stow() {
		setArmPosition(Constants.ARM_STOWED_ANGLE);
	}

	public void extend() {
		setArmPosition(Constants.ARM_EXTENDED_ANGLE);
	}

	public double getAngle() {
		return rotaryEncoder.getVoltage();
	}

	public boolean isLatestOperationFinished() {
		return finishedLatestOperation;
	}
}
