package game.gameScreen.sprites.buttons;

import engine.objects.Button;
import engine.screens.Screen;
import engine.screens.SubScreen;
import game.gameScreen.sprites.characters.Player;
import game.screens.GameScreen;

import java.awt.*;

public class ButtonRespawn extends Button {
    GameScreen gameScreen;
    public ButtonRespawn(GameScreen gameScreen, Screen screen, double x1, double y1, double x2, double y2) {
        super(screen, "Restart", 26, 26, 26, 255, 255, 255, x1, y1, x2, y2);
        this.setFixedPos(true);
        this.gameScreen = gameScreen;
    }

    @Override
    public void onClick() {
        Player player = new Player(this.gameScreen,-600,-600);
        this.gameScreen.setPlayer(player);
        this.gameScreen.getCamera().setX(-920);
        this.gameScreen.getCamera().setY(-920);
        this.gameScreen.getCamera().attachObject(player);
        if(this.getScreen() instanceof SubScreen) {
            ((SubScreen) this.getScreen()).delete();
        }
    }

    @Override
    public void paint(Graphics2D g2d) {
        super.paint(g2d);
    }
}
