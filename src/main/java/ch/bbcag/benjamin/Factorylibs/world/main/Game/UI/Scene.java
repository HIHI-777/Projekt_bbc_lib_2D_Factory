package ch.bbcag.benjamin.Factorylibs.world.main.Game.UI;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.Global.Variables;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Scene {
    private Texture img;
    protected boolean visable;
    protected List<Button> buttons;

    public Scene(String internalPath){
        this.img = new Texture(internalPath);
        this.visable = false;
        this.buttons = new ArrayList<>();
    }

    public void addButtons(Button... buttons){
        this.buttons.addAll(List.of(buttons));
    }

    public void draw(SpriteBatch batch){
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        for (Button button: buttons){
            button.draw(batch);
        }
    }

    public void dispose(){
        for (Button button: buttons){
            button.dispose();
        }
        img.dispose();
    }

    public boolean isCurrentScene() {
        return Objects.equals(Variables.currentScene, this.getClass().getSimpleName());
    }

    public abstract void update();

    public boolean isVisable() {
        return visable;
    }
}
