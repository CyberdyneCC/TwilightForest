package twilightforest.structures.stronghold;

import net.minecraft.init.Blocks;
import twilightforest.block.TFBlocks;
import twilightforest.structures.StructureTFDecorator;

public class StructureTFDecoratorStronghold extends StructureTFDecorator {

    public StructureTFDecoratorStronghold() {
        this.blockID = TFBlocks.underBrick;
        this.blockMeta = 0;
        this.accentID = TFBlocks.underBrick;
        this.accentMeta = 3;
        this.fenceID = Blocks.field_150463_bK;
        this.stairID = Blocks.field_150390_bg;
        this.pillarID = Blocks.field_150417_aV;
        this.pillarMeta = 1;
        this.platformID = Blocks.field_150333_U;
        this.platformMeta = 13;
        this.randomBlocks = new StructureTFKnightStones();
    }
}
