package twilightforest.world;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class TFGenOutsideStalagmite extends TFGenCaveStalactite {

    public TFGenOutsideStalagmite() {
        super(Blocks.field_150348_b, 1.0F, false);
    }

    public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
        int length = rand.nextInt(10) + 5;

        return !this.isAreaClear(world, rand, x, y, z, 1, length, 1) ? false : this.makeSpike(world, rand, x, y - 1, z, length);
    }
}
