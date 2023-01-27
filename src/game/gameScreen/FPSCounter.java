package game.gameScreen;

import engine.objects.Text;
import engine.skeleton.App;
import game.Global;
import game.screens.GameScreen;

import java.util.LinkedList;
import java.util.Queue;

public class FPSCounter {
    private GameScreen gameScreen;
    private Text fpsText;
    private int index = 0;
    private int rollSize = 100;
    private double[] fpsArray = new double[rollSize];
    private double prevTime;
    public FPSCounter(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        fpsText = Text.newInstance(gameScreen,"       ",5,5,30,-1,-1);
        prevTime = System.nanoTime();
    }
    public void tick() {
        double nextTime = System.nanoTime();
        fpsArray[index] = Global.secondsToTicks(1)/((nextTime - prevTime) / Math.pow(10, 9) * Global.secondsToTicks(1));
        prevTime = nextTime;
        index = (index + 1) % rollSize;
        double sum = 0;
        for(double fps : fpsArray) {
            sum += fps;
        }
        fpsText.setString(String.format("FPS: %.1f", sum/rollSize));
    }

    public Text getFpsText() {
        return fpsText;
    }
}
