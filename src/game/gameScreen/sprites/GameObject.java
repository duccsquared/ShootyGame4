package game.gameScreen.sprites;

import engine.objects.BaseObject;
import engine.objects.Sprite;
import engine.screens.Screen;
import game.screens.GameScreen;

public abstract class GameObject extends Sprite {
    private boolean isStatic = true;
    public GameObject(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x1, double y1, double x2, double y2) {
        super(screen, r, g, b, borderR, borderG, borderB, x1, y1, x2, y2);
        this.initGameObject();
    }

    public GameObject(Screen screen, String imgPath, double x1, double y1, double x2, double y2) {
        super(screen, imgPath, x1, y1, x2, y2);
        this.initGameObject();
    }

    protected void initGameObject() {
    }

    @Override
    public void setX(double x) {
        super.setX(x); isStatic = false;
    }

    @Override
    public void setY(double y) {
        super.setY(y); isStatic = false;
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width); isStatic = false;
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height); isStatic = false;
    }

    @Override
    public void delete() {
        super.delete();
    }

    @Override
    public void tick() {
        super.tick();
    }
}
