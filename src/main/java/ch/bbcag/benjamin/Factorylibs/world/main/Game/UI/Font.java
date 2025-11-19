package ch.bbcag.benjamin.Factorylibs.world.main.Game.UI;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.Global.Variables;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Camera;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Drawable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

public class Font implements Drawable {
    private final GlyphLayout layout;
    private final String internalPath;
    private final Color color;
    private Vector2 pos;
    private float width, height;
    private BitmapFont font;
    private String text;
    private int size;

    public Font(Vector2 pos, int size, Color color, String internalPath, String text) {
        if (size < 3) {
            throw new IllegalArgumentException("Size cannot be less than 3. size: " + size);
        }
        this.pos = pos;
        this.internalPath = internalPath;
        this.color = color;
        this.text = text;
        this.size = size;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(internalPath));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = size * Variables.PREFERDWIDTHMULTIPLIER;
        parameter.color = color;

        font = generator.generateFont(parameter);

        layout = new GlyphLayout();
        layout.setText(font, text);

        this.width = layout.width;
        this.height = layout.height;
    }

    @Override
    public void draw(Camera camera) {
        font.draw(camera.batch(), layout,
                Variables.PREFERDWIDTHMULTIPLIER * pos.x,
                Variables.PREFERDWIDTHMULTIPLIER * pos.y + this.height);
    }

    public void dispose() {
        font.dispose();
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public void setCenterPos(Vector2 pos) {
        this.pos = pos.sub(width / 2f, height / 2f);
    }

    public void setText(String text) {
        layout.setText(font, text);
        this.text = text;

        this.width = layout.width;
        this.height = layout.height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(internalPath));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = size * Variables.PREFERDWIDTHMULTIPLIER;
        parameter.color = color;

        font = generator.generateFont(parameter);

        layout.setText(font, text);

        this.width = layout.width;
        this.height = layout.height;
    }
}
