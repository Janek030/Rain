package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class DirtFlowerTile extends Tile {
    public DirtFlowerTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        /*
        convert into pixel precision
         */
        screen.renderTile(x << 4, y << 4, this, 0);
    }
}