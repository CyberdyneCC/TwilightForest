package twilightforest.structures.minotaurmaze;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.StructureComponent.BlockSelector;
import twilightforest.block.TFBlocks;

public class StructureTFMazeStones extends BlockSelector {

    public void func_75062_a(Random par1Random, int par2, int par3, int par4, boolean wall) {
        if (!wall) {
            this.field_151562_a = Blocks.field_150350_a;
            this.field_75065_b = 0;
        } else {
            this.field_151562_a = TFBlocks.mazestone;
            float rf = par1Random.nextFloat();

            if (rf < 0.2F) {
                this.field_75065_b = 5;
            } else if (rf < 0.5F) {
                this.field_75065_b = 4;
            } else {
                this.field_75065_b = 1;
            }
        }

    }
}
