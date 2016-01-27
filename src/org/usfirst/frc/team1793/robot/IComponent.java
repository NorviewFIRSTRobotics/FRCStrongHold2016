package org.usfirst.frc.team1793.robot;

public interface IComponent {
		public void autonomousInit();
	    public void autonomousPeriodic();
	    public void disabledInit();
	    public void disabledPeriodic();
	    public void teleopInit();
	    public void teleopPeriodic();
}
