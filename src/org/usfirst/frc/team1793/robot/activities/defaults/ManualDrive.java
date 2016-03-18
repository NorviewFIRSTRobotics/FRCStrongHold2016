package org.usfirst.frc.team1793.robot.activities.defaults;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.activities.DepositBoulder;
import org.usfirst.frc.team1793.robot.activities.breach.BreachSimpleDefense;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.ExtendArm;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.StowArm;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;
import org.usfirst.frc.team1793.robot.inputs.ButtonHandler;
import org.usfirst.frc.team1793.robot.inputs.Press;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualDrive extends Activity {
	private boolean manualArm = false;
	private DepositBoulder depositBoulder = new DepositBoulder(activity,controllers);
	private BreachSimpleDefense simpleDefense = new BreachSimpleDefense(activity, controllers);
	private ExtendArm extendArm = new ExtendArm(activity, controllers);
	private StowArm stowArm = new StowArm(activity, controllers);
	public ManualDrive(IRobotActivity activity, IRobotControllers controllers) {
		super(activity,controllers);
		ButtonHandler.registerActivityButton(Constants.ARM_STICK_PID, Constants.ARM_THROW_BUTTON, depositBoulder);
		ButtonHandler.registerActivityButton(Constants.DRIVE_STICK_PID, Constants.DRIVE_SIMPLE_DEFENSE_BUTTON, simpleDefense);
		ButtonHandler.registerActivityButton(Constants.ARM_STICK_PID, Constants.ARM_EXTEND_BUTTON, extendArm);
		ButtonHandler.registerActivityButton(Constants.ARM_STICK_PID, Constants.ARM_STOW_BUTTON, stowArm);
	}

	@Override
	public void update() {
		SmartDashboard.putBoolean("Arm Manual Mode", manualArm);
		if(!ButtonHandler.event.isEmpty()) {
			detectButtonEvents();
		} else {
			if(manualArm) {
				double stickY = controllers.getLeft().getY();
				controllers.getArm().lift(stickY);
			}
			controllers.getDrive().arcadeDrive(controllers.getRight().getY(), controllers.getRight().getZ());
		}
		

	}
	
	public void detectButtonEvents() {
		if(ButtonHandler.isModPressed(Constants.ARM_MANUAL_CONTROL_BUTTON)) {
			manualArm = !manualArm;
		}
		for (Press press : ButtonHandler.event) { 
			SmartDashboard.putString("Pressing",press.toString());
			Activity a = ButtonHandler.getActivityFromButton(press);				
			if(a != null) {
				activity.setActivity(a);
			}
		}
	}
	
	@Override
	public boolean isComplete() {
		return false;
	} 

}
