package game.screens;

import engine.objects.Text;
import engine.screens.BaseScreen;
import engine.screens.SubScreen;
import engine.skeleton.App;
import engine.utils.Key;
import game.gameScreen.sprites.buttons.ButtonQuit;
import game.gameScreen.sprites.buttons.ButtonRespawn;

import java.awt.*;

public class GameOverSubscreen extends SubScreen {
    public GameOverSubscreen(String id, GameScreen screen) {
        super(id, screen, "res/TransBox.png",
                App.getInstance().getWidth()/2-100,
                App.getInstance().getHeight()/2-50,
                App.getInstance().getWidth()/2+100,
                App.getInstance().getHeight()/2+50);
        Text.newInstance(this,"Game Over",100,10,30,0,-1);
        new ButtonRespawn(screen,this,20,70,80,90);
        new ButtonQuit(this,120,70,180,90);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
