package engine.camera.transitions;

public class Transition {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x;
    private double y;
    private double totalTicks;
    private double currentTicks;
    private Tween tween;
    public Transition(double x1, double y1, double x2, double y2, Tween tween) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x = x1;
        this.y = y1;
        this.totalTicks = tween.getSteps();
        this.currentTicks = 0;
        this.tween = tween;
    }
    public void next() {
        currentTicks += 1;
        tween.next();
        double t = tween.currentPos();
        this.x = x1 + t * (x2 - x1);
        this.y = y1 + t * (y2 - y1);
    }
    public boolean isActive() {
        return currentTicks < totalTicks;
    }
    public double x() {return x;}
    public double y() {return y;}
}
