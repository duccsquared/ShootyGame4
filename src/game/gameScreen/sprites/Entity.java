package game.gameScreen.sprites;

import engine.managers.ObjectInstanceManager;
import engine.objects.BaseObject;
import engine.objects.Sprite;
import engine.screens.Screen;
import game.Faction;
import game.Force;
import game.Global;

import java.util.ArrayList;

public class Entity extends Sprite {
    private double speedX = 0;
    private double speedY = 0;
    private Faction faction;
    private ArrayList<Force> forceArray;
    public Entity(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x1, double y1, double x2, double y2) {
        super(screen, r, g, b, borderR, borderG, borderB, x1, y1, x2, y2);
        this.faction = Global.neutralFaction;
        this.init();
    }

    public Entity(Screen screen, String imgPath, double x1, double y1, double x2, double y2) {
        super(screen, imgPath, x1, y1, x2, y2);
        this.faction = Global.neutralFaction;
        this.init();
    }

    public Entity(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x1, double y1, double x2, double y2, Faction faction) {
        super(screen, r, g, b, borderR, borderG, borderB, x1, y1, x2, y2);
        this.faction = faction;
        this.init();
    }

    public Entity(Screen screen, String imgPath, double x1, double y1, double x2, double y2, Faction faction) {
        super(screen, imgPath, x1, y1, x2, y2);
        this.faction = faction;
        this.init();
    }
    protected void init() {
        this.forceArray = new ArrayList<>();
        ObjectInstanceManager.getInstance().addInstance(this,Entity.class);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.forceArray.size()>0) {
            for(Force force: (ArrayList<Force>) this.forceArray.clone()) {
                force.tick();
            }
        }

    }

    @Override
    public void delete() {
        super.delete();
        ObjectInstanceManager.getInstance().removeInstance(this,Entity.class);
    }



    public double getSpeedX() {return speedX;}
    public double getSpeedY() {return speedY;}
    public void setSpeedX(double speedX) {this.speedX = speedX;}
    public void setSpeedY(double speedY) {this.speedY = speedY;}
    public void changeSpeedX(double speedX) {this.setSpeedX(this.getSpeedX()+speedX);}
    public void changeSpeedY(double speedY) {this.setSpeedY(this.getSpeedY()+speedY);}
    public Faction getFaction() {return faction;}
    protected void moveEntity() {
        this.moveX(speedX);
        this.resolveCollisionsFactionDoorX(-speedX);
        this.resolveCollisionsX(Wall.class,-speedX);
        this.moveY(speedY);
        this.resolveCollisionsFactionDoorY(-speedY);
        this.resolveCollisionsY(Wall.class,-speedY);
    }
    public void addForce(double speedX, double speedY) {
        this.forceArray.add(new Force(this,speedX,speedY));
    }
    public void addForceDir(double dir, double magnitude) {
        this.forceArray.add(new Force(magnitude,this,dir));
    }
    public void removeForce(Force force) {
        this.forceArray.remove(force);
    }

    public void resolveCollisionsFactionDoorX(double dirX) {
        ArrayList<BaseObject> objectArray = ObjectInstanceManager.getInstance().getArrayList(FactionDoor.class,this.getScreen());
        if(dirX==0){return;}
        for(BaseObject object: objectArray) {
            FactionDoor factionDoor = (FactionDoor) object;
            if(factionDoor.getFaction()!=this.getFaction()) {
                while(this.intersects(object)) {
                    if(object.equals(this)) {break;}
                    this.moveX(Math.signum(dirX));
                }
            }
        }
    }
    public void resolveCollisionsFactionDoorY(double dirY) {
        ArrayList<BaseObject> objectArray = ObjectInstanceManager.getInstance().getArrayList(FactionDoor.class,this.getScreen());
        if(dirY==0){return;}
        for(BaseObject object: objectArray) {
            FactionDoor factionDoor = (FactionDoor) object;
            if(factionDoor.getFaction()!=this.getFaction()) {
                while (this.intersects(object)) {
                    if (object.equals(this)) {break;}
                    this.moveY(Math.signum(dirY));
                }
            }
        }
    }



}