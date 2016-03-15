package org.usfirst.frc.team1793.robot.activities.breach;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.ApproachDefense;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;

public class BreachLowbar extends BreachSimpleDefense {

	public BreachLowbar(IRobotActivity activity, IRobotControllers controllers) {
		super(activity,controllers);
		_approach = new ApproachDefense(activity, controllers) {
			
			//Save the previous left range
			private double prevLeftRange = front.getLeftRange();
			@Override
			public void update() {
				//Get the the difference between the current left range and the previous to test if 
				//one: we have stayed fairly straight
				//two: are in a lowbar, as there should always be a wall to the left
				double leftDiff = front.getLeftRange()-prevLeftRange;
				if(front.getRightRange() > (Constants.BREACH/2) && (leftDiff <= 1)) {
					this.controllers.getDrive().drive(Constants.DRIVE_SPEED);
				} else {				
					this.controllers.getDrive().drive(0);
					isComplete = true;
					front.setRunning(false);
					front = null;
				}
				prevLeftRange = front.getLeftRange();
			}
		};
	}

}
