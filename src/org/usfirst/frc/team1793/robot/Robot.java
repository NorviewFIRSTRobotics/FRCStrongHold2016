package org.usfirst.frc.team1793.robot;

import org.usfirst.frc.team1793.robot.state.game.Auto;
import org.usfirst.frc.team1793.robot.state.game.Disable;
import org.usfirst.frc.team1793.robot.state.game.GameState;
import org.usfirst.frc.team1793.robot.state.game.Teleop;
import org.usfirst.frc.team1793.robot.state.senarios.Senario;
import org.usfirst.frc.team1793.robot.system.Drive;
import org.usfirst.frc.team1793.robot.system.DriveController;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static DriveController driveController;
	private GameState state;
	public static Joystick leftStick, rightStick;
	
	@Override
	public void robotInit() {
		leftStick = new Joystick(0);
		rightStick = new Joystick(1);
		driveController = new DriveController(new Drive(new Victor(0), new Victor(1)));
	}

	@Override
	public void autonomousInit() {
		state = new Auto();
	}

	@Override
	public void autonomousPeriodic() {
		state.run();
	}

	@Override
	public void teleopInit() {
		state = new Teleop();
	}

	@Override
	public void teleopPeriodic() {
		SmartDashboard.putString("main", Thread.currentThread().toString());
		state.run();
	}

	@Override
	public void disabledInit() {
		state = new Disable();
	}

	@Override
	public void disabledPeriodic() {
		state.run();
	}
	public class SenarioButton extends JoystickButton {
		Senario senario;
		public SenarioButton(GenericHID joystick, int buttonNumber,Senario senario) {
			super(joystick, buttonNumber);
			this.senario = senario; 
		}
		public void run() {
			senario.run();
		}
	}
}
