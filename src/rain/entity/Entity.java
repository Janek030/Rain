package rain.entity;

import rain.graphics.Screen;
import rain.level.Level;

import java.util.Random;

public abstract class Entity {
    public int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();

    public void update(){

    }

    public void render(Screen screen){
    }

    public void remove(){
        removed = true;
    }

    public boolean isRemoved(){
        return removed;
    }

    public void init(Level level){
        this.level = level;
    }

}
