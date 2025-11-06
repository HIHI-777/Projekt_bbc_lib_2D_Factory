package ch.bbcag.benjamin.Factorylibs.world.main.Game;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.Global.Variables;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.UI.UImain;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.World;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private World world;
    private UImain ui;

    @Override
    public void create() {

        batch = new SpriteBatch();
        world = new World(batch);
        ui = new UImain(batch); // default white font
        Gdx.input.setInputProcessor(new HandleInput());
    }

    @Override
    public void render() {
        HandleInput.handlePollingInput(world);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Variables.updateDeltatime();
        batch.begin();
        world.draw();
        ui.draw();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        world.dispose();
        ui.dispose();
    }
}
