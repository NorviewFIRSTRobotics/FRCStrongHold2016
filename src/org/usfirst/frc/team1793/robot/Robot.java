package org.usfirst.frc.team1793.robot;

import org.usfirst.frc.team1793.robot.component.Arm;
import org.usfirst.frc.team1793.robot.component.ComponentList;
import org.usfirst.frc.team1793.robot.component.Drive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@SuppressWarnings("unused")
public class Robot extends IterativeRobot {
	private ComponentList components = new ComponentList();
	
	private SpeedController leftVictor, rightVictor, armVictor;

	private MPU6050 gyro;

	public static Configuration config = new Configuration();
	public enum EnumMotor {
		LEFT,
		RIGHT,
		ARM;
	}
	
	public static final String TANK = "TANK";
	public static final String AUTONOMOUS = "AUTONOMOUS";
	public static final String TELEOP = "TELEOP";
	
	public void robotInit() {
		config.put(TANK, false);
		config.put(AUTONOMOUS, true);
		config.put(TELEOP, true);
		gyro = new MPU6050();
		components.add(new Drive(leftVictor = new Victor(EnumMotor.LEFT.ordinal()), rightVictor = new Victor(EnumMotor.RIGHT.ordinal())));
		components.add(new Arm(armVictor = new Victor(EnumMotor.ARM.ordinal())));
	}
	
	public void autonomousInit() {
		if(config.getBoolean(AUTONOMOUS)) {
			components.autonomousInit();
		}
		
	}

	public void autonomousPeriodic() {
		if(config.getBoolean(AUTONOMOUS)) {
			components.autonomousPeriodic();
		}
	}

	public void teleopInit() {
		if(config.getBoolean(TELEOP)) {
			components.teleopInit();
		}
	}

	public void teleopPeriodic() {
		if(config.getBoolean(TELEOP)) {
			components.teleopPeriodic();
			SmartDashboard.putNumber("rate", gyro.getRate());
		}
	}

}
 