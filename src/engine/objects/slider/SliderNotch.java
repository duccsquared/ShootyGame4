package engine.objects.slider;

import engine.objects.Sprite;
import engine.objects.Text;
import engine.screens.Screen;
import engine.utils.BaseGlobal;

public class SliderNotch extends Sprite {
    private Slider slider;
    Text text;
    public SliderNotch(Screen screen, Slider slider, double defaultValue, int r, int g, int b, int borderR, int borderG, int borderB, double x1, double y1, double x2, double y2) {
        super(screen, r, g, b, borderR, borderG, borderB, x1, y1, x2, y2);
        this.slider = slider;
        this.setDraggable(true);
        text = Text.newInstance(screen,""+defaultValue,x1,y1,x2,y2);
        this.addChild(text);
    }

    @Override
    public void onDrag() {
        super.onDrag();
        this.setX(BaseGlobal.limitMinMax(this.x(), slider.x1(), slider.x2()));
        this.setY(slider.y());
        this.setValue(BaseGlobal.roundTo(value(), slider.getInterval()));
    }
    public double value() {
        return slider.value();
    }
    public void setValue(double value) {
        slider.setValue(value);
    }
    @Override
    public void tick() {
        super.tick();
        if(!text.getString().equals("" + this.value())) {
            text.setString("" + this.value());
        }
    }
}
