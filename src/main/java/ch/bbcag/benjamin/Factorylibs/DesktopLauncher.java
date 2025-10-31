package ch.bbcag.benjamin.Factorylibs;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        config.setTitle("Placfactory");
        config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());

        // Set icons
        config.setWindowIcon(
                "main/icon/icon16.png",
                "main/icon/icon32.png",
                "main/icon/icon128.png"
        );

        new Lwjgl3Application(new MyGame(), config);
    }
}
