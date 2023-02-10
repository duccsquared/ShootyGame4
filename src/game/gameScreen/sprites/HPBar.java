package game.gameScreen.sprites;

import engine.objects.Sprite;
import game.gameScreen.sprites.characters.Character;

public class HPBar {
    private boolean showBar = false;
    private Character chara;
    private Sprite bg;
    private Sprite hp;
    public HPBar(Character chara) {
        this.chara = chara;
        this.bg = new BGSprite(chara);
        this.hp = new HPSprite(chara);

    }
    public void tick() {
//        this.bg.setVisible(showBar);
//        this.hp.setVisible(showBar);
        this.bg.setPos(this.chara.x(),this.chara.y()-chara.width()*0.5-15);
        double hpRatio = this.chara.getHp()/this.chara.getMaxHp();
        this.hp.setPos(
            chara.x()-28,
            chara.y()-chara.width()*0.5-18,
            chara.x()-28 + hpRatio * 56,
            chara.y()-chara.width()*0.5-12
        );

    }

    public void delete() {
        this.bg.delete();
        this.hp.delete();
    }
}

class BGSprite extends Sprite {
    public BGSprite(Character chara) {
        super(chara.getScreen(), 10, 10, 10, 10, 10, 10,
                chara.x()-30,
                chara.y()-chara.width()*0.5-20,
                chara.x()+30,
                chara.y()-chara.width()*0.5-10
        );
        this.setDrawLast(true);
    }
}
class HPSprite extends Sprite {
    public HPSprite(Character chara) {
        super(chara.getScreen(), 255, 10, 10, 255, 10, 10,
                chara.x()-28,
                chara.y()-chara.width()*0.5-18,
                chara.x()+28,
                chara.y()-chara.width()*0.5-12
        );
        this.setDrawLast(true);
    }
}