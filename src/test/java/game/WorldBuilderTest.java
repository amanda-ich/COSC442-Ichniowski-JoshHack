package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorldBuilderTest {

    @Test
    void repeatingSeedProducesIdenticalWorlds() {
        long seed = 442L;
        World first = new WorldBuilder(40, 40, 2)
            .makeCaves(seed)
            .build();
        World second = new WorldBuilder(40, 40, 2)
            .makeCaves(seed)
            .build();

        assertEquals(tileLayout(first), tileLayout(second));
    }

    private String tileLayout(World world) {
        StringBuilder layout = new StringBuilder();
        for (int z = 0; z < world.depth(); z++) {
            for (int y = 0; y < world.height(); y++) {
                for (int x = 0; x < world.width(); x++) {
                    layout.append(world.tile(x, y, z).name()).append(',');
                }
            }
        }
        return layout.toString();
    }
}
