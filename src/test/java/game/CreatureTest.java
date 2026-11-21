package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreatureTest {
    private World world;
    private Creature creature;

    @BeforeEach
    void setUp() {
        Tile[][][] tiles = new Tile[3][3][1];

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                tiles[x][y][0] = Tile.FLOOR;
            }
        }

        world = new World(tiles);
        creature = new Creature(world, '@', Color.GRAY, "josh", 100, 10, 5);
        creature.x = 1;
        creature.y = 1;
        creature.z = 0;
    }

    @Test
    void testEffects() {
        assertTrue(creature.effects().isEmpty());
        assertThrows(
            UnsupportedOperationException.class,
            () -> creature.effects().add(new Effect(1)));
        assertTrue(creature.effects().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
        "-200, -100, true",
        "-10, 90, false",
        "20, 100, false"
    })
    void testModifyHp(int amount, int expectedHp, boolean expected) {
        creature.modifyHp(amount, "");
        assertEquals(expectedHp, creature.hp());
        assertEquals(expected, CombatRules.isDead(creature.hp()));
    }
}
