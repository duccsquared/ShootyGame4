package game.gameScreen.sprites;

import engine.managers.ObjectInstanceManager;
import engine.objects.Sprite;
import engine.screens.Screen;

public class Wall extends GameObject {
    public static final double HALF_SIZE = 16;
    public Wall(Screen screen, double x, double y) {
        super(screen, 26,26,26,128,128,128, x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE);
        ObjectInstanceManager.getInstance().addInstance(this,Wall.class);
    }

    @Override
    public void delete() {
        super.delete();
        ObjectInstanceManager.getInstance().removeInstance(this,Wall.class);
    }
}
