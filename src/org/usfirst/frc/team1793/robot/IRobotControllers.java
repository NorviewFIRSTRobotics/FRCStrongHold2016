package org.usfirst.frc.team1793.robot;

import org.usfirst.frc.team1793.robot.system.ArmController;
import org.usfirst.frc.team1793.robot.system.DriveController;
import org.usfirst.frc.team1793.robot.system.ShooterController;

import edu.wpi.first.wpilibj.Joystick;

public interface IRobotControllers {
	public DriveController getDrive();
	public ShooterController getShooter();
	public ArmController getArm();
	public Joystick getLeft();
	public Joystick getRight();
	
}
