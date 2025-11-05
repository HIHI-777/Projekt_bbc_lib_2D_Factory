package ch.bbcag.benjamin.Factorylibs.world.main.Game.World;

import ch.bbcag.benjamin.Factorylibs.help.json.ChunkLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    private List<Chunk> chunks;
    private final int layer;

    public Layer(int layer){
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

    public void draw(SpriteBatch batch, Camera camera){
        for (Chunk chunk : chunks) {
            if (chunk.isinsidecamera(camera)) {
                chunk.draw(batch, camera);
            }
        }
    }

    public void dispose() {
        for (Chunk chunk : chunks) {
            chunk.dispose();
        }
    }

    public List<Chunk> getChunks() { return chunks; }

    public int getLayer() { return layer; }
}
