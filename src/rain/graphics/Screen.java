package rain.graphics;

import rain.entity.projectile.Projectile;
import rain.level.tile.Tile;

import java.util.Random;

public class Screen {
    public int width;
    public int height;
    public int[] pixels;
    public final int MAP_SIZE = 8;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;
    public int xOffset, yOffset;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; // Map = smaller than pixels[]?

    private Random random = new Random();

    public Screen(int width, int height) {
        /*
        1) initiate internal pixel-array to match screen size
        2) define random color for each tile on map
         */
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
            tiles[i] = random.nextInt(0xffffff);
            tiles[0] = 0; // top left tile is black
        }

    }

    public void clear() {
        /*
        1) overwrite internal pixel with 0 (==black)
         */
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
        //Arrays.fill(pixels, 0);
    }

    public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
        if (fixed) {   //fixed on map
            xp -= xOffset; // move map to opposite direction of player's movement
            yp -= yOffset;
        }
        for (int y = 0; y < sheet.HEIGHT; y++) {
            int ya = y + yp;
            for (int x = 0; x < sheet.WIDTH; x++) {
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                pixels[xa + ya * width] = sheet.pixels[x + y * sheet.WIDTH];
            }

        }
    }
    public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
        if (fixed) {   //fixed on map
            xp -= xOffset; // move map to opposite direction of player's movement
            yp -= yOffset;
        }
        for (int y = 0; y < sprite.getHeight(); y++) {
            int ya = y + yp;
            for (int x = 0; x < sprite.getWidth(); x++) {
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
            }

        }
    }

    public void renderTile(int xp, int yp, Tile tile, int flip) {
        /*
        flip = [0=no flip | 1=horizontal flip | 2=vertical flip | 3=both]
        x|y = tile pixel to render [0-15]
        xs|ys = pixel to render possibly flipped
        xp|yp = tile coordinate (pixel precise) [0-299]
        xa|ya = screen pixel to render
        xOffset|yOffset = location of player
         */

        xp -= xOffset; // move map to opposite direction of player's movement
        yp -= yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int ya = y + yp; //y-absolute; (y + yp) == [0-15] + [offset]
            int ys = y;
            if (flip == 2 || flip == 3) ys = tile.sprite.SIZE - 1 - y;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xa = x + xp; //x-absolute
                int xs = x;
                if (flip == 1 || flip == 3) xs = tile.sprite.SIZE - 1 - x;

                if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height)
                    break; // only render what you can see on screen
                if (xa < 0) xa = 0; //if xa is outside of screen (by less than one tile) reset to 0
                pixels[xa + ya * width] = tile.sprite.pixels[xs + ys * tile.sprite.SIZE];
            }
        }
    }

    public void renderProjectile(int xp, int yp, Projectile p, int flip) {
        xp -= xOffset; // move map to opposite direction of player's movement
        yp -= yOffset;
        for (int y = 0; y < p.getSpriteSize(); y++) {
            int ya = y + yp; //y-a
            // absolute; (y + yp) == [0-15] + [offset]
            int ys = y;
            if (flip == 2 || flip == 3) ys = p.getSpriteSize() - 1 - y;
            for (int x = 0; x < p.getSpriteSize(); x++) {
                int xa = x + xp; //x-absolute
                int xs = x;
                if (flip == 1 || flip == 3) xs = p.getSpriteSize() - 1 - x;

                if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height)
                    break; // only render what you can see on screen
                if (xa < 0) xa = 0; //if xa is outside of screen (by less than one tile) reset to 0

                int col = p.getSprite().pixels[xs + ys * p.getSpriteSize()];
                if (col != 0xFFFF00FF) pixels[xa + ya * width] = col;  // make pink transparent

            }
        }
    }

    public void renderPlayer(int xp, int yp, Sprite sprite) {
        xp -= xOffset; // move map to opposite direction of player's movement
        yp -= yOffset;
        for (int y = 0; y < sprite.SIZE; y++) {
            int ya = y + yp; //y-absolute; (y + yp) == [0-15] + [offset
            for (int x = 0; x < sprite.SIZE; x++) {
                int xa = x + xp; //x-absolute
                if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height)
                    break; // only render what you can see on screen
                if (xa < 0) xa = 0; //if xa is outside of screen (by less than one tile) reset to 0
                int col = sprite.pixels[x + y * sprite.SIZE];
                if (col != 0xFFFF00FF) pixels[xa + ya * width] = col;  // make pink transparent
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}
