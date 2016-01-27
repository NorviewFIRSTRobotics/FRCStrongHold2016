package org.usfirst.frc.team1793.robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class SpeedControllerPair implements SpeedController {
	public SpeedController left,right;
	public SpeedControllerPair(Victor left, Victor right) {
		this.right = right;
		this.left = left;
		
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
