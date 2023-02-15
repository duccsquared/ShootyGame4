package game.gameScreen.sprites.characters;

import engine.objects.Sprite;
import engine.screens.Screen;
import game.Global;
import game.behaviours.DirectMelee;
import game.behaviours.IdleAttackInRange;
import game.behaviours.Wander;
import game.gameScreen.sprites.characters.NPC;

public class Soldier extends NPC {
    public static final double HALF_SIZE = 16;
    private final static double WANDER_SPEED = Global.convertSpeedInSecondsToTicks(Global.randInt(45,55));
    private final static double ATTACK_SPEED = Global.convertSpeedInSecondsToTicks(Global.randInt(90,110));
    private final static double AGGRO_RANGE = 400;
    private final static double DE_AGGRO_RANGE = 600;
    public Soldier(Screen screen, double x, double y) {
        super(screen, "res/TurretBaseWoodenBlue.png", x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE, 4,WANDER_SPEED,2,Global.allyFaction);
        this.setBehaviour(
                new IdleAttackInRange(
                        this, new Wander(this),
                        new DirectMelee(this),
                        WANDER_SPEED,ATTACK_SPEED,AGGRO_RANGE,DE_AGGRO_RANGE));
    }
}