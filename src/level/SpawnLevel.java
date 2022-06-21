package level;

import level.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpawnLevel extends Level{

    private Tile[] tiles;
    private int[] levelPixels;

    public SpawnLevel(String path) {
        super(path);
    }

    protected void loadLevel(String path) {
        try {

            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, levelPixels, 0, w);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Exception! Could not load level file.");
        }
    }

    protected void generateLevel(){
//map pixels of source file to tile
    }
}
