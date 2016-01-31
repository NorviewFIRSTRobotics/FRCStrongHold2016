package org.usfirst.frc.team1793.robot.api.routine;

import java.util.Random;

public abstract class Producer<S extends Signal<T>,T> implements Runnable{
	private S signal;
	private T complete;
	private T[] info;
	@SafeVarargs
	public Producer(S signal,T complete, T... info) {
		this.signal = signal;
		this.complete = complete;
		this.info = info;
	}
	protected void produce() {
		T[] data = info.clone();
		Random random = new Random();
		for( int i = 0; i < data.length;i++) {
			signal.put(data[i]);
			try {
				Thread.sleep(random.nextInt(5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		signal.put(complete);
	}
	@Override
	public void run() {
		produce();
	}
}
