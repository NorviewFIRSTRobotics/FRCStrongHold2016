package org.usfirst.frc.team1793.robot.activities.breach;

import java.util.ArrayList;
import java.util.Arrays;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.Constants.Progress;
import org.usfirst.frc.team1793.robot.IRobotControllers;
import org.usfirst.frc.team1793.robot.Position;
import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.activities.IRobotActivity;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.MoveForward;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.SubActivity;

public class BreachSimpleDefense extends Breach {

	private ArrayList<SubActivity> order;
	private SubActivity currentActivity;
	private ApproachDefense _approach;
	private EnterDefense _enter;
	private ExitDefense _exit;
	private	ClearDefense _clear;
	private MoveForward _move;
	public BreachSimpleDefense(IRobotActivity activity, IRobotControllers controllers) {
		super(activity,controllers);
		_approach = new ApproachDefense(activity,controllers);
		_enter = new EnterDefense(activity,controllers);
		_exit = new ExitDefense(activity,controllers);
		_clear = new ClearDefense(activity,controllers);
		_move = new MoveForward(activity,controllers,2);
		
		//Not sure about this idea, let me know what you think
		order = new ArrayList<SubActivity>(Arrays.asList(_approach,_enter,_exit,_clear,_move));
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
		setActivity(order.remove(0));
	}
	
	@Override
	public void update() {
		//run the static method that checks all of the sensors
		//TODO rework, more info at method
		Position.sense();
		
		if(!currentActivity.isComplete()) {
			currentActivity.update();
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

	public class ApproachDefense extends SubActivity {
		
		public ApproachDefense(IRobotActivity activity, IRobotControllers controllers) {
			super(activity,controllers);
		}

		@Override
		public void initialize() {
			isComplete = false;
		}

		@Override
		public void update() {			
			if(Position.breaching == Progress.NONE) {
				this.controllers.getDrive().drive(Constants.DRIVE_SPEED);
			} else {				
				this.controllers.getDrive().drive(0);
				isComplete = true;
			}
		}
		
	}

	public class EnterDefense extends SubActivity {
		
		public EnterDefense(IRobotActivity activity, IRobotControllers controllers) {
			super(activity,controllers);
		}

		@Override
		public void initialize() {
			isComplete = false;
		}

		@Override
		public void update() {			
			if(Position.breaching == Progress.JUSTFRONT) {
				this.controllers.getDrive().drive(Constants.DRIVE_SPEED);
			} else {
				this.controllers.getDrive().drive(0);
				isComplete = true;
			}
		}
		
	}
	public class ExitDefense extends SubActivity {
		
		public ExitDefense(IRobotActivity activity, IRobotControllers controllers) {
			super(activity,controllers);
		}

		@Override
		public void initialize() {
			isComplete = false;
		}

		@Override
		public void update() {			
			if(Position.breaching == Progress.ALL) {
				this.controllers.getDrive().drive(Constants.DRIVE_SPEED);
			} else {
				this.controllers.getDrive().drive(0);
				isComplete = true;
			}
		}
		
	}
	public class ClearDefense extends SubActivity {
		
		public ClearDefense(IRobotActivity activity, IRobotControllers controllers) {
			super(activity,controllers);
		}

		@Override
		public void initialize() {
			isComplete = false;
		}

		@Override
		public void update() {			
			if(Position.breaching == Progress.JUSTBACK) {
				this.controllers.getDrive().drive(Constants.DRIVE_SPEED);
			} else {
				this.controllers.getDrive().drive(0);
				isComplete = true;
			}
		}
		
	}
}
