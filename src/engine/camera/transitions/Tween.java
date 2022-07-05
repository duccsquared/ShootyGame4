package engine.camera.transitions;

public abstract class Tween {
    private int steps;
    private double t;
    protected Tween(int steps) {
        this.steps = steps;
        this.t = 0;
    }
    protected void next() {
        t += 1.0/steps;
    }
    protected abstract double currentPos();
    protected double getT() {
        return t;
    }
    public int getSteps() {
        return steps;
    }
    protected void setT(double t) {
        this.t = t;
    }
}
