package org.usfirst.frc.team1793.robot.activities.defaults;

import org.usfirst.frc.team1793.robot.ButtonHandler;
import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.Press;
import org.usfirst.frc.team1793.robot.PressEvent;
import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.activities.DepositBoulder;
import org.usfirst.frc.team1793.robot.activities.breach.BreachSimpleDefense;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualDrive extends Activity {

	private DepositBoulder depositBoulder = new DepositBoulder(activity,controllers);
	private BreachSimpleDefense simpleDefense = new BreachSimpleDefense(activity, controllers);
	public ManualDrive(IRobotActivity activity, IRobotControllers controllers) {
		super(activity,controllers);
		ButtonHandler.registerActivityButton(Constants.ARM_STICK_PID, Constants.ARM_THROW_BUTTON, depositBoulder);
		ButtonHandler.registerActivityButton(Constants.DRIVE_STICK_PID, Constants.DRIVE_SIMPLE_DEFENSE_BUTTON, simpleDefense);
	}

	@Override
	public void initialize() {
		
	}

	@Override
	public void update() {
		PressEvent event = ButtonHandler.listen();
		
		if (!event.isEmpty()) {
			for (Press press : event) {
				SmartDashboard.putString("Pressing",press.toString());
				Activity a = ButtonHandler.getActivityFromButton(press);				
				if(a != null) {
					activity.setActivity(a);
				}
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
