package org.usfirst.frc.team1793.robot.state;

public abstract class State implements Runnable {
	protected boolean finished;
	protected State next;
	public abstract State next();
	
	public State setNext(State next) {
		this.next = next;
		return this;
	}
	
	
}
