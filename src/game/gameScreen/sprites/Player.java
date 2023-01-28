package game.gameScreen.sprites;

import engine.screens.Screen;
import engine.utils.Key;
import engine.utils.Mouse;
import game.Global;

public class Player extends Character {
    private int cooldown = 0;
    public static final double HALF_SIZE = 16;
    private double speed = Global.convertSpeedInSecondsToTicks(200);
    public Player(Screen screen, double x, double y) {
        super(screen, 100,250,100,100,250,100, x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE,10,Global.playerFaction);    }

    @Override
    public void tick() {
        super.tick();
        this.setSpeedX(0); this.setSpeedY(0);
        if(Key.held(Key.W)) {this.changeSpeedY(-this.speed);}
        if(Key.held(Key.A)) {this.changeSpeedX(-this.speed);}
        if(Key.held(Key.S)) {this.changeSpeedY(this.speed);}
        if(Key.held(Key.D)) {this.changeSpeedX(this.speed);}
        this.moveEntity();
        if(cooldown>0) {cooldown -= 1;}
        if(Key.pressed(Key.O) && cooldown<=0) {
            new Soldier(this.getScreen(),this.x(),this.y());
        }
    }
    public void onLeftHeld() {
        if(cooldown<=0) {
            cooldown = (int) Global.secondsToTicks(0.5);
            new Bullet(this.getScreen(),50,250,50,50,250,50,this.x(),this.y(),Mouse.relMousePosX(this.getScreen()),Mouse.relMousePosY(this.getScreen()),2,this.getFaction());
        }
    }
}
