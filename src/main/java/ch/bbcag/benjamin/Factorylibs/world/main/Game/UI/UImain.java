package ch.bbcag.benjamin.Factorylibs.world.main.Game.UI;

import ch.bbcag.benjamin.Factorylibs.help.Scene.SceneFactory;
import ch.bbcag.benjamin.Factorylibs.help.Scene.SceneLoader;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.Global.Variables;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Camera;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Drawable;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class UImain implements Drawable {
    private final List<Scene> scenes;

    public UImain(String SceneFolderpath, String scenePackage) {
        SceneFactory.setScenePackage(scenePackage);
        scenes = getScenes(SceneFolderpath);
    }

    public List<Scene> getScenes(String SceneFolderpath) {
        return SceneLoader.loadScenesFromJson(SceneFolderpath);
    }

    @Override
    public void draw(Camera camera) {
        for (Scene scene : scenes) {
            scene.update();
            if (scene.isVisable()) {
                scene.draw(camera);
            }
        }
    }

    public boolean click(Vector2 mousePos) {
        boolean result = false;
        for (Scene scene : scenes) {
            if (scene.getClass().getSimpleName().equals(Variables.currentScene)) {
                for (Button button : scene.buttons) {
                    if (button.click(mousePos)){
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    public void dispose() {
        for (Scene scene : scenes) {
            scene.dispose();
        }
    }
}
