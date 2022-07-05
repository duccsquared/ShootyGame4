package game.gameScreen.sprites;

import engine.objects.Sprite;
import engine.screens.Screen;

public class Entity extends Sprite {
    public Entity(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x1, double y1, double x2, double y2) {
        super(screen, r, g, b, borderR, borderG, borderB, x1, y1, x2, y2);
    }

    public Entity(Screen screen, String imgPath, double x1, double y1, double x2, double y2) {
        super(screen, imgPath, x1, y1, x2, y2);
    }
}
