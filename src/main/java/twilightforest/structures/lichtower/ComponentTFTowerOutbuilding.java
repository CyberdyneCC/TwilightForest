package twilightforest.structures.lichtower;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentTFTowerOutbuilding extends ComponentTFTowerWing {

    public ComponentTFTowerOutbuilding() {}

    protected ComponentTFTowerOutbuilding(int i, int x, int y, int z, int pSize, int pHeight, int direction) {
        super(i, x, y, z, pSize, pHeight, direction);
    }

    public void makeABeard(StructureComponent parent, List list, Random rand) {}

    public boolean makeTowerWing(List list, Random rand, int index, int x, int y, int z, int wingSize, int wingHeight, int direction) {
        return y > 7 ? super.makeTowerWing(list, rand, index, x, y, z, wingSize, wingHeight, direction) : false;
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        for (int x = 0; x < this.size; ++x) {
            for (int z = 0; z < this.size; ++z) {
                this.func_151554_b(world, Blocks.field_150347_e, 0, x, -1, z, sbb);
            }
        }

        return super.func_74875_a(world, rand, sbb);
    }
}
