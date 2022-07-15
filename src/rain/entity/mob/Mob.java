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
    //protected boolean moving = false;
    protected boolean walking = false;
    protected boolean shooting = false;
    private boolean jumping = false;

    public void move(double xa, double ya) {

        if (xa > 0 && ya == 0) dir = Direction.E; //E
        if (xa < 0 && ya == 0) dir = Direction.W; //W
        if (xa == 0 && ya > 0) dir = Direction.S; //S
        if (xa == 0 && ya < 0) dir = Direction.N; //N

        if (xa > 0 && ya > 0) dir = Direction.SE; //SE
        if (xa > 0 && ya < 0) dir = Direction.NE; //NE
        if (xa < 0 && ya > 0) dir = Direction.SW; //SW
        if (xa < 0 && ya < 0) dir = Direction.NW; //NW

        //System.out.println("Move: [" + this.x + "|" + this.y + "] >> [" + xa + "|" + ya + "]");

        while (xa != 0) {
            if (Math.abs(xa) > 1) {
                if (!collision(abs(xa), 0)) this.x += abs(xa);
                xa -= abs(xa);
            } else {
                if (!collision(abs(xa), 0)) this.x += xa;
                xa = 0;
            }
        }

        while (ya != 0) {
            if (Math.abs(ya) > 1) {
                if (!collision(0, abs(ya))) this.y += abs(ya);
                ya -= abs(ya);
            } else {
                if (!collision(0, abs(ya))) this.y += ya;
                ya = 0;
            }
        }

//        for (int x = 0; x < Math.abs(xa); x++) {
//            if (!collision(abs(xa), 0)) this.x += abs(xa);
//        }
//
//        for (int y = 0; y < Math.abs(ya); y++) {
//            if (!collision(0, abs(ya))) this.y += abs(ya);

    }


    public Mob(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    private int abs(double value) {
        if (value < 0) return -1;
        return 1;

    }


    public abstract void update();

    public abstract void render(Screen screen);

    protected void shoot(double x, double y, double dir) {

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


    private boolean collision(double xa, double ya) {
        /*
        1) Determine future position pixel
        2) Widen Collision Coordinate to Collision area
        3) Convert Collision area corner pixels to tile coordinate
        4) Check whether target tile is solid
         */
        boolean solid = false;
        double xt, yt;

        for (int c = 0; c < 4; c++) {
            //TODO: Adjust  Offsets
            xt = ((x + xa) - c % 2 * 4 - 6) / 16; // remainder  0, 1, 0, 1
            yt = ((y + ya) - c / 2 * 32 + 15) / 16; // quotient   0, 0, 1, 1

//            System.out.println(c + ": [" +xt +"|" + yt  + "]");


            //in case of offset/size-shift --> round up
            int ix = (int) Math.ceil(xt); //round up
            int iy = (int) Math.ceil(yt); //round up

            //otherwise --> round down
            if (c % 2 == 0) ix = (int) Math.floor(xt); //round down
            if (c / 2 == 0) iy = (int) Math.floor(yt); //round down
//            System.out.println(c + ": [" +ix  +"|" + iy  + "]");
            if (level.getTile(ix, iy).solid()) solid = true;
        }
        return solid;
    }
}
