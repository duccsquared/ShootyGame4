package engine.utils;

import engine.skeleton.App;

public class Key {
    public static boolean held(String key) {
        return App.getInstance().held(key);
    }
    public static boolean pressed(String key) {
        return App.getInstance().pressed(key);
    }
    public static boolean bothHeld(String key1, String key2) {
        return Key.held(key1) && Key.held(key2);
    }
    public static boolean bothPressed(String key1, String key2) {
        return Key.pressed(key1) && Key.pressed(key2);
    }
    public static final String A = "A";
    public static final String B = "B";
    public static final String C = "C";
    public static final String D = "D";
    public static final String E = "E";
    public static final String F = "F";
    public static final String G = "G";
    public static final String H = "H";
    public static final String I = "I";
    public static final String J = "J";
    public static final String K = "K";
    public static final String L = "L";
    public static final String M = "M";
    public static final String N = "N";
    public static final String O = "O";
    public static final String P = "P";
    public static final String Q = "Q";
    public static final String R = "R";
    public static final String S = "S";
    public static final String T = "T";
    public static final String U = "U";
    public static final String V = "V";
    public static final String W = "W";
    public static final String X = "X";
    public static final String Y = "Y";
    public static final String Z = "Z";
    public static final String SPACE = "Space";
    public static final String UP = "Up";
    public static final String DOWN = "Down";
    public static final String LEFT = "Left";
    public static final String RIGHT = "Right";
    public static final String SHIFT = "Shift";
    public static final String CTRL = "Ctrl";
    public static final String ALT = "Alt";
    public static final String CAPS_LOCK = "Caps lock";
    public static final String ESCAPE = "Escape";
    public static final String BACK_QUOTE = "Back Quote";
    public static final String KEY_1 = "1";
    public static final String KEY_2 = "2";
    public static final String KEY_3 = "3";
    public static final String KEY_4 = "4";
    public static final String KEY_5 = "5";
    public static final String KEY_6 = "6";
    public static final String KEY_7 = "7";
    public static final String KEY_8 = "8";
    public static final String KEY_9 = "9";
    public static final String KEY_0 = "0";
    public static final String MINUS = "Minus";
    public static final String EQUALS = "Equals";
    public static final String BACKSPACE = "Backspace";
    public static final String ENTER = "Enter";
    public static final String F1 = "F1";
    public static final String F2 = "F2";
    public static final String F3 = "F3";
    public static final String F4 = "F4";
    public static final String F5 = "F5";
    public static final String F6 = "F6";
    public static final String F7 = "F7";
    public static final String F8 = "F8";
    public static final String F9 = "F9";
    public static final String F10 = "F10";
    public static final String F11 = "F11";
    public static final String F12 = "F12";
    public static final String OPEN_BRACKET = "Open Bracket";
    public static final String CLOSE_BRACKET = "Close Bracket";
    public static final String SEMICOLON = "Semicolon";
    public static final String QUOTE = "Quote";
    public static final String COMMA = "Comma";
    public static final String PERIOD = "Period";
    public static final String SLASH = "Slash";
    public static final String BACK_SLASH = "Back Slash";
    public static final String PAGE_UP = "Page Up";
    public static final String PAGE_DOWN = "Page Down";
    public static final String INSERT = "Insert";
    public static final String DELETE = "Delete";

}
