package game;

import engine.utils.BaseGlobal;

public class Global extends BaseGlobal {
    public static Faction playerFaction = new Faction("PLAYER","Player Faction");
    public static Faction enemyFaction = new Faction("ENEMY","Enemy Faction");
    public static Faction neutralFaction = new Faction("NEUTRAL","Neutral Faction");


    public static double convertSpeedInSecondsToTicks(double speedInSeconds) {
        return speedInSeconds/Global.secondsToTicks(1);
    }
}
