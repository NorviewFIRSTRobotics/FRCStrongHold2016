package org.usfirst.frc.team1793.robot.debug;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DebugMotor implements SpeedController {
	public String name;
	private  boolean inverted;
	public DebugMotor(String name) {
		this.name = name;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void pidWrite(double output) {
	
		
	}

	@Override
	public double get() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void set(double speed, byte syncGroup) {
		this.set(speed);
	}

	@Override
	public void set(double speed) {
		SmartDashboard.putNumber("Motor"+ name + ":", (inverted ? -1 : 1) * speed);

	}

	@Override
	public void setInverted(boolean isInverted) {
		inverted = isInverted;
	}

	@Override
	public boolean getInverted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopMotor() {
		set(0);
		
	}

}
