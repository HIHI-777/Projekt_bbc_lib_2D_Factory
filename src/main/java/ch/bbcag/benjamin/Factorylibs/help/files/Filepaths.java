package ch.bbcag.benjamin.Factorylibs.help.files;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Filepaths {

    public static boolean isValidFilePath(String path) {
        try {
            FileHandle file = Gdx.files.internal(path);
            return file.exists() && !file.isDirectory();
        } catch (Exception e) {
            return false;
        }
    }
}
