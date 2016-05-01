package twilightforest.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import twilightforest.block.BlockTFAuroraSlab;

public class ItemBlockTFAuroraSlab extends ItemSlab {

    public ItemBlockTFAuroraSlab(Block block, BlockTFAuroraSlab singleSlab, BlockTFAuroraSlab doubleSlab, Boolean isDouble) {
        super(block, singleSlab, doubleSlab, isDouble.booleanValue());
    }
}
