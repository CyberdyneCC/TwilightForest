package twilightforest.structures.minotaurmaze;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.TFTreasure;
import twilightforest.block.TFBlocks;

public class ComponentTFMazeRoomVault extends ComponentTFMazeRoom {

    public ComponentTFMazeRoomVault() {}

    public ComponentTFMazeRoomVault(int i, Random rand, int x, int y, int z) {
        super(i, rand, x, y, z);
        this.spawnListIndex = Integer.MAX_VALUE;
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.func_151556_a(world, sbb, 0, 1, 0, 15, 4, 15, TFBlocks.mazestone, 3, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 0, 2, 0, 15, 3, 15, TFBlocks.mazestone, 1, Blocks.field_150350_a, 0, false);
        this.func_74878_a(world, sbb, 6, 2, 6, 9, 3, 9);
        this.func_151556_a(world, sbb, 6, 2, 5, 9, 2, 5, Blocks.field_150452_aw, 0, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 6, 2, 10, 9, 2, 10, Blocks.field_150452_aw, 0, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 5, 2, 6, 5, 2, 9, Blocks.field_150452_aw, 0, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 10, 2, 6, 10, 2, 9, Blocks.field_150452_aw, 0, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 6, 4, 5, 9, 4, 5, Blocks.field_150354_m, 0, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 6, 4, 10, 9, 4, 10, Blocks.field_150354_m, 0, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 5, 4, 6, 5, 4, 9, Blocks.field_150354_m, 0, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 10, 4, 6, 10, 4, 9, Blocks.field_150354_m, 0, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 6, 0, 5, 9, 0, 5, Blocks.field_150335_W, 0, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 6, 0, 10, 9, 0, 10, Blocks.field_150335_W, 0, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 5, 0, 6, 5, 0, 9, Blocks.field_150335_W, 0, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 10, 0, 6, 10, 0, 9, Blocks.field_150335_W, 0, Blocks.field_150350_a, 0, false);
        this.func_151550_a(world, Blocks.field_150486_ae, 0, 7, 2, 6, sbb);
        this.placeTreasureAtCurrentPosition(world, rand, 8, 2, 6, TFTreasure.labyrinth_vault, sbb);
        this.func_151550_a(world, Blocks.field_150486_ae, 0, 8, 2, 9, sbb);
        this.placeTreasureAtCurrentPosition(world, rand, 7, 2, 9, TFTreasure.labyrinth_vault, sbb);
        this.func_151550_a(world, Blocks.field_150486_ae, 0, 6, 2, 7, sbb);
        this.placeTreasureAtCurrentPosition(world, rand, 6, 2, 8, TFTreasure.labyrinth_vault, sbb);
        this.func_151550_a(world, Blocks.field_150486_ae, 0, 9, 2, 8, sbb);
        this.placeTreasureAtCurrentPosition(world, rand, 9, 2, 7, TFTreasure.labyrinth_vault, sbb);
        return true;
    }
}
