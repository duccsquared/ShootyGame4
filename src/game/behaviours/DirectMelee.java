package game.behaviours;

import game.gameScreen.sprites.Character;
import game.gameScreen.sprites.Entity;
import game.gameScreen.sprites.NPC;

public class DirectMelee extends Behaviour {
    protected Character target;
    public DirectMelee(NPC npc,Character target) {
        super(npc);
        this.target = target;
    }

    @Override
    public void onTickStart() {
        if(this.getNpc().intersects(target)) {
            target.damage(0.1);
        }
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
