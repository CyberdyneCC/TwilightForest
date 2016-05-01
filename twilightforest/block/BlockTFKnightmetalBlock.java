package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCompressed;
import net.minecraft.block.material.MapColor;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twilightforest.TwilightForestMod;
import twilightforest.item.TFItems;

public class BlockTFKnightmetalBlock extends BlockCompressed {

    private static final float BLOCK_DAMAGE = 4.0F;

    public BlockTFKnightmetalBlock() {
        super(MapColor.field_151668_h);
        this.func_149711_c(5.0F);
        this.func_149752_b(41.0F);
        this.func_149672_a(Block.field_149777_j);
        this.func_149658_d("TwilightForest:knightmetal_block");
        this.func_149647_a(TFItems.creativeTab);
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        float f = 0.0625F;

        return AxisAlignedBB.func_72330_a((double) ((float) x + f), (double) ((float) y + f), (double) ((float) z + f), (double) ((float) (x + 1) - f), (double) ((float) (y + 1) - f), (double) ((float) (z + 1) - f));
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        entity.func_70097_a(DamageSource.field_76367_g, 4.0F);
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return TwilightForestMod.proxy.getKnightmetalBlockRenderID();
    }

    @SideOnly(Side.CLIENT)
    public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return true;
    }
}
