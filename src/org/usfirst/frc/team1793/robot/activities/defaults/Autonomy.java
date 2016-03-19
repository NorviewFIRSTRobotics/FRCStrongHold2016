package org.usfirst.frc.team1793.robot.activities.defaults;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;

import javax.lang.model.element.Element;

import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.activities.DepositBoulder;
import org.usfirst.frc.team1793.robot.activities.Turn;
import org.usfirst.frc.team1793.robot.activities.breach.BreachSimpleDefense;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.SubActivity;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;
@SuppressWarnings("unused")
public class Autonomy extends Activity implements IRobotActivity {
	public ArrayList<Activity> order;
	public Activity currentActivity;


	private DepositBoulder depositBoulder = new DepositBoulder(activity,controllers);
	private BreachSimpleDefense simpleDefense = new BreachSimpleDefense(activity, controllers);
	private Turn turn = new Turn(activity, controllers, 90);
	
	public Autonomy(IRobotActivity activity, IRobotControllers controllers) {
		super(activity,controllers);
	}

	@Override
	public void initialize() {
		order = new ArrayList<Activity>();
		order.add(simpleDefense);
//		order.add(simpleDefense);
		setActivity(order.remove(0));
		isComplete = false;
	}

	@Override
	public void update() {
		
		if(!currentActivity.isComplete()) {
			currentActivity.update();
		} else {
			if(order.isEmpty()) {
				isComplete = true;
			} else {
				setActivity(order.remove(0));
			}
		}
//		activity.setActivity(activity.getDetectDefenseActivity());
		
	}


	@Override
	public void setActivity(Activity activity) {
		
			this.currentActivity = activity;
			this.currentActivity.initialize();
	
	}

	@Override
	public Activity getDefaultActivity() {
		return null;
	}

	@Override
	public Activity getDetectDefenseActivity() {
		return null;
	}


}
