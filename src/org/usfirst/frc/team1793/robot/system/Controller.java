package org.usfirst.frc.team1793.robot.system;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.usfirst.frc.team1793.robot.Robot;

public abstract class Controller {
	protected Robot robot;
	protected ExecutorService executor;

	public Controller(int poolSize, Robot robot) {
		this.robot = robot;
		executor = Executors.newFixedThreadPool(poolSize);
	}

	public void shutdown() {
		executor.shutdown();
	}
}
