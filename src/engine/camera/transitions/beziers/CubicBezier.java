package engine.camera.transitions.beziers;

import engine.camera.transitions.Tween;

import java.util.LinkedList;
import java.util.Queue;

public class CubicBezier extends Tween {
    public static final int SUBSTEP_CONSTANT = 4;
    private double currentPos;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;
    private double x4;
    private double y4;
    private double subT;
    private Queue<Double[]> coordQueue = new LinkedList<>();
    public CubicBezier(int steps, double x2, double y2, double x3, double y3) {
        super(steps);
        this.x1 = 0; this. y1 = 0;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = 1; this.y4 = 1;
        this.subT = 0;
    }

    @Override
    protected void next() {
        super.next();
        this.subNext();
        Double[] coord = coordQueue.peek();
        while(coord[0]<this.getT()) {
            coordQueue.remove();
            this.subNext();
            coord = coordQueue.peek();
        }
        currentPos = coord[1];
    }
    private void subNext() {
        subT += 1.0/(this.getSteps()*CubicBezier.SUBSTEP_CONSTANT);
        coordQueue.add(new Double[]{bezierX(subT),bezierY(subT)});
    }
    @Override
    public double currentPos() {
        return currentPos;
    }
    public double bezierX(double t) {
        return bezier(t,3,0)* x1 + bezier(t,3,1)* x2 + bezier(t,3,2)* x3 + bezier(t,3,3)* x4;
    }
    public double bezierY(double t) {
        return bezier(t,3,0)* y1 + bezier(t,3,1)* y2 + bezier(t,3,2)* y3 + bezier(t,3,3)* y4;
    }
    private double bezier(double t, int n, int i) {
        return choose(n,i)*Math.pow(t,i)*Math.pow(1 - t,n-i);
    }
    private int choose(int n,int r) {
        return factorial(n)/(factorial(r)*factorial(n-r));
    }
    private int factorial(int n) {
        if(n<=0) {
            return 1;
        }
        else {
            return n * factorial(n-1);
        }
    }
}
