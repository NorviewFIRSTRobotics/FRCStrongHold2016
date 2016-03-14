package org.usfirst.frc.team1793.robot.activities;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.IRobotControllers;
import org.usfirst.frc.team1793.robot.system.ShooterController;

public class DepositBoulder extends Activity {

	public DepositBoulder(IRobotActivity activity, IRobotControllers controllers) {
		super(activity, controllers);
	}

	@Override
	public void initialize() {
		isComplete = false;
	}

	@Override
	public void update() {
		ShooterController shooter = controllers.getShooter();
		if (!shooter.isRunning() && shooter.inStorePosition()) {
			shooter.shoot(Constants.SHOOT_SPEED);
		} else if (!shooter.isRunning()) {
			isComplete = true;
		}
	}
	
}
