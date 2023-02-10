package game.behaviours;

import game.Global;
import game.gameScreen.sprites.characters.Character;
import game.gameScreen.sprites.characters.NPC;

public class IdleAttackInRange extends Behaviour {
    private Behaviour subBehaviour;
    private boolean attackMode = false;
    private Behaviour idleBehaviour;
    private Behaviour attackBehaviour;
    private double idleSpeed;
    private double attackSpeed;
    private double aggroRange;
    private double deAggroRange;

    public IdleAttackInRange(NPC npc, Behaviour idleBehaviour, Behaviour attackBehaviour, double idleSpeed, double attackSpeed, double aggroRange, double deAggroRange) {
        super(npc);
        this.idleBehaviour = idleBehaviour;
        this.attackBehaviour = attackBehaviour;
        this.idleSpeed = idleSpeed;
        this.attackSpeed = attackSpeed;
        this.aggroRange = aggroRange;
        this.deAggroRange = deAggroRange;
        this.subBehaviour = this.idleBehaviour;
    }

    @Override
    public void onDamage(double hp) {
        super.onDamage(hp);
        this.subBehaviour.onDamage(hp);
        this.setAttackMode(true);
    }

    @Override
    public void onTickStart() {
        NPC npc = this.getNpc();
        if (Global.randInt(0, 10) == 0) {
            Character hostile = npc.getClosestHostile();
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
            this.subBehaviour = this.attackBehaviour;
            npc.setSpeed(this.attackSpeed);
        }
        else {
            this.subBehaviour = this.idleBehaviour;
            npc.setSpeed(this.idleSpeed);
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
