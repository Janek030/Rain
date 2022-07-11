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


    protected enum Direction {
        N, E, S, W, SE, NE, SW, NW
    }
    protected Direction dir = Direction.N; // 0-7
    protected Direction shoot_dir = Direction.N; // 0-7
    protected boolean moving = false;
    protected boolean walking = false;
    protected boolean shooting = false;
    private boolean jumping = false;

    public void move(int xa, int ya) {
        if (!collision(0, ya)) y += ya;
        if (!collision(xa, 0)) x += xa;

        if (xa > 0 && ya == 0) dir = Direction.E; //E
        if (xa < 0 && ya == 0) dir = Direction.W; //W
        if (xa == 0 && ya > 0) dir = Direction.S; //S
        if (xa == 0 && ya < 0) dir = Direction.N; //N

        if (xa > 0 && ya > 0) dir = Direction.SE; //SE
        if (xa > 0 && ya < 0) dir = Direction.NE; //NE
        if (xa < 0 && ya > 0) dir = Direction.SW; //SW
        if (xa < 0 && ya < 0) dir = Direction.NW; //NW
    }
    public Mob(int x, int y, Sprite sprite){
        super(x,y, sprite);
    }
    public abstract void update();

    public abstract void render(Screen screen);

    protected void shoot(int x, int y, double dir) {

        int range = (int) (dir / (Math.PI / 8));
        if (-1 < range && range <= 1) {
            shoot_dir = Direction.E; //E
            x += 8;
            y -= 12;
        } else if (1 < range && range <= 3) {
            shoot_dir = Direction.SE; //SE
            x += 4;
        } else if (3 < range && range <= 5) {
            shoot_dir = Direction.S; //S
            x -= 7;
            y -= 2;
        } else if (5 < range && range <= 7) {
            shoot_dir = Direction.SW; //SW
            x -= 18;
            //y -= 12;
        } else if (7 < range || range <= -7) {
            shoot_dir = Direction.W; //W
            x -= 22;
            y -= 12;
        } else if (-7 < range && range <= -5) {
            shoot_dir = Direction.NW; //NW
            x -= 18;
            y -= 15;
        } else if (-5 < range && range <= -3) {
            shoot_dir = Direction.N; //N
            x -= 7;
            y -= 25;
        } else if (-4 < range && range <= -1) {
            shoot_dir = Direction.NE; //NE
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
        int colAreaHeightDown = 16; //20
        int colAreaHeightTop = 1; //5
        for (int c = 0; c < 4; c++) {
            //TODO: Issue: If collision area is wider/higher than tile size (16px) character might pass through solid tile
            xt = ((x + xa) + c % 2 * colAreaRight - colAreaLeft) >> 4;
            yt = ((y + ya) + c / 2 * colAreaHeightDown - colAreaHeightTop) >> 4;
            if (level.getTile(xt, yt).solid()) solid = true;
        }
        return solid;
    }
}
