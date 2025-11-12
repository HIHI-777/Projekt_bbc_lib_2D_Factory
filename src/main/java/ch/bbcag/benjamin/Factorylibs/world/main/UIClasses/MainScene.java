package ch.bbcag.benjamin.Factorylibs.world.main.UIClasses;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.UI.Font;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.UI.Scene;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Camera;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Drawable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class MainScene extends Scene implements Drawable {
    Font Fps;

    public MainScene(String internalPath) {
        super(internalPath);
        this.visable = true;
        Fps = new Font(new Vector2(10, Gdx.graphics.getHeight() - 10), 10, Color.WHITE, "Fonts/framd.ttf", "FPS: 0");
    }

    @Override
    public void draw(Camera camera) {
        super.draw(camera);
        Fps.draw(camera);
    }

    @Override
    public void dispose() {
        super.dispose();
        Fps.dispose();
    }

    @Override
    public void update() {
        int fps = Gdx.graphics.getFramesPerSecond();
        Fps.setText("FPS: " + fps);
    }

}
