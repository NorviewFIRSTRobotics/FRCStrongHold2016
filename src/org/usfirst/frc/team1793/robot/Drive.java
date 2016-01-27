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
			arcadeDrive(leftStick.getY(),leftStick.getZ(),true);
		}		
	}
	

}

//
//	public double finesseYSpeed() {
//		if (Math.abs(stick.getY()) < .8) {
//			adjustedYSpeed = stick.getY() * (.5 / .8); // a = .75, b =
//															// joystick value
//		} else {
//			adjustedYSpeed = ((.8 - stick.getY()) / (.95 - .8)); // a =
//																		// .75,
//																		// b =
//																		// joystick
//																		// value
//		}
//
//		return -adjustedYSpeed;
//	}
//
//	public double finesseTwistSpeed() {
//		if (Math.abs(stick.getTwist()) < .75) {
//			adjustedTwistSpeed = stick.getTwist() / .75; // a = .75, b =
//																// joystick
//																// value
//		} else {
//			// adjustedTwistSpeed = ((1-driveStick.getTwist())/(1-.90)); // a =
//			// .75, b = joystick value
//			adjustedTwistSpeed = stick.getTwist() * .75;
//		}
//
//		return -adjustedTwistSpeed;
//	}
