package ch.bbcag.benjamin.Factorylibs.help.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Clicks {
    public static Vector2 getClickposFromScreenXAndY(int screenX, int screenY){
        return new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
    }
}
