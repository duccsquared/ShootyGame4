package engine.camera;

import engine.camera.transitions.Transition;
import engine.camera.transitions.Tween;
import engine.camera.transitions.beziers.BezierEase;
import engine.objects.BaseObject;

public class Camera {
    private double x = 0;
    private double y = 0;
    private ObjectAttacher objectAttacher = null;
    private Transition transition = null;
    public double x() {return x;}
    public double y() {return y;}
    public void setX(double x) {this.x = x;}
    public void setY(double y) {this.y = y;}
    public void attachObject(BaseObject object) {
        this.objectAttacher = new ObjectAttacher(this,object);
        transition = null;
    }
    public void attachObject(BaseObject object, boolean centered) {
        this.objectAttacher = new ObjectAttacher(this,object,centered);
        transition = null;
    }
    public void attachObject(BaseObject object, double relX, double relY) {
        this.objectAttacher = new ObjectAttacher(object,relX,relY);
        transition = new Transition(this.x,this.y,objectAttacher.x(),objectAttacher.y(),new BezierEase(100));
    }
    public void panTo(double x, double y, Tween tween) {
        objectAttacher = null;
        transition = new Transition(this.x,this.y,x,y,tween);
    }
    public void panTo(double x, double y) {panTo(x,y,new BezierEase(100));}
    public void updateCameraPos() {
        if(objectAttacher!=null) {
            this.x = objectAttacher.x();
            this.y = objectAttacher.y();
        }
        if(transition!=null) {
            if(transition.isActive()) {
                transition.next();
                setX(transition.x());
                setY(transition.y());
            }
            else {
                transition = null;
            }
        }
    }
}
