package org.usfirst.frc.team1793.robot;

import java.util.HashMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Configuration {
	private HashMap<String, Object> config = new HashMap<String, Object>();

	public void put(String name, Object value) {
		config.put(name, value);

	}

	public void putBoolean(String name, boolean value) {
		put(name, value);
		SmartDashboard.putBoolean(name, value);
	}

	public void putInt(String name, int value) {
		put(name, value);
		SmartDashboard.putNumber(name, value);
	}

	public Object get(String name) {
		return config.get(name);
	}

	public boolean getBoolean(String name) {
		Object o = this.get(name);
		return o instanceof Boolean ? (boolean) o : false;
	}

	public int getInt(String name) {
		Object o = this.get(name);
		return o instanceof Integer ? (int) o : 0;
	}
}
