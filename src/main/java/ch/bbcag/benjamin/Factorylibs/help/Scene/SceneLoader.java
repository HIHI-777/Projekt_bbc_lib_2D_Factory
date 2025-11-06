package ch.bbcag.benjamin.Factorylibs.help.Scene;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.UI.Scene;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SceneLoader {
    public static List<Scene> loadScenesFromJson(String SceneFolderpath) {
        try {
            List<Scene> scenes = new ArrayList<>();
            File folder = new File(SceneFolderpath);
            if (folder.exists() && folder.isDirectory()) {
                File[] subFiles = folder.listFiles();
                if (subFiles != null) {
                    Arrays.sort(subFiles, Comparator.comparing(File::getName));
                    for (File f : subFiles) {
                        String jsonString = Gdx.files.internal(f.getPath()).readString();
                        JsonValue root = new JsonReader().parse(jsonString);

                        String className = root.getString("className");
                        String internalPath = root.getString("internalPath");
                        scenes.add(SceneFactory.createScene(className, internalPath));
                    }
                } else {
                    System.err.println("Could not read folder contents!");
                }
            } else {
                System.err.println("Folder not found!");
            }
            return scenes;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load Scenes: " + SceneFolderpath, e);
        }
    }
}
