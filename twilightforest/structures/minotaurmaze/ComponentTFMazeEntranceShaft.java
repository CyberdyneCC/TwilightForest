package twilightforest.structures.minotaurmaze;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.block.TFBlocks;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFMazeEntranceShaft extends StructureTFComponent {

    private int averageGroundLevel = -1;

    public ComponentTFMazeEntranceShaft() {}

    public ComponentTFMazeEntranceShaft(int i, Random rand, int x, int y, int z) {
        super(i);
        this.field_74885_f = rand.nextInt(4);
        this.field_74887_e = new StructureBoundingBox(x, y, z, x + 6 - 1, y + 14, z + 6 - 1);
    }

    public void func_74861_a(StructureComponent structurecomponent, List list, Random random) {}

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        if (this.averageGroundLevel < 0) {
            this.averageGroundLevel = this.getAverageGroundLevel(world, sbb);
            if (this.averageGroundLevel < 0) {
                return true;
            }

            this.field_74887_e.func_78886_a(0, this.averageGroundLevel - this.field_74887_e.field_78894_e + 15 - 1, 0);
        }

        this.func_151556_a(world, sbb, 0, -10, 0, 5, 30, 5, TFBlocks.mazestone, 1, Blocks.field_150350_a, 0, true);
        this.func_74878_a(world, sbb, 1, -10, 1, 4, 30, 4);
        int i = this.func_74865_a(0, 0);
        int j = this.func_74862_a(0);
        int k = this.func_74873_b(0, 0);

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
