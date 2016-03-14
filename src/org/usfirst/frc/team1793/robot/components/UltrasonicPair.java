package org.usfirst.frc.team1793.robot.components;

import org.usfirst.frc.team1793.robot.Sensors.Ultrasonics;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;

public class UltrasonicPair {
	private class UltrasonicChecker extends Thread {
		public synchronized void run() {
			while (running) {
				left.ping();
				Timer.delay(.1);
				leftRange = left.getRangeInches();
				right.ping();
				Timer.delay(.1);
				rightRange = right.getRangeInches();
			}
		}
	}

	private Ultrasonic left, right;
	private volatile double leftRange, rightRange;
	private volatile boolean running;
	private UltrasonicChecker checker = new UltrasonicChecker();
	public UltrasonicPair(Ultrasonics left, Ultrasonics right) {
		this.left = new Ultrasonic(left.ping, left.echo);
		this.right = new Ultrasonic(right.ping, right.echo);
		
	}

	public double getLeftRange() {
		return leftRange;
	}

	public double getRightRange() {
		return rightRange;
	}
	public void setRunning(boolean run) {
		running = run;
		if(running) {
			checker.start();
		}
		
	}
}
