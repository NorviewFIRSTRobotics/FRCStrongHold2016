package org.usfirst.frc.team1793.robot;

import java.util.ArrayList;

public class ComponentList extends ArrayList<IComponent> implements IComponent {

	private static final long serialVersionUID = 6468602516500497371L;

	@Override
	public void autonomousInit() {
		forEach(c -> c.autonomousInit());	
	}
	@Override
	public void autonomousPeriodic() {
		forEach(c -> c.autonomousPeriodic());
	}

	@Override
	public void disabledInit() {
		forEach(c -> c.disabledInit());
	}

	@Override
	public void disabledPeriodic() {
		forEach(c -> c.disabledPeriodic());
	}

	@Override
	public void teleopInit() {
		forEach(c -> c.teleopInit());
	}

	@Override
	public void teleopPeriodic() {
		forEach(c -> c.teleopPeriodic());
	}

}
