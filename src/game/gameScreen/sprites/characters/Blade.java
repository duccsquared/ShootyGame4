package game.gameScreen.sprites.characters;

import engine.drawHandlers.DrawHandler;
import engine.drawHandlers.ImageDraw;
import engine.objects.BaseObject;
import engine.screens.Screen;
import game.Global;
import game.gameScreen.sprites.GameObject;

import java.util.ArrayList;

public class Blade extends GameObject {
    private int cooldown = 0;
    private boolean activated = true;
    private NPC npc;
    private DrawHandler altDrawHandler;
    private double damage;
    private int cooldownTime;
    private double angularSpeed;
    public Blade(Screen screen, NPC npc, String activatedImgPath, double damage, int cooldown) {
        super(screen, activatedImgPath, npc.x()-32,npc.y()-8,npc.x()+32,npc.y()+8);
        this.npc = npc;
        this.altDrawHandler = new ImageDraw("res/BladesWoodenDeactivated.png",this.width(),this.height());
        this.damage = damage;
        this.cooldownTime = cooldown;
        this.angularSpeed = Global.randRange(6,10);
        if(Global.randRange(0,1)==0) {this.angularSpeed *= -1;}
    }

    private void swapDrawHandlers() {
        DrawHandler temp = this.altDrawHandler;
        this.altDrawHandler = this.getDrawHandler();
        this.setDrawHandler(temp);
    }

    public boolean isActivated() {return activated;}
    @Override
    public void tick() {
        super.tick();
        if(this.cooldown>0) {this.cooldown-=1;}
        if(this.cooldown<=0 && !this.activated) {
            this.activated = true;
            this.swapDrawHandlers();
        }
        this.changeAngle(8);
        ArrayList<BaseObject> intersectingList = this.findAllIntersecting(Character.class);
        if(intersectingList!=null) {
            for(BaseObject baseObject: intersectingList) {
                Character character = (Character) baseObject;
                if(this.npc.getFaction().isHostileTo(character.getFaction())) {
                    if(this.cooldown<=0) {
                        character.damage(this.damage);
                        this.cooldown = this.cooldownTime;
                        this.activated = false;
                        this.swapDrawHandlers();
                        double dir = Global.coorToDir(this.x(),this.y(),character.x(),character.y());
                        character.addForceDir(dir,20);
                    }

                }
            }
        }
    }
}
