package twilightforest.structures.lichtower;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class ComponentTFTowerRoofPointy extends ComponentTFTowerRoof {

    public ComponentTFTowerRoofPointy() {}

    public ComponentTFTowerRoofPointy(int i, ComponentTFTowerWing wing) {
        super(i, wing);
        this.setCoordBaseMode(wing.getCoordBaseMode());
        this.size = wing.size;
        this.height = this.size;
        this.makeCapBB(wing);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        byte slabMeta = 2;

        for (int y = 0; y <= this.height; ++y) {
            int slopeChange = this.slopeChangeForSize(this.size);
            int min;
            int max;

            if (y < slopeChange) {
                min = y;
                max = this.size - y - 1;
            } else {
                min = (y + slopeChange) / 2;
                max = this.size - (y + slopeChange) / 2 - 1;
            }

            int mid = min + (max - min) / 2;

            for (int x = min; x <= max; ++x) {
                for (int z = min; z <= max; ++z) {
                    this.func_151550_a(world, Blocks.field_150344_f, slabMeta, x, y, z, sbb);
                    if (x == min && (z == min || z == max) || x == max && (z == min || z == max)) {
                        this.func_151550_a(world, Blocks.field_150376_bx, slabMeta, x, y + 1, z, sbb);
                    }

                    if (((x == min || x == max) && z == mid && x % 2 == 0 || (z == min || z == max) && x == mid && z % 2 == 0) && mid != min + 1) {
                        this.func_151550_a(world, Blocks.field_150376_bx, slabMeta, x, y + 1, z, sbb);
                    }
                }
            }
        }

        return true;
    }

    public int slopeChangeForSize(int pSize) {
        return this.size > 10 ? 3 : (this.size > 6 ? 2 : 1);
    }
}
