package game.behaviours;

import game.gameScreen.sprites.characters.Blade;
import game.gameScreen.sprites.characters.NPC;

public class BladeAttackAndRetreat extends Behaviour {
    private Behaviour subBehaviour;
    private Behaviour attackBehaviour;
    private Behaviour retreatBehaviour;
    private Blade blade;
    private boolean attackMode = true;

    public BladeAttackAndRetreat(NPC npc, Behaviour attackBehaviour, Blade blade) {
        super(npc);
        this.attackBehaviour = attackBehaviour;
        this.retreatBehaviour = new Retreat(npc);
        this.blade = blade;
        this.subBehaviour = this.attackBehaviour;
    }

    @Override
    public void onDamage(double hp) {
        super.onDamage(hp);
        this.subBehaviour.onDamage(hp);
    }

    @Override
    public void onTickStart() {
        this.subBehaviour.onTickStart();
        this.setAttackMode(this.blade.isActivated());
    }

    public void setAttackMode(boolean attackMode) {
        if(attackMode==this.attackMode) {return;}
        this.attackMode = attackMode;
        if(this.attackMode) {
            this.subBehaviour = this.attackBehaviour;
        }
        else {
            this.subBehaviour = this.retreatBehaviour;
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
