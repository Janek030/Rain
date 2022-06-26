package rain.entity.mob;

import rain.entity.Entity;
import rain.entity.particle.Particle;
import rain.entity.projectile.Projectile;
import rain.entity.projectile.WizardProjectile;
import rain.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public abstract class Mob extends Entity {
    /*
    Mob is a generic rain.entity with a Sprite and ability to move
     */
    protected Sprite sprite;
    protected int dir = 0; // 0==north | 1==east | 2==south | 3==west
    protected boolean moving = false;
    protected boolean walking = false;


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

        if (!collision(0, ya)) y += ya;
        if (!collision(xa, 0)) x += xa;
    }

    public void update() {
    }

    protected void shoot(int x, int y, double dir) {
        Projectile p = new WizardProjectile(x, y, dir);
        level.add(p);
    }

    public void render() {
    }

    private boolean collision(int xa, int ya) {
        /*
        1) Determine future position pixel
        2) Widen Collision Coordinate to Collision area
        3) Convert Collision area corner pixels to tile coordinate
        4) Check whether target tile is solid
         */
        boolean solid = false;
        int xt, yt;
        int colAreaRight = 11;
        int colAreaLeft = 6;
        int colAreaHeightDown = 20;
        int colAreaHeightTop = 5;
        for (int c = 0; c < 4; c++) {

            xt = ((x + xa) + c % 2 * colAreaRight - colAreaLeft) >> 4;
            yt = ((y + ya) + c / 2 * colAreaHeightDown - colAreaHeightTop) >> 4;
            if (level.getTile(xt, yt).solid()) solid = true;
        }
        return solid;
    }


}
