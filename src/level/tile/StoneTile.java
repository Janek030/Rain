package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class StoneTile extends Tile {
    public StoneTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        /*
        convert into pixel precision
         */
        screen.renderTile(x << 4, y << 4, this);
    }

    public boolean solid() {
        return true;
    }
}
