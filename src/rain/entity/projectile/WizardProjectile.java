package rain.entity.projectile;

import rain.graphics.Screen;
import rain.graphics.Sprite;
import rain.level.tile.Tile;

public class WizardProjectile extends Projectile {
//TODO: Add collision
    public WizardProjectile(int x, int y, double dir) {
        super(x, y, dir);
        range = random.nextInt(100) + 20;
        damage = 20;
        rateOfFire = 15;
        speed = 3;

        sprite = Sprite.projectile_wizard;

        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }

    public void update() {
        move();
    }

    protected void move() {
        x += nx;
        y += ny;

        if (distance() > range) this.remove();

    }

    private double distance() {
        return Math.sqrt(Math.pow(xOrigin - x, 2) + Math.pow(yOrigin - y, 2)); //phytagoran theorem
    }

    public void render(Screen screen) {
        screen.renderProjectTile((int) x - 3, (int) y, this, 0);
    }
}
