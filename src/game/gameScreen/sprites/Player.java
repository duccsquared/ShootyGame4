package game.gameScreen.sprites;

import engine.screens.Screen;
import engine.utils.Key;
import game.Global;

public class Player extends Entity {
    public static final double HALF_SIZE = 16;
    private double speed = 200/Global.secondsToTicks(1);
    public Player(Screen screen, double x, double y) {
        super(screen, 26,26,26,100,250,100, x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE);
    }

    @Override
    public void tick() {
        super.tick();
        double speedX = 0; double speedY = 0;
        if(Key.held(Key.W)) {speedY -= this.speed;}
        if(Key.held(Key.A)) {speedX -= this.speed;}
        if(Key.held(Key.S)) {speedY += this.speed;}
        if(Key.held(Key.D)) {speedX += this.speed;}
        this.movePos(speedX,speedY);
    }
}
