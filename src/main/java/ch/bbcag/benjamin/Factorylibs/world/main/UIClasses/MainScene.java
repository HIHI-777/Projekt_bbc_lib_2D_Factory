package ch.bbcag.benjamin.Factorylibs.world.main.UIClasses;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.UI.Scene;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainScene extends Scene {
    BitmapFont font;
    public MainScene(String internalPath) {
        super(internalPath);
        this.visable = true;
        font = new BitmapFont();
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        // --- FPS COUNTER ---
        int fps = Gdx.graphics.getFramesPerSecond();
        font.draw(batch, "FPS: " + fps, 10, Gdx.graphics.getHeight() - 10);
        // -------------------
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }

    @Override
    public void update() {

    }

}
