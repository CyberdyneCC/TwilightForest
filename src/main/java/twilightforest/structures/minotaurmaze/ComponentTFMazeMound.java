package twilightforest.structures.minotaurmaze;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFMazeMound extends StructureTFComponent {

    public static final int DIAMETER = 35;
    private int averageGroundLevel = -1;
    private ComponentTFMazeUpperEntrance mazeAbove;

    public ComponentTFMazeMound() {}

    public ComponentTFMazeMound(int i, Random rand, int x, int y, int z) {
        super(i);
        this.field_74885_f = rand.nextInt(4);
        this.field_74887_e = new StructureBoundingBox(x, y, z, x + 35, y + 8, z + 35);
    }

    public void func_74861_a(StructureComponent structurecomponent, List list, Random random) {
        super.func_74861_a(structurecomponent, list, random);
        this.mazeAbove = new ComponentTFMazeUpperEntrance(3, random, this.field_74887_e.field_78897_a + 10, this.field_74887_e.field_78895_b + 0, this.field_74887_e.field_78896_c + 10);
        list.add(this.mazeAbove);
        this.mazeAbove.func_74861_a(this, list, random);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        int x;

        if (this.averageGroundLevel < 0) {
            this.averageGroundLevel = this.getAverageGroundLevel(world, sbb);
            if (this.averageGroundLevel < 0) {
                return true;
            }

            x = this.averageGroundLevel - this.field_74887_e.field_78894_e + 8 - 1;
            this.field_74887_e.func_78886_a(0, x, 0);
            if (this.mazeAbove != null) {
                this.mazeAbove.func_74874_b().func_78886_a(0, x, 0);
            }
        }

        for (x = 0; x < 35; ++x) {
            for (int z = 0; z < 35; ++z) {
                int cx = x - 17;
                int cz = z - 17;
                int dist = (int) Math.sqrt((double) (cx * cx + cz * cz));
                int hheight = (int) (Math.cos((double) dist / 35.0D * 3.141592653589793D) * 11.0D);

                if ((cx > 2 || cx < -1 || cz > 2 || cz < -1) && ((cx > 2 || cx < -1) && (cz > 2 || cz < -1) || hheight > 6)) {
                    this.func_151550_a(world, Blocks.field_150349_c, 0, x, hheight, z, sbb);
                    if ((cx > 2 || cx < -1) && (cz > 2 || cz < -1)) {
                        this.func_151554_b(world, Blocks.field_150346_d, 0, x, hheight - 1, z, sbb);
                    } else if (hheight > 6) {
                        this.func_151549_a(world, sbb, x, 6, z, x, hheight - 1, z, Blocks.field_150346_d, Blocks.field_150350_a, false);
                    }
                }
            }
        }

        return true;
    }

    protected int getAverageGroundLevel(World par1World, StructureBoundingBox par2StructureBoundingBox) {
        int totalHeight = 0;
        int totalMeasures = 0;

        for (int i = this.field_74887_e.field_78896_c; i <= this.field_74887_e.field_78892_f; ++i) {
            for (int j = this.field_74887_e.field_78897_a; j <= this.field_74887_e.field_78893_d; ++j) {
                if (par2StructureBoundingBox.func_78890_b(j, 64, i)) {
                    totalHeight += Math.max(par1World.func_72825_h(j, i), par1World.field_73011_w.func_76557_i());
                    ++totalMeasures;
                }
            }
        }

        if (totalMeasures == 0) {
            return -1;
        } else {
            return totalHeight / totalMeasures;
        }
    }
}
