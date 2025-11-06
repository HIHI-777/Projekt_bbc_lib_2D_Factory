package ch.bbcag.benjamin.Factorylibs.world.main.Game.World;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Tile {
    private Vector2 pos;
    private Texture img;
    private Chunk parentChunk;

    public static final int TILESIZE = 32;

    public Tile(float x, float y, String internalPath, Chunk parentChunk){
        if (x < 0 || x > 15 || y < 0 || y > 15) {
            throw new IllegalArgumentException(
                    "Tile coordinates out of range (must be 0-15): x=" + x + ", y=" + y
            );
        }
        this.pos = new Vector2(x, y);
        this.img = new Texture(internalPath);
        this.parentChunk = parentChunk;
    }

    public boolean isintheSameSpot(Tile other){
        return this.pos.x == other.getX() && this.pos.y == other.getY();
    }

    public Tile getLeftTile(Chunk parentchunk, Layer parentLayer){
        if (this.pos.x != 0) {
            for (Tile t : parentchunk.getTiles()) {
                if (t.pos.x == this.pos.x -1 && t.pos.y == this.pos.y) {
                    return t;
                }
            }
            return null;
        }else {
            for (Chunk c : parentLayer.getChunks()){
                if (c.getChunkX() == parentchunk.getChunkX() -1 && c.getChunkY() == parentchunk.getChunkY() -1){
                    for (Tile t : c.getTiles()) {
                        if (t.pos.x == Chunk.WIDTH - 1 && t.pos.y == this.pos.y) {
                            return t;
                        }
                    }
                    return null;
                }
            }
        }
        return null;
    }

    public float getX() { return this.pos.x; }

    public float getY() { return this.pos.y; }

    public Chunk getParentChunk() { return this.parentChunk; }

    public void draw(Camera camera){
        float x = ((this.parentChunk.getChunkX() * Chunk.WIDTH * TILESIZE) + this.pos.x * TILESIZE) + camera.getX();
        float y = ((this.parentChunk.getChunkY() * Chunk.HEIGHT * TILESIZE) + this.pos.y * TILESIZE) + camera.getY();
        camera.getBatch().draw(this.img, x, y);
    }
    public void dispose(){
        this.img.dispose();
    }
}
