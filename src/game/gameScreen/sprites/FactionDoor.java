package game.gameScreen.sprites;

import engine.managers.ObjectInstanceManager;
import engine.objects.Sprite;
import engine.screens.Screen;
import game.Faction;

public class FactionDoor extends GameObject {
    private Faction faction;
    public Faction getFaction() {return faction;}
    public FactionDoor(Screen screen, String imgPath, double x1, double y1, double x2, double y2, Faction faction) {
        super(screen,imgPath,
                x1,y1,x2,y2);
        this.faction = faction;
        ObjectInstanceManager.getInstance().addInstance(this,FactionDoor.class);
    }
    @Override
    public void delete() {
        super.delete();
        ObjectInstanceManager.getInstance().removeInstance(this,FactionDoor.class);
    }
}