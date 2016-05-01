package twilightforest.structures.lichtower;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class ComponentTFTowerRoofSlabForwards extends ComponentTFTowerRoofSlab {

    public ComponentTFTowerRoofSlabForwards() {}

    public ComponentTFTowerRoofSlabForwards(int i, ComponentTFTowerWing wing) {
        super(i, wing);
        this.setCoordBaseMode(wing.getCoordBaseMode());
        this.size = wing.size + 2;
        this.height = this.size / 2;
        this.makeAttachedOverhangBB(wing);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        for (int y = 0; y <= this.height; ++y) {
            int min = 2 * y;
            int max = this.size - 2 * y - 1;

            for (int x = 0; x <= max - 1; ++x) {
                for (int z = min; z <= max; ++z) {
                    if (x != max - 1 && z != min && z != max) {
                        this.func_151550_a(world, Blocks.field_150373_bw, 2, x, y, z, sbb);
                    } else {
                        this.func_151550_a(world, Blocks.field_150376_bx, 2, x, y, z, sbb);
                    }
                }
            }
        }

        return true;
    }
}
