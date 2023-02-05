package game.behaviours;

import game.Global;
import game.gameScreen.sprites.Bullet;
import game.gameScreen.sprites.Character;
import game.gameScreen.sprites.NPC;
import game.gameScreen.sprites.NPCTurret;

public class RangedStrafe extends Behaviour {
    private int cooldown = 60;
    private Character target;
    private NPCTurret npcTurret;
    public RangedStrafe(NPC npc, Character target) {
        super(npc);
        this.target = target;
        this.npcTurret = null;
    }

    public RangedStrafe(NPC npc, Character target, NPCTurret npcTurret) {
        super(npc);
        this.target = target;
        this.npcTurret = npcTurret;
    }

    public RangedStrafe(NPC npc) {
        super(npc);
        this.target = this.getNpc().getClosestHostile();
        this.npcTurret = null;
    }

    public RangedStrafe(NPC npc,NPCTurret npcTurret) {
        super(npc);
        this.target = this.getNpc().getClosestHostile();
        this.npcTurret = npcTurret;
    }

    public Character getTarget() {
        return target;
    }

    @Override
    public void onDamage(double hp) {
        super.onDamage(hp);
        if(hp>=1 || Global.randInt(0,4)==0) {this.target = this.getNpc().getClosestHostile();};
    }

    @Override
    public void onTickStart() {
        if(this.cooldown>0) {this.cooldown-=1;}
        if(this.target==null) {
            this.target = npc.getClosestHostile();
        }
        else if(this.target.getHp()<=0) {
            this.target = null;
        }
        else  {
            if(this.npcTurret!=null) {
                this.npcTurret.setAngleFromTarget(this.target.x(),this.target.y());
            }
            if(this.cooldown==0) {
                this.cooldown = 60;
                new Bullet(this.getNpc().getScreen(),"res/BulletRed.png",this.getNpc().x(),this.getNpc().y(),target.x(),target.y(),this.getNpc().getFaction());
            }
        }
    }

    @Override
    public void onTickEnd() {

    }

    @Override
    public void calculateTarget() {
        NPC npc = this.getNpc();
        double speedX = 0;
        double speedY = 0;
        if(this.target!=null) {
            double dir = Global.coorToDir(npc.x(),npc.y(),target.x(),target.y());
            double[] coords = Global.dirToCoor(dir);
            double dist = Global.distance(npc.x(),npc.y(),target.x(),target.y());
            double moveMag = Global.limitMinMax((dist - 250)/100,-1,1);
            speedX += coords[0] * moveMag;
            speedY += coords[1] * moveMag;
            double strafeDir = dir + 90;
            double[] strafeCoords = Global.dirToCoor(strafeDir);
            speedX += strafeCoords[0] * 0.2;
            speedY += strafeCoords[1] * 0.2;
            double mag = Global.distance(0,0,speedX,speedY);
            speedX /= mag;
            speedY /= mag;
            speedX *= npc.getSpeed();
            speedY *= npc.getSpeed();
        }
        npc.setSpeedX(speedX);
        npc.setSpeedY(speedY);

    }
}
