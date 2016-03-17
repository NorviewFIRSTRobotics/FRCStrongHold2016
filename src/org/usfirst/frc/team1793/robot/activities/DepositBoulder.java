package org.usfirst.frc.team1793.robot.activities;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.ExtendArm;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.StowArm;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;
import org.usfirst.frc.team1793.robot.system.ShooterController;

public class DepositBoulder extends Activity {
	private ExtendArm extendArm = new ExtendArm(activity, controllers);
	private StowArm stowArm = new StowArm(activity, controllers);

	public DepositBoulder(IRobotActivity activity, IRobotControllers controllers) {
		super(activity, controllers);
	}

	@Override
	public void update() {
		if (!extendArm.isComplete()) {
			extendArm.update();
		} else {
			ShooterController shooter = controllers.getShooter();
			// TODO move
			if (!shooter.isRunning() && shooter.isInStorePosition()) {
				shooter.shoot(Constants.SHOOT_SPEED);
			} else if (!shooter.isRunning()) {
				if(!stowArm.isComplete()) {
					stowArm.update();
				} else {
					isComplete = true;
				}
			}
		}
	}

}
