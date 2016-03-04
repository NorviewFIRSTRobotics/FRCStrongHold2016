package org.usfirst.frc.team1793.robot.system;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Controller {
	protected ExecutorService executor;

	public Controller(int poolSize) {

		executor = Executors.newFixedThreadPool(poolSize);
	}

	public void shutdown() {
		executor.shutdown();
	}
}
