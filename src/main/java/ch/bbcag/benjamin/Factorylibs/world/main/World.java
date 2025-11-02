package ch.bbcag.benjamin.Factorylibs.world.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class World {
    private List<Layer> layers;
    private Camera mainCamera;
    private SpriteBatch batch;

    public World(SpriteBatch batch) {
        mainCamera = new Camera(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        layers = getLayers();
        this.batch = batch;
    }

    private List<Layer> getLayers(){
        layers = new ArrayList<>();
        File folder = new File("src/main/resources/World/chunks");

        if (folder.exists() && folder.isDirectory()) {
            File[] subDirs = folder.listFiles(File::isDirectory);
            if (subDirs != null) {
                Arrays.sort(subDirs, Comparator.comparing(File::getName));
                for (int i = 0; i < subDirs.length; i++) {
                    layers.add(new Layer(i));
                }
            } else {
                System.err.println("Could not read folder contents!");
            }
        } else {
            System.err.println("Folder not found!");
        }
        return layers;
    }

    public void draw(){
        for (Layer layer : layers) {
            layer.draw(batch, mainCamera);
        }
    }

    public void dispose() {
        for (Layer layer: layers){
            layer.dispose();
        }
    }

    public Camera getMainCamera() {
        return mainCamera;
    }
}
