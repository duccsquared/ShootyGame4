package game.gameScreen.sprites.characters.enemies;

import engine.objects.BaseObject;
import engine.objects.Sprite;
import engine.screens.Screen;
import game.Global;
import game.behaviours.*;
import game.gameScreen.sprites.characters.Blade;
import game.gameScreen.sprites.characters.Enemy;

import java.awt.*;

public class Stabber extends Enemy {
    public static final double HALF_SIZE = 16;
    private final static double WANDER_SPEED = Global.convertSpeedInSecondsToTicks(Global.randInt(45,55));
    private final static double ATTACK_SPEED = Global.convertSpeedInSecondsToTicks(Global.randInt(90,110));
    private final static double AGGRO_RANGE = 400;
    private final static double DE_AGGRO_RANGE = 600;

    public Stabber(Screen screen, double x, double y) {
        super(screen, "res/TurretBaseWoodenRed.png", x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE, 4,WANDER_SPEED,0,5);
        Blade blade = new Blade(this.getScreen(),this,"res/BladesWoodenRed.png",2,60);
        this.addChild(blade);
        this.addChild(new DummyRect(screen,"res/TurretBaseWoodenRed.png",this));
        this.setBehaviour(
                new IdleAttackInRange(
                        this, new Wander(this),
                        new BladeAttackAndRetreat(this,new DirectMelee(this),blade),
                        WANDER_SPEED,ATTACK_SPEED,AGGRO_RANGE,DE_AGGRO_RANGE));

    }

}

class DummyRect extends Sprite {
    public DummyRect(Screen screen, String imgPath, Sprite sprite) {
        super(screen,imgPath, sprite.x1(),sprite.y1(),sprite.x2(),sprite.y2());
    }
}