package ch.bbcag.benjamin.Factorylibs.world.main.Game.UI;

import ch.bbcag.benjamin.Factorylibs.help.Scene.SceneLoader;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

public class UImain {
    private List<Scene> scenes;
    SpriteBatch batch;

    public UImain(SpriteBatch batch){
        this.batch = batch;
        scenes = getScenes();
    }

    public List<Scene> getScenes(){
        return SceneLoader.loadScenesFromJson("src/main/resources/World/Scenes");
    }

    public void draw(){
        for (Scene scene: scenes){
            scene.draw(batch);
        }
    }
    public void dispose(){
        for (Scene scene: scenes){
            scene.dispose();
        }
    }
}
