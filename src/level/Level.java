package level;

import graphics.Screen;
import level.tile.Tile;

public class Level {
    protected int width, height; //tile precise
    protected int[] tilesInt;
    protected Tile[] tiles;

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

    }

    private void time() {

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
                    if (x < 0 || y < 0 || x >= width || y >= height){
                    Tile.voidTile.render(x, y, screen);
                    continue;
                }
                tiles[x + y * width].render(x, y, screen);
            }
        }
    }

    private Tile getTile(int x, int y) {
        //if (tiles[x + y * width] == 0) return Tile.grass;

        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;

        switch (tilesInt[x + y * width]) {
            case 0:
                return Tile.grass;
            case 1:
                return Tile.dirt;
            case 2:
                return Tile.flower;
            case 3:
                return Tile.rock;
            case 4:
                return Tile.water;
            case 5:
                return Tile.wall_H;
            default:
                return Tile.voidTile;
        }


    }
}
