package game.gameScreen.sprites;

import engine.screens.Screen;
import game.Global;

public class Stabber extends Enemy {
    public static final double HALF_SIZE = 16;
    private double ticksToNextTargetSwitch = 0;
    private double ticksSinceLastTargetSwitch = 0;

    public Stabber(Screen screen, double x, double y) {
        super(screen, 26,26,26,250,100,100, x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE);
        this.setSpeed(Global.convertSpeedInSecondsToTicks(100));
    }

    @Override
    protected void onTickStart() {
    }

    @Override
    protected void calculateTarget() {
        this.ticksSinceLastTargetSwitch += 1;
        if(this.getDistanceFromTarget()<50 || ticksSinceLastTargetSwitch>=ticksToNextTargetSwitch) {
            this.setTargetX(Global.randInt(-100,700));
            this.setTargetY(Global.randInt(-100,700));
            this.ticksToNextTargetSwitch = Global.secondsToTicks(Global.randRange(5,8));
            this.ticksSinceLastTargetSwitch = 0;

        }
    }

    @Override
    protected void onTickEnd() {}
}
