package ch.bbcag.benjamin.Factorylibs.help.Chunk;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Chunk;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Tile;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class TileFactory {

    public static String TILE_PACKAGE;

    public static Tile createTile(String className, int x, int y, int rotation, String internalPath, Chunk parentChunk, JsonValue data) {
        try {
            // Automatically add package prefix if missing
            if (!className.contains(".")) {
                className = TILE_PACKAGE + className;
            }

            Class<?> clazz = Class.forName(className);

            if (!Tile.class.isAssignableFrom(clazz)) {
                throw new IllegalArgumentException(className + " is not a subclass of Tile");
            }

            return (Tile) clazz
                    .getConstructor(int.class, int.class, int.class, String.class, Chunk.class, JsonValue.class)
                    .newInstance(x, y, rotation, internalPath, parentChunk, data);

        } catch (Exception e) {
            throw new RuntimeException("Failed to create tile: " + className, e);
        }
    }

    public static void setTilePackage(String tilePackage) {
        TILE_PACKAGE = tilePackage;
    }
}
