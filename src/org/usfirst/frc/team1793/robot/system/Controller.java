package org.usfirst.frc.team1793.robot.system;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller  {
	ISystem slave; 
	ExecutorService executor;
	public Controller(ISystem slave,int poolSize) {
		this.slave = slave;
		executor = Executors.newFixedThreadPool(poolSize);
	}
	public void shutdown() {
		executor.shutdown();
	}
}