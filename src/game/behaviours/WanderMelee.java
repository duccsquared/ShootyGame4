package game.behaviours;

import game.Global;
import game.gameScreen.sprites.Character;
import game.gameScreen.sprites.NPC;

public class WanderMelee extends Behaviour {
    private Behaviour subBehaviour;
    private boolean attackMode = false;
    private double wanderSpeed;
    private double attackSpeed;
    private double aggroRange;
    private double deAggroRange;
    private Character hostile = null;

    public WanderMelee(NPC npc, double wanderSpeed, double attackSpeed, double aggroRange, double deAggroRange) {
        super(npc);
        this.wanderSpeed = wanderSpeed;
        this.attackSpeed = attackSpeed;
        this.aggroRange = aggroRange;
        this.deAggroRange = deAggroRange;
        this.subBehaviour = new Wander(npc);
    }

    @Override
    public void onTickStart() {
        NPC npc = this.getNpc();
        if (Global.randInt(0, 10) == 0) {
            hostile = npc.getClosestHostile();
            if (hostile != null) {
                double dist = Global.distance(npc.x(), npc.y(), hostile.x(), hostile.y());
                this.setAttackMode(dist < this.aggroRange || attackMode && dist < this.deAggroRange);
            } else {
                this.setAttackMode(false);
            }
        }
        this.subBehaviour.onTickStart();
    }

    public void setAttackMode(boolean attackMode) {
        if(attackMode==this.attackMode) {return;}
        this.attackMode = attackMode;
        if(this.attackMode) {
            this.subBehaviour = new DirectMelee(npc, hostile);
            npc.setSpeed(this.attackSpeed);
        }
        else {
            this.subBehaviour = new Wander(npc);
            npc.setSpeed(this.wanderSpeed);
        }


    }

    @Override
    public void onTickEnd() {
        this.subBehaviour.onTickEnd();
    }

    @Override
    public void calculateTarget() {
        this.subBehaviour.calculateTarget();
    }
}
