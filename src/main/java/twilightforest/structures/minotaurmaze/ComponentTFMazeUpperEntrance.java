package twilightforest.structures.minotaurmaze;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.block.TFBlocks;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFMazeUpperEntrance extends StructureTFComponent {

    private int averageGroundLevel = -1;

    public ComponentTFMazeUpperEntrance() {}

    public ComponentTFMazeUpperEntrance(int i, Random rand, int x, int y, int z) {
        super(i);
        this.field_74885_f = rand.nextInt(4);
        this.field_74887_e = new StructureBoundingBox(x, y, z, x + 15, y + 4, z + 15);
    }

    public void func_74861_a(StructureComponent structurecomponent, List list, Random random) {}

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.func_151551_a(world, sbb, rand, 0.7F, 0, 5, 0, 15, 5, 15, TFBlocks.mazestone, Blocks.field_150350_a, true);
        this.func_151556_a(world, sbb, 0, 0, 0, 15, 0, 15, TFBlocks.mazestone, 6, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 0, 1, 0, 15, 1, 15, TFBlocks.mazestone, 3, Blocks.field_150350_a, 0, true);
        this.func_151556_a(world, sbb, 0, 2, 0, 15, 3, 15, TFBlocks.mazestone, 1, Blocks.field_150350_a, 0, true);
        this.func_151556_a(world, sbb, 0, 4, 0, 15, 4, 15, TFBlocks.mazestone, 3, Blocks.field_150350_a, 0, true);
        this.func_151551_a(world, sbb, rand, 0.2F, 0, 0, 0, 15, 5, 15, Blocks.field_150351_n, Blocks.field_150350_a, true);
        this.func_151549_a(world, sbb, 6, 1, 0, 9, 4, 0, Blocks.field_150422_aJ, Blocks.field_150350_a, false);
        this.func_74878_a(world, sbb, 7, 1, 0, 8, 3, 0);
        this.func_151549_a(world, sbb, 6, 1, 15, 9, 4, 15, Blocks.field_150422_aJ, Blocks.field_150350_a, false);
        this.func_74878_a(world, sbb, 7, 1, 15, 8, 3, 15);
        this.func_151549_a(world, sbb, 0, 1, 6, 0, 4, 9, Blocks.field_150422_aJ, Blocks.field_150350_a, false);
        this.func_74878_a(world, sbb, 0, 1, 7, 0, 3, 8);
        this.func_151549_a(world, sbb, 15, 1, 6, 15, 4, 9, Blocks.field_150422_aJ, Blocks.field_150350_a, false);
        this.func_74878_a(world, sbb, 15, 1, 7, 15, 3, 8);
        this.func_74878_a(world, sbb, 1, 1, 1, 14, 4, 14);
        this.func_151556_a(world, sbb, 5, 1, 5, 10, 1, 10, TFBlocks.mazestone, 3, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 5, 4, 5, 10, 4, 10, TFBlocks.mazestone, 3, Blocks.field_150350_a, 0, false);
        this.func_151551_a(world, sbb, rand, 0.7F, 5, 2, 5, 10, 3, 10, Blocks.field_150411_aY, Blocks.field_150350_a, false);
        this.func_74878_a(world, sbb, 6, 0, 6, 9, 4, 9);
        return true;
    }

    protected int getAverageGroundLevel(World par1World, StructureBoundingBox par2StructureBoundingBox) {
        int i = 0;
        int j = 0;

        for (int k = this.field_74887_e.field_78896_c; k <= this.field_74887_e.field_78892_f; ++k) {
            for (int l = this.field_74887_e.field_78897_a; l <= this.field_74887_e.field_78893_d; ++l) {
                if (par2StructureBoundingBox.func_78890_b(l, 64, k)) {
                    i += Math.max(par1World.func_72825_h(l, k), par1World.field_73011_w.func_76557_i());
                    ++j;
                }
            }
        }

        if (j == 0) {
            return -1;
        } else {
            return i / j;
        }
    }
}
