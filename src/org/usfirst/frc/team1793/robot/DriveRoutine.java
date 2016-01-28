package org.usfirst.frc.team1793.robot;

public abstract class DriveRoutine  {
	public String name;
	public double time;
	public DriveRoutine(String name,double time) {
		this.name = name;
		this.time = time;
	}
	public abstract void action();
	
	
	public class TurnRoutine extends DriveRoutine {
		public int angle = 0;
		public Drive drive; 
		public TurnRoutine(String name, double time, int angle, Drive drive) {
			super(name, time);
			this.angle = angle;
			this.drive = drive;
		}
		
		@Override
		public void action() {
			if (angle > 0) {
				drive.setLeftRightMotorOutputs(1, -1);
			} else {
				drive.setLeftRightMotorOutputs(-1, 1);
			}
		}
		
	}
}
