package game.gameScreen.sprites;

import engine.managers.ObjectInstanceManager;
import engine.screens.Screen;
import game.Global;
import game.behaviours.Behaviour;
import game.screens.GameScreen;

public abstract class Enemy extends NPC {
    private double coinsOnDeath;
    public Enemy(Screen screen, String imgpath, double x1, double y1, double x2, double y2, double maxHp, double speed, double contactDamage, double coinsOnDeath) {
        super(screen, imgpath, x1, y1, x2, y2, maxHp, speed,contactDamage, Global.enemyFaction);
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