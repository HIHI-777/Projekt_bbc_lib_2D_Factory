package ch.bbcag.benjamin.Factorylibs.world.main.Game.World;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Camera {
    public Vector2 pos;
    public int width;
    public int height;
    public SpriteBatch batch;

    public Camera(float x, float y, int width, int height, SpriteBatch batch) {
        this.pos = new Vector2(x, y);
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

    public Vector2 getWorldPosFromCameraPos(Vector2 clickpos) {
        return clickpos.sub(pos);
    }

    public void updateSize(int width, int height){
        this.width = width;
        this.height = height;
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

    public void dispose() {
        this.batch.dispose();
    }

    @Override
    public String toString() {
        return pos.toString() + width + height;
    }
}