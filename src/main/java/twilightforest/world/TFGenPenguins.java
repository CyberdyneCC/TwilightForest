package twilightforest.world;

import java.util.Random;
import net.minecraft.world.World;
import twilightforest.entity.passive.EntityTFPenguin;

public class TFGenPenguins extends TFGenerator {

    public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
        for (int i = 0; i < 10; ++i) {
            int dx = x + rand.nextInt(8) - rand.nextInt(8);
            int dz = z + rand.nextInt(8) - rand.nextInt(8);
            int dy = world.func_72825_h(dx, dz);
            EntityTFPenguin penguin = new EntityTFPenguin(world);

            penguin.func_70012_b((double) dx, (double) dy, (double) dz, rand.nextFloat() * 360.0F, 0.0F);
            world.func_72838_d(penguin);
        }

        return true;
    }
}
