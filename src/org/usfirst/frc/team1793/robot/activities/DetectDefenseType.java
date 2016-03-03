package org.usfirst.frc.team1793.robot.activities;

import org.usfirst.frc.team1793.robot.activities.breach.Breach.BreachType;

public class DetectDefenseType extends Activity {

	
	public DetectDefenseType(IRobotActivity robot) {
		super(robot);
	}

	public static BreachType detectBreachType() {
	
		//TODO implement breach detection
		
		
		return BreachType.None;
	}

	@Override
	public void initialize() {}

	@Override
	public void update() {}

	@Override
	public void cancel() {}
}
