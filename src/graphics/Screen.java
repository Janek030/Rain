package graphics;

import java.util.Arrays;
import java.util.Random;

public class Screen {
    private int width;
    private int height;
    public int[] pixels;

    public int[] tiles = new int[64 * 64];

    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        for (int i = 0; i < 64 * 64; i++) {
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    public void clear() {
        //for (int i = 0; i < pixels.length; i++) {
        //    pixels[i] = 0;
        //}
        Arrays.fill(pixels,0);
    }

    public void render() {
        for (int y = 0; y < height; y++) {
            //if (y < 0 || y >= height) break;
            for (int x = 0; x < width; x++) {
                //if (x < 0 || x >= width) break;
                //int tileIndex = (x / 16) + (y / 16) * 64; // every tile is 16 pixels wide
                int tileIndex = (x >> 4 ) + (y >> 4) * 64; // same as above with bit shift
                pixels[x + y * width] = tiles[tileIndex];
            }
        }
    }
}
