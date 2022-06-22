package level;

import level.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpawnLevel extends Level {

    private int[] levelPixels;

    public SpawnLevel(String path) {
        super(path);

    }

    protected void loadLevel(String path) {
        try {

            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            height = h;
            width = w;
            tiles = new Tile[w * h];
            levelPixels = new int[w * h];
            image.getRGB(0, 0, w, h, levelPixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception! Could not load level file.");
        }
    }

    protected void generateLevel() {
        //map pixels of source file to tile
        //0x0094FF = water
        //0x00FF21 = grass
        //0xB97A57 = dirt
        //0xFFD800 = grass flower
        //0x000000 = grass rock
        //0x111111 = wall vertical
        //0x222222 = wall horizontal
        //0x333333 = wall T east
        //0x444444 = wall T south
        //0x555555 = wall T west
        //0x666666 = wall T north
        //0x777777 = wall X
        //0xAAAAAA = wall corner upper right
        //0xBBBBBB = wall corner upper left
        //0xCCCCCC = wall corner lower left
        //0xDDDDDD = wall corner lower right

        for (int i = 0; i < levelPixels.length; i++) {
            switch (levelPixels[i]) {
                case 0xFF0094FF:
                    tiles[i] = Tile.water;
                    break;
                case 0xFF00FF21:
                    tiles[i] = Tile.grass;
                    break;
                case 0xFFFFD800:
                    tiles[i] = Tile.grass_flower;
                    break;
                case 0xFF000000:
                    tiles[i] = Tile.grass_rock;
                    break;
                case 0xFFB97A57:
                    tiles[i] = Tile.dirt;
                    break;
                case 0xFFFF6A00:
                    tiles[i] = Tile.dirt_flower;
                    break;
                case 0xFF7F0000:
                    tiles[i] = Tile.dirt_rock;
                    break;
                case 0xFF111111:
                    tiles[i] = Tile.wall_V;
                    break;
                case 0xFF222222:
                    tiles[i] = Tile.wall_H;
                    break;
                case 0xFF333333:
                    tiles[i] = Tile.wall_T_E;
                    break;
                case 0xFF444444:
                    tiles[i] = Tile.wall_T_S;
                    break;
                case 0xFF555555:
                    tiles[i] = Tile.wall_T_W;
                    break;
                case 0xFF666666:
                    tiles[i] = Tile.wall_T_N;
                    break;
                case 0xFF777777:
                    tiles[i] = Tile.wall_X;
                    break;
                case 0xFFAAAAAA:
                    tiles[i] = Tile.wall_L_UR;
                    break;
                case 0xFFBBBBBB:
                    tiles[i] = Tile.wall_L_UL;
                    break;
                case 0xFFCCCCCC:
                    tiles[i] = Tile.wall_L_LL;
                    break;
                case 0xFFDDDDDD:
                    tiles[i] = Tile.wall_L_LR;
                    break;
                default:
                    tiles[i] = Tile.voidTile;
            }
        }


    }
}
