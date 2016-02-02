package org.usfirst.frc.team1793.robot.system;

public class Controller extends Thread {
	ISystem slave; 
	Runnable target;
	public Controller(ISystem slave) {
		this.slave = slave;
	}
	public void setTarget(Runnable target) {
		this.target = target;
	}
	
	@Override
	public void run() {
		if(target != null) {
			target.run();
		}
	}
}
