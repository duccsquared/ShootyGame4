package engine.camera;

import engine.exceptions.CantAttachCameraToFixedObjectException;
import engine.exceptions.ExceptionThrower;
import engine.objects.BaseObject;

public class ObjectAttacher {
    private BaseObject object;
    private double relX;
    private double relY;
    private boolean centered = true;
    protected ObjectAttacher(Camera camera, BaseObject object) {
        this.object = object;
        this.init(object,camera.x() - objectX(),camera.y() - objectY(),true);
    }
    protected ObjectAttacher(Camera camera, BaseObject object, boolean centered) {
        this.object = object;
        this.centered = centered;
        this.init(object,camera.x() - objectX(),camera.y() - objectY(),centered);
    }
    protected ObjectAttacher(BaseObject object, double relX, double relY) {
        this.init(object,relX,relY,true);
    }
    protected ObjectAttacher(BaseObject object, double relX, double relY, boolean centered) {
        this.init(object,relX,relY,centered);
    }
    private void init(BaseObject object, double relX, double relY,boolean centered) {
        if(object.isFixedPos()) {
            ExceptionThrower.throwException(new CantAttachCameraToFixedObjectException());
        }
        this.centered = centered;
        this.object = object;
        this.relX = relX;
        this.relY = relY;
    }
    private double objectX() {
        if(this.centered) {return object.x();}
        else {return object.x1();}
    }
    private double objectY() {
        if(this.centered) {return object.y();}
        else {return object.y1();}
    }
    public double x() {
        return objectX() + relX;
    }
    public double y() {
        return objectY() + relY;
    }
}
