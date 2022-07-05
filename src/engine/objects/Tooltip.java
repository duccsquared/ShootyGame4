package engine.objects;

import com.sun.javaws.exceptions.InvalidArgumentException;
import engine.exceptions.ExceptionThrower;
import engine.screens.Screen;
import engine.utils.Mouse;

public class Tooltip extends Sprite {
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;
    private Text text;
    public Tooltip(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x1, double y1, double x2, double y2) {
        super(screen, r, g, b, borderR, borderG, borderB, x1, y1, x2, y2);
    }
    public static Tooltip newInstance(Screen screen, BaseObject parent, String tooltipStr) {
        return newInstance(screen,parent,tooltipStr,Tooltip.UP,20,80,80,80,200,200,200);
    }
    public static Tooltip newInstance(Screen screen, BaseObject parent, String tooltipStr, int orientation, int fontSize, int r, int g, int b, int borderR, int borderG, int borderB) {
        Text text = Text.newInstance(screen,tooltipStr,parent.x(),parent.y(),fontSize);
        double borderMarginFrac = 0.2;
        double borderMarginWidth = text.width() * borderMarginFrac;
        double borderMarginHeight = text.height() * borderMarginFrac;
        double diffX = parent.width()/2 + (text.width())/2 + borderMarginWidth;
        double diffY = parent.height()/2 + (text.height())/2 + borderMarginHeight;
        double diffMargin = 0.2 * Math.min(parent.width(),parent.height());

        switch (orientation) {
            case Tooltip.UP:
                text.setY(parent.y()-diffY-diffMargin);
                break;
            case Tooltip.DOWN:
                text.setY(parent.y()+diffY+diffMargin);
                break;
            case Tooltip.LEFT:
                text.setX(parent.x()-diffX-diffMargin);
                break;
            case Tooltip.RIGHT:
                text.setX(parent.x()+diffX+diffMargin);
                break;
            default:
                ExceptionThrower.throwException(new InvalidArgumentException(new String[]{"invalid orientation for tooltip"}));
        }
        Tooltip tooltip = new Tooltip(screen,r,g,b,borderR,borderB,borderG,
                text.x1()-borderMarginWidth,text.y1()-borderMarginHeight,
                text.x2()+borderMarginWidth,text.y2()+borderMarginHeight);
        tooltip.addChild(text);
        tooltip.text = text;
        //parent.addChild(tooltip);
        tooltip.setParent(parent);
        return tooltip;
    }

    @Override
    public void tick() {
        super.tick();
        if(parent().intersectsAbs(Mouse.mousePosX(),Mouse.mousePosY())) {
            this.setVisible(true);
            text.setVisible(true);
        }
        else {
            this.setVisible(false);
            text.setVisible(false);
        }
    }
}
