package ch.bbcag.benjamin.Factorylibs.world.main.Game.Input;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.UI.UImain;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.Camera;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.World.World;
import com.badlogic.gdx.InputProcessor;

public abstract class HandleInput implements InputProcessor {
    public static final float CAMERASPEED = 1000f;
    protected final World world;
    protected final UImain ui;
    protected final Camera camera;

    public HandleInput(World world, UImain ui, Camera camera) {
        this.world = world;
        this.ui = ui;
        this.camera = camera;
    }
    public void handlePollingInput() {}
}
