package org.usfirst.frc.team1793.robot.state.game;

import org.usfirst.frc.team1793.robot.state.State;
import org.usfirst.frc.team1793.robot.state.drive.Turn;

public class Auto extends GameState {
	private boolean left,spy; 
	
	@Override
	public State next() throws InvalidStateException {
		return null;
	}

	@Override
	public void run() {
		new Turn(1).run();
	}

}
