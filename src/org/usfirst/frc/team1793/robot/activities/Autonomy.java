package org.usfirst.frc.team1793.robot.activities;

public class Autonomy extends Activity {
	
	public Autonomy(IRobotActivity robot) {
		super(robot);
	}

	@Override
	public void initialize() {}

	@Override
	public void update() {
		
		robot.setActivity(robot.getDetectDefenseActivity());
		//Do other auto stuff
		
	}

	@Override
	public void cancel() {}

}
