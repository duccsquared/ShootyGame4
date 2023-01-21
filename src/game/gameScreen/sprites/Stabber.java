package game.gameScreen.sprites;

import engine.screens.Screen;
import game.Global;
import game.behaviours.Wander;
import game.screens.GameScreen;

public class Stabber extends Enemy {
    public static final double HALF_SIZE = 16;
    private double ticksToNextTargetSwitch = 0;
    private double ticksSinceLastTargetSwitch = 0;

    public Stabber(Screen screen, double x, double y) {
        super(screen, 250,100,100,250,100,100, x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE);
        this.setSpeed(Global.convertSpeedInSecondsToTicks(100));
        this.setBehaviour(new Wander(this));
    }


    @Override
    protected void calculateTarget() {
        super.calculateTarget();
    }

}