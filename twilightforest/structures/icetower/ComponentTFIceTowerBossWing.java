package twilightforest.structures.icetower;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.block.TFBlocks;

public class ComponentTFIceTowerBossWing extends ComponentTFIceTowerWing {

    public ComponentTFIceTowerBossWing() {
        this.spawnListIndex = -1;
    }

    public ComponentTFIceTowerBossWing(int index, int x, int y, int z, int wingSize, int wingHeight, int direction) {
        super(index, x, y, z, wingSize, wingHeight, direction);
        this.spawnListIndex = -1;
    }

    protected boolean shouldHaveBase(Random rand) {
        return false;
    }

    protected void placeFloor(World world, Random rand, StructureBoundingBox sbb, int floorHeight, int floor) {
        for (int x = 1; x < this.size - 1; ++x) {
            for (int z = 1; z < this.size - 1; ++z) {
                Block ice = rand.nextInt(4) == 0 ? Blocks.field_150432_aD : Blocks.field_150403_cj;
                int thickness = 1 + rand.nextInt(2) + rand.nextInt(2) + rand.nextInt(2);

                for (int y = 0; y < thickness; ++y) {
                    this.func_151550_a(world, ice, 0, x, floor * floorHeight + floorHeight - y, z, sbb);
                }
            }
        }

    }

    protected void decorateFloor(World world, Random rand, int floor, int bottom, int top, int ladderUpDir, int ladderDownDir, StructureBoundingBox sbb) {
        for (int y = 0; y < 3; ++y) {
            int rotation = (ladderDownDir + y) % 4;
            int rotation2 = (ladderDownDir + y + 2) % 4;

            this.placeIceStairs(world, sbb, rand, bottom + y * 3, rotation);
            this.placeIceStairs(world, sbb, rand, bottom + y * 3, rotation2);
        }

    }

    private void placeIceStairs(World world, StructureBoundingBox sbb, Random rand, int y, int rotation) {
        this.fillBlocksRotated(world, sbb, 8, y + 1, 1, 10, y + 1, 3, Blocks.field_150403_cj, 0, rotation);
        if (y > 1) {
            this.randomlyFillBlocksRotated(world, sbb, rand, 0.5F, 8, y + 0, 1, 10, y + 0, 3, Blocks.field_150403_cj, 0, Blocks.field_150350_a, 0, rotation);
        }

        this.fillBlocksRotated(world, sbb, 11, y + 2, 1, 13, y + 2, 3, Blocks.field_150403_cj, 0, rotation);
        this.randomlyFillBlocksRotated(world, sbb, rand, 0.5F, 11, y + 1, 1, 13, y + 1, 3, Blocks.field_150403_cj, 0, Blocks.field_150350_a, 0, rotation);
        this.fillBlocksRotated(world, sbb, 11, y + 3, 4, 13, y + 3, 6, Blocks.field_150403_cj, 0, rotation);
        this.randomlyFillBlocksRotated(world, sbb, rand, 0.5F, 11, y + 2, 4, 13, y + 2, 6, Blocks.field_150403_cj, 0, Blocks.field_150350_a, 0, rotation);
    }

    protected void decorateTopFloor(World world, Random rand, int floor, int bottom, int top, int ladderUpDir, int ladderDownDir, StructureBoundingBox sbb) {
        for (int x = 1; x < this.size - 1; ++x) {
            for (int z = 1; z < this.size - 1; ++z) {
                Block ice = rand.nextInt(10) == 0 ? Blocks.field_150426_aN : Blocks.field_150403_cj;
                int thickness = rand.nextInt(2) + rand.nextInt(2);

                for (int y = 0; y < thickness; ++y) {
                    this.func_151550_a(world, ice, 0, x, top - 1 - y, z, sbb);
                }
            }
        }

        this.placeBlockRotated(world, TFBlocks.bossSpawner, 5, 7, top - 6, 7, 0, sbb);
        this.func_74878_a(world, sbb, 5, top - 3, 5, 9, top - 1, 9);
    }
}
