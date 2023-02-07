package engine.screens;

import engine.drawHandlers.EmptyDraw;
import engine.objects.ConcreteSprite;
import engine.objects.Sprite;
import engine.utils.Mouse;

import java.awt.*;

public abstract class SubScreen extends Screen {
    private Sprite bgSprite;
    private BaseScreen parentScreen;
    public SubScreen(String id, BaseScreen screen, double x1, double y1, double x2, double y2) {
        super(id,screen); bgSprite = new ConcreteSprite(screen,new EmptyDraw(),x1,y1,x2,y2);
        bgSprite.setFixedPos(true);
        this.parentScreen = screen;
    }
    public SubScreen(String id, BaseScreen screen, Color color, Color borderColor, double x1, double y1, double x2, double y2) {
        super(id,screen); bgSprite = new ConcreteSprite(screen,color,borderColor,x1,y1,x2,y2);
        bgSprite.setFixedPos(true);
        this.parentScreen = screen;
    }
    public SubScreen(String id, BaseScreen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x1, double y1, double x2, double y2)  {
        super(id,screen); bgSprite = new ConcreteSprite(screen,r,g,b,borderR,borderG,borderB,x1,y1,x2,y2);
        bgSprite.setFixedPos(true);
        this.parentScreen = screen;
    }
    public SubScreen(String id, BaseScreen screen, String imgPath, double x1, double y1, double x2, double y2)  {
        super(id,screen); bgSprite = new ConcreteSprite(screen,imgPath,x1,y1,x2,y2);
        bgSprite.setFixedPos(true);
        this.parentScreen = screen;
    }
    public double x() {return bgSprite.x();}
    public double y() {return bgSprite.y();}
    public double x1() {return bgSprite.x1();}
    public double y1() {return bgSprite.y1();}
    public double x2() {return bgSprite.x2();}
    public double y2() {return bgSprite.y2();}
    public double width() {return bgSprite.width();}
    public double height() {return bgSprite.height();}
    public void setX(double x) {bgSprite.setX(x);}
    public void setY(double y) {bgSprite.setY(y);}
    public void setX1(double x1) {bgSprite.setX1(x1);}
    public void setX2(double x2) {bgSprite.setX2(x2);}
    public void setY1(double y1) {bgSprite.setY1(y1);}
    public void setY2(double y2) {bgSprite.setY2(y2);}
    public void setWidth(double width) {bgSprite.setWidth(width);}
    public void setHeight(double height) {bgSprite.setHeight(height);}
    public void setPos(double x1, double y1, double x2, double y2) {
        this.setX1(x1);
        this.setY1(y1);
        this.setX2(x2);
        this.setY2(y2);
    }
    public void setPos(double x, double y) {
        this.setX(x);
        this.setY(y);
    }
    public void moveX(double x) {
        this.setX(this.x()+x);
    }
    public void moveY(double y) {
        this.setY(this.y()+y);
    }
    public void movePos(double x, double y) {
        this.moveX(x);
        this.moveY(y);
    }
    public void setSize(double width, double height) {
        this.setWidth(width);
        this.setHeight(height);
    }
    @Override
    public boolean onUnhandledMouseClick() {
        return this.intersects(Mouse.mousePosX(),Mouse.mousePosY());
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if(bgSprite!=null) {
            bgSprite.paint(g2d);
        }
        super.paintComponent(g);
    }

    public boolean intersects(double x, double y) {
        return x >= this.x1() && y >= this.y1() && x <= this.x2() && y <= this.y2();
    }

    public void delete() {
        this.bgSprite.delete();
        this.parentScreen.removeSubScreen(this);
    }
}
