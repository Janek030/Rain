package graphics;

public class Sprite {
    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;
    public static Sprite grass = new Sprite(16,0,0, SpriteSheet.tiles);
    public static Sprite field = new Sprite(16,1,0, SpriteSheet.tiles);
    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        /*
        #size == standard size of each sprite in SpriteSheet
        #x,y == location of Sprite in spritesheet based on size

        1. initiate sprite's pixel array
        2. find upper left corner of Sprite
         */

        this.SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * size; //e.g. 2 sprites * 16 pixels
        this.y = y * size;
        this.sheet = sheet;
        load();
    }

    private void load() {
        /*
        Traverse through Sprite's pixels using this.x / this.y as offset
         */
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }
}
