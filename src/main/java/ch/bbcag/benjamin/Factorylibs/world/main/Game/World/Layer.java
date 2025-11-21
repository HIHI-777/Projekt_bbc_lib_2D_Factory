package ch.bbcag.benjamin.Factorylibs.world.main.Game.World;

import ch.bbcag.benjamin.Factorylibs.help.Chunk.ChunkLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Layer implements Drawable {
    private final Map<Point, Chunk> chunks = new HashMap<>();
    private final int layer;

    public Layer(int layer, String layerFolderPath) {
        this.layer = layer;

        loadChunksFromFolder(layerFolderPath + layer);
    }

    private void loadChunksFromFolder(String folderPath) {
        FileHandle folder = Gdx.files.internal(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Chunk folder not found: " + folderPath);
            return;
        }

        for (FileHandle file : folder.list()) {
            if (file.extension().equals("json")) {
                try {
                    Chunk chunk = ChunkLoader.loadChunkFromJson(file.path());

                    int cx = (int) chunk.getChunkX();
                    int cy = (int) chunk.getChunkY();

                    Point key = new Point(cx, cy);

                    // Check duplicates
                    if (chunks.containsKey(key)) {
                        throw new IllegalArgumentException(
                                "Chunks cannot overlap at (" + cx + "," + cy + ")"
                        );
                    }

                    chunks.put(key, chunk);

                } catch (Exception e) {
                    System.err.println("Failed to load chunk: " + file.name());
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void draw(Camera camera) {
        for (Chunk chunk : chunks.values()) {
            if (chunk.isinsidecamera(camera)) {
                chunk.draw(camera);
            }
        }
    }

    public void dispose() {
        for (Chunk chunk : chunks.values()) {
            chunk.dispose();
        }
    }

    public void newChunkAtWorldPos(Vector2 worldpos) {
        Vector2 cp = Chunk.getChunkposFromWorldpos(worldpos);
        Point key = new Point((int)cp.x, (int)cp.y);

        if (!chunks.containsKey(key)) {
            chunks.put(key, new Chunk(cp));
        }
    }



    public Collection<Chunk> getChunks() {
        return chunks.values();
    }

    public boolean replaceTileFromWorldpos(Vector2 worldpos) {
        Vector2 cp = Chunk.getChunkposFromWorldpos(worldpos);
        Point key = new Point((int)cp.x, (int)cp.y);

        Chunk chunk = chunks.get(key);
        if (chunk == null) return false;

        return chunk.replaceTileAtChunkposAndWorldpos(cp, worldpos);
    }

    public boolean setTileFromWorldpos(Vector2 worldpos) {
        Vector2 cp = Chunk.getChunkposFromWorldpos(worldpos);
        Point key = new Point((int)cp.x, (int)cp.y);

        Chunk chunk = chunks.get(key);
        if (chunk == null) return false;

        return chunk.setTileAtChunkposAndWorldpos(cp, worldpos);
    }



    public void deleteTileFromWorldpos(Vector2 worldpos) {
        Vector2 cp = Chunk.getChunkposFromWorldpos(worldpos);
        Point key = new Point((int)cp.x, (int)cp.y);

        Chunk chunk = chunks.get(key);
        if (chunk == null) return;

        chunk.DeleteTileAtChunkposAndWorldpos(cp, worldpos);
    }



    public int getLayer() {
        return layer;
    }
}
