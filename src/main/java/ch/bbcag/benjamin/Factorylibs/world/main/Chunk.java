package ch.bbcag.benjamin.Factorylibs.world.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Chunk{
    private final int chunkX;
    private final int chunkY;
    private final List<Tile> tiles;

    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;
    public static final int MAX_TILES = WIDTH * HEIGHT;


    public Chunk(int chunkX, int chunkY) {
        this.chunkX = chunkX;
        this.chunkY = chunkY;
        this.tiles = new ArrayList<>(MAX_TILES);
    }

    public void addTile(Tile tile) {
        if (tiles.size() >= MAX_TILES) {
            throw new IllegalStateException("Chunk full!");
        }
        for (Tile t : tiles) {
            if (tile.isintheSameSpot(t)) {
                throw new IllegalArgumentException(
                        "Tiles cannot overlap, please check for duplicates"
                );
            }
        }
        tiles.add(tile);
    }

    public boolean isintheSameSpot(Chunk other){ return this.chunkX == other.getChunkX() && this.chunkY == other.getChunkY(); }

    public void draw(SpriteBatch batch, Camera camera){
        for (Tile tile: this.tiles) {
            tile.draw(batch, camera, this);
        }
    }

    public boolean isinsidecamera(Camera camera) {
        int chunkPixelX = chunkX * Tile.TILESIZE;
        int chunkPixelY = chunkY * Tile.TILESIZE;
        int chunkPixelWidth = Chunk.WIDTH * Tile.TILESIZE;
        int chunkPixelHeight = Chunk.HEIGHT * Tile.TILESIZE;

        boolean xOverlap = chunkPixelX > camera.getX() || chunkPixelX + chunkPixelWidth < camera.getX() + camera.getWidth();
        boolean yOverlap = chunkPixelY > camera.getY() || chunkPixelY + chunkPixelHeight < camera.getY() + camera.getHeight();
        return xOverlap && yOverlap;
    }

    public void dispose(){
        for (Tile tile: tiles){
            tile.dispose();
        }
    }

    // Getters
    public int getChunkX() { return chunkX; }
    public int getChunkY() { return chunkY; }
    public List<Tile> getTiles() { return tiles; }
}
