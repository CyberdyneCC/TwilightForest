package twilightforest.structures.darktower;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.structures.lichtower.ComponentTFTowerWing;

public class ComponentTFDarkTowerRoofAntenna extends ComponentTFDarkTowerRoof {

    public ComponentTFDarkTowerRoofAntenna() {}

    public ComponentTFDarkTowerRoofAntenna(int i, ComponentTFTowerWing wing) {
        super(i, wing);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        super.func_74875_a(world, rand, sbb);

        int y;

        for (y = 1; y < 10; ++y) {
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, this.size / 2, y, this.size / 2, sbb);
        }

        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, this.size / 2 - 1, 1, this.size / 2, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, this.size / 2 + 1, 1, this.size / 2, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, this.size / 2, 1, this.size / 2 - 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, this.size / 2, 1, this.size / 2 + 1, sbb);

        for (y = 7; y < 10; ++y) {
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, this.size / 2 - 1, y, this.size / 2, sbb);
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, this.size / 2 + 1, y, this.size / 2, sbb);
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, this.size / 2, y, this.size / 2 - 1, sbb);
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, this.size / 2, y, this.size / 2 + 1, sbb);
        }

        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, this.size / 2 - 1, 8, this.size / 2 - 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, this.size / 2 - 1, 8, this.size / 2 + 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, this.size / 2 + 1, 8, this.size / 2 - 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, this.size / 2 + 1, 8, this.size / 2 + 1, sbb);
        return true;
    }
}
