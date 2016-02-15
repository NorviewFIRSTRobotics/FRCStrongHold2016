package org.usfirst.frc.team1793.robot.system;

import java.util.concurrent.Future;

import edu.wpi.first.wpilibj.AnalogGyro;

public class GyroController extends Controller {
	AnalogGyro gyro;
	public GyroController() {
		super(1);
		gyro = new AnalogGyro(0);
	}
	public Future<Double> getAngle() {
		return executor.submit(() -> gyro.getAngle());
	}
	public Future<Integer> getCenter() {
		return executor.submit(() -> gyro.getCenter());
	}
}
