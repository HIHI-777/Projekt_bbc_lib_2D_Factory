package ch.bbcag.benjamin.Factorylibs.world.main.ButtonClasses;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.UI.TextButton;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Camera;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Drawable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class ExitButton extends TextButton implements Drawable {

    public ExitButton(Vector2 pos) {
        super(pos, new Texture("src/main/resources/main/UI/Buttons/Button_Blue.png"), "Exit", Color.WHITE, "Fonts/framd.ttf");
    }

    @Override
    public boolean click(Vector2 mousePos) {
        if (isInButton(mousePos)) {
            Gdx.app.exit();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void draw(Camera camera) {
        super.draw(camera);
    }
}
