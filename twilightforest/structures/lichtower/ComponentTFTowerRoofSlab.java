package twilightforest.structures.lichtower;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class ComponentTFTowerRoofSlab extends ComponentTFTowerRoof {

    public ComponentTFTowerRoofSlab() {}

    public ComponentTFTowerRoofSlab(int i, ComponentTFTowerWing wing) {
        super(i, wing);
        this.setCoordBaseMode(wing.getCoordBaseMode());
        this.size = wing.size;
        this.height = this.size / 2;
        this.makeCapBB(wing);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        byte slabMeta = 2;

        return this.makePyramidCap(world, slabMeta, sbb);
    }

    protected boolean makePyramidCap(World world, int slabMeta, StructureBoundingBox sbb) {
        for (int y = 0; y <= this.height; ++y) {
            int min = 2 * y;
            int max = this.size - 2 * y - 1;

            for (int x = min; x <= max; ++x) {
                for (int z = min; z <= max; ++z) {
                    if (x != min && x != max && z != min && z != max) {
                        this.func_151550_a(world, Blocks.field_150344_f, slabMeta, x, y, z, sbb);
                    } else {
                        this.func_151550_a(world, Blocks.field_150376_bx, slabMeta, x, y, z, sbb);
                    }
                }
            }
        }

        return true;
    }

    protected boolean makeConnectedCap(World world, int slabMeta, StructureBoundingBox sbb) {
        for (int y = 0; y < this.height; ++y) {
            int min = 2 * y;
            int max = this.size - 2 * y - 1;

            for (int x = 0; x <= max; ++x) {
                for (int z = min; z <= max; ++z) {
                    if (x != max && z != min && z != max) {
                        this.func_151550_a(world, Blocks.field_150344_f, slabMeta, x, y, z, sbb);
                    } else {
                        this.func_151550_a(world, Blocks.field_150376_bx, slabMeta, x, y, z, sbb);
                    }
                }
            }
        }

        return true;
    }
}
