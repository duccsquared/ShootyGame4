package game;

import game.gameScreen.sprites.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Faction {
    private final String ID;
    private String name;
    private ArrayList<Faction> hostileFactionArray = new ArrayList<>();

    public Faction(String id, String name) {
        this.ID = id;
        this.name = name;
    }
    public Faction(String name) {
        this.name = name;
        this.ID = name;
    }
    public static void setAsHostile(Faction faction1, Faction faction2) {
        faction1.addHostileFaction(faction2);
        faction2.addHostileFaction(faction1);
    }
    public static void setAsNotHostile(Faction faction1, Faction faction2) {
        faction1.removeHostileFaction(faction2);
        faction2.removeHostileFaction(faction1);
    }
    public void addHostileFaction(Faction faction) {
        if(!this.hostileFactionArray.contains(faction)) {
            this.hostileFactionArray.add(faction);
        }
    }
    public void removeHostileFaction(Faction faction) {
        if(this.hostileFactionArray.contains(faction)) {
            this.hostileFactionArray.remove(faction);
        }
    }
    public boolean isHostileTo(Faction faction) {
        return this.hostileFactionArray.contains(faction);
    }
    public boolean isHostileTo(Entity entity) {
        return this.hostileFactionArray.contains(entity.getFaction());
    }
    public String getName() {return name;}
    public String getID() {return ID;}
    public void setName(String name) {this.name = name;}

}
