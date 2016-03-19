package org.usfirst.frc.team1793.robot.activities;

import java.util.ArrayList;
import java.util.Arrays;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.MiddleArm;
import org.usfirst.frc.team1793.robot.activities.breach.subactivities.StowArm;
import org.usfirst.frc.team1793.robot.api.IRobotActivity;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;
import org.usfirst.frc.team1793.robot.system.ShooterController;

public class DepositBoulder extends Activity implements IRobotActivity {
	private MiddleArm middleArm = new MiddleArm(activity, controllers);
	private ShootBall shootBall = new ShootBall(activity, controllers);
	private StowArm stowArm = new StowArm(activity, controllers);
	private ArrayList<Activity> order;
	private Activity currentActivity;

	public DepositBoulder(IRobotActivity activity, IRobotControllers controllers) {
		super(activity, controllers);
	}

	@Override
	public void initialize() {
		if(controllers.getArm().getAngle() <= (Constants.ARM_EXTENDED_ANGLE+Constants.ARM_STOWED_ANGLE)/2)
			order = new ArrayList<Activity>(Arrays.asList(shootBall,stowArm));
		else 
			order = new ArrayList<Activity>(Arrays.asList(middleArm, shootBall,stowArm));
		super.initialize();
		setActivity(order.remove(0));
	}

	@Override
	public void update() {
		if (!currentActivity.isComplete()) {
			currentActivity.update();
		} else {
			if (order.isEmpty()) {
				isComplete = true;
			} else {
				setActivity(order.remove(0));
			}
		}
	}

	public class ShootBall extends Activity {

		public ShootBall(IRobotActivity activity, IRobotControllers controllers) {
			super(activity, controllers);
			// TODO Auto-generated constructor stub
		}
	
		@Override
		public void initialize() {
			// TODO Auto-generated method stub
			super.initialize();
			ShooterController shooter = controllers.getShooter();
			shooter.shoot(Constants.SHOOT_SPEED);
		}

		@Override
		public void update() {
			ShooterController shooter = controllers.getShooter();
			if(!shooter.isRunning())
				isComplete = true;
		}

	}

	@Override
	public void setActivity(Activity activity) {
		this.currentActivity = activity;
		currentActivity.initialize();
	}

	@Override
	public Activity getDefaultActivity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Activity getDetectDefenseActivity() {
		// TODO Auto-generated method stub
		return null;
	}

}
