package ch.bbcag.benjamin.Factorylibs.world.main.UIClasses;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.UI.Font;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.UI.Scene;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MainScene extends Scene {
    Font font;

    public MainScene(String internalPath) {
        super(internalPath);
        this.visable = true;
        font = new Font(new Vector2(10, Gdx.graphics.getHeight() - 10), 10, Color.WHITE, "Fonts/framd.ttf", "FPS: 0");
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        // --- FPS COUNTER ---
        int fps = Gdx.graphics.getFramesPerSecond();
        font.setText("FPS: " + fps);
        font.draw(batch);
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
