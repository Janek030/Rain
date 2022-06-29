package rain.level.tile;

import rain.graphics.Screen;
import rain.graphics.Sprite;

public class DirtRockTile extends Tile {
    public DirtRockTile(Sprite sprite) {
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
