package game.screens;

import engine.screens.BaseScreen;
import game.Global;
import game.gameScreen.sprites.Player;
import game.gameScreen.sprites.Wall;

public class GameScreen extends BaseScreen {
    public static String id = "gameWorld";
    Player player;
    public GameScreen() {
        super(id);
        player = new Player(this,300,300);
        for(int i = 0; i < 20; i++) {
            double posX = Global.randInt(-4,24);
            double posY = Global.randInt(-4,24);
            new Wall(this,2*Wall.HALF_SIZE*posX+Wall.HALF_SIZE,2*Wall.HALF_SIZE*posY+Wall.HALF_SIZE);
        }
        new Wall(this,2*Wall.HALF_SIZE*-4+Wall.HALF_SIZE,2*Wall.HALF_SIZE*-4+Wall.HALF_SIZE);
        new Wall(this,2*Wall.HALF_SIZE*24+Wall.HALF_SIZE,2*Wall.HALF_SIZE*-4+Wall.HALF_SIZE);
        new Wall(this,2*Wall.HALF_SIZE*-4+Wall.HALF_SIZE,2*Wall.HALF_SIZE*24+Wall.HALF_SIZE);
        new Wall(this,2*Wall.HALF_SIZE*24+Wall.HALF_SIZE,2*Wall.HALF_SIZE*24+Wall.HALF_SIZE);
        this.getCamera().attachObject(player);
    }
}
