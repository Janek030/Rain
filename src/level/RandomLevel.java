package level;

import level.tile.Tile;

import java.util.Random;

public class RandomLevel extends Level {

    private static final Random random = new Random();

    public RandomLevel(int width, int height) {
        super(width, height);
    }

    protected void generateLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tilesInt[x + y * width] = random.nextInt(7);
                //if (x == y) tiles[x + y * width] = 3;
            }
        }

    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        if (tilesInt[x + y * width] == 1) return Tile.water;
        if (tilesInt[x + y * width] == 2) return Tile.grass;
        if (tilesInt[x + y * width] == 3) return Tile.grass_flower;
        if (tilesInt[x + y * width] == 4) return Tile.grass_rock;
        if (tilesInt[x + y * width] == 5) return Tile.dirt;
        if (tilesInt[x + y * width] == 6) return Tile.dirt_flower;
        if (tilesInt[x + y * width] == 7) return Tile.dirt_rock;
        return Tile.voidTile;
    }
}
