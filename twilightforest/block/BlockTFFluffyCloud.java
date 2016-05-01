package twilightforest.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import twilightforest.item.TFItems;

public class BlockTFFluffyCloud extends Block {

    protected BlockTFFluffyCloud() {
        super(Material.field_151598_x);
        this.func_149672_a(BlockTFFluffyCloud.field_149775_l);
        this.func_149647_a(TFItems.creativeTab);
        this.func_149711_c(0.8F);
        this.func_149658_d("TwilightForest:fluffy_cloud");
    }
}
