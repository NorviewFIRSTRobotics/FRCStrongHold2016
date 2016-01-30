package org.usfirst.frc.team1793.robot.routines;

public abstract class Routine implements Runnable {
	public String name;
	public double time;
	public Routine(String name,double time) {
		this.name = name;
		this.time = time;
	}
	public abstract void run();
}
