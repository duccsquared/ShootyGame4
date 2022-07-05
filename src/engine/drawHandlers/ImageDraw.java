package engine.drawHandlers;

import engine.objects.BaseObject;
import engine.skeleton.App;

import javax.swing.*;
import java.awt.*;

public class ImageDraw extends DrawHandler{
    private Image baseImg;
    private Image img;
    private double currentWidth;
    private double currentHeight;
    public ImageDraw(String imgPath,double width, double height) {
        ImageIcon ii = new ImageIcon(imgPath);
        this.currentWidth = width;
        this.currentHeight = height;
        baseImg = ii.getImage();
        img = baseImg.getScaledInstance((int) currentWidth, (int) currentHeight, Image.SCALE_DEFAULT);
    }
    @Override
    public void paint(Graphics2D g2d, BaseObject object) {
        if(object.width()!= currentWidth || object.height() != currentHeight) {
            currentWidth = object.width();
            currentHeight = object.height();
            img = baseImg.getScaledInstance((int) object.width(), (int) object.height(), Image.SCALE_DEFAULT);
            boolean result = false;
            while (!result) {
                result = rotateAndDraw(g2d,object);
            }
        }
        else {
            rotateAndDraw(g2d,object);
        }
    }
    private boolean rotateAndDraw(Graphics2D g2d, BaseObject object) {
        boolean result;
        double x = object.getAbsX1();
        double y = object.getAbsY1();
        double angle = object.getAngle();
        g2d.rotate(Math.toRadians(-angle), (int) (x+object.width()/2), (int) (y+object.height()/2));
        result = g2d.drawImage(this.img,(int) x,(int) y, App.getInstance().getPanel());
        g2d.rotate(Math.toRadians(angle), (int) (x+object.width()/2), (int) (y+object.height()/2));
        return result;
    }
}
