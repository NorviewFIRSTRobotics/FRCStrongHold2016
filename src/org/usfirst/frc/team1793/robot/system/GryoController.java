package org.usfirst.frc.team1793.robot.system;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team1793.robot.components.sensor.MPU6050;

public class GryoController extends Controller {
	public volatile double accumlation;
	public boolean isRunning = false;
	public final int FREQUENCY=100;
	
	public GryoController(MPU6050 gyro) {
		super(gyro, 1);
	}
	public void run() {
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::accumlate, 0, 10, TimeUnit.MILLISECONDS);
	}
	public Runnable accumlate() {
		
		return () -> {
			
		};
	}
	

}
