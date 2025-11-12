package ch.bbcag.benjamin.Factorylibs.world.main.Game;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.Global.Variables;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.UI.UImain;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Camera;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.World;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends ApplicationAdapter {
    private Camera mainCamera;
    private World world;
    private UImain ui;
    private HandleInput handleInput;

    @Override
    public void create() {

        mainCamera = new Camera(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new SpriteBatch());
        world = new World();
        ui = new UImain();
        handleInput = new HandleInput(world, ui, mainCamera);
        Gdx.input.setInputProcessor(handleInput);
    }

    @Override
    public void render() {
        update();
        draw();
    }

    @Override
    public void dispose() {
        mainCamera.dispose();
        world.dispose();
        ui.dispose();
    }

    public void update() {
        handleInput.handlePollingInput();
        Variables.updateDeltatime();
    }

    public void draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainCamera.batch().begin();
        world.draw(mainCamera);
        ui.draw(mainCamera);
        mainCamera.batch().end();
    }
}
