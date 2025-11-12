package ch.bbcag.benjamin.Factorylibs.world.main.Game.World;

import ch.bbcag.benjamin.Factorylibs.help.Chunk.ChunkLoader;
import ch.bbcag.benjamin.Factorylibs.help.Chunk.TileFactory;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.Global.Variables;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Layer implements Drawable {
    private final List<Chunk> chunks;
    private final int layer;

    public Layer(int layer) {
        chunks = new ArrayList<>();
        this.layer = layer;

        loadChunksFromFolder("src/main/resources/World/layers/Layer" + layer);
    }


    private void loadChunksFromFolder(String folderPath) {
        FileHandle folder = Gdx.files.internal(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Chunk folder not found: " + folderPath);
            return;
        }

        // Loop through all files in the folder
        for (FileHandle file : folder.list()) {
            if (file.extension().equals("json")) {
                try {
                    Chunk chunk = ChunkLoader.loadChunkFromJson(file.path());
                    for (Chunk c : chunks) {
                        if (chunk.isintheSameSpot(c)) {
                            throw new IllegalArgumentException(
                                    "Chunks cannot overlap, please check for duplicates"
                            );
                        }
                    }
                    chunks.add(chunk);
                } catch (Exception e) {
                    System.err.println("Failed to load chunk: " + file.name());
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void draw(Camera camera) {
        for (Chunk chunk : chunks) {
            if (chunk.isinsidecamera(camera)) {
                chunk.draw(camera);
            }
        }
    }

    public void dispose() {
        for (Chunk chunk : chunks) {
            chunk.dispose();
        }
    }

    public List<Chunk> getChunks() {
        return chunks;
    }

    public void setTileFromWorldpos(Vector2 worldpos){
        Vector2 copyofWorldpos = new Vector2(worldpos);
        copyofWorldpos.x = (float) Math.floor(copyofWorldpos.x / (Chunk.WIDTH * Tile.TILESIZE));
        copyofWorldpos.y = (float) Math.floor(copyofWorldpos.y / (Chunk.HEIGHT * Tile.TILESIZE));
        for (Chunk chunk : chunks){
            if(chunk.getChunkX() == copyofWorldpos.x && chunk.getChunkY() == copyofWorldpos.y){
                Vector2 tilepos;
                if (copyofWorldpos.x >= 0 && copyofWorldpos.y >= 0) {
                    tilepos = new Vector2((float) Math.floor((worldpos.x / Tile.TILESIZE) % Chunk.WIDTH), (float) Math.floor((worldpos.y / Tile.TILESIZE) % Chunk.HEIGHT));
                } else if (copyofWorldpos.x < 0 && copyofWorldpos.y >= 0) {
                    float x = (float) Math.floor(Chunk.WIDTH + (worldpos.x / Tile.TILESIZE) % Chunk.WIDTH);
                    float y = (float) Math.floor((worldpos.y / Tile.TILESIZE) % Chunk.HEIGHT);
                    tilepos = new Vector2(x, y);
                } else if (copyofWorldpos.x >= 0 && copyofWorldpos.y < 0) {
                    float x = (float) Math.floor((worldpos.x / Tile.TILESIZE) % Chunk.WIDTH);
                    float y = (float) Math.floor(Chunk.HEIGHT + (worldpos.y / Tile.TILESIZE) % Chunk.HEIGHT);
                    tilepos = new Vector2(x, y);
                } else if (copyofWorldpos.x < 0 && copyofWorldpos.y < 0) {
                    float x = (float) Math.floor(Chunk.WIDTH + (worldpos.x / Tile.TILESIZE) % Chunk.WIDTH);
                    float y = (float) Math.floor(Chunk.HEIGHT + (worldpos.y / Tile.TILESIZE) % Chunk.HEIGHT);
                    tilepos = new Vector2(x, y);
                } else {
                    throw new RuntimeException("Java is Broken");
                }
                Tile tile = TileFactory.createTile(Variables.currentTileType, (int) tilepos.x, (int) tilepos.y, Variables.currentInternalTilePath, chunk);
                chunk.addTile(tile);
            }
        }
    }

    public int getLayer() {
        return layer;
    }
}
