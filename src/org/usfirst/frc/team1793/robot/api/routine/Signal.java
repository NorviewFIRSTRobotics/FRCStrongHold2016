package org.usfirst.frc.team1793.robot.api.routine;

public class Signal<T> {
	private T t;
	private boolean empty = true;
	
	public synchronized T take() {
		while(empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		empty = true;
		notifyAll();
		return t; 
	}
	public synchronized void put(T t) {
		while(!empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		empty = false;
		this.t= t;
		notify();
	}
}
 