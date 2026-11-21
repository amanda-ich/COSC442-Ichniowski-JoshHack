package game;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreatureMovementTest {

    @Test
    void moveIntoFloorMovesCreature() {
        Tile[][][] tiles = new Tile[3][3][1];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                tiles[x][y][0] = Tile.FLOOR;
            }
        }

        World world = new World(tiles);
        Creature creature = new Creature(
            world, '@', Color.WHITE, "player", 100, 10, 5);
        creature.x = 1;
        creature.y = 1;
        creature.z = 0;
        new CreatureAi(creature);

        creature.moveBy(1, 0, 0);

        assertEquals(2, creature.x);
        assertEquals(1, creature.y);
    }

    @Test
    void moveOutOfBoundsDoesNotMoveCreature() {
        Tile[][][] tiles = new Tile[3][3][1];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                tiles[x][y][0] = Tile.FLOOR;
            }
        }

        World world = new World(tiles);
        Creature creature = new Creature(
            world, '@', Color.WHITE, "player", 100, 10, 5);
        creature.x = 0;
        creature.y = 1;
        creature.z = 0;
        new CreatureAi(creature);

        creature.moveBy(-1, 0, 0);

        assertEquals(0, creature.x);
        assertEquals(1, creature.y);
    }

    @Test
    void moveIntoWallDoesNotMoveCreature() {
        Tile[][][] tiles = new Tile[3][3][1];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                tiles[x][y][0] = Tile.FLOOR;
            }
        }
        tiles[2][1][0] = Tile.WALL;

        World world = new World(tiles);
        Creature creature = new Creature(
            world, '@', Color.WHITE, "player", 100, 10, 5);
        creature.x = 1;
        creature.y = 1;
        creature.z = 0;
        new CreatureAi(creature);

        creature.moveBy(1, 0, 0);

        assertEquals(1, creature.x);
        assertEquals(1, creature.y);
    }
}
