package ch.bbcag.benjamin.Factorylibs.world.main.Game.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class TextButton extends Button{
    protected Font font;
    public TextButton(Vector2 pos, Texture img, String text, Color color, String internalPathFont) {
        super(pos, img);

        System.out.println(pos.x + (0.1f * img.getWidth()) + " " + pos.y);
        font = new Font(new Vector2(pos.x + (0.1f * img.getWidth()), pos.y), 0, color, internalPathFont, text);
        while (!(font.getWidth() > 0.8f * img.getWidth()) || !(font.getHeight() < img.getHeight())){
            font.setSize(font.getSize() + 1);
        }

        font.setSize(font.getSize() -1);
        font.setCenterPos(pos.add(img.getWidth() / 2f , img.getHeight() / 2f));
    }


}
