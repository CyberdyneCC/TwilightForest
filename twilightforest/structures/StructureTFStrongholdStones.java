package twilightforest.structures;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.StructureComponent.BlockSelector;

public class StructureTFStrongholdStones extends BlockSelector {

    public void func_75062_a(Random par1Random, int par2, int par3, int par4, boolean par5) {
        if (!par5) {
            this.field_151562_a = Blocks.field_150350_a;
            this.field_75065_b = 0;
        } else {
            this.field_151562_a = Blocks.field_150417_aV;
            float f = par1Random.nextFloat();

            if (f < 0.2F) {
                this.field_75065_b = 2;
            } else if (f < 0.5F) {
                this.field_75065_b = 1;
            } else if (f < 0.55F) {
                this.field_151562_a = Blocks.field_150418_aU;
                this.field_75065_b = 2;
            } else {
                this.field_75065_b = 0;
            }
        }

    }
}
