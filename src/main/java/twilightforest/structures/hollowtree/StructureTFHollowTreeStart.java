package twilightforest.structures.hollowtree;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureStart;
import twilightforest.world.TFWorld;

public class StructureTFHollowTreeStart extends StructureStart {

    public StructureTFHollowTreeStart() {}

    public StructureTFHollowTreeStart(World world, Random rand, int chunkX, int chunkZ) {
        int x = (chunkX << 4) + 8;
        int z = (chunkZ << 4) + 8;
        int y = TFWorld.SEALEVEL + 1;
        ComponentTFHollowTreeTrunk trunk = new ComponentTFHollowTreeTrunk(world, rand, 0, x, y, z);

        this.field_75075_a.add(trunk);
        trunk.func_74861_a(trunk, this.field_75075_a, rand);
        this.func_75072_c();
    }
}
