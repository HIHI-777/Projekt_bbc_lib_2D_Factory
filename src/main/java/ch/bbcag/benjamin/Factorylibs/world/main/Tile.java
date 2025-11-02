package ch.bbcag.benjamin.Factorylibs.world.main;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Tile {
    private int x;
    private int y;

    private Texture img;

    public static final int TILESIZE = 32;

    public Tile(int x, int y, String internalPath){
        if (x < 0 || x > 15 || y < 0 || y > 15) {
            throw new IllegalArgumentException(
                    "Tile coordinates out of range (must be 0-15): x=" + x + ", y=" + y
            );
        }

        this.x = x;
        this.y = y;
        this.img = new Texture(internalPath);
    }

    public boolean isintheSameSpot(Tile other){
        return this.x == other.getX() && this.y == other.getY();
    }

    public Tile getLeftTile(Chunk parentchunk, Layer parentLayer){
        if (this.x != 0) {
            for (Tile t : parentchunk.getTiles()) {
                if (t.x == this.x -1 && t.y == this.y) {
                    return t;
                }
            }
            return null;
        }else {
            for (Chunk c : parentLayer.getChunks()){
                if (c.getChunkX() == parentchunk.getChunkX() -1 && c.getChunkY() == parentchunk.getChunkY() -1){
                    for (Tile t : c.getTiles()) {
                        if (t.x == Chunk.WIDTH - 1 && t.y == this.y) {
                            return t;
                        }
                    }
                    return null;
                }
            }
        }
        return null;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public void draw(SpriteBatch batch, Camera camera, Chunk chunk){
        int x = ((chunk.getChunkX() * Chunk.WIDTH * TILESIZE) + this.x * TILESIZE) + camera.getX();
        int y = ((chunk.getChunkY() * Chunk.HEIGHT * TILESIZE) + this.y * TILESIZE) + camera.getY();
        batch.draw(this.img, x, y);
    }
    public void dispose(){
        this.img.dispose();
    }
}
