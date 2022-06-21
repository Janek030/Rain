package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class Grass2Tile extends Tile {
    public Grass2Tile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        /*
        convert into pixel precision
         */
        screen.renderTile(x << 4, y << 4, this);
    }
}
