package org.usfirst.frc.team1793.robot.components;

import edu.wpi.first.wpilibj.SpeedController;

public class SpeedControllerPair implements SpeedController {
	public SpeedController left,right;
	public SpeedControllerPair(SpeedController left, SpeedController right) {
		this.right = right;
		this.left = left;
//		right.setInverted(true);
	}
	@Override
	public void pidWrite(double output) {
		left.pidWrite(output);
	}
	@Override
	public double get() {
		return left.get();
	}
	@Override
	public void set(double speed, byte syncGroup) {
		left.set(speed,syncGroup);
		right.set(speed,syncGroup);
	}
	@Override
	public void set(double speed) {
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
	
	
	
	
}
