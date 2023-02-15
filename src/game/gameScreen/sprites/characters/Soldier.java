package game.gameScreen.sprites.characters;

import engine.objects.Sprite;
import engine.screens.Screen;
import game.Global;
import game.behaviours.BladeAttackAndRetreat;
import game.behaviours.DirectMelee;
import game.behaviours.IdleAttackInRange;
import game.behaviours.Wander;
import game.gameScreen.sprites.characters.NPC;

public class Soldier extends NPC {
    public static final double HALF_SIZE = 16;
    private final static double WANDER_SPEED = Global.convertSpeedInSecondsToTicks(50);
    private final static double ATTACK_SPEED = Global.convertSpeedInSecondsToTicks(100);
    private final static double AGGRO_RANGE = 250;
    private final static double DE_AGGRO_RANGE = 450;
    public Soldier(Screen screen, double x, double y) {
        super(screen, "res/TurretBaseWoodenBlue.png", x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE, 4,WANDER_SPEED,0,Global.allyFaction);
        Blade blade = new Blade(this.getScreen(),this,"res/BladesWoodenBlue.png",1,60);
        this.addChild(blade);
        this.addChild(new DummyRect(screen,"res/TurretBaseWoodenBlue.png",this));
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