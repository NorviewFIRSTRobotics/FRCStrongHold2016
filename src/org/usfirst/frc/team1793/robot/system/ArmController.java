package org.usfirst.frc.team1793.robot.system;

public class ArmController extends Controller {

	public ArmController(Arm arm) {
		super(arm, 2);
	}
	public void lift(double speed) {
		executor.execute( () -> ((Arm)slave).lift(speed));
	}
}
