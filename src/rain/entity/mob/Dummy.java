package rain.entity.mob;

import rain.graphics.Screen;
import rain.graphics.Sprite;

public class Dummy extends Mob {

    public Dummy(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.mob_N;
    }

    public void update() {

    }

    public void render(Screen screen) {
        screen.renderMob(x, y, sprite);

    }
}
