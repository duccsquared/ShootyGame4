package game.gameScreen.sprites;

import engine.objects.BaseObject;
import engine.objects.Sprite;
import engine.screens.Screen;
import game.gameScreen.grids.RegionGrid;
import game.gameScreen.grids.Tile;
import game.gameScreen.grids.WorldGrid;
import game.screens.GameScreen;

public abstract class GameObject extends Sprite {
    private RegionGrid currentRegion;
    private Tile currentTile;
    private WorldGrid worldGrid;
    private boolean isStatic = true;
    public GameObject(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x1, double y1, double x2, double y2) {
        super(screen, r, g, b, borderR, borderG, borderB, x1, y1, x2, y2);
        this.initGameObject();
    }

    public GameObject(Screen screen, String imgPath, double x1, double y1, double x2, double y2) {
        super(screen, imgPath, x1, y1, x2, y2);
        this.initGameObject();
    }

    protected void initGameObject() {
        worldGrid = ((GameScreen) this.getScreen()).getWorldGrid();
        currentRegion = worldGrid.regionFromCoords(x(),y());
        if(currentRegion==null) {
            worldGrid.instantiateRegionGridFromCoords(x(),y());
            currentRegion = worldGrid.regionFromCoords(x(),y());
            worldGrid.paint();
        }
        currentRegion.addObject(this);
        currentTile = worldGrid.tileFromCoords(x(),y());
        currentTile.addObject(this);
    }

    @Override
    public void setX(double x) {
        super.setX(x); isStatic = false;
    }

    @Override
    public void setY(double y) {
        super.setY(y); isStatic = false;
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width); isStatic = false;
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height); isStatic = false;
    }

    @Override
    public void delete() {
        super.delete();
        currentRegion.removeObject(this);
        currentTile.removeObject(this);
        currentRegion = null;
        currentTile = null;
    }

    @Override
    public void tick() {
        super.tick();
        if(!this.isStatic) {
            RegionGrid nextRegion = currentRegion.getWorldGrid().regionFromCoords(x(),y());
            if(nextRegion==null) {
                this.onOutOfBounds();
            }
            if(!currentRegion.equals(nextRegion)) {
                currentRegion.removeObject(this);
                currentRegion = nextRegion;
                currentRegion.addObject(this);
            }
            Tile nextTile = currentRegion.getWorldGrid().tileFromCoords(x(),y());
            if(!currentTile.equals(nextTile)) {
                currentTile.removeObject(this);
                currentTile = nextTile;
                currentTile.addObject(this);
            }
        }
    }
    protected void onOutOfBounds() {
        this.delete();
    }

    public RegionGrid getCurrentRegion() {return currentRegion;}
    public Tile getCurrentTile() {return currentTile;}
}
