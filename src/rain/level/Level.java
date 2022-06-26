package rain.level;

import rain.entity.Entity;
import rain.entity.particle.Particle;
import rain.entity.projectile.Projectile;
import rain.graphics.Screen;
import rain.level.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class Level {
    protected int width, height; //tile precise
    protected int[] tilesInt;
    protected int[] tilesCol; // color values

    private List<Entity> entities = new ArrayList<Entity>();
    private List<Projectile> projectiles = new ArrayList<Projectile>();
    private List<Particle> particles = new ArrayList<Particle>();

    public Level(int width, int height) {
        this.width = width;
        this.height = height;

        tilesInt = new int[width * height];

        generateLevel();
    }

    public Level(String path) {

        loadLevel(path);
        generateLevel();

    }

    protected void generateLevel() {

    }

    protected void loadLevel(String path) {

    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }

        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).update();
        }

        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).update();
        }
        remove();
    }

    private void remove(){
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isRemoved()) entities.remove(i);
        }

        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).isRemoved()) projectiles.remove(i);
        }

        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).isRemoved()) particles.remove(i);
        }
    }
    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    private void time() {
    }

    public boolean tileCollision(double x, double y, double xa, double ya) {
        /*
        x|x current position
        1) Determine future position pixel
        2) Widen Collision Coordinate to Collision area
        3) Convert Collision area corner pixels to tile coordinate
        4) Check whether target tile is solid
         */
        boolean collision = false;
        int xt, yt;

        for (int c = 0; c < 4; c++) {
            xt = (((int) x + (int) xa) + c % 2 * 9) >> 4;
            yt = (((int) y + (int) ya) + c / 2 * 7 + 4) >> 4;
            if (getTile((int) xt, (int) yt).solid()) collision = true;
        }
        return collision;
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        /*
        1) xScroll/yScroll  is where player is located
        2) define render region of screen
        3) convert coordinates to tile precision
        4) call tile's render method
         */
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4; // divide by 16; y value for x=0 (left border)
        int x1 = ((xScroll + screen.width) >> 4) + 1; //right border (+1 tile to allow partial tile)
        int y0 = yScroll >> 4; // divide by 16; x value for y=0 (upper border)
        int y1 = ((yScroll + screen.height) >> 4) + 1; //lower border

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                //getTile(x, y).render(x, y, screen);
                //if (((x + y * width) < 0) || ((x + y * width) >= width * height)) {
                if (x < 0 || y < 0 || x >= width || y >= height) {
                    Tile.voidTile.render(x, y, screen);
                    continue;
                }
                getTile(x, y).render(x, y, screen);
                //tiles[x + y * width].render(x, y, screen);
            }
        }

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(screen);
        }
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).render(screen);
        }
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).render(screen);
        }
    }

    public void add(Entity e) {
        e.init(this);
        if (e instanceof Particle) {
            particles.add((Particle) e);
        } else if (e instanceof Projectile) {
            projectiles.add((Projectile) e);
        } else {
            entities.add(e);
        }
    }


    public Tile getTile(int x, int y) {
        //will be overwritten by Level specific implementation
        return Tile.voidTile;
    }
}