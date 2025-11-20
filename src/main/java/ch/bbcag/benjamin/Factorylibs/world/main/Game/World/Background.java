package ch.bbcag.benjamin.Factorylibs.world.main.Game.World;

import com.badlogic.gdx.graphics.Texture;

public class Background implements Drawable {

    private final Texture img;

    public Background(String imgPath) {
        img = new Texture(imgPath);
    }

    @Override
    public void draw(Camera camera) {
        float camX = camera.pos.x;
        float camY = camera.pos.y;

        float w = img.getWidth();
        float h = img.getHeight();

        // modulo offset fixes the unwanted inversion!
        float offsetX = ((camX % w) + w) % w;
        float offsetY = ((camY % h) + h) % h;

        // draw enough tiles to fill the screen
        for (int x = -1; x <= camera.width / w + 1; x++) {
            for (int y = -1; y <= camera.height / h + 1; y++) {

                float drawX = x * w + offsetX;
                float drawY = y * h + offsetY;

                camera.batch.draw(img, drawX, drawY);
            }
        }
    }



    public void dispose(){
        img.dispose();
    }
}
