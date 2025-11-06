package ch.bbcag.benjamin.Factorylibs.world.main.Game.World;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Camera {
    private Vector2 pos;
    private int width, height;
    private SpriteBatch batch;

    public Camera(float x, float y, int width, int height, SpriteBatch batch){
        this.pos = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.batch = batch;
    }
    public Camera(Vector2 pos, int width, int height, SpriteBatch batch){
        this.pos = pos;
        this.width = width;
        this.height = height;
        this.batch = batch;
    }

    // getters and setters
    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public Vector2 getPos() { return pos; }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public SpriteBatch getBatch() { return batch; }

    public Vector2 getWorldPosFromCameraPos(Vector2 clickpos){
        return clickpos;
    }


    public void moveUp(float amount) {
        this.pos.y -= amount;
    }

    public void moveDown(float amount) {
        this.pos.y += amount;
    }

    public void moveLeft(float amount) {
        this.pos.x += amount;
    }

    public void moveRight(float amount) {
        this.pos.x -= amount;
    }
}
