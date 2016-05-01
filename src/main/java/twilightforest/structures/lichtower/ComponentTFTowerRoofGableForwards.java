package twilightforest.structures.lichtower;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class ComponentTFTowerRoofGableForwards extends ComponentTFTowerRoof {

    public ComponentTFTowerRoofGableForwards() {}

    public ComponentTFTowerRoofGableForwards(int i, ComponentTFTowerWing wing) {
        super(i, wing);
        this.setCoordBaseMode(wing.getCoordBaseMode());
        this.size = wing.size + 2;
        this.height = this.size;
        this.makeAttachedOverhangBB(wing);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        byte slabMeta = 2;
        int slopeChange = this.slopeChangeForSize(this.size);

        int top;
        int zMid;

        for (top = 0; top <= this.height; ++top) {
            int max;

            if (top < slopeChange) {
                zMid = top;
                max = this.size - top - 1;
            } else {
                zMid = (top + slopeChange) / 2;
                max = this.size - (top + slopeChange) / 2 - 1;
            }

            for (int x = 0; x <= this.size - 2; ++x) {
                for (int z = zMid; z <= max; ++z) {
                    if (z != zMid && z != max) {
                        if (x < this.size - 2) {
                            this.func_151550_a(world, Blocks.field_150344_f, slabMeta, x, top, z, sbb);
                        }
                    } else {
                        this.func_151550_a(world, Blocks.field_150344_f, slabMeta, x, top, z, sbb);
                    }
                }
            }
        }

        top = this.size + 1 - slopeChange;
        zMid = this.size / 2;
        this.func_151550_a(world, Blocks.field_150376_bx, slabMeta | 10, this.size - 1, top - 1, zMid, sbb);
        this.func_151550_a(world, Blocks.field_150376_bx, slabMeta, 0, top, zMid, sbb);
        this.func_151550_a(world, Blocks.field_150376_bx, slabMeta, this.size - 3, top, zMid, sbb);
        this.func_151550_a(world, Blocks.field_150344_f, slabMeta, this.size - 2, top, zMid, sbb);
        this.func_151550_a(world, Blocks.field_150344_f, slabMeta, this.size - 1, top, zMid, sbb);
        this.func_151550_a(world, Blocks.field_150344_f, slabMeta, this.size - 1, top + 1, zMid, sbb);
        return true;
    }

    public int slopeChangeForSize(int pSize) {
        return this.size > 10 ? 3 : (this.size > 6 ? 2 : 1);
    }
}
