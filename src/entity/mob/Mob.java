package entity.mob;

import entity.Entity;
import graphics.Sprite;

public abstract class Mob extends Entity {
    /*
    Mob is a generic entity with a Sprite and ability to move
     */
    protected Sprite sprite;
    protected int dir = 0; // 0==north | 1==east | 2==south | 3==west
    protected boolean moving = false;

    public void move(int xa, int ya) {
        // 0==north | 1==east | 2==south | 3==west
        if (xa > 0 && ya == 0) dir = 1;
        if (xa < 0 && ya == 0) dir = 3;
        if (xa == 0 && ya > 0) dir = 2;
        if (xa == 0 && ya < 0) dir = 0;

        // 4==SE | 5==NE | 6==SW | 7==NW
        if (xa > 0 && ya > 0) dir = 4; //SE
        if (xa > 0 && ya < 0) dir = 5; //NE
        if (xa < 0 && ya > 0) dir = 6; //SW
        if (xa < 0 && ya < 0) dir = 7; //NW


        if (!collision(xa, 0)) x += xa;
        if (!collision(0, ya)) y += ya;

    }

    public void update() {

    }

    public void render() {
    }

    private boolean collision(int xa, int ya) {
        if (level.getTile((x + xa) >> 4, (y + ya) >> 4).solid()) return true;
        return false;
    }

}
