package rain.graphics;

public class Sprite {
    public final int SIZE;

    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    public static Sprite voidSprite = new Sprite(16, 0x1B87E0);

    // Map Sprites next:
    public static Sprite grass = new Sprite(16,0,0, SpriteSheet.tiles);
    public static Sprite grass_flower = new Sprite(16,0,1, SpriteSheet.tiles);
    public static Sprite grass_rock = new Sprite(16,0,2, SpriteSheet.tiles);
    public static Sprite dirt = new Sprite(16,1,0, SpriteSheet.tiles);
    public static Sprite dirt_flower = new Sprite(16,1,1, SpriteSheet.tiles);
    public static Sprite dirt_rock = new Sprite(16,1,2, SpriteSheet.tiles);
    public static Sprite water = new Sprite(16,2,0, SpriteSheet.tiles);
    public static Sprite wall_H = new Sprite(16,4,0, SpriteSheet.tiles);
    public static Sprite wall_V = new Sprite(16,3,1, SpriteSheet.tiles);
    public static Sprite wall_L_LL = new Sprite(16,3,2, SpriteSheet.tiles);
    public static Sprite wall_L_LR = new Sprite(16,5,2, SpriteSheet.tiles);
    public static Sprite wall_L_UL = new Sprite(16,3,0, SpriteSheet.tiles);
    public static Sprite wall_L_UR = new Sprite(16,5,0, SpriteSheet.tiles);
    public static Sprite wall_X = new Sprite(16,6,0, SpriteSheet.tiles);
    public static Sprite wall_T_H = new Sprite(16,7,0, SpriteSheet.tiles);
    public static Sprite wall_T_V = new Sprite(16,8,0, SpriteSheet.tiles);


    // Player Sprites next:
    public static Sprite player_S = new Sprite(32,0,0, SpriteSheet.player);
    public static Sprite player_S_1 = new Sprite(32,1,0, SpriteSheet.player);
    public static Sprite player_S_2 = new Sprite(32,2,0, SpriteSheet.player);
    public static Sprite player_S_3 = new Sprite(32,3,0, SpriteSheet.player);
    public static Sprite player_W = new Sprite(32,0,2, SpriteSheet.player);
    public static Sprite player_W_1 = new Sprite(32,1,2, SpriteSheet.player);
    public static Sprite player_W_2 = new Sprite(32,2,2, SpriteSheet.player);
    public static Sprite player_W_3 = new Sprite(32,3,2, SpriteSheet.player);

    public static Sprite player_N = new Sprite(32,0,4, SpriteSheet.player);
    public static Sprite player_N_1 = new Sprite(32,1,4, SpriteSheet.player);
    public static Sprite player_N_2 = new Sprite(32,2,4, SpriteSheet.player);
    public static Sprite player_N_3 = new Sprite(32,3,4, SpriteSheet.player);

    public static Sprite player_E = new Sprite(32,0,6, SpriteSheet.player);
    public static Sprite player_E_1 = new Sprite(32,1,6, SpriteSheet.player);
    public static Sprite player_E_2 = new Sprite(32,2,6, SpriteSheet.player);
    public static Sprite player_E_3 = new Sprite(32,3,6, SpriteSheet.player);

    public static Sprite player_NE = new Sprite(32,0,5, SpriteSheet.player);
    public static Sprite player_NE_1 = new Sprite(32,1,5, SpriteSheet.player);
    public static Sprite player_NE_2 = new Sprite(32,2,5, SpriteSheet.player);
    public static Sprite player_NE_3 = new Sprite(32,3,5, SpriteSheet.player);

    public static Sprite player_NW = new Sprite(32,0,3, SpriteSheet.player);
    public static Sprite player_NW_1 = new Sprite(32,1,3, SpriteSheet.player);
    public static Sprite player_NW_2 = new Sprite(32,2,3, SpriteSheet.player);
    public static Sprite player_NW_3 = new Sprite(32,3,3, SpriteSheet.player);

    public static Sprite player_SE = new Sprite(32,0,7, SpriteSheet.player);
    public static Sprite player_SE_1 = new Sprite(32,1,7, SpriteSheet.player);
    public static Sprite player_SE_2 = new Sprite(32,2,7, SpriteSheet.player);
    public static Sprite player_SE_3 = new Sprite(32,3,7, SpriteSheet.player);

    public static Sprite player_SW = new Sprite(32,0,1, SpriteSheet.player);
    public static Sprite player_SW_1 = new Sprite(32,1,1, SpriteSheet.player);
    public static Sprite player_SW_2 = new Sprite(32,2,1, SpriteSheet.player);
    public static Sprite player_SW_3 = new Sprite(32,3,1, SpriteSheet.player);

    // Projectile Sprites next:
    public static Sprite projectile_wizard = new Sprite(16,0,0, SpriteSheet.projectile_wizard);

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

    public Sprite(int size, int colour){
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColour(colour);
    }

    private void setColour(int colour) {
        for (int i=0; i < SIZE*SIZE; i++){
            pixels[i]=colour;
        }
    }

    private void load() {
        /*
        Traverse through Sprite's pixels using this.x / this.y as offset
         */
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.XSIZE];
            }
        }
    }
}
