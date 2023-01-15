package engine.drawHandlers;

import engine.objects.BaseObject;
import engine.skeleton.App;

import java.awt.*;

public class RectDraw extends DrawHandler {
    public Color colour;
    public Color borderColour;
    public RectDraw() {
        colour = new Color(128,128,128);
        borderColour = new Color(168,168,168);
    }
    public RectDraw(Color colour, Color borderColour) {
        this.colour = colour;
        this.borderColour = borderColour;
    }
    public Color getColour() {
        return colour;
    }
    public Color getBorderColour() {
        return borderColour;
    }
    public void setColour(Color colour) {
        this.colour = colour;
    }
    public void setBorderColour(Color borderColour) {
        this.borderColour = borderColour;
    }

    @Override
    public void paint(Graphics2D g2d, BaseObject object) {
        int x = (int) object.getAbsX1();
        int y = (int) object.getAbsY1();
        int width = (int) object.width();
        int height = (int) object.height();
        if(x < App.getInstance().getWidth() &&
                x+width > 0 &&
                y < App.getInstance().getHeight() &&
                y+height > 0) {
            g2d.setColor(colour);
            g2d.fillRect(x,y,width,height);
            g2d.setColor(borderColour);
            g2d.drawRect(x,y,width,height);
        }
    }
}
