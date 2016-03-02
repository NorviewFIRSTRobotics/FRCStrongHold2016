package org.usfirst.frc.team1793.robot.state.senarios;

import org.usfirst.frc.team1793.robot.state.State;

public class Scenario extends State {
	State currentState;
	public Scenario(State intial) {
		currentState = intial;
	}
	@Override
	public void run() {
		currentState.run();
	}

	@Override
	public State next() {
		return currentState = currentState.next();
	}

}
