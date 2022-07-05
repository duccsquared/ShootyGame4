package engine.objects;

import engine.drawHandlers.DrawHandler;
import engine.screens.Screen;

import java.awt.*;

public class ConcreteSprite extends Sprite{
    public ConcreteSprite(Screen screen, double x1, double y1, double x2, double y2) {
        super(screen, x1, y1, x2, y2);
    }

    public ConcreteSprite(Screen screen, DrawHandler drawHandler, double x1, double y1, double x2, double y2){
        super(screen, drawHandler, x1, y1, x2, y2);
    }

    public ConcreteSprite(Screen screen, Color color, Color borderColor, double x1, double y1, double x2, double y2) {
        super(screen, color, borderColor, x1, y1, x2, y2);
    }

    public ConcreteSprite(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x1, double y1, double x2, double y2) {
        super(screen, r, g, b, borderR, borderG, borderB, x1, y1, x2, y2);
    }

    public ConcreteSprite(Screen screen, String imgPath, double x1, double y1, double x2, double y2)  {
        super(screen, imgPath, x1, y1, x2, y2);
    }

    @Override
    public void tick() {
    }
}
