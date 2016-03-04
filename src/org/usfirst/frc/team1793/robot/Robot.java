package org.usfirst.frc.team1793.robot;

import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.activities.DetectDefenseType;
import org.usfirst.frc.team1793.robot.activities.IRobotActivity;
import org.usfirst.frc.team1793.robot.activities.defaults.Autonomy;
import org.usfirst.frc.team1793.robot.activities.defaults.Idle;
import org.usfirst.frc.team1793.robot.activities.defaults.ManualDrive;
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
	
	private ManualDrive _manualDrive;
	private Idle _idle;
	private DetectDefenseType _detectDefenseType;
	private Autonomy _autonomy;
	
	@Override
	public void robotInit() {
		
		_manualDrive = new ManualDrive(this);
		_idle = new Idle(this);
		_detectDefenseType = new DetectDefenseType(this);
		_autonomy = new Autonomy(this);
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
		drive = new DriveController(leftPair, rightPair,this);
		shooter = new ShooterController(new Victor(nextChannel()),this);
		arm = new ArmController(new Victor(nextChannel()),this);

	}

	@Override
	public void autonomousInit() {
		setActivity(getDefaultActivity());
	}

	@Override
	public void autonomousPeriodic() {
		if(!currentActivity.isComplete()) {
			currentActivity.update();
		} else {
			//????
		}
	}

	@Override
	public void teleopInit() {
		
	}

	@Override
	public void teleopPeriodic() {
		if(!currentActivity.isComplete()) {
			currentActivity.update();
		} else {
			//????
		}
	}

	@Override
	public void disabledInit() {
		currentActivity.cancel();
	}

	@Override
	public void disabledPeriodic() {
		//No need to update, nothing is happening
	}

	public static int nextChannel() {
		int pid = motorChannel;
		motorChannel++;
		System.out.println(pid);
		return pid;
	}


	public Activity getDetectDefenseActivity() {
		return _detectDefenseType;
	}

	@Override
	public Activity getDefaultActivity() {
		if (isAutonomous()) {
			return _autonomy;
		} else if (isOperatorControl()) {
			return _manualDrive;
		} else {
			return _idle;
		}
	}

	@Override
	public void setActivity(Activity activity) {
		this.currentActivity = activity;
		this.currentActivity.initialize();
	}


}
