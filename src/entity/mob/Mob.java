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

    public void move() {

    }

    public void update() {

    }

    private boolean collision(){
        return false;
    }
}
