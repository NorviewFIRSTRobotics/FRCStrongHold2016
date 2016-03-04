package org.usfirst.frc.team1793.robot;

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

}
