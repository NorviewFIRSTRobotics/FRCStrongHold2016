package org.usfirst.frc.team1793.robot.activities;

import java.util.HashMap;

import org.usfirst.frc.team1793.robot.ButtonHandler;
import org.usfirst.frc.team1793.robot.ButtonHandler.Press;
import org.usfirst.frc.team1793.robot.ButtonHandler.PressEvent;
import org.usfirst.frc.team1793.robot.Robot;

public class ManualDrive extends Activity {
	private static HashMap<Press,Activity> activities = new HashMap<Press,Activity>();
	
	public ManualDrive(IRobotActivity robot) {
		super(robot);
	}

	@Override
	public void initialize() {
		
	}

	@Override
	public void update() {
		Robot robot = Robot.getInstance();
		PressEvent event = ButtonHandler.listen();
		if(!event.isEmpty()) {
			for(Press press:event) {
				Robot.getInstance().setActivity(activities.get(press));
			}
		} else {			
			robot.drive.arcadeDrive(robot.driveStick.getY(), robot.driveStick.getZ());
		}
		
	}

	@Override
	public void cancel() {}

}
