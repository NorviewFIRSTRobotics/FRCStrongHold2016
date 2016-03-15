package org.usfirst.frc.team1793.robot.system;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.Robot;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;

public class ArmController extends Controller {
	public static final double OFFSET = -.2;

	private AnalogInput rotaryEncoder = new AnalogInput(Constants.RE_PID);
	private SpeedController motor;

	public ArmController(SpeedController motor, IRobotControllers robot) {
		super(2,robot);
		this.motor = motor;
	}

	public void lift(double speed) {
		executor.execute(() -> {
			if (speed < 0) {
				if (getAngle() < 1)
					motor.set(speed);
			} else if (speed > 0) {
				if (getAngle() > .05)
					motor.set(speed);
			} else {
				motor.set(0);
			}
		});
	}

	public void setArmPosition(double angle) {
		executor.execute(() -> {
			boolean finished = Math.abs(angle - getAngle()) <= Constants.ARM_THRESHOLD;
			// TODO check which way is which and document it!!!! try to make -1
			// mean towards the store position!
			int direction = getAngle() > angle ? -1 : 1;

			while (!finished) {
				motor.set(Constants.ARM_SPEED * direction);
			}
		});
	}

	public double getAngle() {
		return rotaryEncoder.getVoltage() - OFFSET;
	}

}
