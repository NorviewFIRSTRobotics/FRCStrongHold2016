package org.usfirst.frc.team1793.robot;

import org.usfirst.frc.team1793.robot.state.game.Auto;
import org.usfirst.frc.team1793.robot.state.game.Disable;
import org.usfirst.frc.team1793.robot.state.game.GameState;
import org.usfirst.frc.team1793.robot.state.game.Teleop;
import org.usfirst.frc.team1793.robot.state.senarios.Senario;
import org.usfirst.frc.team1793.robot.system.ArmController;
import org.usfirst.frc.team1793.robot.system.DriveController;
import org.usfirst.frc.team1793.robot.system.ShooterController;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static DriveController drive;
	public static ArmController arm;
	public static ShooterController shooter;
	
	private GameState state;
	public static Joystick leftStick, armStick;	
	@Override
	public void robotInit() {
//		leftStick = new Joystick(0);
//		armStick = new Joystick(1);
	
//		drive = new DriveController(new Victor(0), new Victor(1));
//		arm = new ArmController(new Victor(2));
//		shooter = new ShooterController(new Victor(3), new Victor(4));
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
		AnalogInput irsensor = new AnalogInput(0);
		SmartDashboard.putNumber("IR", irsensor.getVoltage());
//		state.run();
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
