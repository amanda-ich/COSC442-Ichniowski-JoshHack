package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StuffFactoryTest {
    private World world;
    private StuffFactory factory;

    @BeforeEach
    void setUp() {
        Tile[][][] tiles = new Tile[20][20][1];
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                tiles[x][y][0] = Tile.FLOOR;
            }
        }
        world = new World(tiles);
        factory = new StuffFactory(world);
    }

    @Test
    void createsAndPlacesMonsterTypes() {
        Creature player = factory.newPlayer(
            new ArrayList<>(), new FieldOfView(world));
        Creature fungus = factory.newFungus(0);
        Creature bat = factory.newBat(0);
        Creature zombie = factory.newZombie(0, player);
        Creature goblin = factory.newGoblin(0, player);

        assertCreaturePlaced(player, "player", '@', 100);
        assertCreaturePlaced(fungus, "fungus", 'f', 10);
        assertCreaturePlaced(bat, "bat", 'b', 15);
        assertCreaturePlaced(zombie, "zombie", 'z', 50);
        assertCreaturePlaced(goblin, "goblin", 'g', 66);

        assertEquals(20, player.attackValue());
        assertEquals(5, player.defenseValue());
        assertEquals(0, fungus.attackValue());
        assertEquals(5, bat.attackValue());
        assertEquals(10, zombie.attackValue());
        assertNotNull(goblin.weapon());
        assertNotNull(goblin.armor());
    }

    @Test
    void createsItemsWithExpectedProperties() {
        Item rock = factory.newRock(0);
        Item victoryItem = factory.newVictoryItem(0);
        Item bread = factory.newBread(0);
        Item fruit = factory.newFruit(0);

        assertItemPlaced(rock, "rock", ',');
        assertEquals(6, rock.thrownAttackValue());
        assertItemPlaced(victoryItem, "teddy bear", '*');
        assertItemPlaced(bread, "bread", '%');
        assertEquals(400, bread.foodValue());
        assertItemPlaced(fruit, "apple", '%');
        assertEquals(100, fruit.foodValue());
    }

    @Test
    void createsWeaponsAndArmorWithExpectedStats() {
        Item dagger = factory.newDagger(0);
        Item sword = factory.newSword(0);
        Item staff = factory.newStaff(0);
        Item bow = factory.newBow(0);
        Item tunic = factory.newLightArmor(0);
        Item chainmail = factory.newMediumArmor(0);
        Item platemail = factory.newHeavyArmor(0);

        assertItemPlaced(dagger, "dagger", ')');
        assertEquals(5, dagger.attackValue());
        assertEquals(6, dagger.thrownAttackValue());

        assertItemPlaced(sword, "sword", ')');
        assertEquals(10, sword.attackValue());
        assertEquals(4, sword.thrownAttackValue());

        assertItemPlaced(staff, "staff", ')');
        assertEquals(5, staff.attackValue());
        assertEquals(3, staff.defenseValue());
        assertEquals(4, staff.thrownAttackValue());

        assertItemPlaced(bow, "bow", ')');
        assertEquals(1, bow.attackValue());
        assertEquals(5, bow.rangedAttackValue());

        assertItemPlaced(tunic, "tunic", '[');
        assertEquals(2, tunic.defenseValue());
        assertItemPlaced(chainmail, "chainmail", '[');
        assertEquals(4, chainmail.defenseValue());
        assertItemPlaced(platemail, "platemail", '[');
        assertEquals(6, platemail.defenseValue());
    }

    @Test
    void healthPotionRestoresHpWithoutExceedingMaximum() {
        Item potion = factory.newPotionOfHealth(0);
        Creature creature = new Creature(
            world, '@', Color.WHITE, "player", 100, 10, 5);
        creature.modifyHp(-20, "Test damage");

        assertEquals("health potion", potion.name());
        assertNotNull(potion.quaffEffect());

        potion.quaffEffect().start(creature);
        assertEquals(95, creature.hp());

        creature.modifyHp(-10, "Test damage");
        potion.quaffEffect().start(creature);
        assertEquals(creature.maxHp(), creature.hp());
    }

    @Test
    void createsPotionVariantsWithEffects() {
        assertPotion(factory.newPotionOfMana(0), "mana potion");
        assertPotion(factory.newPotionOfSlowHealth(0), "slow health potion");
        assertPotion(factory.newPotionOfPoison(0), "poison potion");
        assertPotion(factory.newPotionOfWarrior(0), "warrior's potion");
        assertPotion(factory.newPotionOfArcher(0), "archers potion");
        assertPotion(factory.newPotionOfExperience(0), "experience potion");
    }

    @Test
    void createsSpellbooksWithExpectedSpells() {
        Item whiteMageBook = factory.newWhiteMagesSpellbook(0);
        Item blueMageBook = factory.newBlueMagesSpellbook(0);

        assertItemPlaced(whiteMageBook, "white mage's spellbook", '+');
        assertSpell(whiteMageBook, "minor heal", 4);
        assertSpell(whiteMageBook, "major heal", 8);
        assertSpell(whiteMageBook, "slow heal", 12);
        assertSpell(whiteMageBook, "inner strength", 16);

        assertItemPlaced(blueMageBook, "blue mage's spellbook", '+');
        assertSpell(blueMageBook, "blood to mana", 1);
        assertSpell(blueMageBook, "blink", 6);
        assertSpell(blueMageBook, "summon bats", 11);
        assertSpell(blueMageBook, "detect creatures", 16);
    }

    @Test
    void writtenSpellsCannotBeRemovedThroughAccessor() {
        Item spellbook = factory.newWhiteMagesSpellbook(0);

        assertThrows(
            UnsupportedOperationException.class,
            () -> spellbook.writtenSpells().clear());
        assertEquals(4, spellbook.writtenSpells().size());
    }

    @Test
    void randomFactoryMethodsReturnValidItems() {
        Item weapon = factory.randomWeapon(0);
        Item armor = factory.randomArmor(0);
        Item potion = factory.randomPotion(0);
        Item spellbook = factory.randomSpellBook(0);

        assertTrue(List.of("dagger", "sword", "bow", "staff")
            .contains(weapon.name()));
        assertTrue(List.of("tunic", "chainmail", "platemail")
            .contains(armor.name()));
        assertTrue(List.of(
            "health potion",
            "mana potion",
            "slow health potion",
            "poison potion",
            "warrior's potion",
            "archers potion",
            "experience potion"
        ).contains(potion.name()));
        assertNotNull(potion.quaffEffect());
        assertTrue(List.of(
            "white mage's spellbook", "blue mage's spellbook"
        ).contains(spellbook.name()));
        assertFalse(spellbook.writtenSpells().isEmpty());

        assertItemPlaced(weapon, weapon.name(), weapon.glyph());
        assertItemPlaced(armor, armor.name(), armor.glyph());
        assertItemPlaced(potion, potion.name(), potion.glyph());
        assertItemPlaced(spellbook, spellbook.name(), spellbook.glyph());
    }

    private void assertCreaturePlaced(
            Creature creature, String name, char glyph, int maxHp) {
        assertEquals(name, creature.name());
        assertEquals(glyph, creature.glyph());
        assertEquals(maxHp, creature.maxHp());
        assertTrue(world.tile(creature.x, creature.y, creature.z).isGround());
        assertSame(creature, world.creature(creature.x, creature.y, creature.z));
    }

    private void assertItemPlaced(Item item, String name, char glyph) {
        assertEquals(name, item.name());
        assertEquals(glyph, item.glyph());
        boolean found = false;
        for (int x = 0; x < world.width(); x++) {
            for (int y = 0; y < world.height(); y++) {
                for (int z = 0; z < world.depth(); z++) {
                    if (world.item(x, y, z) == item) {
                        assertTrue(world.tile(x, y, z).isGround());
                        found = true;
                    }
                }
            }
        }
        assertTrue(found, item.name() + " should be placed in the world");
    }

    private void assertPotion(Item potion, String name) {
        assertItemPlaced(potion, name, '!');
        assertNotNull(potion.quaffEffect());
    }

    private void assertSpell(Item spellbook, String name, int manaCost) {
        Spell spell = spellbook.writtenSpells().stream()
            .filter(candidate -> candidate.name().equals(name))
            .findFirst()
            .orElse(null);

        assertNotNull(spell, name + " should be in " + spellbook.name());
        assertEquals(manaCost, spell.manaCost());
        assertNotNull(spell.effect());
    }
}
