package ch.bbcag.benjamin.Factorylibs.world.main.Game.World;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Chunk implements Drawable {
    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;
    public static final int MAX_TILES = WIDTH * HEIGHT;
    private final Vector2 chunkPos;
    private final List<Tile> tiles;


    public Chunk(float chunkX, float chunkY) {
        this.chunkPos = new Vector2(chunkX, chunkY);
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

    public boolean isintheSameSpot(Chunk other) {
        return this.chunkPos.x == other.getChunkX() && this.chunkPos.y == other.getChunkY();
    }

    public void draw(Camera camera) {
        for (Tile tile : this.tiles) {
            tile.draw(camera);
        }
    }

    public boolean isinsidecamera(Camera camera) {
        float chunkPixelX = chunkPos.x * Tile.TILESIZE;
        float chunkPixelY = chunkPos.y * Tile.TILESIZE;
        float chunkPixelWidth = Chunk.WIDTH * Tile.TILESIZE;
        float chunkPixelHeight = Chunk.HEIGHT * Tile.TILESIZE;

        boolean xOverlap = chunkPixelX > camera.getX() || chunkPixelX + chunkPixelWidth < camera.getX() + camera.width();
        boolean yOverlap = chunkPixelY > camera.getY() || chunkPixelY + chunkPixelHeight < camera.getY() + camera.height();
        return xOverlap && yOverlap;
    }

    public void dispose() {
        for (Tile tile : tiles) {
            tile.dispose();
        }
    }

    // Getters
    public float getChunkX() {
        return chunkPos.x;
    }

    public float getChunkY() {
        return chunkPos.y;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}
