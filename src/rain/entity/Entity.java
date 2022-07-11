package rain.entity;

import rain.graphics.Screen;
import rain.graphics.Sprite;
import rain.level.Level;

import java.util.Random;

public abstract class Entity {
    protected int x, y;
    private boolean removed = false;
    protected Level level;
    protected Sprite sprite;
    protected final Random random = new Random();

    public Entity(int x, int y, Sprite sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }
    public void update(){

    }

    public void render(Screen screen){
        if (sprite != null) screen.renderSprite(x,y,sprite,true);
    }

    public void remove(){
        removed = true;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Sprite getSprite(){
        return sprite;
    }
    public boolean isRemoved(){
        return removed;
    }

    public void init(Level level){
        this.level = level;
    }

}
