package org.usfirst.frc.team1793.robot;

import org.usfirst.frc.team1793.robot.components.SpeedControllerPair;
import org.usfirst.frc.team1793.robot.state.GameState;
import org.usfirst.frc.team1793.robot.state.Teleop;
import org.usfirst.frc.team1793.robot.system.ArmController;
import org.usfirst.frc.team1793.robot.system.DriveController;
import org.usfirst.frc.team1793.robot.system.ShooterController;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	public static int motorChannel = 0;
	public static DriveController drive;
	public static ArmController arm;
	public static ShooterController shooter;
	public static AnalogGyro gyro;
	public static GameState state;
	public static Joystick leftStick, armStick;	
	@Override
	public void robotInit() {
		leftStick = new Joystick(0);
		armStick = new Joystick(1);
		gyro = new AnalogGyro(0);
		SpeedControllerPair leftPair = new SpeedControllerPair(new Talon(nextChannel()), new Talon(nextChannel()));
		
		SpeedControllerPair rightPair = new SpeedControllerPair(new Talon(nextChannel()), new Talon(nextChannel()));
		rightPair.setInverted(true);
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
	Ultrasonic ultra = new Ultrasonic(0, 1);
	@Override
	public void teleopInit() {
		state = new Teleop();
		ultra.setAutomaticMode(true);
	}
	
	@Override
	public void teleopPeriodic() {
		state.run();
		
		SmartDashboard.putNumber("ultra", ultra.getRangeInches());
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
