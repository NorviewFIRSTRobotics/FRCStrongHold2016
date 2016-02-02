package org.usfirst.frc.team1793.robot.state;

public abstract class State implements Runnable {
	protected State next;
	public abstract State next() throws InvalidStateException;
	
	public void setNext(State next) {
		this.next = next;
	}

	public class InvalidStateException extends Exception {
		private static final long serialVersionUID = 1L;
		@Override
		public String getMessage() {
			return "Invalid State!";
		}
	}
}
