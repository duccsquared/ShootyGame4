package game.screens;

import engine.objects.BaseObject;
import engine.objects.ConcreteSprite;
import engine.objects.Sprite;
import engine.screens.BaseScreen;
import engine.screens.SubScreen;
import engine.skeleton.App;
import engine.utils.Mouse;
import game.Faction;
import game.Global;
import game.gameScreen.FPSCounter;
import game.gameScreen.grids.WorldGrid;
import game.gameScreen.sprites.Player;
import game.gameScreen.sprites.Stabber;
import game.gameScreen.sprites.Wall;

import java.awt.*;
import java.awt.event.ActionEvent;

public class GameScreen extends BaseScreen {
    public static String id = "gameWorld";
    private WorldGrid worldGrid;
    Player player;
    FPSCounter fpsCounter;
    public GameScreen() {
        super(id);
        this.fpsCounter = new FPSCounter(this);
        this.worldGrid = new WorldGrid(this);
        player = new Player(this,300,300);
        for(int i = 0; i < 50; i++) {
            double posX = Global.randInt(-24,24);
            double posY = Global.randInt(-24,24);
            new Wall(this,2*Wall.HALF_SIZE*posX+Wall.HALF_SIZE,2*Wall.HALF_SIZE*posY+Wall.HALF_SIZE);
        }
        new Stabber(this,200,200);
        new Stabber(this,200,400);
        new Stabber(this,400,200);
        new Stabber(this,400,400);
        this.getCamera().attachObject(player);
        Faction.setAsHostile(Global.playerFaction,Global.enemyFaction);
    this.worldGrid.paint();
    }

    public Player getPlayer() {return player;}
    public WorldGrid getWorldGrid() {return worldGrid;}

    @Override
    public void tick() {
        super.tick();
        worldGrid.tick();
        fpsCounter.tick();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //super.actionPerformed(e); // WorldGrid handles this instead
        worldGrid.actionPerformed(e);
        for(SubScreen subScreen: this.getSubScreenArray()) {subScreen.actionPerformed(e);}
        this.handleMouseClick();
    }

    @Override
    public void paintComponent(Graphics g) {
        //super.paintComponent(g); // WorldGrid handles this instead
        worldGrid.paintComponent(g);
        for(SubScreen subScreen : getSubScreenArray()) {subScreen.paintComponent(g);}
        fpsCounter.getFpsText().paint((Graphics2D) g);
    }

    @Override
    public boolean handleMouseClick() {
        boolean handled = false;
        if(getDragManager().getObject()!=null) {return true;}
        for(int i = getSubScreenArray().size()-1; i>=0; i--) {
            SubScreen subScreen = getSubScreenArray().get(i);
            handled = subScreen.handleMouseClick();
            if (handled) {return true;}
        }
        handled = worldGrid.handleMouseClick();
        if(handled) {return true;}
        return onUnhandledMouseClick();
    }

    @Override
    public boolean onUnhandledMouseClick() {
        if(Mouse.leftHeld()) {
            player.onLeftHeld();
            return true;
        }
        return false;
    }
}
