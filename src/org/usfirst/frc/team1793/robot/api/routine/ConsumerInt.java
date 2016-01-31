package org.usfirst.frc.team1793.robot.api.routine;

public abstract class ConsumerInt extends Consumer<Signal<Integer>, Integer> {

	public ConsumerInt(SignalInt signal, Integer complete) {
		super(signal, complete);
	}

}
