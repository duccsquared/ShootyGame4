package engine.objects.slider;

import engine.objects.ConcreteSprite;
import engine.objects.Sprite;
import engine.screens.Screen;
import engine.utils.BaseGlobal;

public class Slider extends Sprite {
    private double minVal;
    private double maxVal;
    private double interval;
    private SliderNotch sliderNotch;
    public Slider(Screen screen, double x1, double y1, double x2, double y2, double minVal, double maxVal) {
        super(screen, 0, 0, 0, 0, 0, 0, x1, y1+0.25*(y2-y1), x2, y2-0.25*(y2-y1));
        this.init(0,0,0,0,0,0,minVal,maxVal,(minVal+maxVal)/2,1);
    }
    public Slider(Screen screen, double x1, double y1, double x2, double y2, double minVal, double maxVal, double interval) {
        super(screen, 0, 0, 0, 0, 0, 0, x1, y1+0.25*(y2-y1), x2, y2-0.25*(y2-y1));
        this.init(0,0,0,0,0,0,minVal,maxVal,(minVal+maxVal)/2,interval);
    }
    public void init(int r, int g, int b, int borderR, int borderG, int borderB, double minVal, double maxVal,double defaultValue, double interval) {
        double thickness = this.height()/2;
        double endMarkerY1 = this.y1() - thickness;
        double endMarkerY2 = this.y2() + thickness;
        this.addChild(new ConcreteSprite(this.getScreen(),r,g,b,borderR,borderG,borderB,this.x1(),endMarkerY1,this.x1()+thickness,endMarkerY2));
        this.addChild(new ConcreteSprite(this.getScreen(),r,g,b,borderR,borderG,borderB,this.x2()-thickness,endMarkerY1,this.x2(),endMarkerY2));
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.interval = interval;
        sliderNotch = new SliderNotch(this.getScreen(),this,defaultValue,r,g,b,borderR,borderG,borderB,this.x()-thickness,endMarkerY1,this.x()+thickness,endMarkerY2);
        this.addChild(sliderNotch);
        this.setFixedPos(true);

    }

    public double getMinVal() {return minVal;}
    public double getMaxVal() {return maxVal;}
    public double getInterval() {return interval;}
    public SliderNotch getSliderNotch() {return sliderNotch;}
    public double value() {
        double ratio = (sliderNotch.x() - this.x1())/ this.width();
        return BaseGlobal.clearFloatingPointRoundingError(this.getMinVal() + ratio * this.getMaxVal());
    }
    public void setValue(double value) {
        value = BaseGlobal.limitMinMax(value,minVal,maxVal);
        double ratio = (value - minVal)/(maxVal - minVal);
        sliderNotch.setX(this.x1() + ratio * this.width());
    }
}
