package rain.entity.projectile;

import rain.entity.spawner.ParticleSpawner;
import rain.entity.spawner.Spawner;
import rain.graphics.Screen;
import rain.graphics.Sprite;


public class WizardProjectile extends Projectile {

    public static final int FIRE_RATE = 4;
    public static final int PROJECTILE_OFFSET = 4;
    public static final int PROJECTILE_SIZE = 8;

    public static final boolean STICKY = false;


    public WizardProjectile(int x, int y, double dir) {
        super(x, y, dir);
//        range = random.nextInt(100) + 20;
        range = 200;
        damage = 20;
        speed = 4;

        sprite = Sprite.projectile_wizard;

        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }

    public void update() {
        if (level.tileCollision(x, y, nx, ny)) {
            if (!STICKY) {
                remove();
                //level.add(new ParticleSpawner((int) x, (int) y, 44, 50, level));
                new ParticleSpawner((int) x, (int) y, 60, 50, level);
            }

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
        screen.renderProjectile((int) x - 3, (int) y, this, 0);
    }
}
