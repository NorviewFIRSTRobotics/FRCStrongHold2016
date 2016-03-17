package org.usfirst.frc.team1793.robot.activities.breach;

import java.util.ArrayList;
import java.util.Arrays;

import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.ApproachDefense;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.ClearDefense;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.EnterDefense;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.ExitDefense;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.MoveForward;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.SubActivity;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BreachSimpleDefense extends Breach {

	private ArrayList<SubActivity> order;
	private SubActivity currentActivity;
	protected ApproachDefense _approach;
	protected EnterDefense _enter;
	protected ExitDefense _exit;
	protected	ClearDefense _clear;
	protected MoveForward _move;
	public BreachSimpleDefense(IRobotActivity activity, IRobotControllers controllers) {
		super(activity,controllers);
		
		_approach = new ApproachDefense(activity,controllers);
		_enter = new EnterDefense(activity,controllers);
		_exit = new ExitDefense(activity,controllers);
		_clear = new ClearDefense(activity,controllers);
		_move = new MoveForward(activity,controllers,2);
		
		//Not sure about this idea, let me know what you think
	
	}
/*
	1. Approach:Move until FWD sensors detect both shields
	2. Enter:Move until AFT sensors detect both shields
	3. Exiting:Move until FWD sensors detect no shields
	4. Clear:Move until AFT sensors detect no shields
	5. Move: Move for 2 seconds
	6. Stop: done!
*/
	@Override
	public void initialize() {
		order = new ArrayList<SubActivity>(Arrays.asList(_approach,_enter,_exit,_clear,_move));
		setActivity(order.remove(0));
		isComplete = false;
	}
	
	@Override
	public void update() {
		if(!currentActivity.isComplete()) {
			currentActivity.update();
			SmartDashboard.putString(this.getClass().getSimpleName()+ " Current Subactivity", currentActivity.getClass().getSimpleName());
		} else {
			if(order.isEmpty()) {
				isComplete = true;
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
		if(activity instanceof SubActivity) {
			this.currentActivity = (SubActivity) activity;
			this.currentActivity.initialize();
		}
	}

	
}
