package org.usfirst.frc.team1793.robot.inputs;

public class Press {

		protected int joystick, button;
		public Press(int joystick, int button) {
			this.joystick = joystick;
			this.button = button;
		}
		@Override
		public int hashCode() {
			return joystick ^ button;
		}
		@Override
		public String toString() {
			return String.format("J:%d B:%d", joystick,button);
		}
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Press) {
				Press p = (Press) obj;
				return p.joystick == joystick && p.button == button;
				
			}
			return false;
		}
	}
