package engine.objects;

import engine.drawHandlers.DrawHandler;
import engine.drawHandlers.TextDraw;
import engine.screens.Screen;
import engine.skeleton.App;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Text extends BaseObject {
    public static final int TOP = -1; public static final int BOTTOM = 1;
    public static final int CENTER = 0;
    public static final int LEFT = -1; public static final int RIGHT = 1;
    private static Color defaultColor = new Color(255,255,255);
    private static String defaultFontStr = "Power Green";
    private Color color;
    private Font font;
    private String string;
    private Boolean coordsFixed;
    private Text(Screen screen, DrawHandler drawHandler, double x1, double y1, double x2, double y2) {
        super(screen, drawHandler, x1, y1, x2, y2);
    }
    public static Text newInstance(Screen screen, String string, double x, double y, int fontSize) {
        return init(screen,string,x,y,defaultFontStr, Font.PLAIN,fontSize,defaultColor.getRed(),defaultColor.getGreen(),defaultColor.getBlue(),Text.CENTER,Text.CENTER,true);
    }
    public static Text newInstance(Screen screen, String string, double x, double y, int fontSize, int hAlign, int vAlign){
        return init(screen,string,x,y,defaultFontStr, Font.PLAIN,fontSize,defaultColor.getRed(),defaultColor.getGreen(),defaultColor.getBlue(),hAlign,vAlign,true);
    }
    public static Text newInstance(Screen screen, String string, double x, double y, String fontStr, int fontSize) {
        return init(screen,string,x,y,fontStr, Font.PLAIN,fontSize,defaultColor.getRed(),defaultColor.getGreen(),defaultColor.getBlue(),Text.CENTER,Text.CENTER,true);
    }
    public static Text newInstance(Screen screen, String string, double x, double y, int fontSize, int r, int g, int b) {
        return init(screen,string,x,y,defaultFontStr, Font.PLAIN,fontSize,r,g,b,Text.CENTER,Text.CENTER,false);
    }
    public static Text newInstance(Screen screen, String string, double x, double y, String fontStr, int fontStyle, int fontSize, int r, int g, int b, int hAlign, int vAlign, boolean fixedPos){
        return init(screen,string,x,y,fontStr, fontStyle,fontSize,r,g,b,hAlign,vAlign,fixedPos);
    }
    public static Text init(Screen screen, String string, double x, double y, String fontStr, int fontStyle, int fontSize, int r, int g, int b, int hAlign, int vAlign, boolean fixedPos) {
        Font font = new Font(fontStr,fontStyle,fontSize);
        double[] coords = getCoordsFromFontSize(font,string,x,y);
        double width = coords[2] - coords[0]; double height = coords[3] - coords[1];
        if(hAlign==Text.CENTER) {coords[2] -= width/2; coords[0] -= width/2;}
        if(hAlign==Text.RIGHT) {coords[2] -= width; coords[0] -= width;}
        if(vAlign==Text.CENTER) {coords[3] -= height/2; coords[1] -= height/2;}
        if(vAlign==Text.BOTTOM) {coords[3] -= height; coords[1] -= height;}
        Text text = new Text(screen, new TextDraw(),coords[0],coords[1],coords[2],coords[3]);
        text.setFixedPos(fixedPos);
        text.coordsFixed = true;
        text.string = string;
        text.font = font;
        text.color = new Color(r,g,b);
        return text;
    }
    public static Text newInstance(Screen screen, String string, double x1, double y1, double x2, double y2) {
        return init(screen,string,x1,y1, x2, y2,defaultFontStr, Font.PLAIN,defaultColor.getRed(),defaultColor.getGreen(),defaultColor.getBlue(),true);
    }
    public static Text newInstance(Screen screen, String string, double x1, double y1, double x2, double y2, String fontStr){
        return init(screen,string,x1,y1, x2, y2,fontStr, Font.PLAIN,defaultColor.getRed(),defaultColor.getGreen(),defaultColor.getBlue(),true);
    }
    public static Text newInstance(Screen screen, String string, double x1, double y1, double x2, double y2, int fontStyle){
        return init(screen,string,x1,y1, x2, y2,defaultFontStr, fontStyle,defaultColor.getRed(),defaultColor.getGreen(),defaultColor.getBlue(),true);
    }
    public static Text newInstance(Screen screen, String string, double x1, double y1, double x2, double y2, int r, int g, int b) {
        return init(screen,string,x1,y1, x2, y2,defaultFontStr,Font.PLAIN,r,g,b,true);
    }
    public static Text newInstance(Screen screen, String string, double x1, double y1, double x2, double y2, String fontStr, int fontStyle, int r, int g, int b, boolean fixedPos) {
        return init(screen,string,x1,y1, x2, y2,fontStr, fontStyle,r,g,b,fixedPos);
    }
    public static Text init(Screen screen, String string, double x1, double y1, double x2, double y2, String fontStr, int fontStyle, int r, int g, int b, boolean fixedPos){
        Text text = new Text(screen, new TextDraw(),x1,y1,x2,y2);
        text.setFixedPos(fixedPos);
        text.coordsFixed = true;
        text.string = string;
        text.font = new Font(fontStr,fontStyle,20);
        text.color = new Color(r,g,b);
        text.updateFontSizeFromCoords();
        return text;
    }
    public Color getColor() {return color;}
    public Font getFont() {return font;}
    public String getString() {return string;}
    public boolean isCoordsFixed() {return coordsFixed;}

    // string width/height
    public int getStringWidth() {
        Graphics g = App.getInstance().getPanel().getGraphics();
        Rectangle2D r = g.getFontMetrics(this.getFont()).getStringBounds(this.getString(),g);
        return (int) r.getWidth();
    }
    public int getStringHeight() {
        Graphics g = App.getInstance().getPanel().getGraphics();
        Rectangle2D r = g.getFontMetrics(this.getFont()).getStringBounds(this.getString(),g);
        return (int) -r.getY();
    }
    public static double[] getCoordsFromFontSize(Font font, String string, double x1, double y1) {
        // return format: {x1,y1,x2,y2}
        Graphics g = App.getInstance().getPanel().getGraphics();
        Rectangle2D r = g.getFontMetrics(font).getStringBounds(string,g);
        return new double[] {x1,y1,x1+r.getWidth(),y1-r.getY()};
    }
    public double[] getCoordsFromFontSize() {
        return this.getCoordsFromFontSize(this.getFont(),this.getString(),this.x1(),this.y1());
    }
    public void updateCoordsFromFontSize() {
        double[] coords = this.getCoordsFromFontSize();
        this.setPos(coords[0],coords[1],coords[2],coords[3]);
    }
    public int getFontSizeFromCoords() {
        double x1 = this.x1(); double y1 = this.y1();
        double x2 = this.x2(); double y2 = this.y2();
        double width = this.width(); double height = this.height();
        this.coordsFixed = false;
        double approxSize = 30;
        final double MARGIN = 0.1;
        double errorRatio = 1 + MARGIN*2;
        int attempts = 0;
        while(Math.abs(1-errorRatio)>(MARGIN/10.0)*(10+attempts) || errorRatio>1) {
            this.setFontSize((int) approxSize);
            double approxWidth = this.x2() - this.x1();
            errorRatio = width/approxWidth;
            approxSize *= errorRatio;
            attempts += 1;
        }
        final int SIZE1 = (int)approxSize-5; final int SIZE2 = (int)approxSize+5;
        this.setFontSize(SIZE1);
        double width1 = this.x2() - this.x1();
        double height1 = this.y2() - this.y1();
        this.setFontSize(SIZE2);
        double width2 = this.x2() - this.x1();
        double height2 = this.y2() - this.y1();
        double mW = (SIZE2 - SIZE1)/(width2 - width1);
        double mH = (SIZE2 - SIZE1)/(height2 - height1);
        double cW = SIZE1 - mW*width1;
        double cH = SIZE1 - mH*height1;
        this.coordsFixed = true;
        this.setPos(x1,y1,x2,y2);
        double fontSizeW = mW * this.width() + cW;
        double fontSizeH = mH * this.height() + cH;
        int result = (int) Math.min(fontSizeW,fontSizeH);
        return result;
    }
    public void updateFontSizeFromCoords() {
        int fontSize = getFontSizeFromCoords();
        this.font = this.getFont().deriveFont((float)fontSize);
    }
    public void setFontSize(int fontSize) {
        this.setFont(this.getFont().deriveFont((float)fontSize));
    }
    public void changeFontSize(int fontSize) {
        this.setFontSize(this.getFont().getSize()+fontSize);
    }
    public void setFontStr(String fontStr) {
        this.setFont(new Font(fontStr,this.getFont().getStyle(),this.getFont().getSize()));
    }
    public void setFontStyle(int fontStyle) {
        this.setFont(this.getFont().deriveFont(fontStyle));
    }
    public void setColor(Color color) {this.color = color;}
    public void setFont(Font font) {
        this.font = font;
        if(coordsFixed) {this.updateFontSizeFromCoords();}
        else {this.updateCoordsFromFontSize();}
    }
    public void setString(String string) {
        this.string = string;
        if(coordsFixed) {this.updateFontSizeFromCoords();}
        else {this.updateCoordsFromFontSize();}
    }

}
