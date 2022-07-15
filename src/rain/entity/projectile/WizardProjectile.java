package rain.entity.projectile;

import rain.entity.spawner.ParticleSpawner;
import rain.graphics.Screen;
import rain.graphics.Sprite;


public class WizardProjectile extends Projectile {

    public static final int FIRE_RATE = 10;
    public static final int PROJECTILE_XOFFSET = 4;
    public static final int PROJECTILE_YOFFSET = 4;
    public static final int PROJECTILE_SIZE = 8;

    public static final boolean STICKY = false;


    public WizardProjectile(double x, double y, double dir) {
        super(x, y, dir);
//        range = random.nextInt(100) + 20;
        range = 200;
        damage = 20;
        speed = 3;

        sprite = Sprite.projectile_wizard;

        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }

    public void update() {
        if (level.tileCollision((int)(x + nx), (int)(y + ny), PROJECTILE_SIZE, PROJECTILE_XOFFSET, PROJECTILE_YOFFSET)) {
            if (!STICKY) {
                remove();
                //level.add(new ParticleSpawner((int) x, (int) y, 44, 50, level));

//                new ParticleSpawner((int) x, (int) y, 44, 50, level);

                int xt = (int) x+PROJECTILE_XOFFSET+PROJECTILE_SIZE/2;
                int yt = (int) y+PROJECTILE_YOFFSET+PROJECTILE_SIZE/2;
                new ParticleSpawner( xt,  yt, 44, 50, level);
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

        screen.renderProjectile((int) x, (int) y, this, 0);
    }
}
