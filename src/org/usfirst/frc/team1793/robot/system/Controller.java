package org.usfirst.frc.team1793.robot.system;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.usfirst.frc.team1793.robot.api.IRobotControllers;

public abstract class Controller {

	protected IRobotControllers controllers;
	protected ExecutorService executor;

	public Controller(int poolSize, IRobotControllers controllers) {
		this.controllers = controllers;
		executor = Executors.newFixedThreadPool(poolSize);
	}

	public void shutdown() {
		executor.shutdown();
	}
}
