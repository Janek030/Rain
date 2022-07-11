package rain.entity.particle;

import rain.entity.Entity;
import rain.graphics.Screen;
import rain.graphics.Sprite;


public class Particle extends Entity {
    private Sprite sprite;
    private int life;
    private int time;
    protected double xx, yy, zz;
    protected double xa, ya, za;

    public Particle(int x, int y, int life) {
        super(x,y,Sprite.particle_normal);
//        this.x = x;
//        this.y = y;
        sprite = Sprite.particle_normal;
        this.xx = x;
        this.yy = y;
        this.life = life + (random.nextInt(25));



        this.xa = random.nextGaussian();
        this.ya = random.nextGaussian();
        this.zz = random.nextFloat() + 5.0;
    }


    public void update() {
        time++;
        if (time >= Integer.MAX_VALUE - 1000) time = 0;
        if (time > life) remove();

        za -= 0.1;
        if (zz < 0) {
            zz = 0;
            za *= -0.55;
            xa *= 0.4;
            ya *= 0.4;
        }
        move(xx + xa, (yy + ya) + (zz + za));
    }

    private void move(double x, double y) {
        if (collision(x, y)) {
            xa *= -1;
            ya *= -1;
        }
        this.xx += xa;
        this.yy += ya;
        this.zz += za;
    }

    public boolean collision(double x, double y) {

        boolean collision = false;
        double xt, yt;

        for (int c = 0; c < 4; c++) {
            //TODO: Review - Why would this be 16 if the particle is only 2px wide?
            xt = (x - (c % 2) * 16) / 16; // remainder  0, 1, 0, 1
            yt = (y - (c / 2) * 16) / 16; // quotient   0, 0, 1, 1
            // [x,         y ]
            // [x + size,  y ]
            // [x,         y + size]
            // [x + size , y + size]

            //in case of offset/size-shift --> round up
            int ix = (int) Math.ceil(xt); //round up
            int iy = (int) Math.ceil(yt); //round up

            //otherwise --> round down
            if (c % 2 == 0) ix = (int) Math.floor(xt); //round down
            if (c / 2 == 0) iy = (int) Math.floor(yt); //round down
            if (level.getTile(ix, iy).solid()) collision = true;

        }
        return collision;
    }

    public void render(Screen screen) {
        screen.renderSprite((int) xx, (int) yy - (int) zz, sprite, true);
    }

}
