package entity.mob;

import graphics.Screen;
import graphics.Sprite;
import input.Keyboard;

public class Player extends Mob {

    private Keyboard input;
    private Sprite sprite;
    private int anim;
    private boolean walking;

    public Player(Keyboard input) {
        this.input = input;
        sprite = Sprite.player_S;
    }

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
        sprite = Sprite.player_S;
    }

    public void update() {
        int xa = 0, ya = 0;
        if (anim < 7500) anim++;
        else anim = 0;
        if (input.up) ya--;
        if (input.down) ya++;
        if (input.left) xa--;
        if (input.right) xa++;

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
    }

    public void render(Screen screen) {

        // 0==north | 1==east | 2==south | 3==west
        if (dir == 0) {
            sprite = Sprite.player_N;
            if (walking){
                if ((anim % 30) > 20) sprite = Sprite.player_N_1;
                else if ((anim % 30) > 10) sprite = Sprite.player_N_2;
                else sprite = Sprite.player_N_3;
            }
        }
        if (dir == 1) {
            sprite = Sprite.player_E;
            if (walking){
                if ((anim % 30) > 20) sprite = Sprite.player_E_1;
                else if ((anim % 30) > 10) sprite = Sprite.player_E_2;
                else sprite = Sprite.player_E_3;
            }
        }
        if (dir == 2) {
            sprite = Sprite.player_S;
            if (walking){
                if ((anim % 30) > 20) sprite = Sprite.player_S_1;
                else if ((anim % 30) > 10) sprite = Sprite.player_S_2;
                else sprite = Sprite.player_S_3;
            }
        }
        if (dir == 3) {
            sprite = Sprite.player_W;
            if (walking){
                if ((anim % 30) > 20) sprite = Sprite.player_W_1;
                else if ((anim % 30) > 10) sprite = Sprite.player_W_2;
                else sprite = Sprite.player_W_3;
            }
        }

        // 4==SE | 5==NE | 6==SW | 7==NW
        if (dir == 4) {
            sprite = Sprite.player_SE;
            if (walking){
                if ((anim % 30) > 20) sprite = Sprite.player_SE_1;
                else if ((anim % 30) > 10) sprite = Sprite.player_SE_2;
                else sprite = Sprite.player_SE_3;
            }
        }
        if (dir == 5) {
            sprite = Sprite.player_NE;
            if (walking){
                if ((anim % 30) > 20) sprite = Sprite.player_NE_1;
                else if ((anim % 30) > 10) sprite = Sprite.player_NE_2;
                else sprite = Sprite.player_NE_3;
            }
        }
        if (dir == 6) {
            sprite = Sprite.player_SW;
            if (walking){
                if ((anim % 30) > 20) sprite = Sprite.player_SW_1;
                else if ((anim % 30) > 10) sprite = Sprite.player_SW_2;
                else sprite = Sprite.player_SW_3;
            }
        }
        if (dir == 7) {
            sprite = Sprite.player_NW;
            if (walking){
                if ((anim % 30) > 20) sprite = Sprite.player_NW_1;
                else if ((anim % 30) > 10) sprite = Sprite.player_NW_2;
                else sprite = Sprite.player_NW_3;
            }
        }

        int xx = x - sprite.SIZE / 2;
        int yy = y - sprite.SIZE / 2;
        screen.renderPlayer(xx, yy, sprite);

    }
}
