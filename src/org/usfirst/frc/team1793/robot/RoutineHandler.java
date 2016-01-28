package org.usfirst.frc.team1793.robot;

import java.util.HashMap;

public class RoutineHandler extends HashMap<String, DriveRoutine> {

	private static final long serialVersionUID = -4437360124911712692L;
	
	public RoutineHandler() {
		
	}
	public void executeRoutineAction(String key) {
		get(key).action();
	}
}
