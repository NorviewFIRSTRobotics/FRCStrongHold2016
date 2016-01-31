package org.usfirst.frc.team1793.robot.api.routine;

public class Routine<T1,T2> implements Runnable {
	private Producer<Signal<T1>,T1> producer;
	private Consumer<Signal<T2>, T2> consumer;
	
	public Routine(T1 t1, T2 t2,Producer<Signal<T1>, T1> p,Consumer<Signal<T2>, T2> c) throws MatchingTypeException{
		producer = p;
		consumer = c;
		if(t1.hashCode() == t2.hashCode()) {
			throw new MatchingTypeException();
		}
	}

	@Override
	public void run() {
		producer.run();
		consumer.run();
	}
	
	public static class MatchingTypeException extends Exception {

		private static final long serialVersionUID = 1L;
		@Override
		public String getMessage() {
			return "Producer and Consumer Types may not match";
		}
	}
}
