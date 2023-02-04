package game.gameScreen.sprites;

import engine.screens.Screen;
import game.Global;
import game.behaviours.DirectMelee;
import game.behaviours.IdleAttackInRange;
import game.behaviours.RangedStrafe;
import game.behaviours.Wander;

public class Shooter extends Enemy {
    public static final double HALF_SIZE = 14;
    private final static double WANDER_SPEED = Global.convertSpeedInSecondsToTicks(50);
    private final static double ATTACK_SPEED = Global.convertSpeedInSecondsToTicks(100);
    private final static double AGGRO_RANGE = 250;
    private final static double DE_AGGRO_RANGE = 450;
    public Shooter(Screen screen, double x, double y) {
        super(screen, 200,100,100,250,100,100, x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE, 4,WANDER_SPEED,0,5);
        this.setBehaviour(new RangedStrafe(this));
        this.setBehaviour(
                        new IdleAttackInRange(this,new Wander(this),
                        new RangedStrafe(this),
                        WANDER_SPEED,ATTACK_SPEED,AGGRO_RANGE,DE_AGGRO_RANGE));

    }
}
