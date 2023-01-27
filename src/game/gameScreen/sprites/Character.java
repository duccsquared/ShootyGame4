package game.gameScreen.sprites;

import engine.managers.ObjectInstanceManager;
import engine.objects.BaseObject;
import engine.screens.Screen;
import game.Faction;
import game.Global;

import java.awt.*;
import java.util.ArrayList;

public class Character extends Entity {
    private double maxHp = 1;
    private double hp = 1;
    protected HPBar hpBar;


    public Character(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x1, double y1, double x2, double y2, double maxHp) {
        super(screen, r, g, b, borderR, borderG, borderB, x1, y1, x2, y2);
        this.init2(maxHp);
    }

    public Character(Screen screen, String imgPath, double x1, double y1, double x2, double y2, double maxHp) {
        super(screen, imgPath, x1, y1, x2, y2);
        this.init2(maxHp);
    }

    public Character(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x1, double y1, double x2, double y2, double maxHp, Faction faction) {
        super(screen, r, g, b, borderR, borderG, borderB, x1, y1, x2, y2, faction);
        this.init2(maxHp);
    }

    public Character(Screen screen, String imgPath, double x1, double y1, double x2, double y2, double maxHp, Faction faction) {
        super(screen, imgPath, x1, y1, x2, y2, faction);
        this.init2(maxHp);
    }

    protected void init2(double maxHp) {
        this.hpBar = new HPBar(this);
        this.setMaxHp(maxHp);
        this.setHp(maxHp);
        ObjectInstanceManager.getInstance().addInstance(this,Character.class);
    }

    @Override
    public void delete() {
        super.delete();
        this.hpBar.delete();
        ObjectInstanceManager.getInstance().removeInstance(this,Character.class);
    }

    public double getMaxHp() {return maxHp;}
    public double getHp() {return hp;}
    public void setMaxHp(double maxHp) {this.maxHp = maxHp;}
    public void setHp(double hp) {this.hp = hp;}
    public void damage(double hp) {this.hp -= hp;}
    public void heal(double hp) {this.hp += hp;}

    @Override
    public void tick() {
        super.tick();
        if(this.getHp()<=0) {
            this.delete();
        }
    }

    @Override
    public void paint(Graphics2D g2d) {
        this.hpBar.tick();
        super.paint(g2d);
    }

    public Character getClosestHostile() {
        ArrayList<BaseObject> entityList = ObjectInstanceManager.getInstance().getArrayList(Character.class);
        Character closest = null;
        double distance = 99999999;
        for(BaseObject object : entityList) {
            Character chara = (Character) object;
            if(chara.getFaction().isHostileTo(this.getFaction())) {
                double calcDist = Global.distance(this.x(),this.y(),chara.x(),chara.y());
                if(calcDist<distance) {
                    closest = chara;
                    distance = calcDist;
                }
            }
        }
        return closest;
    }
}
