package frc.team1793.frcstronghold;

import edu.wpi.first.wpilibj.Timer;

/**
 * Created by tyler on 10/16/16.
 */
public class TrackableTimer extends Timer  {
    private boolean started;
    private int lap = 0;
    @Override
    public void start() {
        if(!started)
            started=true;
        super.start();
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public boolean hasPeriodPassed(double period) {
        boolean has = super.hasPeriodPassed(period);
        if(has) {
            lap++;
            RobotModule.logger.info("Timer Lap: " + lap);
        }
        return has;
    }

    @Override
    public void stop() {
        if(started)
            started=false;
        super.stop();
    }

    public boolean isStarted() {
        return started;
    }

    public int getLap() {
        return lap;
    }
}
