package org.usfirst.frc.team1793.robot.system;

import org.usfirst.frc.team1793.robot.Constants;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;

public class ArmController extends Controller {
	public Arm arm;
	public ArmController(SpeedController motor) {
		super(2);
		arm = new Arm(motor);
	}
	public void lift(double speed) {
		executor.execute( () -> arm.lift(speed));
	}
	private static class Arm {
		public static final double SCALE = 10;
		AnalogInput rotaryEncoder = new AnalogInput(Constants.RE_PID);
		
		SpeedController motor;
		public Arm(SpeedController motor) {
			this.motor = motor;
		}
		public void lift(double speed) {
			motor.set(speed);
		}
		public double getAngle() {
			return rotaryEncoder.getVoltage()*SCALE;
		}
	}
}
