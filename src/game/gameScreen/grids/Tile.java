package game.gameScreen.grids;

import engine.objects.AABB;
import engine.objects.BaseObject;
import game.gameScreen.sprites.GameObject;

import java.util.LinkedList;

public class Tile {
    public final static int WIDTH = 32;
    public final static int HEIGHT = 32;
    private AABB aabb;
    RegionGrid regionGrid;
    LinkedList<GameObject> objectList = new LinkedList<>();
    public Tile(RegionGrid regionGrid,double regionX, double regionY) {
        this.regionGrid = regionGrid;
        this.aabb = new AABB();
        this.aabb.setX1(regionGrid.x() + regionX*WIDTH);
        this.aabb.setY1(regionGrid.y() + regionY*HEIGHT);
        this.aabb.setX2(regionGrid.x() + (regionX+1)*WIDTH);
        this.aabb.setY2(regionGrid.y() + (regionY+1)*HEIGHT);
    }
    public double x() {return aabb.x();}
    public double y() {return aabb.y();}
    public double x1() {return aabb.x1();}
    public double y1() {return aabb.y1();}
    public double x2() {return aabb.x2();}
    public double y2() {return aabb.y2();}
    public double width() {return aabb.width();}
    public double height() {return aabb.height();}

    public int worldX() {return this.regionGrid.worldX();}
    public int worldY() {return this.regionGrid.worldY();}

    public int regionX() {return regionGrid.regionXFromX(this.x1());}
    public int regionY() {return regionGrid.regionYFromY(this.y1());}

    public RegionGrid getRegionGrid() {return regionGrid;}
    public WorldGrid getWorldGrid() {return regionGrid.getWorldGrid();}

    public void addObject(GameObject gameObject) {this.objectList.add(gameObject);}
    public void removeObject(GameObject gameObject) {this.objectList.remove(gameObject);}

}
