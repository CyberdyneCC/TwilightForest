package twilightforest.structures.icetower;

import java.util.Random;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.structures.lichtower.ComponentTFTowerRoof;
import twilightforest.structures.lichtower.ComponentTFTowerWing;

public class ComponentTFIceTowerRoof extends ComponentTFTowerRoof {

    public ComponentTFIceTowerRoof() {}

    public ComponentTFIceTowerRoof(int i, ComponentTFTowerWing wing) {
        super(i, wing);
        this.setCoordBaseMode(wing.getCoordBaseMode());
        this.size = wing.size;
        this.height = 12;
        this.deco = wing.deco;
        this.makeCapBB(wing);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        super.func_74875_a(world, rand, sbb);

        for (int x = 0; x < this.size; ++x) {
            for (int z = 0; z < this.size; ++z) {
                int rHeight = Math.round(MathHelper.func_76129_c((float) (x * x + z * z)));

                for (int y = 0; y < rHeight; ++y) {
                    this.func_151550_a(world, this.deco.blockID, this.deco.blockMeta, x, y, z, sbb);
                }
            }
        }

        return true;
    }
}
