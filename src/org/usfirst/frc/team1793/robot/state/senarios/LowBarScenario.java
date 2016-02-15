package org.usfirst.frc.team1793.robot.state.senarios;

import org.usfirst.frc.team1793.robot.Constants.Axis;
import org.usfirst.frc.team1793.robot.state.drive.Align;
import org.usfirst.frc.team1793.robot.state.drive.PeriodDrive;

public class LowBarScenario extends Scenario {
	
	public static PeriodDrive forward = new PeriodDrive(10);
	public static Align align = (Align) new Align(Axis.NORTH).setNext(forward);
	
	public LowBarScenario() {
		super(align);
	}
	
	
}
