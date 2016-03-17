package org.usfirst.frc.team1793.robot.inputs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.components.EJoystick;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ButtonHandler {
	public static PressEvent event;
	public static HashMap<Press,Activity> activityButtons = new HashMap<Press,Activity>();
	
	public static HashSet<EJoystick> joysticks = new HashSet<EJoystick>();

	public static void registerJoystick(EJoystick joystick) {
		joysticks.add(joystick);
	
	}
	public static void registerActivityButton(int joystick, int button, Activity activity) {
		Press p = new Press(joystick,button);
		activityButtons.put(p, activity);
	}
	public static Activity getActivityFromButton(Press press) {
		return activityButtons.get(press);
	}
	public static Activity getActivityFromButton(int joystick, int button) {
		return getActivityFromButton(new Press(joystick,button));
	}
	public static void listen() {
		PressEvent event = new PressEvent();
		for (Iterator<EJoystick> iterator = joysticks.iterator(); iterator.hasNext();) {
			EJoystick joystick = (EJoystick) iterator.next();
			for(int i = 1; i <= joystick.getButtonCount();i++)
			if(joystick.getRawButton(i)) {
				event.add(new Press(joystick.getPort(),i));
			}
		}
		SmartDashboard.putString("PressEvent", event.toString());
		ButtonHandler.event = event; 
	}
	
	public static boolean pressed(int joystick, int button) {
		return event.isEmpty() ? false : event.pressed(joystick,button);
	}

	
	
	
}
