package game.gameScreen.sprites;

import engine.managers.ObjectInstanceManager;
import engine.screens.Screen;
import game.Global;
import game.behaviours.Behaviour;

public abstract class Enemy extends NPC {
    public Enemy(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x1, double y1, double x2, double y2, double maxHp, double speed) {
        super(screen, r, g, b, borderR, borderG, borderB, x1, y1, x2, y2, maxHp, speed, Global.enemyFaction);
    }
}