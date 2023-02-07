package game.mainMenu;

import engine.objects.Button;
import engine.screens.Screen;
import engine.skeleton.App;
import game.screens.GameScreen;

public class StartGameButton extends Button {
    public StartGameButton(Screen screen, double x1, double y1, double x2, double y2) {
        super(screen, "Start Game", 26, 26, 26, 255, 255, 255, x1, y1, x2, y2);
        this.setFixedPos(true);
    }

    @Override
    public void onClick() {
        App.getInstance().getPanel().registerScreen(new GameScreen());
        App.getInstance().getPanel().setCurrentScreen(GameScreen.id);
    }
}
