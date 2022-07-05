package engine.utils;

import engine.screens.Screen;
import engine.skeleton.App;

import java.awt.*;

public class Mouse {
    public static final int LEFT = 1;
    public static final int MIDDLE = 2;
    public static final int RIGHT = 3;
    public static boolean clicked(int button) {return App.getInstance().clicked(button);}
    public static boolean held(int button) {return App.getInstance().held(button);}
    public static boolean doubleClicked(int button) {return App.getInstance().doubleClicked(button);}
    public static boolean leftClicked() {return Mouse.clicked(Mouse.LEFT);}
    public static boolean leftHeld() {return Mouse.held(Mouse.LEFT);}
    public static boolean leftDoubleClicked() {return Mouse.doubleClicked(Mouse.LEFT);}
    public static boolean middleClicked() {return Mouse.clicked(Mouse.MIDDLE);}
    public static boolean middleHeld() {return Mouse.held(Mouse.MIDDLE);}
    public static boolean middleDoubleClicked() {return Mouse.doubleClicked(Mouse.MIDDLE);}
    public static boolean rightClicked() {return Mouse.clicked(Mouse.RIGHT);}
    public static boolean rightHeld() {return Mouse.held(Mouse.RIGHT);}
    public static boolean rightDoubleClicked() {return Mouse.doubleClicked(Mouse.RIGHT);}
    public static double mousePosX() {return App.getInstance().getMousePosX();}
    public static double mousePosY() {return App.getInstance().getMousePosY();}
    public static double relMousePosX(Screen screen) {return screen.windowX() + App.getInstance().getMousePosX();}
    public static double relMousePosY(Screen screen) {return screen.windowY() + App.getInstance().getMousePosY();}
    public static void setCurrentCursor(int cursorType) {
        App.getInstance().getPanel().setCurrentCursor(new Cursor(cursorType));
    }
    public static void setCursorToDefaultCursor() {Mouse.setCurrentCursor(Cursor.DEFAULT_CURSOR);}
    public static void setCursorToHandCursor() {Mouse.setCurrentCursor(Cursor.HAND_CURSOR);}
}
