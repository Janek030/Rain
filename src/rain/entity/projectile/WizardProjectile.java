package rain.entity.projectile;

import rain.graphics.Screen;
import rain.graphics.Sprite;


public class WizardProjectile extends Projectile {

    public static final int FIRE_RATE = 5;
    public static final int PROJECTILE_SIZE = 7;
    public static final boolean STICKY = false;


    public WizardProjectile(int x, int y, double dir) {
        super(x, y, dir);
//        range = random.nextInt(100) + 20;
        range = 200;
        damage = 20;
        speed = 2;

        sprite = Sprite.projectile_wizard;

        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }

    public void update() {
        if (level.tileCollision(x, y, nx, ny, PROJECTILE_SIZE)) {
            if(!STICKY) remove();
        } else {
            move();
        }
    }

    protected void move() {
        x += nx;
        y += ny;
        if (distance() > range) this.remove();
    }

    private double distance() {
        return Math.sqrt(((xOrigin - x) * (xOrigin - x)) + ((yOrigin - y) * (yOrigin - y))); //phytagoran theorem
    }

    public void render(Screen screen) {
        screen.renderProjectTile((int) x - 3, (int) y, this, 0);
    }
}
