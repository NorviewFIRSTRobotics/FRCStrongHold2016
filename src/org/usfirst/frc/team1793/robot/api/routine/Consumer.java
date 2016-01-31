package org.usfirst.frc.team1793.robot.api.routine;

import java.util.Random;

public abstract class Consumer<S extends Signal<T>, T> implements Runnable {
	private S signal;
	private T complete;

	public Consumer(S signal, T complete) {
		this.signal = signal;
		this.complete = complete;
	}
	
	protected void consume() {
		Random random = new Random();
		for (T message = signal.take(); !message.equals(complete); message = signal.take()) {
			System.out.format("MESSAGE RECEIVED: %s%n", message);
			action(message);
			try {
				Thread.sleep(random.nextInt(5000));
			} catch (InterruptedException e) {}
		}
	}
	@Override
	public void run() {
		consume();
	}
	
	public abstract boolean action(T message);

}
