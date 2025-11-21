package ch.bbcag.benjamin.Factorylibs.world.main.Game.World;

import ch.bbcag.benjamin.Factorylibs.help.Chunk.TileFactory;
import ch.bbcag.benjamin.Factorylibs.world.main.Game.Global.Variables;
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
    public Chunk(Vector2 chunkPos) {
        this.chunkPos = new Vector2(chunkPos);
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
    public void replaceTile(Tile tile) {
        tiles.removeIf(tile::isintheSameSpot);
        tiles.add(tile);
    }
    public boolean setTile(Tile tile) {
        try {
            tiles.add(tile);
            return true;
        }catch (Exception ignored) {}
        return false;
    }
    public void deleteTile(Vector2 tilepos) {
        tiles.removeIf(tile -> tile.isintheSameSpot(tilepos));
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

        boolean xOverlap = chunkPixelX > camera.getX() || chunkPixelX + chunkPixelWidth < camera.getX() + camera.width;
        boolean yOverlap = chunkPixelY > camera.getY() || chunkPixelY + chunkPixelHeight < camera.getY() + camera.height;
        return xOverlap && yOverlap;
    }

    public void dispose() {
        for (Tile tile : tiles) {
            tile.dispose();
        }
    }

    public boolean replaceTileAtChunkposAndWorldpos(Vector2 chunkpos , Vector2 worldpos){
        if (this.chunkPos.equals(chunkpos)) {
            Vector2 tilepos = Tile.getTileposFromChunkposAndWorldpos(chunkpos, worldpos);
            Tile tile = TileFactory.createTile(Variables.currentTileType, (int) tilepos.x, (int) tilepos.y,
                    Variables.currentRotation, Variables.currentInternalTilePath, this, Variables.currentData);
            replaceTile(tile);
            return true;
        }
        return false;
    }

    public boolean setTileAtChunkposAndWorldpos(Vector2 chunkpos , Vector2 worldpos){
        if (this.chunkPos.equals(chunkpos)) {
            Vector2 tilepos = Tile.getTileposFromChunkposAndWorldpos(chunkpos, worldpos);
            Tile tile = TileFactory.createTile(Variables.currentTileType, (int) tilepos.x, (int) tilepos.y,
                    Variables.currentRotation, Variables.currentInternalTilePath, this, Variables.currentData);
            return setTile(tile);
        }
        return false;
    }

    public boolean DeleteTileAtChunkposAndWorldpos(Vector2 chunkpos , Vector2 worldpos){
        if (this.chunkPos.equals(chunkpos)) {
            Vector2 tilepos = Tile.getTileposFromChunkposAndWorldpos(chunkpos, worldpos);
            deleteTile(tilepos);
            return true;
        }
        return false;
    }

    // Getters
    public float getChunkX() {
        return chunkPos.x;
    }

    public float getChunkY() {
        return chunkPos.y;
    }

    public static Vector2 getChunkposFromWorldpos(Vector2 worldpos){
        Vector2 chunkpos = new Vector2(worldpos);
        chunkpos.x = (float) Math.floor(chunkpos.x / (Chunk.WIDTH * Tile.TILESIZE));
        chunkpos.y = (float) Math.floor(chunkpos.y / (Chunk.HEIGHT * Tile.TILESIZE));
        return chunkpos;
    }
}
