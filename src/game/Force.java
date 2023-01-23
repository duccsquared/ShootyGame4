package game;

import game.gameScreen.sprites.Entity;
import game.gameScreen.sprites.Wall;

public class Force {
    private double decelerationRate = 0.8;
    private Entity entity;
    private double speedX;
    private double speedY;
    public Force(Entity entity, double speedX, double speedY) {
        this.entity = entity;
        this.speedX = speedX;
        this.speedY = speedY;
    }
    public Force(double magnitude, Entity entity, double dir) {
        this.entity = entity;
        double[] coords = Global.dirToCoor(dir);
        this.speedX = coords[0] * magnitude;
        this.speedY = coords[1] * magnitude;
    }

    public void tick() {
        this.entity.moveX(speedX);
        this.entity.resolveCollisionsX(Wall.class,-speedX);
        this.entity.moveY(speedY);
        this.entity.resolveCollisionsY(Wall.class,-speedY);

        this.speedX *= this.decelerationRate;
        this.speedY *= this.decelerationRate;
        if(Math.abs(this.speedX)<=1 && Math.abs(this.speedY)<=1) {
            this.delete();
        }
    }

    public void delete() {
        this.entity.removeForce(this);
    }
}
