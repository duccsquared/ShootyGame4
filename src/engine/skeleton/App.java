package engine.skeleton;

import engine.exceptions.AppNotInstantiatedException;
import engine.exceptions.ExceptionThrower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

public class App extends JFrame {
    private static App instance = null;
    private Panel panel;
    private int width;
    private int height;
    private KListener kListener;
    private MListener mListener;
    private double mousePosX;
    private double mousePosY;

    @Override
    public int getWidth() {return width;}
    @Override
    public int getHeight() {
        return height;
    }
    public Panel getPanel() {return panel;}
    public double getMousePosX() {return mousePosX;}
    public double getMousePosY() {return mousePosY;}

    public static App getInstance() {
        if(instance==null) {
            ExceptionThrower.throwException(new AppNotInstantiatedException());
        }
        return instance;
    }
    public static boolean isInstantiated() {
        return instance != null;
    }
    public static void instantiate(String title, int width, int height, Panel panel) {
        instance = new App(title,width,height,panel);
        panel.start();
    }
    private App(String title, int width, int height, Panel panel) {
        super(title);
        this.panel = panel;
        this.add(panel);
        this.width = width;
        this.height = height;
        // listeners
        this.kListener = new KListener();
        this.addKeyListener(kListener);
        this.mListener = new MListener(this);
        this.addMouseListener(mListener);
        // JFrame functions
        this.setFocusable(true);
        this.setResizable(false);
        this.setSize(this.width+16,this.height+39);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformedStart() {
        Point p = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(p, this);
        this.mousePosX = p.getX()-8;
        this.mousePosY = p.getY()-31;
    }
    public void actionPerformedEnd() {
        this.kListener.tick();
        this.mListener.tick();
    }
    public boolean held(String key) {return this.kListener.held(key);}
    public boolean pressed(String key) {return this.kListener.pressed(key);}
    public boolean clicked(int button) {return this.mListener.clicked(button);}
    public boolean held(int button) {return this.mListener.held(button);}
    public boolean doubleClicked(int button) {return this.mListener.doubleClicked(button);}

    class KListener implements KeyListener {
        private HashSet<String> heldSet = new HashSet<String>();
        private HashSet<String> pressedSet = new HashSet<String>();
        public void keyTyped(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {
            String key = getKey(e);
            if(!heldSet.contains(key)) {
                pressedSet.add(key);
            }
            heldSet.add(key);
        }
        public void keyReleased(KeyEvent e) {
            heldSet.remove(getKey(e));
        }
        private String getKey(KeyEvent e) {
            return KeyEvent.getKeyText(e.getKeyCode());
        }
        private boolean held(String key) {
            return heldSet.contains(key);
        }
        private boolean pressed(String key) {
            return pressedSet.contains(key);
        }
        public void tick() {
            pressedSet.clear();
        }
    }
    class MListener implements MouseListener {
        private boolean mouseIn;
        private HashSet<Integer> heldSet = new HashSet<Integer>();
        private HashSet<Integer> clickedSet = new HashSet<Integer>();
        private HashSet<Integer> doubleClickedSet = new HashSet<Integer>();
        public MListener(App app) {
            Point p = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(p, app);
            int x = (int) p.getX()-8;
            int y = (int) p.getY()-31;
            int width = app.getWidth();
            int height = app.getHeight();
            this.mouseIn = x>0 && y > 0 && x < width && y < height;
        }

        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {
            if(!this.heldSet.contains(e.getButton())) {
                this.clickedSet.add(e.getButton());
                if(e.getClickCount()==2) {
                    this.doubleClickedSet.add(e.getButton());
                }
            }
            this.heldSet.add(e.getButton());
        }
        @Override
        public void mouseReleased(MouseEvent e) {this.heldSet.remove(e.getButton());}
        @Override
        public void mouseEntered(MouseEvent e) {this.mouseIn = true;}
        @Override
        public void mouseExited(MouseEvent e) {this.mouseIn = false;}
        public void tick() {
            this.clickedSet.clear();
            this.doubleClickedSet.clear();
        }
        private boolean clicked(int button) {return clickedSet.contains(button);}
        private boolean held(int button) {return heldSet.contains(button);}
        private boolean doubleClicked(int button) {return doubleClickedSet.contains(button);}

    }
}
