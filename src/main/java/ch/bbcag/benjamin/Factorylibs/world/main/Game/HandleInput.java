package ch.bbcag.benjamin.Factorylibs.world.main.Game;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.Global.Variables;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.UI.UImain;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Camera;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class HandleInput implements InputProcessor {
    public static final float CAMERASPEED = 1000f;
    private final World world;
    private final UImain ui;
    private final Camera camera;

    public HandleInput(World world, UImain ui, Camera camera) {
        this.world = world;
        this.ui = ui;
        this.camera = camera;
    }

    public void handlePollingInput() {
        if (Variables.currentScene.equals("MainScene")) {
            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
                camera.moveUp((float) (CAMERASPEED * Variables.deltatime * Variables.PREFERDHEIGHTMULTIPLIER));
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                camera.moveDown((float) (CAMERASPEED * Variables.deltatime * Variables.PREFERDHEIGHTMULTIPLIER));
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                camera.moveLeft((float) (CAMERASPEED * Variables.deltatime * Variables.PREFERDWIDTHMULTIPLIER));
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                camera.moveRight((float) (CAMERASPEED * Variables.deltatime * Variables.PREFERDWIDTHMULTIPLIER));
            }
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            if (Variables.currentScene.equals("ExitMenu") || Variables.currentScene.equals("MainScene")) {
                if (!Variables.isExitMenuOpen) {
                    Variables.currentScene = "ExitMenu";
                    Variables.isExitMenuOpen = true;
                } else {
                    Variables.currentScene = "MainScene";
                    Variables.isExitMenuOpen = false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 clickpos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
        world.setTileFromWorldPosAndLayer(camera.getWorldPosFromCameraPos(new Vector2(clickpos)));
        return ui.click(new Vector2(clickpos));
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (amountY < 0) {
            System.out.println("Up");
        }
        if (amountY > 0) {
            System.out.println("Down");
        }
        return false;
    }
}
