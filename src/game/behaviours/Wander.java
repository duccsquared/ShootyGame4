package game.behaviours;

import game.Global;
import game.gameScreen.sprites.Entity;
import game.gameScreen.sprites.NPC;
import game.screens.GameScreen;

public class Wander extends Behaviour {
    private double currentTargetX;
    private double currentTargetY;
    private double targetX;
    private double targetY;
    private double ticksToNextTargetSwitch = 0;
    private double ticksSinceLastTargetSwitch = 0;
    public Wander(NPC npc) {
        super(npc);
        this.targetX = npc.x();
        this.targetY = npc.y();
        this.currentTargetX = this.targetX;
        this.currentTargetY = this.targetY;
    }

    public void setTargetX(double targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(double targetY) {
        this.targetY = targetY;
    }
    @Override
    public void onTickStart() {
        if(this.currentTargetX!=this.targetX || this.currentTargetY!=this.targetY) {
            this.currentTargetX = this.targetX;
            this.currentTargetY = this.targetY;
            this.targetToSpeed(this.targetX,this.targetY);
        }
    }

    @Override
    public void onTickEnd() {
    }



    @Override
    public void calculateTarget() {
        NPC npc = this.getNpc();
        GameScreen gameScreen = (GameScreen) npc.getScreen();
        this.ticksSinceLastTargetSwitch += 1;
        if(this.getDistanceFromTarget(targetX,targetY)<50 || ticksSinceLastTargetSwitch>=ticksToNextTargetSwitch) {
            this.setTargetX(Global.limitMinMax(Global.randInt(npc.x1()-100,npc.x2()+100),gameScreen.MIN_X,gameScreen.MAX_X));
            this.setTargetY(Global.limitMinMax(Global.randInt(npc.y1()-100,npc.y2()+100),gameScreen.MIN_Y,gameScreen.MAX_Y));
            this.ticksToNextTargetSwitch = Global.secondsToTicks(Global.randRange(5,8));
            this.ticksSinceLastTargetSwitch = 0;
        }
    }
}
