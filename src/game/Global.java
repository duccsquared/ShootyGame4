package game;

import engine.utils.BaseGlobal;

public class Global extends BaseGlobal {
    public static double convertSpeedInSecondsToTicks(double speedInSeconds) {
        return speedInSeconds/Global.secondsToTicks(1);
    }
}
