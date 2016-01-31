package org.usfirst.frc.team1793.robot.routine;

import org.usfirst.frc.team1793.robot.api.routine.ConsumerInt;
import org.usfirst.frc.team1793.robot.api.routine.ProducerInt;
import org.usfirst.frc.team1793.robot.api.routine.Routine;
import org.usfirst.frc.team1793.robot.api.routine.SignalInt;
import org.usfirst.frc.team1793.robot.component.Drive;

public class DriveRoutine extends Routine<Integer,Integer>{
	public Drive drive;
	public enum Action {
		FORWARD,
		BACKWARD;
		public static final Action[] values = new Action[]{FORWARD,BACKWARD};
	}
	public DriveRoutine(ProducerInt p,ConsumerInt c) {
		producer = p;
		consumer = c;
	}
	public static boolean isValidMessage(int message) {
		for(int i = 0; i < Action.values.length;i++) {
			boolean equal = Action.values[i].ordinal() == message;
			if (equal) {
				return equal;
			} else {
				continue;
			}
		}
		return false;
	}
	public static DriveRoutine createDriveRoutine(Drive drive,SignalInt producerSignal, SignalInt consumerSignal) {
		DriveRoutine routine = null;
		int complete = 0;
		ProducerInt p = new ProducerInt(producerSignal, -1, new Integer[]{0,0,0,0,0});
		ConsumerInt c = new ConsumerInt(consumerSignal, complete) {
			@Override
			public boolean action(Integer message) {
				if(DriveRoutine.isValidMessage(message)) {
					Action action = Action.values[message];
					if(action == Action.FORWARD) {
						//TODO add drive functions
					} else if (action == Action.BACKWARD) {
												
					}
							
					return true;
				} 
				return false;
			}
		};
		routine = new DriveRoutine(p, c);
		return routine;
	}
	 
	
}
