package org.usfirst.frc.team1793.robot.state.senarios;

import org.usfirst.frc.team1793.robot.state.State;
import org.usfirst.frc.team1793.robot.state.State.InvalidStateException;

public class Senario extends State {
	State currentState;
	public Senario(State intial) {
		currentState = intial;
	}
	@Override
	public void run() {
		currentState.run();
	}

	@Override
	public State next() throws InvalidStateException {
		return currentState = currentState.next();
	}

}
