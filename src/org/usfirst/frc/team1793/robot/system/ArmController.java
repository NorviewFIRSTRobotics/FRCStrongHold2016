package org.usfirst.frc.team1793.robot.system;

import org.usfirst.frc.team1793.robot.Constants;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;

public class ArmController extends Controller {
	public static final double OFFSET = -.2;

	private AnalogInput rotaryEncoder = new AnalogInput(Constants.RE_PID);
	private SpeedController motor;

	public ArmController(SpeedController motor) {
		super(2);
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

	public double getAngle() {
		return rotaryEncoder.getVoltage() - OFFSET;
	}

}
