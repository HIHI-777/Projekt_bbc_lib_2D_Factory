package ch.bbcag.benjamin.Factorylibs.world.main.Game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Button {
    protected Vector2 pos;
    protected Texture img;

    public Button(Vector2 pos, Texture img) {
        this.pos = pos;
        this.img = img;
    }

    public abstract void click();

    public void draw(SpriteBatch batch) {
        batch.draw(img,
                (float) Gdx.graphics.getWidth() / 3840 * pos.x,
                (float) Gdx.graphics.getHeight() / 2160 * pos.y,
                (float) Gdx.graphics.getWidth() / 3840 * img.getWidth(),
                (float) Gdx.graphics.getHeight() / 2160 * img.getHeight());
    }

    public void dispose() {
        img.dispose();
    }

    public Vector2 getPos() {
        return pos;
    }

    public int getWidth() {
        return img.getWidth();
    }

    public int getHeight() {
        return img.getHeight();
    }
}
