package game.behaviours;

import game.gameScreen.sprites.Entity;
import game.gameScreen.sprites.NPC;

public class DirectMelee extends Behaviour {
    protected Entity target;
    public DirectMelee(NPC npc,Entity target) {
        super(npc);
        this.target = target;
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
        npc.setTargetX(this.target.x());
        npc.setTargetY(this.target.y());
    }
}
