package rain.level;

import rain.entity.mob.Dummy;
import rain.level.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpawnLevel extends Level {


    public SpawnLevel(String path) {
        super(path);

    }

    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            height = image.getHeight();
            width = image.getWidth();
            tilesCol = new int[width * height];
            image.getRGB(0, 0, width, height, tilesCol, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception! Could not load rain.level file.");
        }
//TODO: For test only
        add(new Dummy(20,55));
    }

    protected void generateLevel() {
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        if (tilesCol[x + y * width] == Tile.col_spawn_water) return Tile.water;
        if (tilesCol[x + y * width] == Tile.col_spawn_grass) return Tile.grass;
        if (tilesCol[x + y * width] == Tile.col_spawn_grass_flower) return Tile.grass_flower;
        if (tilesCol[x + y * width] == Tile.col_spawn_grass_rock) return Tile.grass_rock;
        if (tilesCol[x + y * width] == Tile.col_spawn_dirt) return Tile.dirt;
        if (tilesCol[x + y * width] == Tile.col_spawn_dirt_flower) return Tile.dirt_flower;
        if (tilesCol[x + y * width] == Tile.col_spawn_dirt_rock) return Tile.dirt_rock;
        if (tilesCol[x + y * width] == Tile.col_spawn_wall_H) return Tile.wall_H;
        if (tilesCol[x + y * width] == Tile.col_spawn_wall_V) return Tile.wall_V;
        if (tilesCol[x + y * width] == Tile.col_spawn_wall_T_E) return Tile.wall_T_E;
        if (tilesCol[x + y * width] == Tile.col_spawn_wall_T_N) return Tile.wall_T_N;
        if (tilesCol[x + y * width] == Tile.col_spawn_wall_T_S) return Tile.wall_T_S;
        if (tilesCol[x + y * width] == Tile.col_spawn_wall_T_W) return Tile.wall_T_W;
        if (tilesCol[x + y * width] == Tile.col_spawn_wall_L_LL) return Tile.wall_L_LL;
        if (tilesCol[x + y * width] == Tile.col_spawn_wall_L_LR) return Tile.wall_L_LR;
        if (tilesCol[x + y * width] == Tile.col_spawn_wall_L_UL) return Tile.wall_L_UL;
        if (tilesCol[x + y * width] == Tile.col_spawn_wall_L_UR) return Tile.wall_L_UR;
        if (tilesCol[x + y * width] == Tile.col_spawn_wall_X) return Tile.wall_X;

//        if (tilesCol[x + y * width] == 1) return Tile.water;
//        if (tilesCol[x + y * width] == 2) return Tile.grass;
//        if (tilesCol[x + y * width] == 3) return Tile.grass_flower;
//        if (tilesCol[x + y * width] == 4) return Tile.grass_rock;
        return Tile.voidTile;
    }
}

