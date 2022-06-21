package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    public String path;
    //public final int SIZE;
    public final int XSIZE;
    public final int YSIZE;
    public int[] pixels;

    public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256, 256);
    public static SpriteSheet player = new SpriteSheet("/textures/player.png", 512, 256);

    public SpriteSheet(String path, int size) {
        /*
        Constructor for square Spritesheet
        1. initiate path
        2. initiate pixels array that holds (partial) SpriteSheet content
         */
        this.path = path;
        //this.SIZE = size;
        this.XSIZE = size;
        this.YSIZE = size;
        pixels = new int[XSIZE * YSIZE];
        load();
    }

    public SpriteSheet(String path, int width, int height) {
        /*
        Constructor for rectangular Spritesheet
        1. initiate path
        2. initiate pixels array that holds (partial) SpriteSheet content
         */
        this.path = path;
        //this.SIZE = (width * height) / 2;
        this.XSIZE = width;
        this.YSIZE = height;
        pixels = new int[width * height];
        load();
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
