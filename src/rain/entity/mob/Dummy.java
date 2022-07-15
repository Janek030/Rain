package rain.entity.mob;

import rain.Game;
import rain.entity.projectile.WizardProjectile;
import rain.graphics.AnimatedSprite;
import rain.graphics.Screen;

import rain.graphics.SpriteSheet;

import javax.imageio.plugins.tiff.TIFFImageReadParam;

public class Dummy extends Mob {
    private AnimatedSprite dummy_unarmed_S = new AnimatedSprite(SpriteSheet.dummy_unarmed_S, 4, 1, 4, 10);
    private AnimatedSprite dummy_unarmed_SW = new AnimatedSprite(SpriteSheet.dummy_unarmed_SW, 4, 1, 4, 10);
    private AnimatedSprite dummy_unarmed_W = new AnimatedSprite(SpriteSheet.dummy_unarmed_W, 4, 1, 4, 10);
    private AnimatedSprite dummy_unarmed_NW = new AnimatedSprite(SpriteSheet.dummy_unarmed_NW, 4, 1, 4, 10);
    private AnimatedSprite dummy_unarmed_N = new AnimatedSprite(SpriteSheet.dummy_unarmed_N, 4, 1, 4, 10);
    private AnimatedSprite dummy_unarmed_NE = new AnimatedSprite(SpriteSheet.dummy_unarmed_NE, 4, 1, 4, 10);
    private AnimatedSprite dummy_unarmed_E = new AnimatedSprite(SpriteSheet.dummy_unarmed_E, 4, 1, 4, 10);
    private AnimatedSprite dummy_unarmed_SE = new AnimatedSprite(SpriteSheet.dummy_unarmed_SE, 4, 1, 4, 10);
    private AnimatedSprite dummy_shooting_S = new AnimatedSprite(SpriteSheet.dummy_shooting_S, 4, 1, 4, 3);
    private AnimatedSprite dummy_shooting_SW = new AnimatedSprite(SpriteSheet.dummy_shooting_SW, 4, 1, 4, 3);
    private AnimatedSprite dummy_shooting_W = new AnimatedSprite(SpriteSheet.dummy_shooting_W, 4, 1, 4, 3);
    private AnimatedSprite dummy_shooting_NW = new AnimatedSprite(SpriteSheet.dummy_shooting_NW, 4, 1, 4, 3);
    private AnimatedSprite dummy_shooting_N = new AnimatedSprite(SpriteSheet.dummy_shooting_N, 4, 1, 4, 3);
    private AnimatedSprite dummy_shooting_NE = new AnimatedSprite(SpriteSheet.dummy_shooting_NE, 4, 1, 4, 3);
    private AnimatedSprite dummy_shooting_E = new AnimatedSprite(SpriteSheet.dummy_shooting_E, 4, 1, 4, 3);
    private AnimatedSprite dummy_shooting_SE = new AnimatedSprite(SpriteSheet.dummy_shooting_SE, 4, 1, 4, 3);
    private AnimatedSprite animSprite = dummy_unarmed_S;
    double  xa = 0; // class wide defined so it doesn't get reset
    double  ya = 0;// class wide defined so it doesn't get reset

    private int fireRate = 0;
    private int ShootFrequency = 5;
    private double speed = 1;
    private int time = 0;


    public Dummy(int x, int y) {
        super(x << 4,y << 4, null);
//        this.x = x << 4;
//        this.y = y << 4;
        sprite = animSprite;
        dir = Direction.S;
        walking = false;
    }

    public void update() {
        time++;
        if (walking || shooting) animSprite.update();
        else animSprite.setFrame(0);

        if (fireRate > 0) fireRate--;


        if (time % (random.nextInt(50) + 30) == 0) {
            ya = speed * (random.nextInt(3) - 1); //[-1|0|1]
            xa = speed * (random.nextInt(3) - 1);
            if (random.nextInt(4) == 0) {
                xa = 0;
                ya = 0;
            }

            //shooting = (random.nextInt(ShootFrequency) == 0);
        }
        if (xa < 0) {
            if (ya < 0) {
                dir = Direction.NW;
            } else if (ya > 0) {
                dir = Direction.SW;
            } else dir = Direction.W;
        } else if (xa > 0) {
            if (ya < 0) {
                dir = Direction.NE;
            } else if (ya > 0) {
                dir = Direction.SE;
            } else dir = Direction.E;
        } else {
            if (ya < 0) {
                dir = Direction.N;
            } else if (ya > 0) {
                dir = Direction.S;
            } //else walking = false;
        }
//        if (dir == Direction.N || dir == Direction.NE || dir == Direction.NW) ya--;
//        if (dir == Direction.S || dir == Direction.SE || dir == Direction.SW) ya++;
//        if (dir == Direction.W || dir == Direction.NW || dir == Direction.SW) xa--;
//        if (dir == Direction.E || dir == Direction.NE || dir == Direction.SE) xa++;
        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }

        //clear();
        updateShooting();
    }
    private void updateShooting() {
        //shooting = (random.nextInt(10) == 0);
        if (shooting && fireRate <= 0) {

            //TODO: Randomized aim
            double dx = random.nextInt(Game.getWindowWidth()) ; // 400*3/2;
            double dy = random.nextInt(Game.getWindowHeight()); //168*3/2;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);

            fireRate = WizardProjectile.FIRE_RATE;
            animSprite.setFrameRate(Math.ceilDiv(fireRate,2));

        } else {

            animSprite.setFrameRate(animSprite.DEFAULT_RATE);
        }


    }
    public void render(Screen screen) {

        if (walking) {
            //System.out.println("Walking " + dir);
            if (dir == Direction.N) animSprite = dummy_unarmed_N;
            if (dir == Direction.E) animSprite = dummy_unarmed_E;
            if (dir == Direction.S) animSprite = dummy_unarmed_S;
            if (dir == Direction.W) animSprite = dummy_unarmed_W;
            if (dir == Direction.SE) animSprite = dummy_unarmed_SE;
            if (dir == Direction.NE) animSprite = dummy_unarmed_NE;
            if (dir == Direction.SW) animSprite = dummy_unarmed_SW;
            if (dir == Direction.NW) animSprite = dummy_unarmed_NW;
        } else if (shooting) {
            //System.out.println("Shooting " + shoot_dir);
            if (shoot_dir == Direction.N) animSprite = dummy_shooting_N;
            if (shoot_dir == Direction.E) animSprite = dummy_shooting_E;
            if (shoot_dir == Direction.S) animSprite = dummy_shooting_S;
            if (shoot_dir == Direction.W) animSprite = dummy_shooting_W;
            if (shoot_dir == Direction.SE) animSprite = dummy_shooting_SE;
            if (shoot_dir == Direction.NE) animSprite = dummy_shooting_NE;
            if (shoot_dir == Direction.SW) animSprite = dummy_shooting_SW;
            if (shoot_dir == Direction.NW) animSprite = dummy_shooting_NW;
        }
        sprite = animSprite.getSprite();

        int xx = (int) (x - sprite.SIZE / 2);
        int yy = (int) (y - sprite.SIZE / 2);
        screen.renderMob(xx, yy, this);
        //screen.renderMob(x, y, sprite);

    }
}
