package game.gameScreen.sprites;

import engine.screens.Screen;
import game.Global;
import game.behaviours.DirectMelee;
import game.behaviours.Wander;
import game.screens.GameScreen;

public class Stabber extends Enemy {
    public static final double HALF_SIZE = 16;
    private boolean attackMode = false;
    public Stabber(Screen screen, double x, double y) {
        super(screen, 250,100,100,250,100,100, x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE);
        this.setSpeed(Global.convertSpeedInSecondsToTicks(50));
        this.setMaxHp(4);
        this.setHp(4);
        this.setBehaviour(new Wander(this));
    }

    @Override
    protected void onTickStart() {
        super.onTickStart();
        if(Global.randInt(0,10)==0) {
            Entity hostile = this.getClosestHostile();
            if(hostile!=null) {
                double dist = Global.distance(this.x(),this.y(),hostile.x(),hostile.y());
                if(dist<250 || attackMode && dist<400) {
                    if(attackMode==false) {
                        this.setBehaviour(new DirectMelee(this,hostile));
                        this.setSpeed(Global.convertSpeedInSecondsToTicks(100));
                        this.attackMode = true;
                    }
                }
                else {
                    if(attackMode==true) {
                        this.setBehaviour(new Wander(this));
                        this.setSpeed(Global.convertSpeedInSecondsToTicks(50));
                        this.attackMode = false;
                    }
                }
            }

        }
    }

    @Override
    protected void calculateTarget() {
        super.calculateTarget();
    }

}