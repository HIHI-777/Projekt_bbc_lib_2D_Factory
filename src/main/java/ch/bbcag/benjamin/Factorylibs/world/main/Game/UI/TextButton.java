package ch.bbcag.benjamin.Factorylibs.world.main.Game.UI;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class TextButton extends Button {
    protected Font font;

    public TextButton(Vector2 pos, Texture img, String text, Color color, String internalPathFont) {
        super(pos, img);
        font = new Font(new Vector2(pos.x, pos.y), 10, color, internalPathFont, text);

        float maxW = img.getWidth() * 0.8f;
        float maxH = img.getHeight() * 0.8f;

        // Binary search range
        int low = 10;
        int high = 300; // or any reasonable max font size
        int best = low;

        while (low <= high) {
            int mid = (low + high) / 2;

            font.setSize(mid);
            float w = font.getWidth();
            float h = font.getHeight();

            if (w <= maxW && h <= maxH) {
                best = mid;      // this size works
                low = mid + 1;   // try larger
            } else {
                high = mid - 1;  // too big
            }
        }

        // Set the final best size
        font.setSize(best);

        // Center it
        font.setCenterPos(new Vector2(
                pos.x + (img.getWidth() / 2f),
                pos.y + (img.getHeight() / 2f)
        ));
    }


    @Override
    public void draw(Camera camera) {
        super.draw(camera);
        font.draw(camera);
    }
}
