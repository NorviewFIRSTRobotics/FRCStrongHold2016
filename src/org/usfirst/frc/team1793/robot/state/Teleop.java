package org.usfirst.frc.team1793.robot.state;

import org.usfirst.frc.team1793.robot.Constants.Axis;
import org.usfirst.frc.team1793.robot.Robot;
import org.usfirst.frc.team1793.robot.state.drive.Align;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop extends GameState {
	JoystickButton test = new JoystickButton(Robot.leftStick, 2);
	boolean started = false;
	Align align = (Align) new Align(Axis.SOUTH).setNext(null);
	State state = null;
	public void run() {
		Robot.drive.arcadeDrive(Robot.leftStick.getY(), -Robot.leftStick.getZ());

		// TODO slow down arm
		Robot.arm.lift(Robot.armStick.getY());
		if(test.get()) {
			started = !started;
		}
		if(started)
			Robot.drive.turn(-90);

		SmartDashboard.putNumber("Gyro", Robot.gyro.getAngle());
		SmartDashboard.putBoolean("started", started);
		super.run();
	}
}