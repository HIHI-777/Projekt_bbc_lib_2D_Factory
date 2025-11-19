package ch.bbcag.benjamin.Factorylibs.world.main.Game.World;

import ch.bbcag.benjamin.Factorylibs.world.main.Game.Global.Variables;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Tile implements Drawable{
    public static final int TILESIZE = 32 * Variables.PREFERDWIDTHMULTIPLIER;
    private final Texture img;
    private final Chunk parentChunk;
    private final Vector2 pos;
    private int rotation;

    public Tile(float x, float y, int rotation, String internalPath, Chunk parentChunk) {
        if (x < 0 || x > Chunk.WIDTH - 1 || y < 0 || y > Chunk.HEIGHT - 1) {
            throw new IllegalArgumentException(
                    "Tile coordinates out of range (must be 0-" + (Chunk.WIDTH - 1) + "): x=" + x + ", y=" + y
            );
        }
        this.pos = new Vector2(x, y);
        this.rotation = rotation;
        this.img = new Texture(internalPath);
        this.parentChunk = parentChunk;
    }

    public boolean isintheSameSpot(Tile other) {
        return this.pos.x == other.getX() && this.pos.y == other.getY();
    }

    public boolean isintheSameSpot(Vector2 otherpos) {
        return this.pos.x == otherpos.x && this.pos.y == otherpos.y;
    }

    public float getX() {
        return this.pos.x;
    }

    public float getY() {
        return this.pos.y;
    }

    public void draw(Camera camera) {
        float x = ((this.parentChunk.getChunkX() * Chunk.WIDTH * TILESIZE) + this.pos.x * TILESIZE) + camera.getX();
        float y = ((this.parentChunk.getChunkY() * Chunk.HEIGHT * TILESIZE) + this.pos.y * TILESIZE) + camera.getY();

        camera.batch().draw(
                this.img,
                x, y,                              // position (bottom-left)
                TILESIZE / 2f, TILESIZE / 2f,      // origin (center of the tile)
                TILESIZE, TILESIZE,                // width & height
                1f, 1f,                            // scaleX & scaleY
                rotation,                                // rotation in degrees (counterclockwise)
                0, 0,                              // srcX & srcY (region start)
                this.img.getWidth(), this.img.getHeight(), // srcWidth & srcHeight
                false, false                       // flipX & flipY
        );
    }

    public void dispose() {
        this.img.dispose();
    }

    public static Vector2 getTileposFromChunkposAndWorldpos(Vector2 chunkpos, Vector2 worldpos){
        Vector2 tilepos;
        float x = (float) Math.floor((worldpos.x / Tile.TILESIZE) % Chunk.WIDTH);
        if (chunkpos.x < 0) x += Chunk.WIDTH;
        float y = (float) Math.floor((worldpos.y / Tile.TILESIZE) % Chunk.HEIGHT);
        if (chunkpos.y < 0) y += Chunk.HEIGHT;
        tilepos = new Vector2(x, y);
        return tilepos;
    }
}
