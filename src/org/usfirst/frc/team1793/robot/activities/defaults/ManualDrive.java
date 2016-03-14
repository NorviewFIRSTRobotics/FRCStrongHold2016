package org.usfirst.frc.team1793.robot.activities.defaults;

import java.util.HashMap;

import org.usfirst.frc.team1793.robot.ButtonHandler;
import org.usfirst.frc.team1793.robot.ButtonHandler.Press;
import org.usfirst.frc.team1793.robot.ButtonHandler.PressEvent;
import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.IRobotControllers;
import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.activities.DepositBoulder;
import org.usfirst.frc.team1793.robot.activities.IRobotActivity;

public class ManualDrive extends Activity {
	
	private static HashMap<Press, Activity> activities = new HashMap<Press, Activity>();
	private DepositBoulder depositBoulder = new DepositBoulder(activity,controllers);
	public ManualDrive(IRobotActivity activity, IRobotControllers controllers) {
		super(activity,controllers);
		activities.put(new Press(Constants.ARM_STICK_PID, Constants.ARM_THROW_BUTTON), depositBoulder);
	}

	@Override
	public void initialize() {

	}

	@Override
	public void update() {
		PressEvent event = ButtonHandler.listen();
		if (!event.isEmpty()) {
			for (Press press : event) {
				System.out.println(press);
				activity.setActivity(activities.get(press));
			}
		} else {
			
			controllers.getDrive().arcadeDrive(controllers.getRight().getY(), controllers.getRight().getZ());
		}

	}

	@Override
	public boolean isComplete() {
		return false;
	}

}
