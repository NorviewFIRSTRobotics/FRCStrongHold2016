package org.usfirst.frc.team1793.robot.state.game;

import org.usfirst.frc.team1793.robot.state.State;

public class NoState extends State {

	@Override
	public void run() {
	}

	@Override
	public State next() throws InvalidStateException {
		return new NoState();
	}

}
