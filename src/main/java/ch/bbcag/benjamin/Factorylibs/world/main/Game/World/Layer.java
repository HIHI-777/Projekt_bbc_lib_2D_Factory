package ch.bbcag.benjamin.Factorylibs.world.main.Game.World;

import ch.bbcag.benjamin.Factorylibs.help.Chunk.ChunkLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Layer implements Drawable {
    private final List<Chunk> chunks;
    private final int layer;

    public Layer(int layer, String layerFolderPath) {
        chunks = new ArrayList<>();
        this.layer = layer;

        loadChunksFromFolder(layerFolderPath + layer);
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
                    System.err.println(Arrays.toString(e.getStackTrace()));
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

    public void newChunkAtWorldPos(Vector2 worldpos){
        chunks.add(new Chunk(Chunk.getChunkposFromWorldpos(worldpos)));
    }

    public List<Chunk> getChunks() {
        return chunks;
    }

    public boolean setTileFromWorldpos(Vector2 worldpos) {
        Vector2 chunkpos = Chunk.getChunkposFromWorldpos(worldpos);
        for (Chunk chunk : chunks) {
            if (chunk.replaceTileAtChunkposAndWorldpos(chunkpos, worldpos)){
                return true;
            }
        }
        return false;
    }

    public void deleteTileFromWorldpos(Vector2 worldpos) {
        Vector2 chunkpos = Chunk.getChunkposFromWorldpos(worldpos);
        for (Chunk chunk : chunks) {
            if (chunk.DeleteTileAtChunkposAndWorldpos(chunkpos, worldpos)){
                return;
            }
        }
    }

    public int getLayer() {
        return layer;
    }
}
