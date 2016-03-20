package org.usfirst.frc.team1793.robot;

import org.usfirst.frc.team1793.robot.activities.Activity;
import org.usfirst.frc.team1793.robot.activities.DetectDefenseType;
import org.usfirst.frc.team1793.robot.activities.defaults.Autonomy;
import org.usfirst.frc.team1793.robot.activities.defaults.Idle;
import org.usfirst.frc.team1793.robot.activities.defaults.ManualDrive;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;
import org.usfirst.frc.team1793.robot.components.EJoystick;
import org.usfirst.frc.team1793.robot.components.SpeedControllerContainer;
import org.usfirst.frc.team1793.robot.components.SpeedControllerPair;
import org.usfirst.frc.team1793.robot.components.UltrasonicPair;
import org.usfirst.frc.team1793.robot.components.UltrasonicPair.SensorPosition;
import org.usfirst.frc.team1793.robot.debug.DebugMotor;
import org.usfirst.frc.team1793.robot.inputs.ButtonHandler;
import org.usfirst.frc.team1793.robot.system.ArmController;
import org.usfirst.frc.team1793.robot.system.DriveController;
import org.usfirst.frc.team1793.robot.system.ShooterController;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot implements IRobotActivity, IRobotControllers {
	public static int motorChannel = 0;
	public final boolean TEST_BOARD = false;

	private DriveController drive;
	private ArmController arm;
	private ShooterController shooter;
	private AnalogGyro gyro;
	private EJoystick driveStick, armStick;
	
	private UltrasonicPair frontSides;
	private UltrasonicPair backSides;
	private UltrasonicPair front_back;
	
	
	private Activity currentActivity;
	
	private ManualDrive _manualDrive;
	private Idle _idle;
	private DetectDefenseType _detectDefenseType;
	private Autonomy _autonomy;
	
	@Override
	public void robotInit() {
		_manualDrive = new ManualDrive(this,this);
		_idle = new Idle(this,this);
		_detectDefenseType = new DetectDefenseType(this,this);
		_autonomy = new Autonomy(this,this);
		driveStick = new EJoystick(Constants.DRIVE_STICK_PID);
		armStick = new EJoystick(Constants.ARM_STICK_PID);
		gyro = new AnalogGyro(Constants.GYRO_PID);
		
		SpeedController leftPair;
		SpeedController rightPair;
		
		SpeedController shooterMotor;
		SpeedController armMotor;
		
		if (TEST_BOARD) {
			leftPair = new DebugMotor("left");
			rightPair = new DebugMotor("right");
			shooterMotor = new DebugMotor("shooter");
			armMotor = new DebugMotor("arm");
		} else {
			leftPair = new SpeedControllerPair(new Talon(nextChannel()), new Talon(nextChannel()), "Left Pair");
			rightPair = new SpeedControllerPair(new Talon(nextChannel()), new Talon(nextChannel()),"Right Pair");
			shooterMotor = new SpeedControllerContainer(new Victor(nextChannel()), "Shooter Motor");
			armMotor = new SpeedControllerContainer(new Victor(nextChannel()), "Arm Motor");
		}
		
		rightPair.setInverted(true);
		drive = new DriveController(leftPair, rightPair,this);
		shooter = new ShooterController(shooterMotor,this);
		arm = new ArmController(armMotor,this);
		
		frontSides = new UltrasonicPair(SensorPosition.FRONTLEFT, SensorPosition.FRONTRIGHT);
		backSides = new UltrasonicPair(SensorPosition.BACKLEFT, SensorPosition.BACKRIGHT);
//		front_back = new UltrasonicPair(SensorPosition.FRONT,SensorPosition.BACK);
		
		setActivity(getDefaultActivity());
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
			setActivity(_idle);
		}
	}

	@Override
	public void teleopInit() {
		setActivity(getDefaultActivity());
	}

	@Override
	public void teleopPeriodic() {
		ButtonHandler.listen();
		if(ButtonHandler.pressed(Constants.DRIVE_STICK_PID, Constants.DRIVE_RESET_BUTTON, Constants.GLOBAL_STICK_MODIFIER_BUTTON)) {
			setActivity(getDefaultActivity());
		} else if(!currentActivity.isComplete()) {
			currentActivity.update();
		} else {
			setActivity(getDefaultActivity());
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

	private static int nextChannel() {
		int pid = motorChannel;
		motorChannel++;
		System.out.println("Adding motor pid:"+pid);
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
		SmartDashboard.putString("Activity", activity.getClass().getSimpleName());
	}

	@Override
	public DriveController getDrive() {
		return drive;
	}

	@Override
	public ShooterController getShooter() {
		return shooter;
	}

	@Override
	public ArmController getArm() {
		return arm;
	}

	@Override
	public Joystick getLeft() {
		return armStick;
	}

	@Override
	public Joystick getRight() {
		return driveStick;
	}

	@Override
	public AnalogGyro getGyro() {
		return gyro;
	}

	@Override
	public UltrasonicPair getFrontSides() {
		return frontSides;
	}

	@Override
	public UltrasonicPair getBackSides() {
		return backSides;
	}

	@Override
	public UltrasonicPair getFrontAndBack() {
		return front_back;
	}

	public void information() {
		SmartDashboard.putNumber("Arm Angle", getArm().getAngle());
		SmartDashboard.putBoolean("Shooter Stored", getShooter().isInStorePosition());
	}
	
}
