package org.usfirst.frc.team1793.robot.system;

public class DriveController extends Controller {
	public DriveController(Drive drive) {
		super(drive,2);
	}
	public void arcadeDrive(double move, double rotation) {
		executor.execute(() -> ((Drive)slave).arcadeDrive(move,rotation));
		
	}
	public void tankDrive(double leftY, double rightY) {
		executor.execute(() -> ((Drive)slave).tankDrive(leftY, rightY));
	}
	
}
