package game.behaviours;

import game.Global;
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
        NPC npc = this.getNpc();
        if(npc.intersects(target)) {
            target.damage(1);
            double dir = Global.coorToDir(npc.x(),npc.y(),target.x(),target.y());
            target.addForceDir(dir,10);
            npc.addForceDir(dir+180,10);

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
