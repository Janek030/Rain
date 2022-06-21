package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class Tile {
    public int x, y;
    public Sprite sprite;
    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile grass1 = new Grass1Tile(Sprite.grass1);
    public static Tile grass2 = new Grass2Tile(Sprite.grass2);
    public static Tile flower = new FlowerTile(Sprite.flower);
    public static Tile rock = new RockTile(Sprite.rock);
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
