package twilightforest.structures.lichtower;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFTowerBridge extends ComponentTFTowerWing {

    int dSize;
    int dHeight;

    public ComponentTFTowerBridge() {}

    protected ComponentTFTowerBridge(int i, int x, int y, int z, int pSize, int pHeight, int direction) {
        super(i, x, y, z, 3, 3, direction);
        this.dSize = pSize;
        this.dHeight = pHeight;
    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        int[] dest = new int[] { 2, 1, 1};

        this.makeTowerWing(list, rand, 1, dest[0], dest[1], dest[2], this.dSize, this.dHeight, 0);
    }

    public StructureBoundingBox getWingBB() {
        int[] dest = this.offsetTowerCoords(2, 1, 1, this.dSize, this.getCoordBaseMode());

        return StructureTFComponent.getComponentToAddBoundingBox(dest[0], dest[1], dest[2], 0, 0, 0, this.dSize - 1, this.dHeight - 1, this.dSize - 1, this.getCoordBaseMode());
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        for (int x = 0; x < 3; ++x) {
            this.func_151550_a(world, Blocks.field_150422_aJ, 0, x, 2, 0, sbb);
            this.func_151550_a(world, Blocks.field_150422_aJ, 0, x, 2, 2, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, x, 1, 0, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, x, 1, 2, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, x, 0, 0, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, x, 0, 1, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, x, 0, 2, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, x, -1, 1, sbb);
        }

        this.func_151550_a(world, Blocks.field_150417_aV, 0, -1, -1, 1, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 0, 3, -1, 1, sbb);
        this.func_74878_a(world, sbb, 0, 1, 1, 2, 2, 1);
        return true;
    }
}
