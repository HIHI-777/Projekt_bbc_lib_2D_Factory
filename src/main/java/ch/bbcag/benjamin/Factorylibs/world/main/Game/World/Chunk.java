package ch.bbcag.benjamin.Factorylibs.world.main.Game.World;

import ch.bbcag.benjamin.Factorylibs.help.Chunk.TileFactory;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.Global.Variables;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Chunk implements Drawable {

    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;
    public static final int MAX_TILES = WIDTH * HEIGHT;

    private final Vector2 chunkPos;
    private final Map<Point, Tile> tiles;

    public Chunk(float chunkX, float chunkY) {
        this.chunkPos = new Vector2(chunkX, chunkY);
        this.tiles = new HashMap<>(MAX_TILES);
    }

    public Chunk(Vector2 chunkPos) {
        this.chunkPos = new Vector2(chunkPos);
        this.tiles = new HashMap<>(MAX_TILES);
    }

    // ------------------------------
    // TILE MANIPULATION
    // ------------------------------

    public void addTile(Tile tile) {
        Point key = new Point((int) tile.getX(), (int) tile.getY());

        if (tiles.containsKey(key)) {
            throw new IllegalArgumentException(
                    "Tile already exists at (" + key.x + ", " + key.y + ")"
            );
        }

        if (tiles.size() >= MAX_TILES) {
            throw new IllegalStateException("Chunk full!");
        }

        tiles.put(key, tile);
    }

    public void replaceTile(Tile tile) {
        Point key = new Point((int) tile.getX(), (int) tile.getY());
        tiles.put(key, tile);
    }

    public boolean setTile(Tile tile) {
        Point key = new Point((int) tile.getX(), (int) tile.getY());
        tiles.put(key, tile);
        return true;
    }

    public void deleteTile(Vector2 tilepos) {
        Point key = new Point((int) tilepos.x, (int) tilepos.y);
        tiles.remove(key);
    }

    // ------------------------------
    // WORLD / CHUNK OPERATIONS
    // ------------------------------

    public boolean replaceTileAtChunkposAndWorldpos(Vector2 chunkpos, Vector2 worldpos) {
        if (!this.chunkPos.equals(chunkpos)) return false;

        Vector2 tilepos = Tile.getTileposFromChunkposAndWorldpos(chunkpos, worldpos);
        Tile tile = TileFactory.createTile(
                Variables.currentTileType,
                (int) tilepos.x, (int) tilepos.y,
                Variables.currentRotation,
                Variables.currentInternalTilePath,
                this,
                Variables.currentData
        );

        replaceTile(tile);
        return true;
    }

    public boolean setTileAtChunkposAndWorldpos(Vector2 chunkpos, Vector2 worldpos) {
        if (!this.chunkPos.equals(chunkpos)) return false;

        Vector2 tilepos = Tile.getTileposFromChunkposAndWorldpos(chunkpos, worldpos);
        Tile tile = TileFactory.createTile(
                Variables.currentTileType,
                (int) tilepos.x, (int) tilepos.y,
                Variables.currentRotation,
                Variables.currentInternalTilePath,
                this,
                Variables.currentData
        );

        return setTile(tile);
    }

    public void DeleteTileAtChunkposAndWorldpos(Vector2 chunkpos, Vector2 worldpos) {
        if (!this.chunkPos.equals(chunkpos)) return;

        Vector2 tilepos = Tile.getTileposFromChunkposAndWorldpos(chunkpos, worldpos);
        deleteTile(tilepos);
    }

    // ------------------------------
    // DRAW + HANDLE
    // ------------------------------

    @Override
    public void draw(Camera camera) {
        for (Tile tile : tiles.values()) {
            tile.draw(camera);
        }
    }

    public boolean isinsidecamera(Camera camera) {
        float chunkPixelX = chunkPos.x * Tile.TILESIZE;
        float chunkPixelY = chunkPos.y * Tile.TILESIZE;
        float chunkPixelWidth = WIDTH * Tile.TILESIZE;
        float chunkPixelHeight = HEIGHT * Tile.TILESIZE;

        boolean xOverlap = chunkPixelX > camera.getX() ||
                chunkPixelX + chunkPixelWidth < camera.getX() + camera.width;
        boolean yOverlap = chunkPixelY > camera.getY() ||
                chunkPixelY + chunkPixelHeight < camera.getY() + camera.height;

        return xOverlap && yOverlap;
    }

    public void dispose() {
        for (Tile tile : tiles.values()) {
            tile.dispose();
        }
    }

    // ------------------------------
    // GETTERS
    // ------------------------------

    public float getChunkX() { return chunkPos.x; }
    public float getChunkY() { return chunkPos.y; }

    public static Vector2 getChunkposFromWorldpos(Vector2 worldpos) {
        Vector2 chunkpos = new Vector2(worldpos);
        chunkpos.x = (float) Math.floor(chunkpos.x / (WIDTH * Tile.TILESIZE));
        chunkpos.y = (float) Math.floor(chunkpos.y / (HEIGHT * Tile.TILESIZE));
        return chunkpos;
    }
}

