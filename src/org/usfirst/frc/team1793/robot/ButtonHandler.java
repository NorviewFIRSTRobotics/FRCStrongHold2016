package org.usfirst.frc.team1793.robot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.usfirst.frc.team1793.robot.components.EJoystick;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
@SuppressWarnings("unused")
public class ButtonHandler {
	public static HashSet<EJoystick> joysticks = new HashSet<EJoystick>();

	public static void registerJoystick(EJoystick joystick) {
		joysticks.add(joystick);
	}

	public static PressEvent listen() {
		PressEvent event = new PressEvent();
		for (Iterator<EJoystick> iterator = joysticks.iterator(); iterator.hasNext();) {
			EJoystick joystick = (EJoystick) iterator.next();
			int c = joystick.getButtonCount();
			
			for(int i = 0; i < c;i++) {
				JoystickButton button = new JoystickButton(joystick, c);
				if(button.get()) {
					Press press = new Press(joystick.getPort(),i);
					if(event != null)  {
						event.add(press);
					}
				}
			}
		}		
		return event;
	}
	
	public static class Press {
		
		private int joystick, button;
		public Press(int joystick, int button) {
			this.joystick = joystick;
			this.button = button;
		}
	
	}

	public static class PressEvent extends ArrayList<Press>{
		private static final long serialVersionUID = 1L;
		public PressEvent() {}
		public PressEvent(Press press) {
			add(press);
		}
				

		public boolean pressed(int joystick, int button) {
			return pressed(new Press(joystick,button));
		}
		public boolean pressed(Press press) {
			return contains(press);
		}
	
	}
}
