package org.usfirst.frc.team1793.robot.activities.defaults;

import org.usfirst.frc.team1793.robot.IRobotControllers;
import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.activities.IRobotActivity;

public class Autonomy extends Activity {
	
	public Autonomy(IRobotActivity activity, IRobotControllers controllers) {
		super(activity,controllers);
	}

	@Override
	public void initialize() {}

	@Override
	public void update() {
		
		activity.setActivity(activity.getDetectDefenseActivity());
		//Do other auto stuff
	}

	@Override
	public void cancel() {}

	
	@Override
	public boolean isComplete() {
		return false;
	}


}
