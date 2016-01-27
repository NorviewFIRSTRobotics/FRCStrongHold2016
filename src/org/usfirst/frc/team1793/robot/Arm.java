package org.usfirst.frc.team1793.robot;

import edu.wpi.first.wpilibj.SpeedController;

public class Arm implements IComponent {
	public SpeedController arm;
	public Arm(SpeedController arm) {
		this.arm = arm;
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
	}

}
