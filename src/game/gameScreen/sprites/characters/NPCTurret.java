package game.gameScreen.sprites.characters;

import engine.objects.Sprite;
import engine.screens.Screen;
import game.Global;

public class NPCTurret extends Sprite {
    public NPCTurret(Screen screen, NPC npc) {
        super(screen, "res/TurretHeadWooden.png",
                npc.x()-npc.width(),
                npc.y()-npc.height(),
                npc.x()+npc.width(),
                npc.y()+npc.height());
    }
    public void setAngleFromTarget(double x, double y) {
        this.setAngle(Global.coorToDir(this.x(),this.y(),x,y));
    }
}
