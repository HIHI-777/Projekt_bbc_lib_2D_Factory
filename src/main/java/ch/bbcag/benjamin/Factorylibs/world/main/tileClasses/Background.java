package ch.bbcag.benjamin.Factorylibs.world.main.tileClasses;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Camera;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Chunk;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Tile;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background extends Tile {
    public Background(int x, int y, String internalPath) {
        super(x, y, internalPath);
    }

    @Override
    public void draw(SpriteBatch batch, Camera camera, Chunk chunk) {
        batch.setColor(1, 1, 1, 0.5f);
        super.draw(batch, camera, chunk);
        batch.setColor(1, 1, 1, 1);
    }
}
