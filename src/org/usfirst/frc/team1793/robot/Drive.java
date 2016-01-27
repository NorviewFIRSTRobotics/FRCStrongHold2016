package org.usfirst.frc.team1793.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Drive extends RobotDrive implements IComponent {
	Joystick leftStick, rightStick;
	

	public Drive(SpeedController leftMotor, SpeedController rightMotor) {
		super(leftMotor, rightMotor);
		leftStick = new Joystick(0);
		rightStick = new Joystick(1);
	}

	@Override
	public void autonomousInit() {
		
	}

	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
	}

	@Override
	public void teleopInit() {
		
	}

	@Override
	public void teleopPeriodic() {
		if(Robot.config.getBoolean(Robot.TANK)) {
			tankDrive(rightStick,leftStick);
		} else {
			double twist = leftStick.getThrottle() + .01d;
			arcadeDrive(twist * leftStick.getY(),twist * -leftStick.getZ(),false);
		}		
	}
	

}

