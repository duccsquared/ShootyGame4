package engine.camera.transitions.beziers;

public class BezierEase extends CubicBezier {
    public BezierEase(int steps) {
        super(steps,.25,.1,.25,1);
    }
}
