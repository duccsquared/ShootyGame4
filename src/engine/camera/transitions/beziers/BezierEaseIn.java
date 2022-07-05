package engine.camera.transitions.beziers;

public class BezierEaseIn extends CubicBezier {
    public BezierEaseIn(int steps) {
        super(steps,.42,0,1,1);
    }
}
