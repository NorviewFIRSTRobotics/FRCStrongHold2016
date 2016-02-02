package org.usfirst.frc.team1793.robot.state;

public abstract class State implements Runnable {
	public abstract State next() throws InvalidStateException;
	
	public class InvalidStateException extends Exception {
		private static final long serialVersionUID = 1L;
		@Override
		public String getMessage() {
			return "Invalid State!";
		}
	}
}
