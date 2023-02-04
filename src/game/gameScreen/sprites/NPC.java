package game.gameScreen.sprites;

import engine.managers.ObjectInstanceManager;
import engine.screens.Screen;
import game.Faction;
import game.Global;
import game.behaviours.Behaviour;

public class NPC extends Character {
    private double speed;
    private Behaviour behaviour = null;
    public NPC(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x1, double y1, double x2, double y2,double maxHp, double speed, Faction faction) {
        super(screen, r, g, b, borderR, borderG, borderB, x1, y1, x2, y2, maxHp, faction);
        this.speed = speed;
        ObjectInstanceManager.getInstance().addInstance(this,Enemy.class);
    }
    public NPC(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x1, double y1, double x2, double y2,double maxHp, double speed,double contactDamage, Faction faction) {
        super(screen, r, g, b, borderR, borderG, borderB, x1, y1, x2, y2, maxHp,contactDamage, faction);
        this.speed = speed;
        ObjectInstanceManager.getInstance().addInstance(this,Enemy.class);
    }
    @Override
    public void delete() {
        super.delete();
        ObjectInstanceManager.getInstance().removeInstance(this,Enemy.class);
    }

    public double getSpeed() {return speed;}
    public Behaviour getBehaviour() {return behaviour;}
    public void setSpeed(double speed) {this.speed = speed;}
    public void setBehaviour(Behaviour behaviour) {this.behaviour = behaviour;}
    @Override
    public void tick() {
        super.tick();
        this.onTickStart();
        this.calculateTarget();
        this.moveEntity();
        this.onTickEnd();
    }
    protected void onTickStart() {
        if(this.behaviour!=null) {this.behaviour.onTickStart();}
    }
    protected void calculateTarget() {
        if(this.behaviour!=null) {this.behaviour.calculateTarget();}
    }

    protected void onTickEnd() {
        if(this.behaviour!=null) {this.behaviour.onTickEnd();}
    }

    @Override
    public void damage(double hp) {
        super.damage(hp);
        if(this.behaviour!=null) {this.behaviour.onDamage(hp);}
    }
}
