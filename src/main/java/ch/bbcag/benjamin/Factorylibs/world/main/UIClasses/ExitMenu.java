package ch.bbcag.benjamin.Factorylibs.world.main.UIClasses;

import ch.bbcag.benjamin.Factorylibs.world.main.ButtonClasses.ExitButton;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.UI.Scene;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ExitMenu extends Scene {
    public ExitMenu(String internalPath) {
        super(internalPath);
        this.addButtons(new ExitButton(new Vector2(1570 , 550)));
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void update() {
        this.visable = this.isCurrentScene();
    }


}
