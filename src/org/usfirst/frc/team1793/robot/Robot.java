package org.usfirst.frc.team1793.robot;

import java.util.HashMap;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {


	
 	Drive drive;
 	Arm arm;
	SpeedController leftVictor, rightVictor, armVictor;
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
		drive = new Drive(leftVictor = new Victor(EnumMotor.LEFT.ordinal()), rightVictor = new Victor(EnumMotor.RIGHT.ordinal()));
		arm = new Arm(armVictor = new Victor(EnumMotor.ARM.ordinal()));
	}
	
	public void autonomousInit() {
		if(config.getBoolean(AUTONOMOUS)) {
			drive.autonomousInit();
		}
		
	}

	public void autonomousPeriodic() {
		if(config.getBoolean(AUTONOMOUS)) {
			drive.autonomousPeriodic();
		}
	}

	public void teleopInit() {
		if(config.getBoolean(TELEOP)) {
			drive.teleopInit();
		}
	}

	public void teleopPeriodic() {
		if(config.getBoolean(TELEOP)) {
			drive.teleopPeriodic();
		}
	}
	//aka the jank jank
	public static class Configuration {
		private HashMap<String,Object> config = new HashMap<String,Object>();
		
		public void put(String name, Object value) {
			config.put(name, value);
			
		}
		public void putBoolean(String name, boolean value) {
			put(name,value);	
			SmartDashboard.putBoolean(name, value);
		}
		
		public Object get(String name) {
			return config.get(name);
		}

		public boolean getBoolean(String name) {
			Object o = this.get(name);
			return o instanceof Boolean ? (boolean) o : false;
		}
		
	}
}
 