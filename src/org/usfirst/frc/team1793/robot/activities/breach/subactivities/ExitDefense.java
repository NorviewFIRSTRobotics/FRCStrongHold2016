package org.usfirst.frc.team1793.robot.activities.breach.subactivities;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.Constants.SensorPosition;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;
import org.usfirst.frc.team1793.robot.components.UltrasonicPair;

public class ExitDefense extends SubActivity {
		UltrasonicPair front;
		public ExitDefense(IRobotActivity activity, IRobotControllers controllers) {
			super(activity,controllers);
		}

		@Override
		public void initialize() {
			isComplete = false;
			front = new UltrasonicPair(SensorPosition.FRONTLEFT, SensorPosition.FRONTRIGHT);
			front.setRunning(true);
		}

		@Override
		public void update() {		
		
			if(front.getSum() <= Constants.BREACH) {
				this.controllers.getDrive().drive(Constants.DRIVE_SPEED);
			} else {
				this.controllers.getDrive().drive(0);
				isComplete = true;
			}
		}
		
	}