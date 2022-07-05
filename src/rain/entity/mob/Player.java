package rain.entity.mob;

import rain.Game;
import rain.entity.projectile.Projectile;
import rain.entity.projectile.WizardProjectile;
import rain.graphics.AnimatedSprite;
import rain.graphics.Screen;
import rain.graphics.Sprite;
import rain.graphics.SpriteSheet;
import rain.input.Keyboard;
import rain.input.Mouse;


public class Player extends Mob {

    private Keyboard input;
    private Mouse mouse;
    private Sprite sprite;
//    private int anim;


    private AnimatedSprite player_unarmed_S = new AnimatedSprite(SpriteSheet.player_unarmed_S, 4, 1, 4,10);
    private AnimatedSprite player_unarmed_SW = new AnimatedSprite(SpriteSheet.player_unarmed_SW, 4, 1, 4,10);
    private AnimatedSprite player_unarmed_W = new AnimatedSprite(SpriteSheet.player_unarmed_W, 4, 1, 4,10);
    private AnimatedSprite player_unarmed_NW = new AnimatedSprite(SpriteSheet.player_unarmed_NW, 4, 1, 4,10);
    private AnimatedSprite player_unarmed_N = new AnimatedSprite(SpriteSheet.player_unarmed_N, 4, 1, 4,10);
    private AnimatedSprite player_unarmed_NE = new AnimatedSprite(SpriteSheet.player_unarmed_NE, 4, 1, 4,10);
    private AnimatedSprite player_unarmed_E = new AnimatedSprite(SpriteSheet.player_unarmed_E, 4, 1, 4,10);
    private AnimatedSprite player_unarmed_SE = new AnimatedSprite(SpriteSheet.player_unarmed_SE, 4, 1, 4,10);
    private AnimatedSprite player_shooting_S = new AnimatedSprite(SpriteSheet.player_shooting_S, 4, 1, 4, 3);
    private AnimatedSprite player_shooting_SW = new AnimatedSprite(SpriteSheet.player_shooting_SW, 4, 1, 4,3);
    private AnimatedSprite player_shooting_W = new AnimatedSprite(SpriteSheet.player_shooting_W, 4, 1, 4,3);
    private AnimatedSprite player_shooting_NW = new AnimatedSprite(SpriteSheet.player_shooting_NW, 4, 1, 4,3);
    private AnimatedSprite player_shooting_N = new AnimatedSprite(SpriteSheet.player_shooting_N, 4, 1, 4,3);
    private AnimatedSprite player_shooting_NE = new AnimatedSprite(SpriteSheet.player_shooting_NE, 4, 1, 4,3);
    private AnimatedSprite player_shooting_E = new AnimatedSprite(SpriteSheet.player_shooting_E, 4, 1, 4, 3);
    private AnimatedSprite player_shooting_SE = new AnimatedSprite(SpriteSheet.player_shooting_SE, 4, 1, 4, 3);
    private AnimatedSprite animSprite = player_unarmed_S;
    private int fireRate = 0;


    public Player(Keyboard input) {
        this.input = input;
        sprite = player_unarmed_S;
//        animSprite = player_unarmed_S;
    }

    public Player(int x, int y, Keyboard input, Mouse mouse) {
        this.x = x;
        this.y = y;
        this.input = input;
        this.mouse = mouse;
        sprite = player_unarmed_S;
        fireRate = WizardProjectile.FIRE_RATE;
    }

    public void update() {
        if (walking || shooting) animSprite.update();
        else animSprite.setFrame(0);

        if (fireRate > 0) fireRate--;
        int xa = 0, ya = 0;
        if (input.up) ya--;
        if (input.down) ya++;
        if (input.left) xa--;
        if (input.right) xa++;
        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }

        clear();
        updateShooting();
    }

    private void clear() {
        for (int i = 0; i < level.getProjectiles().size(); i++) {
            Projectile p = level.getProjectiles().get(i);

            if (p.isRemoved()) {
                //System.out.println("projectile" + i + " out of " +projectiles.size() + "removed: " + p.isRemoved());
                level.getProjectiles().remove(i);
            }
        }
    }

    private void updateShooting() {
        shooting = (mouse.getButton() == 1);
        if (shooting && fireRate <= 0) {

            double dx = mouse.getX() - Game.getWindowWidth() / 2; // 400*3/2;
            double dy = mouse.getY() - Game.getWindowHeight() / 2; //168*3/2;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);

            fireRate = WizardProjectile.FIRE_RATE;
            animSprite.setFrameRate(Math.ceilDiv(fireRate,2));

        } else {

            animSprite.setFrameRate(animSprite.DEFAULT_RATE);
        }


    }

    public void render(Screen screen) {

        // 0==north | 1==east | 2==south | 3==west
        if (walking) {
            //System.out.println("Walking " + dir);
            if (dir == Direction.N) animSprite = player_unarmed_N;
            if (dir == Direction.E) animSprite = player_unarmed_E;
            if (dir == Direction.S) animSprite = player_unarmed_S;
            if (dir == Direction.W) animSprite = player_unarmed_W;
            if (dir == Direction.SE) animSprite = player_unarmed_SE;
            if (dir == Direction.NE) animSprite = player_unarmed_NE;
            if (dir == Direction.SW) animSprite = player_unarmed_SW;
            if (dir == Direction.NW) animSprite = player_unarmed_NW;
        } else if (shooting) {
            //System.out.println("Shooting " + shoot_dir);
            if (shoot_dir == Direction.N) animSprite = player_shooting_N;
            if (shoot_dir == Direction.E) animSprite = player_shooting_E;
            if (shoot_dir == Direction.S) animSprite = player_shooting_S;
            if (shoot_dir == Direction.W) animSprite = player_shooting_W;
            if (shoot_dir == Direction.SE) animSprite = player_shooting_SE;
            if (shoot_dir == Direction.NE) animSprite = player_shooting_NE;
            if (shoot_dir == Direction.SW) animSprite = player_shooting_SW;
            if (shoot_dir == Direction.NW) animSprite = player_shooting_NW;
        }
        sprite = animSprite.getSprite();

        int xx = x - sprite.SIZE / 2;
        int yy = y - sprite.SIZE / 2;
        screen.renderMob(xx, yy, sprite);

    }
}
