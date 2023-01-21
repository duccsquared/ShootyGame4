package game.behaviours;

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
}
