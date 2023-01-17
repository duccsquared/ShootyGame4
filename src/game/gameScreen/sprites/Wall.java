package game.gameScreen.sprites;

import engine.managers.ObjectInstanceManager;
import engine.objects.Sprite;
import engine.screens.Screen;

public class Wall extends GameObject {
    public static double wallCoordToCoord(int coord) {
        return 2*Wall.HALF_SIZE*coord+Wall.HALF_SIZE;
    }
    public static final double HALF_SIZE = 16;
    public Wall(Screen screen, double x, double y) {
        super(screen, 128,128,128,128,128,128, x-HALF_SIZE,y-HALF_SIZE,x+HALF_SIZE,y+HALF_SIZE);
        ObjectInstanceManager.getInstance().addInstance(this,Wall.class);
    }
    public Wall(Screen screen, double x1, double y1, double x2, double y2) {
        super(screen, 128,128,128,128,128,128, x1,y1,x2,y2);
        ObjectInstanceManager.getInstance().addInstance(this,Wall.class);
    }
    @Override
    public void delete() {
        super.delete();
        ObjectInstanceManager.getInstance().removeInstance(this,Wall.class);
    }
}
