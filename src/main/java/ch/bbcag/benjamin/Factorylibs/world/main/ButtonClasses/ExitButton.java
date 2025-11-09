package ch.bbcag.benjamin.Factorylibs.world.main.ButtonClasses;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.Global.Variables;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.UI.Button;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.UI.TextButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;

public class ExitButton extends TextButton {

    public ExitButton(Vector2 pos) {
        super(pos, new Texture("src/main/resources/main/UI/Buttons/Button_Blue.png"), "Exit", Color.WHITE, "Fonts/framd.ttf");
    }

    @Override
    public void click() {

    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }


}
