package game;

import engine.skeleton.App;

public class Main {
    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        App.instantiate("Shooty Game 4",600,600,gamePanel);
        gamePanel.start();
    }
}
