package org.usfirst.frc.team1793.robot.state.game;

import org.usfirst.frc.team1793.robot.state.State;
import org.usfirst.frc.team1793.robot.state.State.InvalidStateException;

public abstract class GameState extends State {
	
	public abstract State next() throws InvalidStateException;

}
