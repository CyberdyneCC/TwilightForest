package twilightforest.structures.darktower;

import java.util.List;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.structures.StructureTFComponent;
import twilightforest.structures.lichtower.ComponentTFTowerRoof;
import twilightforest.structures.lichtower.ComponentTFTowerWing;

public class ComponentTFDarkTowerRoof extends ComponentTFTowerRoof {

    public ComponentTFDarkTowerRoof() {}

    public ComponentTFDarkTowerRoof(int i, ComponentTFTowerWing wing) {
        super(i, wing);
        this.setCoordBaseMode(wing.getCoordBaseMode());
        this.size = wing.size;
        this.height = 12;
        this.makeCapBB(wing);
        this.spawnListIndex = 1;
    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        if (parent != null && parent instanceof StructureTFComponent) {
            this.deco = ((StructureTFComponent) parent).deco;
        }

    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        for (int x = 0; x <= this.size - 1; ++x) {
            for (int z = 0; z <= this.size - 1; ++z) {
                if (x == 0 || x == this.size - 1 || z == 0 || z == this.size - 1) {
                    this.func_151550_a(world, this.deco.fenceID, this.deco.fenceMeta, x, 1, z, sbb);
                }
            }
        }

        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, 0, 1, 0, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, this.size - 1, 1, 0, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, 0, 1, this.size - 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, this.size - 1, 1, this.size - 1, sbb);
        return true;
    }
}
