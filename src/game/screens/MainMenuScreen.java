package game.screens;

import engine.screens.BaseScreen;
import game.mainMenu.StartGameButton;

public class MainMenuScreen extends BaseScreen {
    public static String id = "mainMenu";
    public MainMenuScreen() {
        super(id);
        new StartGameButton(this,100,200,500,400);
    }
}
