package game;

import engine.skeleton.Panel;
import game.screens.MainMenuScreen;

import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePanel extends Panel {
    public GamePanel() {
        super(new Color(100,120,100), 25);
    }

    @Override
    public void start() {
        this.registerScreen(new MainMenuScreen());
        this.setCurrentScreen(MainMenuScreen.id);
    }

    @Override
    public void tick(ActionEvent e) {
    }
}
