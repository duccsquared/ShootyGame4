package game.gameScreen.sprites.buttons;

import engine.objects.Button;
import engine.screens.Screen;
import engine.screens.SubScreen;
import engine.skeleton.App;
import game.screens.GameScreen;

public class ButtonQuit extends Button {

    public ButtonQuit(Screen screen, double x1, double y1, double x2, double y2) {
        super(screen, "Exit to Menu", 26, 26, 26, 255, 255, 255, x1, y1, x2, y2);
        this.setFixedPos(true);
    }

    @Override
    public void onClick() {
        if(this.getScreen() instanceof SubScreen) {
            ((SubScreen) this.getScreen()).delete();
        }
        App.getInstance().getPanel().setCurrentScreen("mainMenu");
    }
}
