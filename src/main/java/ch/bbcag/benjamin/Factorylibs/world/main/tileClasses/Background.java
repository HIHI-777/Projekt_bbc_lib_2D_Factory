package ch.bbcag.benjamin.Factorylibs.world.main.tileClasses;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Camera;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Chunk;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Tile;

public class Background extends Tile {
    public Background(int x, int y, String internalPath, Chunk parentChunk) {
        super(x, y, internalPath, parentChunk);
    }

    @Override
    public void draw(Camera camera) {
        camera.getBatch().setColor(1, 1, 1, 0.5f);
        super.draw(camera);
        camera.getBatch().setColor(1, 1, 1, 1);
    }
}
