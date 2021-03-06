package twilightforest.structures;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.StructureComponent.BlockSelector;
import twilightforest.block.TFBlocks;

public class StructureTFCastleBlocks extends BlockSelector {

    public void func_75062_a(Random par1Random, int x, int y, int z, boolean isWall) {
        if (!isWall) {
            this.field_151562_a = Blocks.field_150350_a;
            this.field_75065_b = 0;
        } else {
            this.field_151562_a = TFBlocks.castleBlock;
            float randFloat = par1Random.nextFloat();

            if (randFloat < 0.1F) {
                this.field_75065_b = 1;
            } else if (randFloat < 0.2F) {
                this.field_75065_b = 2;
            } else {
                this.field_75065_b = 0;
            }
        }

    }
}
