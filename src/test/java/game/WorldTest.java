package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorldTest {

    @Test
    void constructorCopiesTileArray() {
        Tile[][][] tiles = new Tile[2][2][1];
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                tiles[x][y][0] = Tile.FLOOR;
            }
        }
        tiles[1][1][0] = Tile.WALL;

        World world = new World(tiles);

        tiles[1][1][0] = Tile.FLOOR;
        assertEquals(Tile.WALL, world.tile(1, 1, 0));

        world.dig(1, 1, 0);
        assertEquals(Tile.FLOOR, world.tile(1, 1, 0));
        assertEquals(Tile.FLOOR, tiles[1][1][0]);
    }
}
