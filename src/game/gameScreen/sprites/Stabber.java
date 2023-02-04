package game.gameScreen.sprites;

import engine.screens.Screen;
import game.Global;
import game.behaviours.DirectMelee;
import game.behaviours.IdleAttackInRange;
import game.behaviours.Wander;

public class Stabber extends Enemy {
    public static final double HALF_SIZE = 16;
    private final static double WANDER_SPEED = Global.convertSpeedInSecondsToTicks(50);
    private final static double ATTACK_SPEED = Global.convertSpeedInSecondsToTicks(100);
    private final static double AGGRO_RANGE = 250;
    private final static double DE_AGGRO_RANGE = 450;

    public Stabber(Screen screen, double x, double y) {
        super(screen, 250,100,100,250,100,100, x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE, 4,WANDER_SPEED,1,5);
        this.setBehaviour(new IdleAttackInRange(this,new Wander(this),new DirectMelee(this),WANDER_SPEED,ATTACK_SPEED,AGGRO_RANGE,DE_AGGRO_RANGE));
    }
}