package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class GrassRockTile extends Tile {
    public GrassRockTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        /*
        convert into pixel precision
         */
        screen.renderTile(x << 4, y << 4, this, 0);
    }

    public boolean solid() {
        return true;
    }
}
