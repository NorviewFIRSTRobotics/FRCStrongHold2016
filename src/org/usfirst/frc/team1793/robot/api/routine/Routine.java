package org.usfirst.frc.team1793.robot.api.routine;

public class Routine<T1,T2> implements Runnable {
	protected Producer<Signal<T1>,T1> producer;
	protected Consumer<Signal<T2>,T2> consumer;
	
	public Routine() {}
	
	public Routine(Producer<Signal<T1>, T1> p,Consumer<Signal<T2>, T2> c) {
		producer = p;
		consumer = c;
	}

	@Override
	public void run() {
		if(producer != null) {
			producer.run();
		}
		if(producer != null) {
			consumer.run();
		}
	}
}
