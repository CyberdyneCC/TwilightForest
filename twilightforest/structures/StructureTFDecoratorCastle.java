package twilightforest.structures;

import net.minecraft.init.Blocks;
import twilightforest.block.TFBlocks;

public class StructureTFDecoratorCastle extends StructureTFDecorator {

    public StructureTFDecoratorCastle() {
        this.blockID = TFBlocks.castleBlock;
        this.blockMeta = 0;
        this.accentID = Blocks.field_150371_ca;
        this.accentMeta = 1;
        this.roofID = TFBlocks.castleBlock;
        this.roofMeta = 3;
        this.pillarID = Blocks.field_150371_ca;
        this.pillarMeta = 2;
        this.fenceID = Blocks.field_150422_aJ;
        this.fenceMeta = 0;
        this.stairID = Blocks.field_150370_cb;
        this.randomBlocks = new StructureTFCastleBlocks();
    }
}
