package game.gameScreen.sprites;

import engine.managers.ObjectInstanceManager;
import engine.screens.Screen;
import game.Global;

public abstract class Enemy extends Entity {
    private double currentTargetX;
    private double currentTargetY;
    private double targetX;
    private double targetY;
    private double speed;
    public Enemy(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x1, double y1, double x2, double y2) {
        super(screen, r, g, b, borderR, borderG, borderB, x1, y1, x2, y2,Global.enemyFaction);
        this.targetX = this.x();
        this.targetY = this.y();
        this.currentTargetX = this.targetX;
        this.currentTargetY = this.targetY;
        ObjectInstanceManager.getInstance().addInstance(this,Enemy.class);
    }
    @Override
    public void delete() {
        super.delete();
        ObjectInstanceManager.getInstance().removeInstance(this,Enemy.class);
    }

    public double getSpeed() {return speed;}
    public double getTargetX() {return targetX;}
    public double getTargetY() {return targetY;}
    public void setSpeed(double speed) {this.speed = speed;}
    public void setTargetX(double targetX) {this.targetX = targetX;}
    public void setTargetY(double targetY) {this.targetY = targetY;}

    @Override
    public void tick() {
        super.tick();
        this.onTickStart();
        this.calculateTarget();
        this.moveEntity();
        onTickEnd();
    }
    protected abstract void onTickStart();
    protected abstract void calculateTarget();

    @Override
    protected void moveEntity() {
        if(this.targetX!=this.currentTargetX || this.targetY!=this.currentTargetY) {
            this.currentTargetX = this.targetX;
            this.currentTargetY = this.targetY;
            double[] coords = Global.dirToCoor(Global.coorToDir(this.x(),this.y(),this.targetX,this.targetY));
            this.setSpeedX(coords[0]*this.getSpeed());
            this.setSpeedY(coords[1]*this.getSpeed());
        }
        super.moveEntity();
    }

    protected abstract void onTickEnd();

    protected double getDistanceFromTarget() {
        return Global.distance(this.x(),this.y(),this.getTargetX(),this.getTargetY());
    }
}