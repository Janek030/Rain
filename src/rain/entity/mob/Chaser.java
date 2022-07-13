package rain.entity.mob;

import rain.Game;
import rain.entity.projectile.WizardProjectile;
import rain.graphics.AnimatedSprite;
import rain.graphics.Screen;
import rain.graphics.SpriteSheet;
import rain.level.Level;

import java.util.List;

public class Chaser extends Mob {

    private AnimatedSprite chaser_unarmed_S = new AnimatedSprite(SpriteSheet.chaser_unarmed_S, 4, 1, 4, 10);
    private AnimatedSprite chaser_unarmed_SW = new AnimatedSprite(SpriteSheet.chaser_unarmed_SW, 4, 1, 4, 10);
    private AnimatedSprite chaser_unarmed_W = new AnimatedSprite(SpriteSheet.chaser_unarmed_W, 4, 1, 4, 10);
    private AnimatedSprite chaser_unarmed_NW = new AnimatedSprite(SpriteSheet.chaser_unarmed_NW, 4, 1, 4, 10);
    private AnimatedSprite chaser_unarmed_N = new AnimatedSprite(SpriteSheet.chaser_unarmed_N, 4, 1, 4, 10);
    private AnimatedSprite chaser_unarmed_NE = new AnimatedSprite(SpriteSheet.chaser_unarmed_NE, 4, 1, 4, 10);
    private AnimatedSprite chaser_unarmed_E = new AnimatedSprite(SpriteSheet.chaser_unarmed_E, 4, 1, 4, 10);
    private AnimatedSprite chaser_unarmed_SE = new AnimatedSprite(SpriteSheet.chaser_unarmed_SE, 4, 1, 4, 10);
    private AnimatedSprite chaser_shooting_S = new AnimatedSprite(SpriteSheet.chaser_shooting_S, 4, 1, 4, 3);
    private AnimatedSprite chaser_shooting_SW = new AnimatedSprite(SpriteSheet.chaser_shooting_SW, 4, 1, 4, 3);
    private AnimatedSprite chaser_shooting_W = new AnimatedSprite(SpriteSheet.chaser_shooting_W, 4, 1, 4, 3);
    private AnimatedSprite chaser_shooting_NW = new AnimatedSprite(SpriteSheet.chaser_shooting_NW, 4, 1, 4, 3);
    private AnimatedSprite chaser_shooting_N = new AnimatedSprite(SpriteSheet.chaser_shooting_N, 4, 1, 4, 3);
    private AnimatedSprite chaser_shooting_NE = new AnimatedSprite(SpriteSheet.chaser_shooting_NE, 4, 1, 4, 3);
    private AnimatedSprite chaser_shooting_E = new AnimatedSprite(SpriteSheet.chaser_shooting_E, 4, 1, 4, 3);
    private AnimatedSprite chaser_shooting_SE = new AnimatedSprite(SpriteSheet.chaser_shooting_SE, 4, 1, 4, 3);
    private AnimatedSprite animSprite = chaser_unarmed_S;


    private int fireRate = 0;
    private int ShootFrequency = 5;
    private int time = 0;
    private int xa = 0;
    private int ya = 0;

    public Chaser(int x, int y) {
        super(x << 4, y << 4, null);
//        this.x = x << 4;
//        this.y = y << 4;
        sprite = animSprite;
        dir = Direction.S;
        moving = false;
    }


    private void move() {
        xa = 0;
        ya = 0;


        List<Player> players = level.getPlayers(this,100);
        //Player player = level.getClientPlayer();
        if (players.size() > 0) {
            Player player = players.get(0);

            if (x < player.getX()) xa++;
            if (x > player.getX()) xa--;
            if (y < player.getY()) ya++;
            if (y > player.getY()) ya--;
        }
        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
    }

    public void update() {
        move();
        time++;
        if (walking || shooting) animSprite.update();
        else animSprite.setFrame(0);

        if (fireRate > 0) fireRate--;


//        if (time % (random.nextInt(50) + 30) == 0) {
//            ya = random.nextInt(3) - 1; //[-1|0|1]
//            xa = random.nextInt(3) - 1;
//            if (random.nextInt(4) == 0) {
//                xa = 0;
//                ya = 0;
//            }
//
//            shooting = (random.nextInt(ShootFrequency) == 0);
//        }

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

//        if (xa != 0 || ya != 0) {
//            move(xa, ya);
//            walking = true;
//        } else {
//            walking = false;
//        }

        //clear();
        updateShooting();
    }

    private void updateShooting() {
        //shooting = (random.nextInt(10) == 0);
        if (shooting && fireRate <= 0) {

            //TODO: Randomized aim
            double dx = random.nextInt(Game.getWindowWidth()); // 400*3/2;
            double dy = random.nextInt(Game.getWindowHeight()); //168*3/2;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);

            fireRate = WizardProjectile.FIRE_RATE;
            animSprite.setFrameRate(Math.ceilDiv(fireRate, 2));

        } else {

            animSprite.setFrameRate(animSprite.DEFAULT_RATE);
        }


    }

    public void render(Screen screen) {

        if (walking) {
            //System.out.println("Walking " + dir);
            if (dir == Direction.N) animSprite = chaser_unarmed_N;
            if (dir == Direction.E) animSprite = chaser_unarmed_E;
            if (dir == Direction.S) animSprite = chaser_unarmed_S;
            if (dir == Direction.W) animSprite = chaser_unarmed_W;
            if (dir == Direction.SE) animSprite = chaser_unarmed_SE;
            if (dir == Direction.NE) animSprite = chaser_unarmed_NE;
            if (dir == Direction.SW) animSprite = chaser_unarmed_SW;
            if (dir == Direction.NW) animSprite = chaser_unarmed_NW;
        } else if (shooting) {
            //System.out.println("Shooting " + shoot_dir);
            if (shoot_dir == Direction.N) animSprite = chaser_shooting_N;
            if (shoot_dir == Direction.E) animSprite = chaser_shooting_E;
            if (shoot_dir == Direction.S) animSprite = chaser_shooting_S;
            if (shoot_dir == Direction.W) animSprite = chaser_shooting_W;
            if (shoot_dir == Direction.SE) animSprite = chaser_shooting_SE;
            if (shoot_dir == Direction.NE) animSprite = chaser_shooting_NE;
            if (shoot_dir == Direction.SW) animSprite = chaser_shooting_SW;
            if (shoot_dir == Direction.NW) animSprite = chaser_shooting_NW;
        }
        sprite = animSprite.getSprite();

        int xx = x - sprite.SIZE / 2;
        int yy = y - sprite.SIZE / 2;
        screen.renderMob(xx, yy, this);
        //screen.renderMob(x, y, sprite);

    }
}
