package rain.util;

public class Vector2i { // 2 dimensional vector holding integer
    private int x, y;

    public Vector2i(){
        set(0,0);
    }
    public Vector2i(int x, int y){
        set(x,y);
    }
    public Vector2i(Vector2i vector){
        set(vector.x, vector.y);
    }
    public void set(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public  Vector2i setX(int x){
        this.x = x;
        return this;
    }

    public  Vector2i setY(int y){
        this.y = y;
        return this;
    }

    public Vector2i add (Vector2i vector){
        this.x += vector.x;
        this.y += vector.y;
        return this;
    }

    public Vector2i subtract (Vector2i vector){
        this.x -= vector.x;
        this.y -= vector.y;
        return this;
    }

}
