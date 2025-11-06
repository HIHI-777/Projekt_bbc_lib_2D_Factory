package ch.bbcag.benjamin.Factorylibs.world.main.Game.World;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.Global.Variables;
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

    public World(SpriteBatch batch) {
        Variables.getVars();
        mainCamera = new Camera(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), batch);
        layers = getLayers();

    }

    private List<Layer> getLayers() {
        layers = new ArrayList<>();
        File folder = new File("src/main/resources/World/layers");

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

    public void draw() {
        for (Layer layer : layers) {
            layer.draw(mainCamera);
        }
    }

    public void dispose() {
        for (Layer layer : layers) {
            layer.dispose();
        }
    }

    public Camera getMainCamera() {
        return mainCamera;
    }
}
