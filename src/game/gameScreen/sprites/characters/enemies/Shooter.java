package game.gameScreen.sprites.characters.enemies;

import engine.screens.Screen;
import game.Global;
import game.behaviours.IdleAttackInRange;
import game.behaviours.RangedStrafe;
import game.behaviours.Wander;
import game.gameScreen.sprites.characters.Enemy;
import game.gameScreen.sprites.characters.NPCTurret;

public class Shooter extends Enemy {
    public static final double HALF_SIZE = 14;
    private final static double WANDER_SPEED = Global.convertSpeedInSecondsToTicks(Global.randInt(45,55));
    private final static double ATTACK_SPEED = Global.convertSpeedInSecondsToTicks(Global.randInt(90,110));
    private final static double AGGRO_RANGE = 400;
    private final static double DE_AGGRO_RANGE = 600;
    private final NPCTurret npcTurret;
    public Shooter(Screen screen, double x, double y) {
        super(screen, "res/TurretBaseWoodenRed.png", x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE, 4,WANDER_SPEED,0,5);
        this.npcTurret = new NPCTurret(this.getScreen(),this);
        this.addChild(npcTurret);
        this.setBehaviour(
                        new IdleAttackInRange(this,new Wander(this),
                        new RangedStrafe(this,this.npcTurret),
                        WANDER_SPEED,ATTACK_SPEED,AGGRO_RANGE,DE_AGGRO_RANGE));

    }
}
