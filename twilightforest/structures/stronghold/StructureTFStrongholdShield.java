package twilightforest.structures.stronghold;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.block.TFBlocks;

public class StructureTFStrongholdShield extends StructureTFStrongholdComponent {

    public StructureTFStrongholdShield() {}

    public StructureTFStrongholdShield(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        super(0, 0, minX, minY, minZ);
        this.field_74887_e = new StructureBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
        this.spawnListIndex = -1;
    }

    public StructureBoundingBox generateBoundingBox(int facing, int x, int y, int z) {
        return null;
    }

    public boolean func_74875_a(World world, Random random, StructureBoundingBox sbb) {
        Block shieldBlock = TFBlocks.shield;

        this.func_151556_a(world, sbb, this.field_74887_e.func_78883_b(), 0, 0, this.field_74887_e.func_78883_b(), this.field_74887_e.func_78882_c(), this.field_74887_e.func_78880_d(), shieldBlock, 4, shieldBlock, 4, false);
        this.func_151556_a(world, sbb, 0, 0, 0, 0, this.field_74887_e.func_78882_c(), this.field_74887_e.func_78880_d(), shieldBlock, 5, shieldBlock, 5, false);
        this.func_151556_a(world, sbb, 0, 0, this.field_74887_e.func_78880_d(), this.field_74887_e.func_78883_b(), this.field_74887_e.func_78882_c(), this.field_74887_e.func_78880_d(), shieldBlock, 2, shieldBlock, 2, false);
        this.func_151556_a(world, sbb, 0, 0, 0, this.field_74887_e.func_78883_b(), this.field_74887_e.func_78882_c(), 0, shieldBlock, 3, shieldBlock, 3, false);
        this.func_151556_a(world, sbb, 0, 0, 0, this.field_74887_e.func_78883_b(), 0, this.field_74887_e.func_78880_d(), shieldBlock, 1, shieldBlock, 1, false);
        this.func_151556_a(world, sbb, 0, this.field_74887_e.func_78882_c(), 0, this.field_74887_e.func_78883_b(), this.field_74887_e.func_78882_c(), this.field_74887_e.func_78880_d(), shieldBlock, 0, shieldBlock, 0, false);
        return true;
    }
}
