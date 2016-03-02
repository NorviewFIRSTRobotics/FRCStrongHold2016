package org.usfirst.frc.team1793.robot.state;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.Robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop extends GameState {

//	JoystickButton turnLeft90 = new JoystickButton(driveStick, 4);
//	JoystickButton turnRight90 = new JoystickButton(driveStick, 5);
//	JoystickButton turn180 = new JoystickButton(driveStick, 2);
	JoystickButton armForward = new JoystickButton(Robot.armStick, 3);
	JoystickButton armBack = new JoystickButton(Robot.armStick, 2);
	JoystickButton dumperForward = new JoystickButton(Robot.armStick, 6);
	
//	Align align = (Align) new Align(Axis.SOUTH).setNext(null);
	State state = null;
	
	public void run() {
		Robot.drive.arcadeDrive(Robot.driveStick.getY(),-Robot.driveStick.getZ());
		if(armForward.get()) {
			System.out.println("arm forward");
			Robot.arm.lift(Constants.ARM_SPEED);
		} else if(armBack.get()) {
			System.out.println("arm back");
			Robot.arm.lift(-Constants.ARM_SPEED);
		} else {
			Robot.arm.lift(0);
		}
		if(dumperForward.get()) {
			Robot.shooter.shoot(Constants.SHOOT_SPEED);				
		}
		SmartDashboard.putNumber("arm angle", Robot.arm.getAngle());
//		Robot.shooter
//		boolean turn = turnLeft90.get() || turnRight90.get() || turn180.get();
//		System.out.println(state != null ? state.getClass(): "null");
//		if (state == null) {
//
//			if (turn) {
//				System.out.println("turn pressed");
//				if (turnLeft90.get()) {
//					state = new Turn90(Relative.L90);
//				} else if (turnRight90.get()) {
//					state = new Turn90(Relative.R90);
//				} else if (turn180.get()) {
//					state = new Turn90(Relative.R90);
//					state.setNext(new Turn90(Relative.R90));
//				}
//			} else {
//				System.out.println("no turn, can drive");
//				Robot.drive.drive(Robot.driveStick.getY());
//				Robot.arm.lift(Robot.armStick.getY());
//			}
//
//		} else {
//			System.out.println("state is running");
//			state.run();
//			if(state.finished) {
//				System.out.println("state is finished!");
//				state=state.next();
//			}
//		}

		super.run();
	}
}