package engine.camera.transitions.beziers;

public class BezierEaseOut extends CubicBezier {
    public BezierEaseOut(int steps) {
        super(steps,0,0,.58,1);
    }
}
