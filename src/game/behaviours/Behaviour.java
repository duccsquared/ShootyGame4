package game.behaviours;

import game.Global;
import game.gameScreen.sprites.Character;
import game.gameScreen.sprites.Entity;
import game.gameScreen.sprites.NPC;

public abstract class Behaviour {
    protected NPC npc;
    public Behaviour(NPC npc) {
        this.npc = npc;
    }

    public abstract void onTickStart();
    public abstract void onTickEnd();
    public abstract void calculateTarget();

    public NPC getNpc() {
        return npc;
    }

    public void targetToSpeed(double targetX, double targetY) {
        NPC npc = this.getNpc();
        if(Math.abs(targetX - npc.x())>npc.getSpeed() || Math.abs(targetY - npc.y())>npc.getSpeed()) {
            double[] coords = Global.dirToCoor(Global.coorToDir(npc.x(),npc.y(),targetX,targetY));
            npc.setSpeedX(coords[0]*npc.getSpeed());
            npc.setSpeedY(coords[1]*npc.getSpeed());
        }
        else {
            npc.setSpeedX(0);
            npc.setSpeedY(0);
        }
    }
    public double getDistanceFromTarget(double targetX, double targetY) {
        return Global.distance(this.getNpc().x(),this.getNpc().y(),targetX,targetY);
    }
}
