package game;

import engine.skeleton.Panel;
import game.screens.GameScreen;
import game.screens.MainMenuScreen;

import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePanel extends Panel {
    public GamePanel() {
        super(new Color(26,26,26), 25);
    }

    @Override
    public void start() {
        this.registerScreen(new MainMenuScreen());
        this.registerScreen(new GameScreen());
        this.setCurrentScreen(MainMenuScreen.id);
    }

    @Override
    public void tick(ActionEvent e) {
    }
}
