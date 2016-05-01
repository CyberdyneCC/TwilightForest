package twilightforest.structures.icetower;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.StructureComponent.BlockSelector;
import twilightforest.block.TFBlocks;

public class StructureTFAuroraBricks extends BlockSelector {

    public void func_75062_a(Random par1Random, int x, int y, int z, boolean wall) {
        if (!wall) {
            this.field_151562_a = Blocks.field_150350_a;
            this.field_75065_b = 0;
        } else {
            this.field_151562_a = TFBlocks.auroraBlock;
            this.field_75065_b = Math.abs(x + z) % 16;
        }

    }
}
