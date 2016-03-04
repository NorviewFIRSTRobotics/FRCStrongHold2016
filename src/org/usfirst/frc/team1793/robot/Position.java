package org.usfirst.frc.team1793.robot;

import org.usfirst.frc.team1793.robot.Constants.Progress;

public class Position {

	public static boolean straight;
	public static boolean frontObstructed, backObstructed;
	public static Progress breaching;
	//TODO rework this to use specific pairs, not all of the sensors
	//this will be easier when we have a test board! 
	public static void sense() {

		double frontPairSum = Sensors.getDistanceSum(Sensors.leftFront, Sensors.rightFront);
		double backPairSum = Sensors.getDistanceSum(Sensors.leftBack, Sensors.rightBack);
		double frontPairDelta = Sensors.getDelta(Sensors.leftFront, Sensors.rightFront);
		double backPairDelta = Sensors.getDelta(Sensors.leftBack, Sensors.rightBack);
		double frontDistance = Sensors.getDistance(Sensors.front);
		double backDistance = Sensors.getDistance(Sensors.back);
		straight = Math.abs(frontPairDelta - backPairDelta) <= Constants.STRAIGHTENOUGH;
		frontObstructed = frontDistance < Constants.FARENOUGH;
		backObstructed = backDistance < Constants.FARENOUGH;
		if (frontPairSum <= Constants.BREACH && backPairSum > Constants.BREACH) {
			breaching = Progress.JUSTFRONT;
		} else if (frontPairSum > Constants.BREACH && backPairSum <= Constants.BREACH) {
			breaching = Progress.JUSTBACK;
		} else if (frontPairSum <= Constants.BREACH && backPairSum <= Constants.BREACH) {
			breaching = Progress.ALL;
		} else {
			breaching = Progress.NONE;
		}

	}

}
