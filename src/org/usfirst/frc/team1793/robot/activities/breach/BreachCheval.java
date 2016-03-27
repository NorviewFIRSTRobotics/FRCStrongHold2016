package org.usfirst.frc.team1793.robot.activities.breach;

import java.util.ArrayList;
import java.util.Arrays;

import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.ApproachArmDefense;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.SubActivity;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;

public class BreachCheval extends Breach {
	private ArrayList<SubActivity> order = new ArrayList<SubActivity>();
 	private SubActivity currentActivity; 
 	
 	private ApproachArmDefense _arm = new ApproachArmDefense(activity, controllers);
	public BreachCheval(IRobotActivity activity, IRobotControllers controllers) {
		super(activity,controllers);
	}

	@Override
	public void initialize() {
		order = new ArrayList<SubActivity>(Arrays.asList(_arm));
		
	}

	@Override
	public void update() {
		if (!currentActivity.isComplete()) {
			currentActivity.update();
		} else {
			if (order.isEmpty()) {
				isComplete = true;
				controllers.getDrive().drive(0);
			} else {
				setActivity(order.remove(0));
			}
		}
		
	}
	
	@Override
	public Activity getDefaultActivity() {
		return null;
	}

	@Override
	public Activity getDetectDefenseActivity() {
		return null;
	}

	@Override
	public void setActivity(Activity activity) {
		if(currentActivity instanceof SubActivity) {
			currentActivity = (SubActivity) activity;
			currentActivity.initialize();
		}
		
	}


}
