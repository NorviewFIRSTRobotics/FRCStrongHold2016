package org.usfirst.frc.team1793.robot.api;

import org.usfirst.frc.team1793.robot.components.UltrasonicPair;
import org.usfirst.frc.team1793.robot.system.ArmController;
import org.usfirst.frc.team1793.robot.system.DriveController;
import org.usfirst.frc.team1793.robot.system.ShooterController;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;

public interface IRobotControllers {
	public DriveController getDrive();
	public ShooterController getShooter();
	public ArmController getArm();
	public AnalogGyro getGyro();
	public Joystick getLeft();
	public Joystick getRight();
	public UltrasonicPair getFrontSides();
	public UltrasonicPair getBackSides();
	public UltrasonicPair getFrontAndBack();
}
