package ch.bbcag.benjamin.Factorylibs.help.json;

import java.util.HashMap;

public class ChunkData {
    public int chunkX;
    public int chunkY;
    public int layer;
    public HashMap<String, TileData> tiles = new HashMap<>();
}
