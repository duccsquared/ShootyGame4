package engine.objects;

import engine.drawHandlers.DrawHandler;
import engine.drawHandlers.ImageDraw;
import engine.drawHandlers.RectDraw;
import engine.screens.Screen;

import java.awt.*;

public abstract class Sprite extends BaseObject{
    public Sprite(Screen screen, double x1, double y1, double x2, double y2) {
        super(screen, new RectDraw(), x1, y1, x2, y2);
    }
    public Sprite(Screen screen, DrawHandler drawHandler,double x1, double y1, double x2, double y2) {
        super(screen, drawHandler, x1, y1, x2, y2);
    }
    public Sprite(Screen screen, Color color, Color borderColor, double x1, double y1, double x2, double y2)  {
        super(screen, new RectDraw(color,borderColor), x1, y1, x2, y2);
    }
    public Sprite(Screen screen,int r, int g, int b, int borderR, int borderG, int borderB, double x1, double y1, double x2, double y2) {
        super(screen, new RectDraw(new Color(r,g,b),new Color(borderR,borderG,borderB)), x1, y1, x2, y2);
    }
    public Sprite(Screen screen, String imgPath, double x1, double y1, double x2, double y2) {
        super(screen, new ImageDraw(imgPath,x2-x1,y2-y1), x1, y1, x2, y2);
    }

}
