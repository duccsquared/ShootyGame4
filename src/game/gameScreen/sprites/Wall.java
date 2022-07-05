package game.gameScreen.sprites;

import engine.objects.Sprite;
import engine.screens.Screen;

public class Wall extends Sprite {
    public static final double HALF_SIZE = 16;
    public Wall(Screen screen, double x, double y) {
        super(screen, "res/crackedBox.png", x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE);
    }
}
