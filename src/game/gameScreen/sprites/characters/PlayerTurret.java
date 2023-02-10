package game.gameScreen.sprites.characters;

import engine.objects.Sprite;
import engine.screens.Screen;
import engine.utils.Mouse;
import game.Global;

public class PlayerTurret extends Sprite {
    public static final double HALF_SIZE = 32;
    private final Player player;
    public PlayerTurret(Screen screen, Player player) {
        super(screen, "res/TurretHeadWooden.png",
                player.x()-HALF_SIZE,
                player.y()-HALF_SIZE,
                player.x()+HALF_SIZE,
                player.y()+HALF_SIZE);
        this.setFixChildrenAngleToParent(false);
        this.player = player;
    }

    @Override
    public void tick() {
        super.tick();
        double mouseX = Mouse.relMousePosX(this.getScreen());
        double mouseY = Mouse.relMousePosY(this.getScreen());
        double dir = Global.coorToDir(this.x(),this.y(),mouseX,mouseY);
        this.setAngle(dir);

    }
}
