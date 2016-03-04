package org.usfirst.frc.team1793.robot.activities;

public interface IRobotActivity {
	public Activity getDetectDefenseActivity();

	public Activity getBreachLowBarActivity();

	public Activity getDefaultActivity();

	public void setActivity(Activity activity);
}
