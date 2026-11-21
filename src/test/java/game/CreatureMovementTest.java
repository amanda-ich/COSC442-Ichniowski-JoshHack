package game;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void moveIntoOccupiedTileAttacksWithoutMoving() {
        Tile[][][] tiles = new Tile[3][3][1];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                tiles[x][y][0] = Tile.FLOOR;
            }
        }

        World world = new World(tiles);
        Creature attacker = new Creature(
            world, '@', Color.WHITE, "player", 100, 10, 5);
        Creature target = new Creature(
            world, 'g', Color.GREEN, "goblin", 100, 0, 0);
        attacker.x = 1;
        attacker.y = 1;
        attacker.z = 0;
        target.x = 2;
        target.y = 1;
        target.z = 0;
        new CreatureAi(attacker);
        new CreatureAi(target);
        world.add(attacker);
        world.add(target);
        int initialTargetHp = target.hp();

        attacker.moveBy(1, 0, 0);

        assertEquals(1, attacker.x);
        assertEquals(1, attacker.y);
        assertEquals(2, target.x);
        assertEquals(1, target.y);
        assertTrue(target.hp() < initialTargetHp);
    }
}
