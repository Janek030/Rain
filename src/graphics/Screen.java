package graphics;

import level.tile.Tile;

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

//    public void render(int xOffset, int yOffset) {
//        /*
//        1) Write content starting at (xx|yy) ~ (xx = x + xOffset)
//        2) Do not write outside of screen
//        3) Determine which pixel-index corresponds to which tile-index
//        4) When end of map/tile[] is reached start at beginning (i.e. repeat map)
//         */
//
//        for (int y = 0; y < height; y++) {
//            int yp = y + yOffset;
//            if (yp < 0 || yp >= height) continue;
//            for (int x = 0; x < width; x++) {
//                int xp = x + xOffset;
//                if (xp < 0 || xp >= width) continue;
//
//                //int tileIndex = (x / 16) + (y / 16) * MAP_SIZE; // increase tile index every 16px
//                //int tileIndex = (xp >> 4) + (yp >> 4)  * MAP_SIZE; // same as above with bit shift
//                //int tileIndex = ((xp >> 4) & MAP_SIZE_MASK) + ((yp >> 4) & MAP_SIZE_MASK) * MAP_SIZE; // same but with repeat when ran out of tile index
//
//                //pixels[x + y * width] = tiles[tileIndex];
//                pixels[xp + yp * width] = Sprite.grass.pixels[(x & 15) + (y & 15) * Sprite.grass.SIZE];
//            }
//        }
//
//    }

    public void renderTile(int xp, int yp, Tile tile) {
        /*
        x|y = tile pixel to render [0-15]
        xp|yp = tile coordinate (pixel precise) [0-299]
        xa|ya = screen pixel to render
        xOffset|yOffset = location of player
         */

        xp -= xOffset; // move map to opposite direction of player's movement
        yp -= yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int ya = y + yp; //y-absolute; (y + yp) == [0-15] + [offset
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xa = x + xp; //x-absolute
                if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height)
                //if (xa < 0 || xa >= width || ya < 0 || ya >= height)
                    break; // only render what you can see on screen
                if (xa < 0) xa = 0; //if xa is outside of screen (by less than one tile) reset to 0
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}
