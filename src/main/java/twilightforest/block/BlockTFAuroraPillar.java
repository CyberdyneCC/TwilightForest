package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import twilightforest.item.TFItems;

public class BlockTFAuroraPillar extends BlockRotatedPillar {

    private IIcon sideIcon;
    private IIcon topIcon;

    protected BlockTFAuroraPillar() {
        super(Material.field_151598_x);
        this.func_149647_a(TFItems.creativeTab);
        this.func_149711_c(2.0F);
        this.func_149752_b(10.0F);
    }

    public int func_149720_d(IBlockAccess world, int x, int y, int z) {
        boolean red = false;
        boolean green = false;
        boolean blue = false;
        int normalColor = TFBlocks.auroraBlock.func_149720_d(world, x, y, z);
        int red1 = normalColor >> 16 & 255;
        int blue1 = normalColor & 255;
        int green1 = normalColor >> 8 & 255;
        float[] hsb = Color.RGBtoHSB(red1, blue1, green1, (float[]) null);

        return Color.HSBtoRGB(hsb[0], hsb[1] * 0.5F, Math.min(hsb[2] + 0.4F, 0.9F));
    }

    @SideOnly(Side.CLIENT)
    public int func_149635_D() {
        return this.func_149720_d((IBlockAccess) null, 16, 0, 16);
    }

    @SideOnly(Side.CLIENT)
    public int func_149741_i(int meta) {
        return this.func_149635_D();
    }

    @SideOnly(Side.CLIENT)
    protected IIcon func_150163_b(int meta) {
        return this.sideIcon;
    }

    @SideOnly(Side.CLIENT)
    protected IIcon func_150161_d(int p_150161_1_) {
        return this.topIcon;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.sideIcon = par1IconRegister.func_94245_a("TwilightForest:aurora_pillar_side");
        this.topIcon = par1IconRegister.func_94245_a("TwilightForest:aurora_pillar_top");
    }
}
