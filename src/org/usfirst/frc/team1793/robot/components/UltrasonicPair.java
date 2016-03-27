package org.usfirst.frc.team1793.robot.components;

import org.usfirst.frc.team1793.robot.Constants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UltrasonicPair {
	public enum SensorPosition {
		FRONTLEFT(0, 1), FRONTRIGHT(6, 5), BACKLEFT(4, 5), BACKRIGHT(3, 1),FACE(7,8);
		public int echo, ping;
		private SensorPosition(int echo, int ping) {
			this.echo = echo;
			this.ping = ping;
		}
		public static final SensorPosition[] VALUES = new SensorPosition[]{FRONTLEFT,FRONTRIGHT,BACKLEFT,BACKRIGHT};
	}

	private static DigitalOutput[] outputs = new DigitalOutput[10];

	static {
		for(SensorPosition pos: SensorPosition.VALUES) {
			if(outputs[pos.ping] == null) {
				outputs[pos.ping] = new DigitalOutput(pos.ping);			
			}			
		}
	}

	private Ultrasonic left, right;
	private SensorPosition leftPos, rightPos;
	private volatile double leftRange, rightRange;

	public UltrasonicPair(SensorPosition left, SensorPosition right) {
		this.left = new Ultrasonic(outputs[left.ping], new DigitalInput(left.echo));
		this.right = new Ultrasonic(outputs[right.ping], new DigitalInput(right.echo));
		leftPos = left;
		rightPos = right;
	}

	public double getLeftRange() {
		left.ping();
		Timer.delay(Constants.ULTRA_SONIC_DELAY);
		leftRange = left.getRangeInches(); 
		SmartDashboard.putNumber(leftPos.name(), leftRange);
		return leftRange;
	}

	public double getRightRange() {
		right.ping();
		Timer.delay(Constants.ULTRA_SONIC_DELAY);
		rightRange = right.getRangeInches();
		SmartDashboard.putNumber(rightPos.name(), rightRange);
		return rightRange;
	}

	public double getSum() {
		return getLeftRange() + getRightRange();
	}

	public double getDifference() {
		return getLeftRange() - getRightRange();
	}
	public void debug() {
		
		SmartDashboard.putNumber(leftPos.name(), getLeftRange());
		SmartDashboard.putNumber(rightPos.name(), getRightRange());
	}
}
