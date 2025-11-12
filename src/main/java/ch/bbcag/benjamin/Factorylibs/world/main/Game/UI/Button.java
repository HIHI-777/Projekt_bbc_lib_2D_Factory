package ch.bbcag.benjamin.Factorylibs.world.main.Game.UI;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.Global.Variables;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Camera;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Drawable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Button implements Drawable {
    protected Vector2 pos;
    protected Texture img;

    public Button(Vector2 pos, Texture img) {
        this.pos = pos;
        this.img = img;
    }

    public abstract boolean click(Vector2 mousePos);

    @Override
    public void draw(Camera camera) {
        camera.batch().draw(img,
                (float) Variables.PREFERDWIDTHMULTIPLIER * pos.x,
                (float) Variables.PREFERDHEIGHTMULTIPLIER * pos.y,
                (float) Variables.PREFERDWIDTHMULTIPLIER * img.getWidth(),
                (float) Variables.PREFERDHEIGHTMULTIPLIER * img.getHeight());
    }

    protected boolean isInButton(Vector2 pos){
        boolean xOverlap = pos.x > this.pos.x && pos.x < this.pos.x + img.getWidth();
        boolean yOverlap = pos.y > this.pos.y && pos.y < this.pos.y + img.getHeight();
        return xOverlap && yOverlap;
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
