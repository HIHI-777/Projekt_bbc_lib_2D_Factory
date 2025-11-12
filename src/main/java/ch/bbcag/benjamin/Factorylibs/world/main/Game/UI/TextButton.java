package ch.bbcag.benjamin.Factorylibs.world.main.Game.UI;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class TextButton extends Button {
    protected Font font;

    public TextButton(Vector2 pos, Texture img, String text, Color color, String internalPathFont) {
        super(pos, img);
        font = new Font(new Vector2(pos.x, pos.y), 3, color, internalPathFont, text);
        while ((!(font.getWidth() > 0.8f * img.getWidth())) && (!(font.getHeight() > 0.8f * img.getHeight()))) {
            font.setSize(font.getSize() + 1);
        }
        font.setSize(font.getSize() - 1);
        font.setCenterPos(new Vector2(pos.x + (img.getWidth() / 2f), pos.y + (img.getHeight() / 2f)));
    }


    @Override
    public void draw(Camera camera) {
        super.draw(camera);
        font.draw(camera);
    }
}
