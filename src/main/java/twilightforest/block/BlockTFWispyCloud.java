package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import twilightforest.item.TFItems;

public class BlockTFWispyCloud extends BlockBreakable {

    protected BlockTFWispyCloud() {
        super("", Material.field_151596_z, false);
        this.func_149672_a(BlockTFWispyCloud.field_149775_l);
        this.func_149647_a(TFItems.creativeTab);
        this.func_149711_c(0.3F);
        this.func_149658_d("TwilightForest:wispy_cloud");
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister p_149651_1_) {
        this.field_149761_L = p_149651_1_.func_94245_a(this.func_149641_N());
    }

    @SideOnly(Side.CLIENT)
    public int func_149701_w() {
        return 1;
    }

    protected boolean func_149700_E() {
        return true;
    }

    public int func_149745_a(Random p_149745_1_) {
        return 0;
    }

    public boolean func_149686_d() {
        return false;
    }
}
