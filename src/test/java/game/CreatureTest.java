package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

// Added CreatureTest with testModifyHp() for Exam - Question 2:
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
    void testArmor() {

    }

    @Test
    void testAttackValue() {

    }

    @Test
    void testCanEnter() {

    }

    @Test
    void testCanSee() {

    }

    @Test
    void testCastSpell() {

    }

    @Test
    void testCauseOfDeath() {

    }

    @Test
    void testColor() {

    }

    @Test
    void testCreature() {

    }

    @Test
    void testDefenseValue() {

    }

    @Test
    void testDetails() {

    }

    @Test
    void testDig() {

    }

    @Test
    void testDoAction() {

    }

    @Test
    void testDoAction2() {

    }

    @Test
    void testDrop() {

    }

    @Test
    void testEat() {

    }

    @Test
    void testEffects() {

    }

    @Test
    void testEquip() {

    }

    @Test
    void testFood() {

    }

    @Test
    void testGainXp() {

    }

    @Test
    void testGlyph() {

    }

    @Test
    void testHp() {

    }

    @Test
    void testInventory() {

    }

    @Test
    void testIsPlayer() {

    }

    @Test
    void testItem() {

    }

    @Test
    void testLearnName() {

    }

    @Test
    void testLevel() {

    }

    @Test
    void testMana() {

    }

    @Test
    void testMaxFood() {

    }

    @Test
    void testMaxHp() {

    }

    @Test
    void testMaxMana() {

    }

    @Test
    void testMeleeAttack() {

    }

    @Test
    void testModifyAttackValue() {

    }

    @Test
    void testModifyDefenseValue() {

    }

    @Test
    void testModifyDetectCreatures() {

    }

    @Test
    void testModifyFood() {

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

    @Test
    void testModifyMana() {

    }

    @Test
    void testModifyMaxHp() {

    }

    @Test
    void testModifyMaxMana() {

    }

    @Test
    void testModifyRegenHpPer1000() {

    }

    @Test
    void testModifyRegenManaPer1000() {

    }

    @Test
    void testModifyVisionRadius() {

    }

    @Test
    void testModifyXp() {

    }

    @Test
    void testMoveBy() {

    }

    @Test
    void testName() {

    }

    @Test
    void testNameOf() {

    }

    @Test
    void testNotify() {

    }

    @Test
    void testPickup() {

    }

    @Test
    void testQuaff() {

    }

    @Test
    void testRangedWeaponAttack() {

    }

    @Test
    void testRealTile() {

    }

    @Test
    void testSetCreatureAi() {

    }

    @Test
    void testSummon() {

    }

    @Test
    void testThrowItem() {

    }

    @Test
    void testTile() {

    }

    @Test
    void testUnequip() {

    }

    @Test
    void testUpdate() {

    }

    @Test
    void testVisionRadius() {

    }

    @Test
    void testWeapon() {

    }

    @Test
    void testXp() {

    }
}
