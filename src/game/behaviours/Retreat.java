package game.behaviours;

import game.Global;
import game.gameScreen.sprites.characters.Character;
import game.gameScreen.sprites.characters.NPC;

public class Retreat extends Behaviour {
    private Character target;

    public Retreat(NPC npc, Character target) {
        super(npc);
        this.target = target;
    }

    public Retreat(NPC npc) {
        super(npc);
        this.target = this.npc.getClosestHostile();
    }

    @Override
    public void onTickStart() {
        if(this.target==null) {
            this.target = npc.getClosestHostile();
        }
        else if(this.target.getHp()<=0) {
            this.target = null;
        }
    }

    @Override
    public void onTickEnd() {
    }

    @Override
    public void calculateTarget() {
        NPC npc = this.getNpc();
        double speedX = 0;
        double speedY = 0;
        if(this.target!=null) {
            double dir = Global.coorToDir(target.x(),target.y(),npc.x(),npc.y());
            double[] coords = Global.dirToCoor(dir);
            speedX = coords[0];
            speedY = coords[1];
            speedX *= npc.getSpeed();
            speedY *= npc.getSpeed();
        }
        npc.setSpeedX(speedX);
        npc.setSpeedY(speedY);
    }
}
