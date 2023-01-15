package game.gameScreen.sprites;

import engine.objects.BaseObject;
import engine.objects.Sprite;
import engine.screens.Screen;
import game.Faction;
import game.Global;

public class Bullet extends Entity {
    public static final double HALF_SIZE = 8;
    private double startX;
    private double startY;
    private double speed = Global.convertSpeedInSecondsToTicks(300);
    public Bullet(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x, double y, double dir) {
        super(screen, r, g, b, borderR, borderG, borderB, x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE);
        this.init(dir);
    }
    public Bullet(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x, double y, double dir, Faction faction) {
        super(screen, r, g, b, borderR, borderG, borderB, x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE,faction);
        this.init(dir);
    }
    public Bullet(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x, double y, double targetX, double targetY, Faction faction) {
        super(screen, r, g, b, borderR, borderG, borderB, x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE,faction);
        this.init(Global.coorToDir(this.x(),this.y(),targetX,targetY));
    }

    protected void init(double dir) {
        double[] coords = Global.dirToCoor(dir);
        this.startX = this.x();
        this.startY = this.y();
        this.setSpeedX(coords[0]*this.speed);
        this.setSpeedY(coords[1]*this.speed);
    }

    @Override
    public void tick() {
        super.tick();
        this.movePos(this.getSpeedX(),this.getSpeedY());
        BaseObject intersectingWall = this.findIntersecting(Wall.class);
        if(intersectingWall!=null) {
            this.delete();
        }
        Entity intersectingEntity = (Entity) this.findIntersecting(Entity.class);
        if(intersectingEntity!=null && this.getFaction().isHostileTo(intersectingEntity)) {
            this.delete();
            intersectingEntity.delete();
        }
    }
}