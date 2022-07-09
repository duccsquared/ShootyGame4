package game.gameScreen.sprites;

import engine.screens.Screen;
import engine.utils.Key;
import game.Global;

public class Player extends Entity {
    public static final double HALF_SIZE = 16;
    private double speed = Global.convertSpeedInSecondsToTicks(200);
    public Player(Screen screen, double x, double y) {
        super(screen, 26,26,26,100,250,100, x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE);
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpeedX(0); this.setSpeedY(0);
        if(Key.held(Key.W)) {this.changeSpeedY(-this.speed);}
        if(Key.held(Key.A)) {this.changeSpeedX(-this.speed);}
        if(Key.held(Key.S)) {this.changeSpeedY(this.speed);}
        if(Key.held(Key.D)) {this.changeSpeedX(this.speed);}
        this.moveEntity();
    }
}
