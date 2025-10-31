package ch.bbcag.benjamin.Factorylibs.world.main;

import ch.bbcag.benjamin.Factorylibs.help.json.ChunkLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Chunk> chunks;
    private Camera mainCamera;
    private SpriteBatch batch;
    public static final int MAXLAYERS = 16;

    public World(SpriteBatch batch) {
        mainCamera = new Camera(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        chunks = new ArrayList<>();
        this.batch = batch;

        loadChunksFromFolder("src/main/resources/World/chunks");
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
                    chunks.add(chunk);
                } catch (Exception e) {
                    System.err.println("Failed to load chunk: " + file.name());
                    e.printStackTrace();
                }
            }
        }
    }

    public void draw(){
        for (int i = 0; i < World.MAXLAYERS; i++) {
            for (Chunk chunk : chunks) {
                if (chunk.getLayer() == i && chunk.isinsidecamera(mainCamera)) {
                    chunk.draw(batch, mainCamera);
                }
            }
        }
    }

    public void dispose() {
        for (Chunk chunk : chunks) {
            chunk.dispose();
        }
    }

    public Camera getMainCamera() {
        return mainCamera;
    }
}
