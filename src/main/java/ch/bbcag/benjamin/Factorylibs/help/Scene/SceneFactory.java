package ch.bbcag.benjamin.Factorylibs.help.Scene;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.UI.Scene;

public class SceneFactory {
    private static String SCENE_PACKAGE;

    public static Scene createScene(String className, String internalPath) {
        try {
            if (!className.contains(".")) {
                className = SCENE_PACKAGE + className;
            }

            Class<?> clazz = Class.forName(className);

            if (!Scene.class.isAssignableFrom(clazz)) {
                throw new IllegalArgumentException(className + " is not a subclass of Scene");
            }

            return (Scene) clazz
                    .getConstructor(String.class)
                    .newInstance(internalPath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load Scene: " + SCENE_PACKAGE + className, e);
        }
    }

    public static void setScenePackage(String scenePackage) {
        SCENE_PACKAGE = scenePackage;
    }
}
