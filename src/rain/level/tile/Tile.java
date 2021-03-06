package rain.level.tile;

import rain.graphics.Screen;
import rain.graphics.Sprite;

public class Tile {
    public int x, y;
    public Sprite sprite;
    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile grass_flower = new GrassFlowerTile(Sprite.grass_flower);
    public static Tile grass_rock = new GrassRockTile(Sprite.grass_rock);
    public static Tile dirt = new DirtTile(Sprite.dirt);
    public static Tile dirt_flower = new DirtFlowerTile(Sprite.dirt_flower);
    public static Tile dirt_rock= new DirtRockTile(Sprite.dirt_rock);

    public static Tile water = new WaterTile(Sprite.water);
    public static Tile wall_H = new Wall_H_Tile(Sprite.wall_H);
    public static Tile wall_V = new Wall_V_Tile(Sprite.wall_V);
    public static Tile wall_L_LL = new Wall_L_LL_Tile(Sprite.wall_L_LL);
    public static Tile wall_L_LR = new Wall_L_LR_Tile(Sprite.wall_L_LR);
    public static Tile wall_L_UL = new Wall_L_UL_Tile(Sprite.wall_L_UL);
    public static Tile wall_L_UR = new Wall_L_UR_Tile(Sprite.wall_L_UR);
    public static Tile wall_X = new Wall_X_Tile(Sprite.wall_X);
    public static Tile wall_T_E = new Wall_T_E_Tile(Sprite.wall_T_H);
    public static Tile wall_T_W = new Wall_T_W_Tile(Sprite.wall_T_H);
    public static Tile wall_T_N = new Wall_T_N_Tile(Sprite.wall_T_V);
    public static Tile wall_T_S = new Wall_T_S_Tile(Sprite.wall_T_V);

    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public static final int col_spawn_water = 0xFF0094FF;
    public static final int col_spawn_grass = 0xFF00FF21;
    public static final int col_spawn_grass_flower = 0xFFFFD800;
    public static final int col_spawn_grass_rock = 0xFF000000;
    public static final int col_spawn_dirt = 0xFFB97A57;
    public static final int col_spawn_dirt_flower = 0xFFFF6A00;
    public static final int col_spawn_dirt_rock = 0xFF7F0000;
    public static final int col_spawn_wall_V = 0xFF111111;
    public static final int col_spawn_wall_H = 0xFF222222;
    public static final int col_spawn_wall_T_E = 0xFF333333;
    public static final int col_spawn_wall_T_S = 0xFF444444;
    public static final int col_spawn_wall_T_W = 0xFF555555;
    public static final int col_spawn_wall_T_N = 0xFF666666;
    public static final int col_spawn_wall_X = 0xFF777777;
    public static final int col_spawn_wall_L_UR = 0xFFAAAAAA;
    public static final int col_spawn_wall_L_UL = 0xFFBBBBBB;
    public static final int col_spawn_wall_L_LL = 0xFFCCCCCC;
    public static final int col_spawn_wall_L_LR = 0xFFDDDDDD;




    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {
    }

    public boolean solid() {
        return false;
    }
}
