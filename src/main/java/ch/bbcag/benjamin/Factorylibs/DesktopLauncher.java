package ch.bbcag.benjamin.Factorylibs;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.MyGame;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        config.setTitle("Placfactory");
        config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        config.setForegroundFPS(60);
        config.setIdleFPS(60);

        // Set icons
        config.setWindowIcon(
                "main/icon/icon16.png",
                "main/icon/icon32.png"
        );


        new Lwjgl3Application(new MyGame(), config);
    }
}
