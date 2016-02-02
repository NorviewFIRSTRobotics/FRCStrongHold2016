package org.usfirst.frc.team1793.robot.system;

public class DriveController extends Controller {

	public DriveController(Drive drive) {
		super(drive);
	}
	public void arcadeDrive(double move, double rotation) {
		target = () -> { ((Drive)slave).arcadeDrive(move, rotation);};
		run();
	}
	public void tankDrive(double leftY, double rightY) {
		target = () -> ((Drive)slave).tankDrive(leftY, rightY);
		run();
	}
	@Override
	public synchronized void start() {
		super.start();
	}
}
