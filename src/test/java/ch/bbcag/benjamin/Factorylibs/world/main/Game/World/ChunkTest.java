package ch.bbcag.benjamin.Factorylibs.world.main.Game.World;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChunkTest {

    @Test
    void isintheSameSpot() {
        Chunk chunk1 = new Chunk(0, 0);
        System.out.println("Chunk 1 x: " + chunk1.getChunkX() + ", y: " + chunk1.getChunkY());
        Chunk chunk2 = new Chunk(1, 0);
        System.out.println("Chunk 2 x: " + chunk2.getChunkX() + ", y: " + chunk2.getChunkY());
        assertFalse(chunk2.isintheSameSpot(chunk1));
    }
}