package game.gameScreen.sprites;

import engine.objects.Sprite;
import engine.screens.Screen;

public class Entity extends Sprite {
    private double speedX = 0;
    private double speedY = 0;
    public Entity(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x1, double y1, double x2, double y2) {
        super(screen, r, g, b, borderR, borderG, borderB, x1, y1, x2, y2);
    }

    public Entity(Screen screen, String imgPath, double x1, double y1, double x2, double y2) {
        super(screen, imgPath, x1, y1, x2, y2);
    }
    public double getSpeedX() {return speedX;}
    public double getSpeedY() {return speedY;}
    public void setSpeedX(double speedX) {this.speedX = speedX;}
    public void setSpeedY(double speedY) {this.speedY = speedY;}
    public void changeSpeedX(double speedX) {this.setSpeedX(this.getSpeedX()+speedX);}
    public void changeSpeedY(double speedY) {this.setSpeedY(this.getSpeedY()+speedY);}

    protected void moveEntity() {
        this.moveX(speedX);
        this.resolveCollisionsX(Wall.class,-speedX);
        this.moveY(speedY);
        this.resolveCollisionsY(Wall.class,-speedY);
    }

}
