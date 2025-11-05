package ch.bbcag.benjamin.Factorylibs.world.main.Game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Scene {
    private Texture img;

    public Scene(String internalPath){
        this.img = new Texture(internalPath);

    }

    public void draw(SpriteBatch batch){
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void dispose(){
        img.dispose();
    }
}
