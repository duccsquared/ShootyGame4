package game.screens;

import engine.screens.BaseScreen;
import engine.utils.Mouse;
import game.Faction;
import game.Global;
import game.gameScreen.sprites.Player;
import game.gameScreen.sprites.Stabber;
import game.gameScreen.sprites.Wall;

public class GameScreen extends BaseScreen {
    public static String id = "gameWorld";
    Player player;
    public GameScreen() {
        super(id);
        player = new Player(this,300,300);
        for(int i = 0; i < 20; i++) {
            int posX = Global.randInt(-4,24);
            int posY = Global.randInt(-4,24);
            new Wall(this,Wall.wallCoordToCoord(posX),Wall.wallCoordToCoord(posY));
        }
        new Stabber(this,200,200);
        this.getCamera().attachObject(player);
        Faction.setAsHostile(Global.playerFaction,Global.enemyFaction);
//        for(int x = -4; x <= 24; x++) {
//            new Wall(this,Wall.wallCoordToCoord(x),Wall.wallCoordToCoord(-4));
//            new Wall(this,Wall.wallCoordToCoord(x),Wall.wallCoordToCoord(24));
//        }
        new Wall(this,
                Wall.wallCoordToCoord(-24)-Wall.HALF_SIZE,
                Wall.wallCoordToCoord(-24)-Wall.HALF_SIZE,
                Wall.wallCoordToCoord(-24)+Wall.HALF_SIZE,
                Wall.wallCoordToCoord(24)+Wall.HALF_SIZE);
        new Wall(this,
                Wall.wallCoordToCoord(24)-Wall.HALF_SIZE,
                Wall.wallCoordToCoord(-24)-Wall.HALF_SIZE,
                Wall.wallCoordToCoord(24)+Wall.HALF_SIZE,
                Wall.wallCoordToCoord(24)+Wall.HALF_SIZE);
        new Wall(this,
                Wall.wallCoordToCoord(-24)-Wall.HALF_SIZE,
                Wall.wallCoordToCoord(-24)-Wall.HALF_SIZE,
                Wall.wallCoordToCoord(24)+Wall.HALF_SIZE,
                Wall.wallCoordToCoord(-24)+Wall.HALF_SIZE);
        new Wall(this,
                Wall.wallCoordToCoord(-24)-Wall.HALF_SIZE,
                Wall.wallCoordToCoord(24)-Wall.HALF_SIZE,
                Wall.wallCoordToCoord(24)+Wall.HALF_SIZE,
                Wall.wallCoordToCoord(24)+Wall.HALF_SIZE);
//        for(int y = -4; y <= 24; y++) {
//            new Wall(this,Wall.wallCoordToCoord(-4),Wall.wallCoordToCoord(y));
//            new Wall(this,Wall.wallCoordToCoord(24),Wall.wallCoordToCoord(y));
//        }
    }


    @Override
    public boolean onUnhandledMouseClick() {
        if (Mouse.leftHeld()) {
            player.onLeftHeld();
            return true;
        }
        return false;
    }
}
