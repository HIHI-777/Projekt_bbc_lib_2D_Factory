package ch.bbcag.benjamin.Factorylibs.help.json;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonReader;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Chunk;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Tile;

public class ChunkLoader {

    public static Chunk loadChunkFromJson(String path) {
        try {
            String jsonString = Gdx.files.internal(path).readString();
            JsonValue root = new JsonReader().parse(jsonString);

            int chunkX = root.getInt("chunkX");
            int chunkY = root.getInt("chunkY");

            Chunk chunk = new Chunk(chunkX, chunkY);

            JsonValue tilesNode = root.get("tiles");
            if (tilesNode != null) {
                for (JsonValue tileEntry = tilesNode.child; tileEntry != null; tileEntry = tileEntry.next) {
                    String className = tileEntry.getString("class");
                    int x = tileEntry.getInt("x");
                    int y = tileEntry.getInt("y");
                    String internalPath = tileEntry.getString("internalPath");

                    Tile tile = TileFactory.createTile(className, x, y, internalPath, chunk);
                    chunk.addTile(tile);
                }
            }

            return chunk;

        } catch (Exception e) {
            throw new RuntimeException("Failed to load chunk: " + path, e);
        }
    }
}
