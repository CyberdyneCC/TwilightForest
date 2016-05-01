package twilightforest.structures.icetower;

import net.minecraft.init.Blocks;
import twilightforest.block.TFBlocks;
import twilightforest.structures.StructureTFDecorator;

public class StructureDecoratorIceTower extends StructureTFDecorator {

    public StructureDecoratorIceTower() {
        this.blockID = TFBlocks.auroraBlock;
        this.blockMeta = 0;
        this.accentID = Blocks.field_150344_f;
        this.accentMeta = 2;
        this.fenceID = Blocks.field_150422_aJ;
        this.stairID = Blocks.field_150487_bG;
        this.pillarID = TFBlocks.auroraPillar;
        this.pillarMeta = 0;
        this.platformID = Blocks.field_150376_bx;
        this.platformMeta = 2;
        this.floorID = Blocks.field_150344_f;
        this.floorMeta = 2;
        this.randomBlocks = new StructureTFAuroraBricks();
    }
}
