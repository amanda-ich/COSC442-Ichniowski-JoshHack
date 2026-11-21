package game;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LineOfSightTest {

    @Test
    void fieldOfViewStopsBeyondWall() {
        Tile[][][] tiles = new Tile[7][5][1];
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 5; y++) {
                tiles[x][y][0] = Tile.FLOOR;
            }
        }
        tiles[3][2][0] = Tile.WALL;

        World world = new World(tiles);
        FieldOfView fieldOfView = new FieldOfView(world);

        fieldOfView.update(1, 2, 0, 5);

        assertTrue(fieldOfView.isVisible(3, 2, 0));
        assertFalse(fieldOfView.isVisible(5, 2, 0));
    }

    @Test
    void wallBlocksCreatureLineOfSight() {
        Tile[][][] tiles = new Tile[7][5][1];
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 5; y++) {
                tiles[x][y][0] = Tile.FLOOR;
            }
        }
        tiles[3][2][0] = Tile.WALL;

        World world = new World(tiles);
        Creature observer = new Creature(
            world, '@', Color.WHITE, "player", 100, 10, 5);
        observer.x = 1;
        observer.y = 2;
        observer.z = 0;
        new CreatureAi(observer);

        assertFalse(observer.canSee(5, 2, 0));
    }
}
