package engine.skeleton;

import engine.screens.BaseScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public abstract class Panel extends JPanel implements ActionListener {
    private Timer timer;
    private final int DELAY;
    private BaseScreen currentScreen;
    private HashMap<String, BaseScreen> screenHash;
    private Cursor currentCursor = new Cursor(Cursor.DEFAULT_CURSOR);
    public Panel(Color color) {
        setBackground(color);
        this.DELAY = 10; // 100 fps
        this.screenHash = new HashMap<>();
        timer = new Timer(DELAY, this);
        timer.start();
    }
    public Panel(Color color, int delay) {
        setBackground(color);
        this.DELAY = delay;
        this.screenHash = new HashMap<>();
        timer = new Timer(DELAY, this);
        timer.start();
    }
    public int getDELAY() {return DELAY;}
    public double getMaxFPS() {return 1000.0/getDELAY();}
    public Cursor getCurrentCursor() {return currentCursor;}
    public void setCurrentCursor(Cursor currentCursor) {this.currentCursor = currentCursor;}
    public abstract void start();
    public void registerScreen(BaseScreen screen) {screenHash.put(screen.getId(),screen);}
    public BaseScreen getCurrentScreen() {return currentScreen;}
    public void setCurrentScreen(String id) {
        if(this.currentScreen!=null) {
            this.currentScreen.onPause();
        }
        BaseScreen s = this.screenHash.get(id);
        if(s==null) {
            throw new RuntimeException("ERROR: screen doesn't exist");
        }
        else {
            s.onUnpause();
            this.currentScreen = s;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!App.isInstantiated()) {return;}
        if(currentScreen==null){return;}
        App.getInstance().actionPerformedStart();
        this.setCurrentCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.currentScreen.actionPerformed(e);
        this.currentScreen.tick();
        this.currentScreen.secondTick();
        this.tick(e);
        App.getInstance().actionPerformedEnd();
        if(!this.getCursor().equals(this.getCurrentCursor())) {
            this.setCursor(this.getCurrentCursor());
        }
    }
    public abstract void tick(ActionEvent e);
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(currentScreen!=null) {
            this.currentScreen.paintComponent(g);
        }
        repaint();
        Toolkit.getDefaultToolkit().sync();
    }
}
