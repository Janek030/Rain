package rain.entity.projectile;

import rain.entity.Entity;
import rain.graphics.Sprite;

import java.util.Random;

public abstract class Projectile extends Entity {
    protected final double xOrigin, yOrigin;
    protected double angle;

    protected double distance;
    protected Sprite sprite;
    protected double nx, ny;
    protected double speed, range, damage;

    protected final Random random = new Random();

    public Projectile(double x, double y, double dir){
        super(x,y,null);
        this.xOrigin = x;
        this.yOrigin = y;
        this.angle = dir;
        //this.x = x;
        //this.y = y;

    }

    public Sprite getSprite(){
        return sprite;
    }

    public int getSpriteSize(){
        return sprite.SIZE;
    }
}
