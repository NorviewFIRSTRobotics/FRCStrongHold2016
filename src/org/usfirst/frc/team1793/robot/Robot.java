package org.usfirst.frc.team1793.robot;

import org.usfirst.frc.team1793.robot.components.SpeedControllerPair;
import org.usfirst.frc.team1793.robot.state.senarios.Scenario;
import org.usfirst.frc.team1793.robot.system.ArmController;
import org.usfirst.frc.team1793.robot.system.DriveController;
import org.usfirst.frc.team1793.robot.system.GyroController;
import org.usfirst.frc.team1793.robot.system.ShooterController;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Robot extends IterativeRobot {
	public static int motorChannel = 0;
	public static DriveController drive;
	public static ArmController arm;
	public static ShooterController shooter;
	public static GyroController gyro;

	public static Joystick leftStick, armStick;	
	@Override
	public void robotInit() {
		leftStick = new Joystick(0);
		armStick = new Joystick(1);
		SpeedControllerPair leftPair = new SpeedControllerPair(new Victor(nextChannel()), new Victor(nextChannel()));
		SpeedControllerPair rightPair = new SpeedControllerPair(new Victor(nextChannel()), new Victor(nextChannel()));
		drive = new DriveController(leftPair,rightPair);
		arm = new ArmController(new Victor(nextChannel()));
		shooter = new ShooterController(new Victor(nextChannel()));
	}

	@Override
	public void autonomousInit() {
		
	}

	@Override
	public void autonomousPeriodic() {
		
	}

	@Override
	public void teleopInit() {
		
	}

	@Override
	public void teleopPeriodic() {
		
	}

	@Override
	public void disabledInit() {
	
	}

	@Override
	public void disabledPeriodic() {
	
	}
	
	
//	public class SenarioButton extends JoystickButton {
//		Scenario senario;
//		public SenarioButton(GenericHID joystick, int buttonNumber,Scenario senario) {
//			super(joystick, buttonNumber);
//			this.senario = senario; 
//		}
//		public void run() {
//			senario.run();
//		}
//	}
	public static int nextChannel() {
		int pid = motorChannel;
		motorChannel++;
		System.out.println(pid);
		return pid;
	}
}
