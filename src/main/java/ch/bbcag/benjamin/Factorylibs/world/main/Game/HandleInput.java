package ch.bbcag.benjamin.Factorylibs.world.main.Game;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class HandleInput implements InputProcessor {
    public static final int CAMERASPEED = 16;

    public static void handlePollingInput(World world){
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            world.getMainCamera().moveUp(CAMERASPEED);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            world.getMainCamera().moveDown(CAMERASPEED);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            world.getMainCamera().moveLeft(CAMERASPEED);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            world.getMainCamera().moveRight(CAMERASPEED);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
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
        if (amountY < 0){
            System.out.println("Up");
        }
        if (amountY > 0){
            System.out.println("Down");
        }
        return false;
    }
}
