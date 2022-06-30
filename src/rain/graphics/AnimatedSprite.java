package rain.graphics;

public class AnimatedSprite extends Sprite {

    private int frame = 0;
    private int rate = 10;
    private Sprite sprite;
    private int length = -1;
    private int time = 0;

    public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {
        super(sheet, width, height);
        this.length = length;

        if (length > sheet.getSprites().length) System.err.println("Error! Length of animation is too long:");
        sprite = sheet.getSprites()[0];
    }

    public void update() {
        time++;
        if (time % rate == 0){
            if (frame >= length-1) frame = 1;
            else frame++;
            sprite = sheet.getSprites()[frame];
        }
        System.out.println(sprite + ", Frame: " + frame);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setFrameRate(int frames) {
        rate = frames;
    }
}
