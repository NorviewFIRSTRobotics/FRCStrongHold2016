package org.usfirst.frc.team1793.robot.components;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SpeedControllerPair implements SpeedController {
	private SpeedController left, right;
	private String name;
	public SpeedControllerPair(SpeedController left, SpeedController right, String name) {
		this.right = right;
		this.left = left;
		this.name = name;
	}

	@Override
	public void pidWrite(double output) {
		left.pidWrite(output);
		right.pidWrite(output);
	}

	@Override
	public double get() {
		return left.get();
	}

	@Override
	public void set(double speed, byte syncGroup) {
		left.set(speed, syncGroup);
		right.set(speed, syncGroup);
	}

	@Override
	public void set(double speed) {
		SmartDashboard.putNumber(getClass().getSimpleName() + name, speed);
		left.set(speed);
		right.set(speed);
	}

	@Override
	public void setInverted(boolean isInverted) {
		left.setInverted(isInverted);
		right.setInverted(isInverted);
	}

	@Override
	public boolean getInverted() {
		return left.getInverted();
	}

	@Override
	public void disable() {
		left.disable();
		right.disable();
	}

	@Override
	public void stopMotor() {}

}
