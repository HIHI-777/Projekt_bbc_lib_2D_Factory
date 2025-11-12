package ch.bbcag.benjamin.Factorylibs.world.main.Game.UI;

import ch.bbcag.benjamin.Factorylibs.help.Scene.SceneLoader;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.Global.Variables;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Camera;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Drawable;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class UImain implements Drawable {
    private final List<Scene> scenes;

    public UImain() {
        scenes = getScenes();
    }

    public List<Scene> getScenes() {
        return SceneLoader.loadScenesFromJson("src/main/resources/World/Scenes");
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

    public boolean click(Vector2 mousePos){
        for (Scene scene : scenes){
            if (scene.getClass().getSimpleName().equals(Variables.currentScene)){
                for (Button button : scene.buttons){
                    button.click(mousePos);
                }
            }
        }
        return false;
    }

    public void dispose() {
        for (Scene scene : scenes) {
            scene.dispose();
        }
    }
}
