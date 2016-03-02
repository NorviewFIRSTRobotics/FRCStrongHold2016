package org.usfirst.frc.team1793.robot;

import org.usfirst.frc.team1793.robot.components.SpeedControllerPair;
import org.usfirst.frc.team1793.robot.state.Auto;
import org.usfirst.frc.team1793.robot.state.GameState;
import org.usfirst.frc.team1793.robot.state.Teleop;
import org.usfirst.frc.team1793.robot.system.ArmController;
import org.usfirst.frc.team1793.robot.system.DriveController;
import org.usfirst.frc.team1793.robot.system.ShooterController;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	public static int motorChannel = 0;
	public static DriveController drive;
	public static ArmController arm;
	public static ShooterController shooter;
	public static AnalogGyro gyro;	
	public static Joystick driveStick, armStick;
	
	public static GameState state;
	
	public static final Robot INSTANCE = new Robot();
	
	@Override
	public void robotInit() {
		driveStick = new Joystick(0);
		armStick = new Joystick(1);
		gyro = new AnalogGyro(0);
		SpeedControllerPair leftPair = new SpeedControllerPair(new Talon(nextChannel()), new Talon(nextChannel()));
		SpeedControllerPair rightPair = new SpeedControllerPair(new Talon(nextChannel()), new Talon(nextChannel()));
		rightPair.setInverted(true);
		drive = new DriveController(leftPair,rightPair);
		shooter = new ShooterController(new Victor(nextChannel()));
		arm = new ArmController(new Victor(nextChannel()));
	}
	Thread runThread;

	@Override
	public void autonomousInit() {
		state = new Auto();
//		runThread = new Thread( () -> state.run() );
	}

	@Override
	public void autonomousPeriodic() {
//		if(state != null) {
//			runThread.start();
//		}
//		state= null;
	}
	
	@Override
	public void teleopInit() {
//		state = new Teleop();
		
	}
	@Override
	public void teleopPeriodic() {
//		state.run();

//		JoystickButton button = new JoystickButton(armStick, 2);
//		if(button.get()){
//			Sensors.test();
//		}
	}

	@Override
	public void disabledInit() {
	
	}

	@Override
	public void disabledPeriodic() {
	
	}

	public static int nextChannel() {
		int pid = motorChannel;
		motorChannel++;
		System.out.println(pid);
		return pid;
	}
}
