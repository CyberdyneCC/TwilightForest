package twilightforest.world;

import java.util.Random;
import net.minecraft.world.World;

public class TFGenNoTree extends TFTreeGenerator {

    public TFGenNoTree() {}

    public TFGenNoTree(boolean par1) {
        super(par1);
    }

    public boolean func_76484_a(World world, Random random, int i, int j, int k) {
        return false;
    }
}
