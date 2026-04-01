package de.cyzetlc.mccs.utils.generator;

import de.articdive.jnoise.generators.noisegen.opensimplex.FastSimplexNoiseGenerator;
import de.articdive.jnoise.pipeline.JNoise;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.generator.GenerationUnit;
import net.minestom.server.instance.generator.Generator;

public class WorldGenerator implements Generator {
    private final JNoise noise;

    public WorldGenerator() {
        this.noise = JNoise.newBuilder()
                .fastSimplex(FastSimplexNoiseGenerator.newBuilder().build())
                .scale(0.005)
                .build();
    }

    @Override
    public void generate(GenerationUnit unit) {
        Point start = unit.absoluteStart();
        for (int x = 0; x < unit.size().x(); x++) {
            for (int z = 0; z < unit.size().z(); z++) {
                double worldX = start.x() + x;
                double worldZ = start.z() + z;

                int height = (int) (40 + (noise.evaluateNoise(worldX, worldZ) * 20));

                unit.modifier().fill(
                        start.add(x, 0, z),
                        start.add(x + 1, height - 3, z + 1),
                        Block.STONE
                );

                unit.modifier().fill(
                        start.add(x, height - 3, z),
                        start.add(x + 1, height, z + 1),
                        Block.DIRT
                );

                unit.modifier().setBlock(start.add(x, height, z), Block.GRASS_BLOCK);
            }
        }
    }
}
