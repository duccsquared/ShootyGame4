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
        player = new Player(this,-600,-600);

        new Stabber(this,200,200);
        this.getCamera().setX(-920);
        this.getCamera().setY(-920);
        this.getCamera().attachObject(player);
        Faction.setAsHostile(Global.playerFaction,Global.enemyFaction);

        // map borders
        final int MIN_X = -800;
        final int MIN_Y = -800;
        final int MAX_X = 1200;
        final int MAX_Y = 800;
        final int MARGIN = 400;

        new Wall(this, MIN_X-MARGIN, MIN_Y, MIN_X, MAX_Y);
        new Wall(this, MAX_X, MIN_Y, MAX_X+MARGIN, MAX_Y);
        new Wall(this, MIN_X-MARGIN, MIN_Y-MARGIN, MAX_X+MARGIN, MIN_Y);
        new Wall(this, MIN_X-MARGIN, MAX_Y, MAX_X+MARGIN, MAX_Y+MARGIN);

        // starting area borders
        new Wall(this, -400, -800, -368, -400);
        new Wall(this, -800, -400, -620, -368);
        new Wall(this, -580, -400, -368, -368);

        // area 1
        for(int i = 0; i < 40; i++) {
            int posX = Global.randInt(-350,0);
            int posY = Global.randInt(-768,768);
            new Wall(this,posX,posY);
        }
        // area 2
        for(int i = 0; i < 20; i++) {
            int posX = Global.randInt(0,600);
            int posY = Global.randInt(-768,768);
            int width = Global.randInt(32,96);
            int height = Global.randInt(32,96);
            new Wall(this,posX,posY,posX+width,posY+height);
        }
        // area 3
        for(int i = 0; i < 20; i++) {
            int posX = Global.randInt(600,1180);
            int posY = Global.randInt(-768,768);
            int width; int height;
            if(Global.randInt(0,1)==1) {
                width = Global.randInt(64,128);
                height = Global.randInt(16,48);
            }
            else {
                width = Global.randInt(16,48);
                height = Global.randInt(64,128);
            }
            new Wall(this,posX,posY,posX+width,posY+height);
        }
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
