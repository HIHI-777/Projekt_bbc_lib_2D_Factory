package ch.bbcag.benjamin.Factorylibs.world.main.ButtonClasses;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.UI.Button;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class ExitButton extends Button {
    BitmapFont font;
    public ExitButton(Vector2 pos) {
        super(pos, new Texture("src/main/resources/main/UI/Buttons/Button_Blue.png"));
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/framd.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();

        parameter.size = 32; // font size in pixels
        parameter.color = Color.WHITE;

        font = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void click() {

    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        font.draw(batch, "Exit game",
                (float) Gdx.graphics.getWidth() / 3840 * pos.x,
                (float) Gdx.graphics.getHeight() / 2160 * pos.y);
    }


}
