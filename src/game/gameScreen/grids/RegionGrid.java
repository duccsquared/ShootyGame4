package game.gameScreen.grids;

import engine.managers.ObjectInstanceManager;
import engine.objects.AABB;
import engine.objects.BaseObject;
import engine.objects.ConcreteSprite;
import engine.skeleton.App;
import game.gameScreen.sprites.GameObject;
import game.grids.FullGrid;
import game.screens.GameScreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public class RegionGrid extends FullGrid<Tile> {
    public final static int WIDTH_COUNT = 32;
    public final static int HEIGHT_COUNT = 32;
    public final static int WIDTH = Tile.WIDTH*WIDTH_COUNT; //1024
    public final static int HEIGHT = Tile.HEIGHT*HEIGHT_COUNT; //1024
    private AABB aabb;
    private WorldGrid worldGrid;
    private GameScreen gameScreen;
    private ArrayList<ConcreteSprite> drawnGridArray = new ArrayList<>();
    LinkedList<GameObject> objectList = new LinkedList<>();
    public RegionGrid(GameScreen gameScreen, WorldGrid worldGrid, double worldX, double worldY) {
        super(0,0,WIDTH_COUNT-1,HEIGHT_COUNT-1);
        this.gameScreen = gameScreen;
        this.worldGrid = worldGrid;
        this.aabb = new AABB();
        this.aabb.setX1(worldX*WIDTH);
        this.aabb.setY1(worldY*HEIGHT);
        this.aabb.setX2((worldX+1)*WIDTH);
        this.aabb.setY2((worldY+1)*HEIGHT);
        for(int i = 0; i < WIDTH_COUNT; i++) {
            for(int j = 0; j < HEIGHT_COUNT; j++) {
                this.set(i,j,new Tile(this,i,j));
            }
        }
    }
    public double xFromRegionX(int regionX) {return this.x1() + regionX*Tile.WIDTH;}
    public double yFromRegionY(int regionY) {return this.y1() + regionY*Tile.WIDTH;}
    public int regionXFromX(double x) {return (int) Math.floor((x - this.x1())/Tile.WIDTH);}
    public int regionYFromY(double y) {return (int) Math.floor((y - this.y1())/Tile.HEIGHT);}

    public double xFromWorldX(int worldX) {return worldGrid.xFromWorldX(worldX);}
    public double yFromWorldY(int worldY) {return worldGrid.yFromWorldY(worldY);}
    public int worldXFromX(double x) {return worldGrid.worldXFromX(x);}
    public int worldYFromY(double y) {return worldGrid.worldYFromY(y);}

    public Tile tileFromCoords(double x, double y) {
        return this.get(regionXFromX(x),regionYFromY(y));
    }

    public int worldX() {return worldGrid.worldXFromX(this.x1());}
    public int worldY() {return worldGrid.worldYFromY(this.y1());}

    public double x() {return aabb.x();}
    public double y() {return aabb.y();}
    public double x1() {return aabb.x1();}
    public double y1() {return aabb.y1();}
    public double x2() {return aabb.x2();}
    public double y2() {return aabb.y2();}
    public double width() {return aabb.width();}
    public double height() {return aabb.height();}

    public WorldGrid getWorldGrid() {return worldGrid;}

    public void addObject(GameObject gameObject) {this.objectList.add(gameObject);}
    public void removeObject(GameObject gameObject) {this.objectList.remove(gameObject);}

    public void actionPerformed(ActionEvent e) {
        LinkedList<GameObject> tempObjectList = (LinkedList<GameObject>) objectList.clone();
        for(GameObject object : tempObjectList) {
            object.tick();
        }
    }
    public boolean handleMouseClick() {
        boolean handled = false;
        for(int i = objectList.size()-1; i >= 0; i--) {
            GameObject object = objectList.get(i);
            if(object.intersectsAbs(App.getInstance().getMousePosX(),App.getInstance().getMousePosY())) {
                handled = object.tickMouse();
            }
            if(handled) {return true;}
            for(BaseObject child : object.getChildList()) {
                if(child.intersectsAbs(App.getInstance().getMousePosX(),App.getInstance().getMousePosY())) {
                    handled = child.tickMouse();
                }
                if(handled) {return true;}
            }

        }
        return false;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for(ConcreteSprite object : drawnGridArray) {
            object.paint(g2d);
        }
        for(GameObject object : objectList) {
            object.paint(g2d);
        }

    }

    public void paint() {
        this.drawGridLines();
    }
    public void drawGridLines() {
        drawnGridArray.clear();
        for(int i = this.getXMin(); i <= this.getXMax(); i++) {
            for(int j = this.getYMin(); j <= this.getYMax(); j++) {
                double lowX = xFromRegionX(i);
                double lowY = yFromRegionY(j);
                double highX = xFromRegionX(i+1);
                double highY = yFromRegionY(j+1);
                int tint = 20;
                drawnGridArray.add(new ConcreteSprite(gameScreen,tint,tint,tint,tint,tint,tint,lowX,lowY-1,highX,lowY+1));
                drawnGridArray.add(new ConcreteSprite(gameScreen,tint,tint,tint,tint,tint,tint,lowX-1,lowY,lowX+1,highY));
                drawnGridArray.add(new ConcreteSprite(gameScreen,tint,tint,tint,tint,tint,tint,lowX,highY-1,highX,highY+1));
                drawnGridArray.add(new ConcreteSprite(gameScreen,tint,tint,tint,tint,tint,tint,highX-1,lowY,highX+1,highY));
            }
        }
    }
}
