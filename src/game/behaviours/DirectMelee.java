package game.behaviours;

import game.Global;
import game.gameScreen.sprites.Character;
import game.gameScreen.sprites.Entity;
import game.gameScreen.sprites.NPC;

public class DirectMelee extends Behaviour {
    private double currentTargetX;
    private double currentTargetY;
    private double targetX;
    private double targetY;
    protected Character target;
    public DirectMelee(NPC npc,Character target) {
        super(npc);
        this.target = target;
        this.targetX = npc.x();
        this.targetY = npc.y();
        this.currentTargetX = this.targetX;
        this.currentTargetY = this.targetY;
    }
    public DirectMelee(NPC npc) {
        super(npc);
        this.target = this.getNpc().getClosestHostile();
        this.targetX = npc.x();
        this.targetY = npc.y();
        this.currentTargetX = this.targetX;
        this.currentTargetY = this.targetY;
    }

    public Character getTarget() {
        return target;
    }

    public void setTargetX(double targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(double targetY) {
        this.targetY = targetY;
    }

    @Override
    public void onDamage(double hp) {
        super.onDamage(hp);
        if(hp>=1 || Global.randInt(0,4)==0) {this.target = this.getNpc().getClosestHostile();};
    }

    @Override
    public void onTickStart() {
        NPC npc = this.getNpc();
        if(this.target==null) {
            this.target = npc.getClosestHostile();
        }
        else if(this.target.getHp()<=0) {
            this.target = null;
        }
        else if(npc.intersects(target)) {
            target.damage(npc.getContactDamage());
            npc.damage(target.getContactDamage());
            double dir = Global.coorToDir(npc.x(),npc.y(),target.x(),target.y());
            target.addForceDir(dir,10);
            npc.addForceDir(dir+180,10);

        }
        if(this.currentTargetX!=this.targetX || this.currentTargetY!=this.targetY || this.target==null) {
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
        if(this.target!=null) {
            this.setTargetX(this.target.x());
            this.setTargetY(this.target.y());
        }
        else {
            this.setTargetX(npc.x());
            this.setTargetY(npc.y());
        }
    }
}
