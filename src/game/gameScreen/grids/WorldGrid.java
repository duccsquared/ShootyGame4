package game.gameScreen.grids;

import engine.objects.ConcreteSprite;
import game.gameScreen.sprites.GameObject;
import game.gameScreen.sprites.Player;
import game.grids.SparseGrid;
import game.screens.GameScreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class WorldGrid extends SparseGrid<RegionGrid> {
    private static final int RENDER_DISTANCE = 1;
    private GameScreen gameScreen;
    private ArrayList<ConcreteSprite> drawnGridArray = new ArrayList<>();
    public WorldGrid(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        instantiateRegionGrid(0,0);
    }

    public double xFromWorldX(int worldX) {return worldX*RegionGrid.WIDTH;}
    public double yFromWorldY(int worldY) {return worldY*RegionGrid.WIDTH;}
    public int worldXFromX(double x) {return (int) Math.floor(x/RegionGrid.WIDTH);}
    public int worldYFromY(double y) {return (int) Math.floor(y/RegionGrid.HEIGHT);}

    public double xFromGlobalRegionX(int regionX) {return regionX*Tile.WIDTH;}
    public double yFromGlobalRegionY(int regionY) {return regionY*Tile.WIDTH;}
    public int globalRegionXFromX(double x) {return (int) Math.floor(x/Tile.WIDTH);}
    public int globalRegionYFromY(double y) {return (int) Math.floor(y/Tile.HEIGHT);}

    public int globalRegionXFromWorldX(int worldX) {return globalRegionXFromX(xFromWorldX(worldX));}
    public int globalRegionYFromWorldY(int worldY) {return globalRegionYFromY(yFromWorldY(worldY));}
    public int worldXFromGlobalRegionX(int regionX) {return worldXFromX(xFromGlobalRegionX(regionX));}
    public int worldYFromGlobalRegionY(int regionY) {return worldYFromY(yFromGlobalRegionY(regionY));}

    public RegionGrid regionFromCoords(double x, double y) {
        return this.get(worldXFromX(x),worldYFromY(y));
    }
    public Tile tileFromCoords(double x, double y) {
        return this.regionFromCoords(x,y).tileFromCoords(x,y);
    }

    public void tick() {
        boolean paintGrid = false;
        Player player = gameScreen.getPlayer();
        int worldX = this.worldXFromX(player.x());
        int worldY = this.worldYFromY(player.y());
        for(int i = worldX - 1; i <= worldX + 1; i++) {
            for(int j = worldY - 1; j <= worldY + 1; j++) {
                if(this.instantiateIfNull(i,j)){paintGrid = true;};
            }
        }
        if(paintGrid) {
            this.paint();
        }
    }
    public void actionPerformed(ActionEvent e) {
        int worldX = worldXFromX(gameScreen.getPlayer().x());
        int worldY = worldYFromY(gameScreen.getPlayer().y());

        for(int i = worldX-RENDER_DISTANCE; i <= worldX+RENDER_DISTANCE; i++) {
            for(int j = worldY-RENDER_DISTANCE; j <= worldY+RENDER_DISTANCE; j++) {
                RegionGrid regionGrid = this.get(i,j);
                if(regionGrid != null) {
                    this.get(i,j).actionPerformed(e);
                }
            }
        }
    }
    public boolean handleMouseClick() {
        boolean handled = false;
        int worldX = worldXFromX(gameScreen.getPlayer().x());
        int worldY = worldYFromY(gameScreen.getPlayer().y());

        for(int i = worldX-RENDER_DISTANCE; i <= worldX+RENDER_DISTANCE; i++) {
            for(int j = worldY-RENDER_DISTANCE; j <= worldY+RENDER_DISTANCE; j++) {
                RegionGrid regionGrid = this.get(i,j);
                if(regionGrid != null) {
                    handled = this.get(i,j).handleMouseClick();
                }
                if(handled) {return true;}
            }
        }
        return false;
    }
    public void paintComponent(Graphics g) {
        int worldX = worldXFromX(gameScreen.getPlayer().x());
        int worldY = worldYFromY(gameScreen.getPlayer().y());
        for(int i = worldX-RENDER_DISTANCE; i <= worldX+RENDER_DISTANCE; i++) {
            for(int j = worldY-RENDER_DISTANCE; j <= worldY+RENDER_DISTANCE; j++) {
                RegionGrid regionGrid = this.get(i,j);
                if(regionGrid != null) {
                    this.get(i,j).paintComponent(g);
                }
            }
        }
        Graphics2D g2d = (Graphics2D) g;
        for(ConcreteSprite object : drawnGridArray) {
            object.paint(g2d);
        }
    }
    public boolean instantiateIfNull(int worldX, int worldY) {
        if(this.get(worldX,worldY)==null) {
            this.instantiateRegionGrid(worldX,worldY);
            return true;
        }
        return false;
    }
    public void instantiateRegionGridFromCoords(double x, double y) {
        int worldX = worldXFromX(x);
        int worldY = worldYFromY(y);
        instantiateRegionGrid(worldX,worldY);
    }
    public void instantiateRegionGrid(int worldX, int worldY) {
        this.set(worldX,worldY,new RegionGrid(gameScreen,this,worldX,worldY));
    }

    public void paint() {
        this.drawGridLines();
    }
    public void drawGridLines() {
        drawnGridArray.clear();
        for(RegionGrid regionGrid : this.nonNullIterator()) {
            regionGrid.paint();
        }
        for(int i = this.getXMin(); i <= this.getXMax(); i++) {
            for(int j = this.getYMin(); j <= this.getYMax(); j++) {
                double lowX = xFromWorldX(i);
                double lowY = yFromWorldY(j);
                double highX = xFromWorldX(i+1);
                double highY = yFromWorldY(j+1);
                drawnGridArray.add(new ConcreteSprite(gameScreen,255,255,255,255,255,255,lowX,lowY-1,highX,lowY+1));
                drawnGridArray.add(new ConcreteSprite(gameScreen,255,255,255,255,255,255,lowX-1,lowY,lowX+1,highY));
                drawnGridArray.add(new ConcreteSprite(gameScreen,255,255,255,255,255,255,lowX,highY-1,highX,highY+1));
                drawnGridArray.add(new ConcreteSprite(gameScreen,255,255,255,255,255,255,highX-1,lowY,highX+1,highY));
            }
        }
    }

}
