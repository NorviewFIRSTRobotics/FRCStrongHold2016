package org.usfirst.frc.team1793.robot.inputs;

import java.util.ArrayList;

public class PressEvent extends ArrayList<Press> {
	private static final long serialVersionUID = 1L;

	public PressEvent() {
	}

	public PressEvent(Press press) {
		add(press);
	}

	public boolean pressed(int joystick, int button) {
		return pressed(new Press(joystick, button));
	}
	

	public boolean pressed(Press press) {
		for(Press p:this) {
			if(p.getButton() == press.getButton() && p.getJoystick() ==  press.getJoystick())
				return true;
		}
		return false;
	}
	

}
