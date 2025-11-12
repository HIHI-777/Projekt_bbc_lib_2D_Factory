package ch.bbcag.benjamin.Factorylibs.world.main.Game.World;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.Global.Variables;
import com.badlogic.gdx.math.Vector2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class World implements Drawable {
    private List<Layer> layers;


    public World() {
        Variables.getVars();
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

    @Override
    public void draw(Camera camera) {
        for (Layer layer : layers) {
            layer.draw(camera);
        }
    }

    public void dispose() {
        for (Layer layer : layers) {
            layer.dispose();
        }
    }

    public void setTileFromWorldPosAndLayer(Vector2 worldPos){
        for (Layer layer1 : layers) {
            if (layer1.getLayer() == Variables.currentLayer){
                System.out.println(Variables.currentLayer);
                layer1.setTileFromWorldpos(worldPos);
            }
        }
    }
}
