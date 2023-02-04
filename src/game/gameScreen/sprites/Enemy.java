package game.gameScreen.sprites;

import engine.managers.ObjectInstanceManager;
import engine.screens.Screen;
import game.Global;
import game.behaviours.Behaviour;
import game.screens.GameScreen;

public abstract class Enemy extends NPC {
    private double coinsOnDeath;
    public Enemy(Screen screen, int r, int g, int b, int borderR, int borderG, int borderB, double x1, double y1, double x2, double y2, double maxHp, double speed, double contactDamage, double coinsOnDeath) {
        super(screen, r, g, b, borderR, borderG, borderB, x1, y1, x2, y2, maxHp, speed,contactDamage, Global.enemyFaction);
        this.coinsOnDeath = coinsOnDeath;
    }

    @Override
    public void delete() {
        super.delete();
        if(this.getScreen() instanceof GameScreen) {
            ((GameScreen) this.getScreen()).coins += this.coinsOnDeath;
        }
    }

}