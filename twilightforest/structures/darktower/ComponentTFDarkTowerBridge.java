package twilightforest.structures.darktower;

import java.util.List;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.structures.StructureTFComponent;
import twilightforest.structures.lichtower.ComponentTFTowerWing;

public class ComponentTFDarkTowerBridge extends ComponentTFTowerWing {

    int dSize;
    int dHeight;

    public ComponentTFDarkTowerBridge() {}

    protected ComponentTFDarkTowerBridge(int i, int x, int y, int z, int pSize, int pHeight, int direction) {
        super(i, x, y, z, 5, 5, direction);
        this.dSize = pSize;
        this.dHeight = pHeight;
    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        if (parent != null && parent instanceof StructureTFComponent) {
            this.deco = ((StructureTFComponent) parent).deco;
        }

        this.makeTowerWing(list, rand, this.func_74877_c(), 4, 1, 2, this.dSize, this.dHeight, 0);
    }

    public boolean makeTowerWing(List list, Random rand, int index, int x, int y, int z, int wingSize, int wingHeight, int rotation) {
        if (wingHeight < 6) {
            return false;
        } else {
            int direction = (this.getCoordBaseMode() + rotation) % 4;
            int[] dx = this.offsetTowerCoords(x, y, z, wingSize, direction);

            if (dx[1] + wingHeight > 255) {
                return false;
            } else {
                ComponentTFDarkTowerWing wing = new ComponentTFDarkTowerWing(index, dx[0], dx[1], dx[2], wingSize, wingHeight, direction);
                StructureComponent intersect = StructureComponent.func_74883_a(list, wing.func_74874_b());

                if (intersect != null && intersect != this) {
                    return false;
                } else {
                    list.add(wing);
                    wing.func_74861_a(this, list, rand);
                    this.addOpening(x, y, z, rotation);
                    return true;
                }
            }
        }
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.func_151549_a(world, sbb, 0, 0, 0, this.size - 1, this.height - 1, this.size - 1, this.deco.blockID, this.deco.blockID, false);

        for (int x = 0; x < this.size; ++x) {
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, x, 0, 0, sbb);
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, x, this.height - 1, 0, sbb);
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, x, 0, this.size - 1, sbb);
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, x, this.height - 1, this.size - 1, sbb);
        }

        this.nullifySkyLightForBoundingBox(world);
        this.func_74878_a(world, sbb, 0, 1, 1, this.size - 1, this.height - 2, this.size - 2);
        return true;
    }

    public StructureBoundingBox getWingBB() {
        int[] dest = this.offsetTowerCoords(4, 1, 2, this.dSize, this.getCoordBaseMode());

        return StructureTFComponent.getComponentToAddBoundingBox(dest[0], dest[1], dest[2], 0, 0, 0, this.dSize - 1, this.dHeight - 1, this.dSize - 1, this.getCoordBaseMode());
    }
}
