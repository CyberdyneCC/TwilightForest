package twilightforest.structures.minotaurmaze;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.TFTreasure;
import twilightforest.block.TFBlocks;

public class ComponentTFMazeDeadEndChest extends ComponentTFMazeDeadEnd {

    public ComponentTFMazeDeadEndChest() {}

    public ComponentTFMazeDeadEndChest(int i, int x, int y, int z, int rotation) {
        super(i, x, y, z, rotation);
        this.spawnListIndex = Integer.MAX_VALUE;
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.func_151550_a(world, Blocks.field_150344_f, 0, 2, 1, 4, sbb);
        this.func_151550_a(world, Blocks.field_150344_f, 0, 3, 1, 4, sbb);
        this.func_151550_a(world, Blocks.field_150476_ad, this.getStairMeta(1), 2, 1, 3, sbb);
        this.func_151550_a(world, Blocks.field_150476_ad, this.getStairMeta(1), 3, 1, 3, sbb);
        this.func_151550_a(world, Blocks.field_150486_ae, 0, 2, 2, 4, sbb);
        this.placeTreasureAtCurrentPosition(world, rand, 3, 2, 4, TFTreasure.labyrinth_deadend, sbb);
        this.func_151556_a(world, sbb, 1, 1, 0, 4, 3, 1, TFBlocks.mazestone, 2, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 1, 4, 0, 4, 4, 1, TFBlocks.mazestone, 3, Blocks.field_150350_a, 0, false);
        this.func_151549_a(world, sbb, 2, 1, 0, 3, 3, 1, Blocks.field_150411_aY, Blocks.field_150350_a, false);
        return true;
    }
}
