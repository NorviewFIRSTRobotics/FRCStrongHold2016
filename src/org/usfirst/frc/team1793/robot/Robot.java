package org.usfirst.frc.team1793.robot;

import org.usfirst.frc.team1793.robot.ButtonHandler.PressEvent;
import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.activities.IRobotActivity;
import org.usfirst.frc.team1793.robot.activities.Idle;
import org.usfirst.frc.team1793.robot.activities.ManualDrive;
import org.usfirst.frc.team1793.robot.components.SpeedControllerPair;
import org.usfirst.frc.team1793.robot.system.ArmController;
import org.usfirst.frc.team1793.robot.system.DriveController;
import org.usfirst.frc.team1793.robot.system.ShooterController;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class Robot extends IterativeRobot implements IRobotActivity {
	public static int motorChannel = 0;
	public final boolean TEST_BOARD = false;
	
	public DriveController drive;
	public ArmController arm;
	public ShooterController shooter;
	public AnalogGyro gyro;
	public Joystick driveStick, armStick;

	public Activity currentActivity;

	private static Robot instance = new Robot();

	@Override
	public void robotInit() {
		driveStick = new EJoystick(0);
		armStick = new EJoystick(1);
		gyro = new AnalogGyro(Constants.GYRO_PID);
		SpeedControllerPair leftPair;
		SpeedControllerPair rightPair;
		if (TEST_BOARD) {
			leftPair = new SpeedControllerPair(new Jaguar(nextChannel()), new Jaguar(nextChannel()));
			rightPair = new SpeedControllerPair(new Jaguar(nextChannel()), new Jaguar(nextChannel()));	
		} else {
			leftPair = new SpeedControllerPair(new Talon(nextChannel()), new Talon(nextChannel()));
			rightPair = new SpeedControllerPair(new Talon(nextChannel()), new Talon(nextChannel()));			
		}
		rightPair.setInverted(true);
		drive = new DriveController(leftPair, rightPair);
		shooter = new ShooterController(new Victor(nextChannel()));
		arm = new ArmController(new Victor(nextChannel()));
	}

	@Override
	public void autonomousInit() {
		instance.setActivity(getDefaultActivity());
	}

	@Override
	public void autonomousPeriodic() {
		instance.currentActivity.update();
	}

	@Override
	public void teleopInit() {
		instance.setActivity(getDefaultActivity());
		
	}

	@Override
	public void teleopPeriodic() {
		
		instance.currentActivity.update();
		// TODO possible event handler for setting a new activity to run in
		// ManualDrive
		PressEvent event = ButtonHandler.listen();
	}

	@Override
	public void disabledInit() {
		instance.setActivity(getDefaultActivity());
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

	@Override
	public Activity getDetectDefenseActivity() {
		return null;
	}

	@Override
	public Activity getBreachLowBarActivity() {
		return null;
	}

	@Override
	public Activity getDefaultActivity() {
		if (isAutonomous()) {
			return getDetectDefenseActivity();
		} else if (isOperatorControl()) {
			return new ManualDrive(instance);
		} else {
			return new Idle(instance);
		}
	}

	@Override
	public void setActivity(Activity activity) {
		this.currentActivity = activity;
	}

	public static Robot getInstance() {
		return instance;
	}

}
