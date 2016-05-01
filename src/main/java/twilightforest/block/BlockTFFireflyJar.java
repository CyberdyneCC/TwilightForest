package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twilightforest.TwilightForestMod;
import twilightforest.entity.passive.EntityTFTinyFirefly;
import twilightforest.item.TFItems;

public class BlockTFFireflyJar extends Block {

    public static IIcon jarTop;
    public static IIcon jarSide;
    public static IIcon jarCork;

    protected BlockTFFireflyJar() {
        super(Material.field_151592_s);
        this.func_149676_a(0.1875F, 0.0F, 0.1875F, 0.8125F, 1.0F, 0.8125F);
        this.func_149711_c(0.3F);
        this.func_149672_a(Block.field_149766_f);
        this.func_149647_a(TFItems.creativeTab);
        this.func_149715_a(1.0F);
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return TwilightForestMod.proxy.getComplexBlockRenderID();
    }

    public IIcon func_149691_a(int side, int meta) {
        return side != 1 && side != 0 ? BlockTFFireflyJar.jarSide : BlockTFFireflyJar.jarTop;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return 15;
    }

    public boolean isBlockNormalCube(World world, int x, int y, int z) {
        return false;
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        this.func_149676_a(0.1875F, 0.0F, 0.1875F, 0.8125F, 1.0F, 0.8125F);
    }

    public void func_149683_g() {
        this.func_149676_a(0.1875F, 0.0F, 0.1875F, 0.8125F, 1.0F, 0.8125F);
    }

    @SideOnly(Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
        double dx = (double) ((float) x + (rand.nextFloat() - rand.nextFloat()) * 0.3F + 0.5F);
        double dy = (double) ((float) y - 0.1F + (rand.nextFloat() - rand.nextFloat()) * 0.4F);
        double dz = (double) ((float) z + (rand.nextFloat() - rand.nextFloat()) * 0.3F + 0.5F);
        EntityTFTinyFirefly tinyfly = new EntityTFTinyFirefly(world, dx, dy, dz);

        world.func_72942_c(tinyfly);
        dx = (double) ((float) x + (rand.nextFloat() - rand.nextFloat()) * 0.3F + 0.5F);
        dy = (double) ((float) y - 0.1F + (rand.nextFloat() - rand.nextFloat()) * 0.4F);
        dz = (double) ((float) z + (rand.nextFloat() - rand.nextFloat()) * 0.3F + 0.5F);
        tinyfly = new EntityTFTinyFirefly(world, dx, dy, dz);
        world.func_72942_c(tinyfly);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        BlockTFFireflyJar.jarTop = par1IconRegister.func_94245_a("TwilightForest:fireflyjar_top");
        BlockTFFireflyJar.jarSide = par1IconRegister.func_94245_a("TwilightForest:fireflyjar_side");
        BlockTFFireflyJar.jarCork = par1IconRegister.func_94245_a("TwilightForest:fireflyjar_cork");
    }
}
