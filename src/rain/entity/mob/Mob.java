package rain.entity.mob;

import rain.entity.Entity;
import rain.entity.particle.Particle;
import rain.entity.projectile.Projectile;
import rain.entity.projectile.WizardProjectile;
import rain.graphics.Screen;
import rain.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public abstract class Mob extends Entity {
    /*
    Mob is a generic rain.entity with a Sprite and ability to move
     */
    protected Sprite sprite;
    protected int dir = 0; // 0-7
    protected int shoot_dir = 0; // 0-7
    protected boolean moving = false;
    protected boolean walking = false;
    protected boolean shooting = false;
    private boolean jumping = false;

    public void move(int xa, int ya) {
        if (!collision(0, ya)) y += ya;
        if (!collision(xa, 0)) x += xa;

        if (xa > 0 && ya == 0) dir = 1; //E
        if (xa < 0 && ya == 0) dir = 3; //W
        if (xa == 0 && ya > 0) dir = 2; //S
        if (xa == 0 && ya < 0) dir = 0; //N

        if (xa > 0 && ya > 0) dir = 4; //SE
        if (xa > 0 && ya < 0) dir = 5; //NE
        if (xa < 0 && ya > 0) dir = 6; //SW
        if (xa < 0 && ya < 0) dir = 7; //NW
    }

    public abstract void update();
    public abstract void render(Screen screen);
    protected void shoot(int x, int y, double dir) {
        int range = (int) (dir / (Math.PI / 8));
        if (-1 < range && range <= 1) {
            shoot_dir = 1; //E
            x += 8;
            y -= 12;
        } else if (1 < range && range <= 3) {
            shoot_dir = 4; //SE
            x += 4;
        } else if (3 < range && range <= 5) {
            shoot_dir = 2; //S
            x -= 7;
            y -= 2;
        } else if (5 < range && range <= 7) {
            shoot_dir = 6; //SW
            x -= 18;
            //y -= 12;
        } else if (7 < range || range <= -7) {
            shoot_dir = 3; //W
            x -= 22;
            y -= 12;
        } else if (-7 < range && range <= -5) {
            shoot_dir = 7; //NW
            x -= 18;
            y -= 15;
        } else if (-5 < range && range <= -3) {
            shoot_dir = 0; //N
            x -= 7;
            y -= 25;
        } else if (-4 < range && range <= -1) {
            shoot_dir = 5; //NE
            x += 4;
            y -= 15;
        }
        Projectile p = new WizardProjectile(x, y, dir);
        level.add(p);
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
