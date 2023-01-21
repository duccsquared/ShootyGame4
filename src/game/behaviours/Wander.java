package game.behaviours;

import game.Global;
import game.gameScreen.sprites.Entity;
import game.gameScreen.sprites.NPC;
import game.screens.GameScreen;

public class Wander extends Behaviour {
    private double ticksToNextTargetSwitch = 0;
    private double ticksSinceLastTargetSwitch = 0;
    public Wander(NPC npc) {
        super(npc);
    }

    @Override
    public void onTickStart() {
    }

    @Override
    public void onTickEnd() {
    }

    @Override
    public void calculateTarget() {
        NPC npc = this.getNpc();
        GameScreen gameScreen = (GameScreen) npc.getScreen();
        this.ticksSinceLastTargetSwitch += 1;
        if(npc.getDistanceFromTarget()<50 || ticksSinceLastTargetSwitch>=ticksToNextTargetSwitch) {
            npc.setTargetX(Global.limitMinMax(Global.randInt(npc.x1()-100,npc.x2()+100),gameScreen.MIN_X,gameScreen.MAX_X));
            npc.setTargetY(Global.limitMinMax(Global.randInt(npc.y1()-100,npc.x2()+100),gameScreen.MIN_Y,gameScreen.MAX_Y));
            this.ticksToNextTargetSwitch = Global.secondsToTicks(Global.randRange(5,8));
            this.ticksSinceLastTargetSwitch = 0;
        }
    }
}
