package ch.bbcag.benjamin.Factorylibs.help.json;

import ch.bbcag.benjamin.Factorylibs.world.main.Tile;

public class TileFactory {

    private static final String TILE_PACKAGE = "ch.bbcag.benjamin.Factorylibs.world.main.tileClasses.";

    public static Tile createTile(String className, int x, int y, String internalPath) {
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
                    .getConstructor(int.class, int.class, String.class)
                    .newInstance(x, y, internalPath);

        } catch (Exception e) {
            throw new RuntimeException("Failed to create tile: " + className, e);
        }
    }
}
