package ch.bbcag.benjamin.Factorylibs.world.main.Game;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.Global.Variables;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class HandleInput implements InputProcessor {
    public static final float CAMERASPEED = 1000f;

    public static void handlePollingInput(World world) {
        if (Variables.currentScene.equals("MainScene")) {
            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
                world.getMainCamera().moveUp((float) (CAMERASPEED * Variables.deltatime));
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                world.getMainCamera().moveDown((float) (CAMERASPEED * Variables.deltatime));
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                world.getMainCamera().moveLeft((float) (CAMERASPEED * Variables.deltatime));
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                world.getMainCamera().moveRight((float) (CAMERASPEED * Variables.deltatime));
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
        return false;
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
