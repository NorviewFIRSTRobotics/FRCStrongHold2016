package org.usfirst.frc.team1793.robot.system;

import org.usfirst.frc.team1793.robot.Constants;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;

public class ArmController extends Controller {
	private Arm arm;
	public ArmController(SpeedController motor) {
		super(2);
		arm = new Arm(motor);
	}
	public void lift(double speed) {
		executor.execute( () -> arm.lift(speed));
	}
	public double getAngle() {
		return arm.getAngle();
	}
	private static class Arm {
		
		public static final double OFFSET = -.2;
		AnalogInput rotaryEncoder = new AnalogInput(Constants.RE_PID);
		
		SpeedController motor;
		public Arm(SpeedController motor) {
			this.motor = motor;
		}
		public void lift(double speed) {
			if(speed < 0 ) {
				if(getAngle() < 1)
					motor.set(speed);
			} else if(speed > 0) {
				if(getAngle() > .05)
					motor.set(speed);
			} else {
				motor.set(0);
			}
			
		}
		public double getAngle() {
			return rotaryEncoder.getVoltage()-OFFSET;
		}
	}
}
