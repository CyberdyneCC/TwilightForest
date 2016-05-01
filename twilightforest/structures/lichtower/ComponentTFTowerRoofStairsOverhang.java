package twilightforest.structures.lichtower;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class ComponentTFTowerRoofStairsOverhang extends ComponentTFTowerRoof {

    public ComponentTFTowerRoofStairsOverhang() {}

    public ComponentTFTowerRoofStairsOverhang(int i, ComponentTFTowerWing wing) {
        super(i, wing);
        this.setCoordBaseMode(0);
        this.size = wing.size + 2;
        this.height = this.size / 2;
        this.field_74887_e = new StructureBoundingBox(wing.func_74874_b().field_78897_a - 1, wing.func_74874_b().field_78894_e, wing.func_74874_b().field_78896_c - 1, wing.func_74874_b().field_78893_d + 1, wing.func_74874_b().field_78894_e + this.height - 1, wing.func_74874_b().field_78892_f + 1);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        for (int y = 0; y <= this.height; ++y) {
            int min = y;
            int max = this.size - y - 1;

            for (int x = y; x <= max; ++x) {
                for (int z = min; z <= max; ++z) {
                    if (x == min) {
                        if (z != min && z != max) {
                            this.func_151550_a(world, Blocks.field_150487_bG, 0, x, y, z, sbb);
                        } else {
                            this.func_151550_a(world, Blocks.field_150376_bx, 2, x, y, z, sbb);
                        }
                    } else if (x == max) {
                        if (z != min && z != max) {
                            this.func_151550_a(world, Blocks.field_150487_bG, 1, x, y, z, sbb);
                        } else {
                            this.func_151550_a(world, Blocks.field_150376_bx, 2, x, y, z, sbb);
                        }
                    } else if (z == max) {
                        this.func_151550_a(world, Blocks.field_150487_bG, 3, x, y, z, sbb);
                    } else if (z == min) {
                        this.func_151550_a(world, Blocks.field_150487_bG, 2, x, y, z, sbb);
                    } else {
                        this.func_151550_a(world, Blocks.field_150344_f, 2, x, y, z, sbb);
                    }
                }
            }
        }

        return true;
    }
}
