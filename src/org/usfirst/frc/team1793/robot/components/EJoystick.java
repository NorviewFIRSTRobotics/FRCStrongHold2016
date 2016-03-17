package org.usfirst.frc.team1793.robot.components;

import org.usfirst.frc.team1793.robot.inputs.ButtonHandler;

import edu.wpi.first.wpilibj.Joystick;

public class EJoystick extends Joystick{
	protected int port;
	public EJoystick(int port) {
		super(port);
		this.port = port;
		ButtonHandler.registerJoystick(this);
	}
	
	public int getPort() {
		return port;
	}
	@Override
	public String toString() {
		return Integer.toString(port);
	}

}
