package org.usfirst.frc.team1793.robot.component;

import org.usfirst.frc.team1793.robot.Robot;

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
	SpeedController leftMotor, rightMotor;

	public enum ControlType {
		ARCADE, TANK, MECHANUM;
		public static ControlType[] values = new ControlType[] { ARCADE, TANK, MECHANUM };
	}

	public Drive(SpeedController leftMotor, SpeedController rightMotor) {
		super(leftMotor, rightMotor);
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		leftStick = new Joystick(0);
		rightStick = new Joystick(1);
	}

	@Override
	public void autonomousInit() {

	}

	@Override
	public void autonomousPeriodic() {}

	@Override
	public void disabledInit() {}

	@Override
	public void disabledPeriodic() {}

	@Override
	public void teleopInit() {

	}

	@Override
	public void teleopPeriodic() {
		drive(ControlType.values[Robot.config.getInt(Robot.CONTROLTYPE)]);
	}

	/**
	 * $
	 * 
	 * @param type
	 *            drive type drive to execute
	 * @param val1
	 *            leftstick.getY() or left speed between -1.0 and 1.0
	 * @param val2
	 *            Dependent on type
	 *            ARCADE: leftStick.getZ() or rotation
	 * 		      TANK: rightStick.getY() or right speed between -1.0 and 1.0
	 */

	public void drive(ControlType type, double val1, double val2) {
		switch (type) {
		case ARCADE: {
			arcadeDrive(val1, -val2, false);
			break;
		}
		case TANK: {
			tankDrive(val2, val1);
			break;
		}
		case MECHANUM:
			// NO-OP
			break;
		default:
			break;
		}

	}

	/**
	 * $
	 * 
	 * @param type
	 *            drive type drive to execute
	 */
	public void drive(ControlType type) {
		switch (type) {
		case ARCADE: {
			double twist = leftStick.getThrottle() + .01d;
			arcadeDrive(twist * leftStick.getY(), twist * -leftStick.getZ(), false);
			break;
		}
		case TANK: {
			tankDrive(rightStick.getY(), leftStick.getY());
			break;
		}
		case MECHANUM:
			// NO-OP
			break;
		default:
			break;
		}

	}

}
