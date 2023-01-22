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
    private double damage;
    public Bullet(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x, double y, double dir, Faction faction) {
        super(screen, r, g, b, borderR, borderG, borderB, x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE,faction);
        this.init(dir,1);
    }
    public Bullet(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x, double y, double targetX, double targetY, Faction faction) {
        super(screen, r, g, b, borderR, borderG, borderB, x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE,faction);
        this.init(Global.coorToDir(this.x(),this.y(),targetX,targetY),1);
    }
    public Bullet(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x, double y, double targetX, double targetY, double damage,Faction faction) {
        super(screen, r, g, b, borderR, borderG, borderB, x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE,faction);
        this.init(Global.coorToDir(this.x(),this.y(),targetX,targetY),damage);
    }

    protected void init(double dir, double damage) {
        double[] coords = Global.dirToCoor(dir);
        this.startX = this.x();
        this.startY = this.y();
        this.setSpeedX(coords[0]*this.speed);
        this.setSpeedY(coords[1]*this.speed);
        this.damage = damage;
    }

    @Override
    public void tick() {
        super.tick();
        this.movePos(this.getSpeedX(),this.getSpeedY());
        BaseObject intersectingWall = this.findIntersecting(Wall.class);
        if(intersectingWall!=null) {
            this.delete();
        }
        Character intersectingChara = (Character) this.findIntersecting(Character.class);
        if(intersectingChara!=null && this.getFaction().isHostileTo(intersectingChara)) {
            this.delete();
            intersectingChara.damage(this.damage);
        }
    }
}