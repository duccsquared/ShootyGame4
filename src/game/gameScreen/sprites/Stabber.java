package game.gameScreen.sprites;

import engine.screens.Screen;
import game.Global;

public class Stabber extends Enemy {
    public static final double HALF_SIZE = 16;

    public Stabber(Screen screen, double x, double y) {
        super(screen, 26,26,26,250,100,100, x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE);
        this.setSpeed(Global.convertSpeedInSecondsToTicks(100));
    }

    @Override
    protected void onTickStart() {
    }

    @Override
    protected void calculateTarget() {
        if(this.getDistanceFromTarget()<50) {
            this.setTargetX(Global.randInt(-100,700));
            this.setTargetY(Global.randInt(-100,700));
        }
    }

    @Override
    protected void onTickEnd() {}
}
