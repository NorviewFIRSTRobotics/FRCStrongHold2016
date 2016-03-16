package org.usfirst.frc.team1793.robot.components;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UltrasonicPair {
	public enum SensorPosition {
		FRONTLEFT(0, 1), FRONTRIGHT(5, 4), BACKLEFT(3, 4), BACKRIGHT(2, 1), FRONT(6, 7), BACK(8, 7);
		public int echo, ping;

		private SensorPosition(int echo, int ping) {
			this.echo = echo;
			this.ping = ping;
		}
	}

	private static DigitalOutput[] outputs = new DigitalOutput[10];

	static {
		outputs[1] = new DigitalOutput(1);
		outputs[4] = new DigitalOutput(4);
		outputs[7] = new DigitalOutput(7);
	}

	private class UltrasonicChecker extends Thread {
		public synchronized void run() {
			while (running) {
				left.ping();
				Timer.delay(.1);
				leftRange = left.getRangeInches();
				
				right.ping();
				Timer.delay(.1);
				rightRange = right.getRangeInches();
				
				Timer.delay(.3);
			}
		}
	}

	private Ultrasonic left, right;
	private SensorPosition leftPos, rightPos;
	private volatile double leftRange, rightRange;
	private volatile boolean running;
	private UltrasonicChecker checker = new UltrasonicChecker();

	public UltrasonicPair(SensorPosition left, SensorPosition right) {
		this.left = new Ultrasonic(outputs[left.ping], new DigitalInput(left.echo));
		this.right = new Ultrasonic(outputs[right.ping], new DigitalInput(right.echo));
		leftPos = left;
		rightPos = right;
	}

	public double getLeftRange() {
		left.ping();
		Timer.delay(.1);
		leftRange =left.getRangeInches(); 
		SmartDashboard.putNumber(String.format("Sonic-e%d-p%d", leftPos.echo, leftPos.ping), leftRange);
		return leftRange;

	}

	public double getRightRange() {
		right.ping();
		Timer.delay(.1);
		rightRange = right.getRangeInches();
		SmartDashboard.putNumber(String.format("Sonic-e%d-p%d", rightPos.echo, rightPos.ping), rightRange);
		return rightRange;
	}

	public void setRunning(boolean run) {

		// running = run;
		// if(running) {
		// checker.start();
		// }

	}

	public double getSum() {
		return getLeftRange() + getRightRange();
	}

	public double getDifference() {
		return getLeftRange() - getRightRange();
	}
}
