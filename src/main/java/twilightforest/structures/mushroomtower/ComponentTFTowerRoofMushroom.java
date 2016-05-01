package twilightforest.structures.mushroomtower;

import java.util.List;
import java.util.Random;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.structures.StructureTFComponent;
import twilightforest.structures.lichtower.ComponentTFTowerRoof;
import twilightforest.structures.lichtower.ComponentTFTowerWing;

public class ComponentTFTowerRoofMushroom extends ComponentTFTowerRoof {

    public ComponentTFTowerRoofMushroom(int i, ComponentTFTowerWing wing, float pHang) {
        super(i, wing);
        this.height = wing.size;
        int overhang = (int) ((float) wing.size * pHang);

        this.size = wing.size + overhang * 2;
        this.setCoordBaseMode(0);
        this.field_74887_e = new StructureBoundingBox(wing.func_74874_b().field_78897_a - overhang, wing.func_74874_b().field_78894_e + 2, wing.func_74874_b().field_78896_c - overhang, wing.func_74874_b().field_78893_d + overhang, wing.func_74874_b().field_78894_e + this.height + 1, wing.func_74874_b().field_78892_f + overhang);
    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        if (parent != null && parent instanceof StructureTFComponent) {
            this.deco = ((StructureTFComponent) parent).deco;
        }

    }

    public ComponentTFTowerRoofMushroom() {}

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        for (int y = 0; y <= this.height; ++y) {
            int radius = (int) (MathHelper.func_76126_a(((float) y + (float) this.height / 1.2F) / ((float) this.height * 2.05F) * 3.14F) * (float) this.size / 2.0F);
            int hollow = MathHelper.func_76141_d((float) radius * 0.9F);

            if (this.height - y < 3) {
                hollow = -1;
            }

            this.makeCircle(world, y, radius, hollow, sbb);
        }

        return true;
    }

    private void makeCircle(World world, int y, int radius, int hollow, StructureBoundingBox sbb) {
        int cx = this.size / 2;
        int cz = this.size / 2;

        for (int dx = -radius; dx <= radius; ++dx) {
            for (int dz = -radius; dz <= radius; ++dz) {
                float dist = MathHelper.func_76129_c((float) (dx * dx + dz * dz));

                if (dist <= (float) radius + 0.5F) {
                    if (dist > (float) hollow) {
                        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, dx + cx, y, dz + cz, sbb);
                    } else {
                        this.func_151550_a(world, this.deco.accentID, 0, dx + cx, y, dz + cz, sbb);
                    }
                }
            }
        }

    }
}
