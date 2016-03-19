package org.usfirst.frc.team1793.robot.components;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SpeedControllerContainer implements SpeedController {
	private SpeedController c;
	private String name;
	public SpeedControllerContainer(SpeedController c, String name) {
		this.c = c;
		this.name = name;
	}
	@Override
	public void pidWrite(double output) {
		c.pidWrite(output);
	}
	@Override
	public double get() {
		return c.get();
	}
	@Override
	public void set(double speed, byte syncGroup) {
		c.set(speed, syncGroup);
	}
	@Override
	public void set(double speed) {
		SmartDashboard.putNumber(name, speed);
		c.set(speed);
	}
	@Override
	public void setInverted(boolean isInverted) {
		c.setInverted(isInverted);
	}
	@Override
	public boolean getInverted() {
		return c.getInverted();
	}
	@Override
	public void disable() {
		c.disable();
	}
	@Override
	public void stopMotor() {
		c.stopMotor();
	}
	
}
