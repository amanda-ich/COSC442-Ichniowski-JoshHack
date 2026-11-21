package game;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GoblinAiTest {

    @Test
    void usesRangedWeaponAgainstVisiblePlayer() {
        Tile[][][] tiles = new Tile[7][7][1];
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                tiles[x][y][0] = Tile.FLOOR;
            }
        }

        World world = new World(tiles);
        Creature player = new Creature(
            world, '@', Color.WHITE, "player", 100, 10, 5);
        Creature goblin = new Creature(
            world, 'g', Color.GREEN, "goblin", 66, 15, 5);
        player.x = 1;
        player.y = 1;
        player.z = 0;
        goblin.x = 4;
        goblin.y = 1;
        goblin.z = 0;
        new CreatureAi(player);
        GoblinAi ai = new GoblinAi(goblin, player);
        world.add(player);
        world.add(goblin);

        Item bow = new Item(')', Color.YELLOW, "bow", null);
        bow.modifyRangedAttackValue(5);
        goblin.equip(bow);
        int initialHp = player.hp();

        ai.onUpdate();

        assertTrue(player.hp() < initialHp);
    }

    @Test
    void huntsVisiblePlayerAlongValidFloorTile() {
        Tile[][][] tiles = new Tile[7][7][1];
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                tiles[x][y][0] = Tile.FLOOR;
            }
        }

        World world = new World(tiles);
        Creature player = new Creature(
            world, '@', Color.WHITE, "player", 100, 10, 5);
        Creature goblin = new Creature(
            world, 'g', Color.GREEN, "goblin", 66, 15, 5);
        player.x = 1;
        player.y = 1;
        player.z = 0;
        goblin.x = 4;
        goblin.y = 1;
        goblin.z = 0;
        new CreatureAi(player);
        GoblinAi ai = new GoblinAi(goblin, player);
        world.add(player);
        world.add(goblin);

        int initialDistance = Math.max(
            Math.abs(goblin.x - player.x),
            Math.abs(goblin.y - player.y));

        ai.onUpdate();

        int finalDistance = Math.max(
            Math.abs(goblin.x - player.x),
            Math.abs(goblin.y - player.y));
        assertTrue(finalDistance < initialDistance);
        assertTrue(world.tile(goblin.x, goblin.y, goblin.z).isGround());
        assertNotEquals(player, world.creature(goblin.x, goblin.y, goblin.z));
    }
}
