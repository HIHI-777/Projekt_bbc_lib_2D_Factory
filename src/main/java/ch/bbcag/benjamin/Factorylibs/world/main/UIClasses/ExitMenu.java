package ch.bbcag.benjamin.Factorylibs.world.main.UIClasses;

import ch.bbcag.benjamin.Factorylibs.world.main.ButtonClasses.ExitButton;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.UI.Scene;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Camera;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Drawable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class ExitMenu extends Scene implements Drawable {
    public ExitMenu(String internalPath) {
        super(internalPath);
        this.addButtons(new ExitButton(new Vector2(Gdx.graphics.getWidth() / 2f - 175f, Gdx.graphics.getHeight() * 0.3f)));
    }

    @Override
    public void draw(Camera camera) {
        super.draw(camera);
    }

    @Override
    public void update() {
        this.visable = this.isCurrentScene();
    }


}
