package org.usfirst.frc.team1793.robot.system;

import org.usfirst.frc.team1793.robot.Constants;
import org.usfirst.frc.team1793.robot.api.IRobotControllers;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmController extends Controller {

	private AnalogInput rotaryEncoder = new AnalogInput(Constants.RE_PID);
	private SpeedController motor;
	

	public ArmController(SpeedController motor, IRobotControllers robot) {
		super(2, robot);
		this.motor = motor;

	}

	public void lift(double speed) {

		SmartDashboard.putNumber("Arm Motor", speed);
		executor.execute(() -> {
			
			//To extend arm speed goes to negative
			//To stow arm speed goes positive
			
			SmartDashboard.putNumber("Arm Angle", controllers.getArm().getAngle());
			
			if (speed > 0 && getAngle() > Constants.ARM_EXTENDED_ANGLE) {
				motor.set(speed);
			} else if (speed < 0 && getAngle() < Constants.ARM_STOWED_ANGLE) {
				motor.set(speed);
			} else {
				motor.set(0);
			}
		});
	}
	private volatile boolean finished = false;
	private volatile boolean running;
	public void setArmPosition(double angle) {
		finished = false;
		if(running) {
			return;
		}
		running = true;
		executor.execute(() -> {
			
			// TODO check which way is which and document it!!!! try to make -1
			// mean towards the store position!
		
			int direction = getAngle() > angle ? 1 : -1;
			
			while (!finished) {
				double speed = Constants.ARM_SPEED*direction;

				if (direction == 1  && getAngle() > angle) {
					motor.set(speed);
				} else if (direction == -1 && getAngle() < angle) {
					motor.set(speed);
				} else {
					motor.set(0);
					finished = true;
					running = false;
				}
			}
		});
	}
	public void middle() {
		setArmPosition((Constants.ARM_EXTENDED_ANGLE+Constants.ARM_STOWED_ANGLE)/2);
	}
	public void stow() {
		setArmPosition(Constants.ARM_STOWED_ANGLE);
	}

	public void extend() {
		setArmPosition(Constants.ARM_EXTENDED_ANGLE);
	}

	public double getAngle() {
		return rotaryEncoder.getVoltage();
	}

	public boolean isFinished() {
		return finished;
	}
}
