package ch.bbcag.benjamin.Factorylibs.world.main.Game.Global;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Variables {
    public static final int PREFERDWIDTH = 1920;
    public static final int PREFERDHEIGHT = 1080;
    public static int PREFERDWIDTHMULTIPLIER = Gdx.graphics.getWidth() / PREFERDWIDTH;
    public static int PREFERDHEIGHTMULTIPLIER = Gdx.graphics.getHeight() / PREFERDHEIGHT;
    public static String varsPath;
    public static String currentScene;
    public static String currentTileType;
    public static String currentInternalTilePath;
    public static int currentLayer;
    public static int currentRotation;
    public static boolean isExitMenuOpen;
    public static long lasttime = System.nanoTime();
    public static double deltatime;
    public static int mouseX = -1_000;
    public static int mouseY = -1_000;
    public static boolean isPlacing;
    public static JsonValue currentData;


    public static void updateDeltatime() {
        long currentime = System.nanoTime();
        deltatime = (double) (currentime - lasttime) / 1_000_000_000;
        lasttime = currentime;
    }

    public static void getVars() {
        FileHandle file = Gdx.files.internal(varsPath);

        if (!file.exists() || !file.file().isFile()) {
            System.err.println("Variable file not found: " + varsPath);
            return;
        }

        if (file.extension().equals("json")) {
            try {
                JsonValue root = new JsonReader().parse(file);

                // Loop through each key in the JSON
                for (JsonValue entry = root.child; entry != null; entry = entry.next) {
                    String key = entry.name();
                    String value = entry.asString();

                    try {
                        Field field = Variables.class.getDeclaredField(key);
                        field.setAccessible(true);

                        // Ensure it’s static
                        if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                            System.err.println("Skipping non-static field: " + key);
                            continue;
                        }

                        Class<?> type = field.getType();

                        // Convert and set values based on type
                        if (type == int.class) {
                            field.setInt(null, Integer.parseInt(value));
                        } else if (type == boolean.class) {
                            field.setBoolean(null, Boolean.parseBoolean(value));
                        } else if (type == float.class) {
                            field.setFloat(null, Float.parseFloat(value));
                        } else if (type == double.class) {
                            field.setDouble(null, Double.parseDouble(value));
                        } else {
                            // Default to String
                            field.set(null, value);
                        }
                    } catch (NoSuchFieldException e) {
                        System.err.println("⚠️ Warning: Variable '" + key + "' not found in Variables.class");
                    } catch (Exception e) {
                        System.err.println("❌ Failed to assign variable: " + key + " (" + e.getMessage() + ")");
                    }
                }

            } catch (Exception e) {
                System.err.println("❌ Failed to load variables JSON: " + file.name());
                System.err.println(Arrays.toString(e.getStackTrace()));
            }
        }

    }
    public static void update(){
        updateDeltatime();
    }

    public static void setVarsPath(String varsPath) {
        Variables.varsPath = varsPath;
    }
}
