package ch.bbcag.benjamin.Factorylibs.world.main.Game.World;

import ch.bbcag.benjamin.Factorylibs.help.Chunk.TileFactory;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.Global.Variables;
import com.badlogic.gdx.math.Vector2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class World implements Drawable {
    private List<Layer> layers;
    private Background background;


    public World(String pathname, String tilePackage, String varsPath, String layerFolderPath, String backgroundImgPath) {
        TileFactory.setTilePackage(tilePackage);
        Variables.setVarsPath(varsPath);
        Variables.getVars();
        layers = getLayers(pathname, layerFolderPath);
        background = new Background(backgroundImgPath);
    }

    private List<Layer> getLayers(String pathname, String layerFolderPath) {
        layers = new ArrayList<>();
        File folder = new File(pathname);

        if (folder.exists() && folder.isDirectory()) {
            File[] subDirs = folder.listFiles(File::isDirectory);
            if (subDirs != null) {
                Arrays.sort(subDirs, Comparator.comparing(File::getName));
                for (int i = 0; i < subDirs.length; i++) {
                    layers.add(new Layer(i, layerFolderPath));
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
        background.draw(camera);
        for (Layer layer : layers) {
            layer.draw(camera);
        }
    }

    public void dispose() {
        background.dispose();
        for (Layer layer : layers) {
            layer.dispose();
        }
    }

    public void setTileFromWorldPosUsingCurrentLayer(Vector2 worldPos) {
        setTileFromWorldPosAndLayer(worldPos, Variables.currentLayer);
    }

    public void setTileFromWorldPosAndLayer(Vector2 worldPos, int layer) {
        for (Layer layer1 : layers) {
            if (layer1.getLayer() == layer) {
                if (!layer1.setTileFromWorldpos(worldPos)){
                    layer1.newChunkAtWorldPos(worldPos);
                    layer1.setTileFromWorldpos(worldPos);
                }
            }
        }
    }

    public void deleteTileFromWorldPosUsingCurrentLayer(Vector2 worldPos) {
        deleteTileFromWorldPosAndLayer(worldPos, Variables.currentLayer);
    }

    public void deleteTileFromWorldPosAndLayer(Vector2 worldPos, int Layer) {
        for (Layer layer1 : layers) {
            if (layer1.getLayer() == Layer) {
                layer1.deleteTileFromWorldpos(worldPos);
            }
        }
    }
}
