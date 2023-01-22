package engine.objects;

import engine.drawHandlers.DrawHandler;
import engine.managers.ObjectInstanceManager;
import engine.screens.Screen;
import engine.screens.SubScreen;
import engine.utils.BaseGlobal;
import engine.utils.Mouse;
import game.gameScreen.sprites.Wall;

import java.awt.*;
import java.util.ArrayList;

public abstract class BaseObject {
    private Screen screen;
    AABB aabb;
    private boolean fixedPos = false;
    private double angle = 0;
    private DrawHandler drawHandler;
    private BaseObject parent = null;
    private boolean visible = true;
    private boolean fixChildrenAngleToParent = true;
    private ArrayList<BaseObject> childList = new ArrayList<>();
    private boolean draggable = false;
    private boolean drawLast = false;
    public BaseObject(Screen screen, DrawHandler drawHandler, double x1, double y1, double x2, double y2){
        this.init(screen,drawHandler,x1,y1,x2,y2);
    }
    public void init(Screen screen, DrawHandler drawHandler, double x1, double y1, double x2, double y2){
        this.screen = screen;
        this.drawHandler = drawHandler;
        screen.addObject(this);
        this.aabb = new AABB();
        this.setPos(x1,y1,x2,y2);
    }
    public boolean isFixedPos() {return fixedPos;}
    public Screen getScreen() {return screen;}
    public double x() {return aabb.x();}
    public double y() {return aabb.y();}
    public double x1() {return aabb.x1();}
    public double y1() {return aabb.y1();}
    public double x2() {return aabb.x2();}
    public double y2() {return aabb.y2();}
    public double width() {return aabb.width();}
    public double height() {return aabb.height();}
    public double getAngle() {return angle;}
    public double getAbsX1() {
        double x1;
        if(fixedPos) {x1 = this.x1();}
        else {x1 = this.x1() - screen.windowX();}
        if(screen instanceof SubScreen) {
            x1 += ((SubScreen) screen).x1();
        }
        return x1;
    }
    public double getAbsY1() {
        double y1;
        if(fixedPos) {y1 = this.y1();}
        else {y1 = this.y1() - screen.windowY();}
        if(screen instanceof SubScreen) {
            y1 += ((SubScreen) screen).y1();
        }
        return y1;
    }
    public double getAbsX2() {
        return getAbsX1() + this.width();
    }
    public double getAbsY2() {
        return getAbsY1() + this.height();
    }
    public DrawHandler getDrawHandler() {return drawHandler;}
    public boolean isVisible() {return visible;}
    public boolean isFixChildrenAngleToParent() {
        return fixChildrenAngleToParent;
    }
    public boolean isDraggable() {return draggable;}
    public boolean isDrawLast() {return drawLast;}
    public ArrayList<BaseObject> getChildList() {
        return (ArrayList<BaseObject>) childList.clone();
    }
    public BaseObject parent() {
        return this.parent;
    }
    public BaseObject root() {
        if(this.parent()==null) {
            return this;
        }
        else {
            return this.parent().root();
        }
    }
    public void setFixedPos(boolean fixedPos) {
        this.fixedPos = fixedPos;
        for(BaseObject child : this.childList) {child.setFixedPos(true);}
    }
    public void setX(double x) {
        for(BaseObject child : this.childList) {child.moveX(x-this.x());}
        aabb.setX(x);
    }
    public void setY(double y) {
        for(BaseObject child : this.childList) {child.moveY(y-this.y());}
        aabb.setY(y);
    }
    public void setX1(double x1) {aabb.setX1(x1);}
    public void setX2(double x2) {aabb.setX2(x2);}
    public void setY1(double y1) {aabb.setY1(y1);}
    public void setY2(double y2) {aabb.setY2(y2);}
    public void setWidth(double width) {
        for(BaseObject child : this.childList) {
            double ratio = width/this.width();
            double newDist = (child.x() - this.x()) * ratio;
            child.setWidth(ratio * child.width());
            child.setX(this.x() + newDist);
        }
        aabb.setWidth(width);
    }
    public void setHeight(double height) {
        for(BaseObject child : this.childList) {
            double ratio = height/this.height();
            double newDist = (child.y() - this.y()) * ratio;
            child.setHeight(ratio * child.height());
            child.setY(this.y() + newDist);
        }
        aabb.setHeight(height);
    }
    public void setAngle(double angle) {
        if(this.fixChildrenAngleToParent) {
            double angleDiff = angle - this.getAngle();
            for(BaseObject child : this.childList) {
                double[] coords = BaseGlobal.rotateAroundPoint(child.x(),child.y(),this.x(),this.y(),angleDiff);
                child.setPos(coords[0],coords[1]);
                child.changeAngle(angleDiff);
            }
        }
        this.angle = angle;
    }
    public void changeAngle(double angle) {
        this.setAngle(this.getAngle()+angle);
    }
    public void setVisible(boolean visible) {this.visible = visible;}
    public void setFixChildrenAngleToParent(boolean fixChildrenAngleToParent) {
        this.fixChildrenAngleToParent = fixChildrenAngleToParent;
    }
    public void setDraggable(boolean draggable) {this.draggable = draggable;}
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
    public void setDrawLast(boolean drawLast) {this.drawLast = drawLast;}
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
    public static void setChildParent(BaseObject parent, BaseObject child) {
        if(child.parent==null) {
            child.getScreen().removeObject(child);
        }
        else {
            if(child.parent.hasChild(child)) {
                child.parent.removeChild(child);
            }
        }
        child.parent = parent;
        if(child.parent==null) {
            child.getScreen().addObject(child);
        }
        else {
            if(!parent.hasChild(child)) {
                parent.childList.add(child);
            }
        }
    }
    public void setParent(BaseObject parent) {
        BaseObject.setChildParent(parent,this);
    }
    public void addChild(BaseObject child) {
        BaseObject.setChildParent(this,child);
    }
    public void removeChild(BaseObject child) {
        this.childList.remove(child);
        if(child.parent() != null) {
            child.setParent(null);
        }
    }
    public boolean hasChild(BaseObject child) {
        return this.childList.contains(child);
    }
    public boolean intersects(double x, double y) {
        return x >= this.x1() && y >= this.y1() && x <= this.x2() && y <= this.y2();
}
    public boolean intersectsAbs(double x, double y) {
        return x >= this.getAbsX1() && y >= this.getAbsY1() && x <= this.getAbsX2() && y <= this.getAbsY2();
    }
    public boolean intersects(BaseObject baseObject) {
        return this.x1() < baseObject.x2() &&
                this.x2() > baseObject.x1() &&
                this.y1() < baseObject.y2() &&
                this.y2() > baseObject.y1();
    }
    public BaseObject findIntersecting(Class<? extends BaseObject> cls) {
        ArrayList<BaseObject> objectArray = ObjectInstanceManager.getInstance().getArrayList(cls,this.getScreen());
        for(BaseObject object: objectArray) {
            if(this.intersects(object)) {
                return object;
            }
        }
        return null;
    }
    public void resolveCollisionsX(Class<? extends BaseObject> cls, double dirX) {
        ArrayList<BaseObject> objectArray = ObjectInstanceManager.getInstance().getArrayList(cls,this.getScreen());
        if(dirX==0){return;}
        for(BaseObject object: objectArray) {
            while(this.intersects(object)) {
                if(object.equals(this)) {break;}
                this.moveX(Math.signum(dirX));
            }
        }
    }
    public void resolveCollisionsY(Class<? extends BaseObject> cls, double dirY) {
        ArrayList<BaseObject> objectArray = ObjectInstanceManager.getInstance().getArrayList(cls,this.getScreen());
        if(dirY==0){return;}
        for(BaseObject object: objectArray) {
            while(this.intersects(object)) {
                if(object.equals(this)) {break;}
                this.moveY(Math.signum(dirY));
            }
        }
    }
    public void resolveCollisions(Class<? extends BaseObject> cls, double dirX, double dirY) {
        resolveCollisionsX(cls,dirX);
        resolveCollisionsY(cls,dirY);
    }
    public void onDrag() {
        Mouse.setCursorToHandCursor();
        for(BaseObject child : childList) {child.onDrag();}
    }
    public void delete() {
        this.screen.removeObject(this);
        for(BaseObject child : childList) {child.delete();}
    }
    public void tick() {
        for(BaseObject child : childList) {child.tick();}
    };
    public boolean tickMouse() {
        if(this.isDraggable()) {
            Mouse.setCursorToHandCursor();
        }
        if(this.isDraggable() && Mouse.leftClicked()) {
            this.getScreen().getDragManager().startDragging(this);
            return true;
        }
        return false;
    }
    public void paint(Graphics2D g2d) {
        if(this.isVisible()) {
            drawHandler.paint(g2d,this);
        }
        for(BaseObject child : childList) {child.paint(g2d);}
    }
}
