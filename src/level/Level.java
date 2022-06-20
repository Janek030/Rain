package level;

import graphics.Screen;
import level.tile.Tile;

public class Level {
    protected int width, height;
    protected int[] tiles;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
    }

    protected void generateLevel() {

    }

    private void loadLevel(String path) {

    }

    public void update() {

    }

    private void time() {

    }

    public void render(int xScroll, int yScroll, Screen screen) {
        /*
        1) xScroll/yScroll  is where player is located
        2) define render region of screen
        3) convert coordinates to tile precision
         */
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4; // divide by 16; y value for x=0 (left border)
        int x1 = ((xScroll + screen.width) >> 4) + 1; //right border (+1 tile to allow partial tile)
        int y0 = yScroll >> 4; // divide by 16; x value for y=0 (upper border)
        int y1 = ((yScroll + screen.height) >> 4) + 1; //lower border

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
    }

    private Tile getTile(int x, int y) {
        //if (tiles[x + y * width] == 0) return Tile.grass;

        if (x < 0 || y < 0 || x > width || y >= height) return Tile.voidTile;

        switch (tiles[x + y * width]) {
            case 0:
                return Tile.grass;
            case 1:
                return Tile.field;
            case 2:
                return Tile.water;
            case 3:
                return Tile.stone;
            default:
                return Tile.voidTile;
        }


    }
}
