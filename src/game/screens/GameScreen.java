package game.screens;

import engine.managers.ObjectInstanceManager;
import engine.objects.BaseObject;
import engine.objects.Text;
import engine.screens.BaseScreen;
import engine.utils.Mouse;
import game.Faction;
import game.Global;
import game.gameScreen.FPSCounter;
import game.gameScreen.sprites.Enemy;
import game.gameScreen.sprites.Player;
import game.gameScreen.sprites.Stabber;
import game.gameScreen.sprites.Wall;

import java.util.ArrayList;

public class GameScreen extends BaseScreen {
    public static String id = "gameWorld";
    public final int MIN_X = -800;
    public final int MIN_Y = -800;
    public final int MAX_X = 1200;
    public final int MAX_Y = 800;
    public final int MARGIN = 400;
    public double coins = 0;
    private Text coinText;
    Player player;
    FPSCounter fpsCounter;
    public GameScreen() {
        super(id);
        player = new Player(this,-600,-600);

        new Stabber(this,200,200);
        this.getCamera().setX(-920);
        this.getCamera().setY(-920);
        this.getCamera().attachObject(player);
        Faction.setAsHostile(Global.playerFaction,Global.enemyFaction);

        // map borders


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

            // UI text
            this.fpsCounter = new FPSCounter(this);
            this.coinText = Text.newInstance(this,"         ",5,20,40,-1,-1);
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

    @Override
    public void tick() {
        handleEnemySpawns();
        super.tick();
        this.fpsCounter.tick();
        this.coinText.setString(String.format("coins: %.0f",this.coins));
    }

    public void handleEnemySpawns() {
        ArrayList<BaseObject> enemyArray = ObjectInstanceManager.getInstance().getArrayList(Enemy.class);
        int enemyCount = enemyArray.size();

        if(enemyCount<5 && Global.randInt(0,100)==0) {
            new Stabber(this,Global.randInt(-350,1180),Global.randInt(-768,768));
        }
    }
}
