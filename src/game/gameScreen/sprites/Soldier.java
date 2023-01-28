package game.gameScreen.sprites;

import engine.screens.Screen;
import game.Faction;
import game.Global;
import game.behaviours.WanderMelee;

public class Soldier extends NPC {
    public static final double HALF_SIZE = 16;
    private final static double WANDER_SPEED = Global.convertSpeedInSecondsToTicks(50);
    private final static double ATTACK_SPEED = Global.convertSpeedInSecondsToTicks(100);
    private final static double AGGRO_RANGE = 250;
    private final static double DE_AGGRO_RANGE = 450;
    public Soldier(Screen screen, double x, double y) {
        super(screen, 0, 0, 250,0, 0, 250, x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE, 4,WANDER_SPEED,Global.allyFaction);
        this.setBehaviour(new WanderMelee(this,WANDER_SPEED,ATTACK_SPEED,AGGRO_RANGE,DE_AGGRO_RANGE));
    }

}
