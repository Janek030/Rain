package rain.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    public String path;
    //public final int SIZE;
    public final int WIDTH;
    public final int HEIGHT;
    public int[] pixels;


    public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256, 256);
    public static SpriteSheet projectile_wizard = new SpriteSheet("/textures/projectiles/wizard.png", 48, 48);

    public static SpriteSheet player = new SpriteSheet("/textures/player.png", 512, 256);
    public static SpriteSheet player_unarmed = new SpriteSheet(player, 0, 0, 4, 8, 32);
    public static SpriteSheet player_sword = new SpriteSheet(player, 4, 0, 4, 8, 32);
    public static SpriteSheet player_gun = new SpriteSheet(player, 8, 0, 4, 8, 32);
    public static SpriteSheet player_jump = new SpriteSheet(player, 12, 0, 4, 8, 32);

    public static SpriteSheet player_unarmed_S = new SpriteSheet(player_unarmed, 0, 0, 4, 1, 32);
    public static SpriteSheet player_unarmed_SW = new SpriteSheet(player_unarmed, 0, 1, 4, 1, 32);
    public static SpriteSheet player_unarmed_W = new SpriteSheet(player_unarmed, 0, 2, 4, 1, 32);
    public static SpriteSheet player_unarmed_NW = new SpriteSheet(player_unarmed, 0, 3, 4, 1, 32);
    public static SpriteSheet player_unarmed_N = new SpriteSheet(player_unarmed, 0, 4, 4, 1, 32);
    public static SpriteSheet player_unarmed_NE = new SpriteSheet(player_unarmed, 0, 5, 4, 1, 32);
    public static SpriteSheet player_unarmed_E = new SpriteSheet(player_unarmed, 0, 6, 4, 1, 32);
    public static SpriteSheet player_unarmed_SE = new SpriteSheet(player_unarmed, 0, 7, 4, 1, 32);

    private Sprite[] sprites;

//    public SpriteSheet(String path, int size) {
//        /*
//        Constructor for square Spritesheet
//        1. initiate path
//        2. initiate pixels array that holds (partial) SpriteSheet content
//         */
//        this.path = path;
//        //this.SIZE = size;
//        this.WIDTH = size;
//        this.HEIGHT = size;
//        pixels = new int[WIDTH * HEIGHT];
//        load();
//    }

    public SpriteSheet(String path, int width, int height) {
        /*
        Constructor for rectangular Spritesheet
        1. initiate path
        2. initiate pixels array that holds (partial) SpriteSheet content
         */
        this.path = path;
        //this.SIZE = (width * height) / 2;
        this.WIDTH = width;
        this.HEIGHT = height;
        pixels = new int[width * height];
        load();
    }

    public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
        int xx = x * spriteSize;
        int yy = y * spriteSize;
        int w = width * spriteSize;
        int h = height * spriteSize;
        this.WIDTH = w;
        this.HEIGHT = h;

        pixels = new int[w * h];
        for (int y0 = 0; y0 < h; y0++) {
            int yp = yy + y0;
            for (int x0 = 0; x0 < w; x0++) {
                int xp = xx + x0;
                pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
            }
        }
        int frame = 0;
        sprites = new Sprite[width*height];
        //loop through sprites
        for (int ya = 0; ya < height; ya++) {
            for (int xa = 0; xa < width; xa++) {
                int[] spritePixels = new int[spriteSize * spriteSize];
                //loop through pixels
                for (int y0 = 0; y0 < spriteSize; y0++) {
                    for (int x0 = 0; x0 < spriteSize; x0++) {
                        spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * WIDTH];
                    }
                }
                Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
                sprites[frame] = sprite;
                frame++;
            }
        }
    }

        public Sprite[] getSprites() {
        return sprites;
    }
    private void load() {
        /*
        1. load image
        2. convert image pixels into sheet's pixel array
        3. Issue: What if, image is smaller than declared size????
         */

        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
