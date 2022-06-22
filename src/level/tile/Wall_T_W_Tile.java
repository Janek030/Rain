package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class Wall_T_W_Tile extends Tile {
    public Wall_T_W_Tile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        /*
        convert into pixel precision
         */
        screen.renderTile(x << 4, y << 4, this, 1);
    }

    public boolean solid() {
        return true;
    }
}
