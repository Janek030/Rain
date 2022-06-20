package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class Tile {
    public int x, y;
    public Sprite sprite;
    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile field = new FieldTile(Sprite.field);
    public static Tile water = new WaterTile(Sprite.water);
    public static Tile stone = new StoneTile(Sprite.stone);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {
    }

    public boolean solid() {
        return false;
    }
}
