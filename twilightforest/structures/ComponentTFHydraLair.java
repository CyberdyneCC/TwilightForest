package twilightforest.structures;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.block.TFBlocks;

public class ComponentTFHydraLair extends ComponentTFHollowHill {

    public ComponentTFHydraLair() {}

    public ComponentTFHydraLair(World world, Random rand, int i, int x, int y, int z) {
        super(world, rand, i, 2, x, y + 2, z);
    }

    public void func_74861_a(StructureComponent structurecomponent, List list, Random random) {}

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        byte stalacts = 64;
        byte stalags = 8;

        int i;
        int[] dest;

        for (i = 0; i < stalacts; ++i) {
            dest = this.getCoordsInHill2D(rand);
            this.generateOreStalactite(world, dest[0], 1, dest[1], sbb);
        }

        for (i = 0; i < stalacts; ++i) {
            dest = this.getCoordsInHill2D(rand);
            this.generateBlockStalactite(world, Blocks.field_150348_b, 1.0F, true, dest[0], 1, dest[1], sbb);
        }

        for (i = 0; i < stalags; ++i) {
            dest = this.getCoordsInHill2D(rand);
            this.generateBlockStalactite(world, Blocks.field_150348_b, 0.9F, false, dest[0], 1, dest[1], sbb);
        }

        this.func_151550_a(world, TFBlocks.bossSpawner, 2, 27, 3, 27, sbb);
        return true;
    }
}
