package game;

import java.awt.Color;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PathFindingTest {

    @Test
    void routesAroundWallWithoutCrossingIt() {
        Tile[][][] tiles = new Tile[7][5][1];
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 5; y++) {
                tiles[x][y][0] = Tile.FLOOR;
            }
        }
        for (int y = 0; y < 4; y++) {
            tiles[3][y][0] = Tile.WALL;
        }

        World world = new World(tiles);
        Creature creature = new Creature(
            world, '@', Color.WHITE, "player", 100, 10, 5);
        creature.x = 1;
        creature.y = 2;
        creature.z = 0;
        new CreatureAi(creature);

        List<Point> path = new Path(creature, 5, 2).points();

        assertNotNull(path);
        assertTrue(path.stream().anyMatch(step -> step.x == 3 && step.y == 4));

        Point previous = new Point(creature.x, creature.y, creature.z);
        for (Point step : path) {
            assertTrue(world.tile(step.x, step.y, step.z).isGround());
            assertTrue(Math.abs(step.x - previous.x) <= 1);
            assertTrue(Math.abs(step.y - previous.y) <= 1);
            previous = step;
        }

        Point destination = path.get(path.size() - 1);
        assertEquals(5, destination.x);
        assertEquals(2, destination.y);
    }

    @Test
    void returnsNoPathWhenFloorRegionsAreIsolated() {
        Tile[][][] tiles = new Tile[5][5][1];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                tiles[x][y][0] = Tile.WALL;
            }
        }
        tiles[1][1][0] = Tile.FLOOR;
        tiles[3][3][0] = Tile.FLOOR;

        World world = new World(tiles);
        Creature creature = new Creature(
            world, '@', Color.WHITE, "player", 100, 10, 5);
        creature.x = 1;
        creature.y = 1;
        creature.z = 0;
        new CreatureAi(creature);

        assertNull(new Path(creature, 3, 3).points());
    }

    @Test
    void findsShortestRouteOnOpenMap() {
        Tile[][][] tiles = new Tile[6][3][1];
        for (int x = 0; x < 6; x++) {
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

        List<Point> path = new Path(creature, 4, 1).points();

        assertNotNull(path);
        assertEquals(3, path.size());
        Point firstStep = path.get(0);
        assertTrue(Math.abs(firstStep.x - creature.x) <= 1);
        assertTrue(Math.abs(firstStep.y - creature.y) <= 1);
        assertTrue(world.tile(firstStep.x, firstStep.y, firstStep.z).isGround());

        Point destination = path.get(path.size() - 1);
        assertEquals(4, destination.x);
        assertEquals(1, destination.y);
        assertEquals(0, destination.z);
    }
}
