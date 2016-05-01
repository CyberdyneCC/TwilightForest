package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemTFFieryPick extends ItemPickaxe {

    protected ItemTFFieryPick(ToolMaterial par2EnumToolMaterial) {
        super(par2EnumToolMaterial);
        this.func_77637_a(TFItems.creativeTab);
    }

    public boolean func_150894_a(ItemStack par1ItemStack, World par2World, Block blockID, int x, int y, int z, EntityLivingBase par7EntityLiving) {
        if (super.func_150894_a(par1ItemStack, par2World, blockID, x, y, z, par7EntityLiving) && this.func_150897_b(blockID)) {
            if (par2World.field_72995_K) {
                int meta = par2World.func_72805_g(x, y, z);
                ArrayList items = blockID.getDrops(par2World, x, y, z, meta, 0);
                Iterator iterator = items.iterator();

                while (iterator.hasNext()) {
                    ItemStack input = (ItemStack) iterator.next();
                    ItemStack result = FurnaceRecipes.func_77602_a().func_151395_a(input);

                    if (result != null) {
                        for (int i = 0; i < 5; ++i) {
                            double rx = ItemTFFieryPick.field_77697_d.nextGaussian() * 0.02D;
                            double ry = ItemTFFieryPick.field_77697_d.nextGaussian() * 0.02D;
                            double rz = ItemTFFieryPick.field_77697_d.nextGaussian() * 0.02D;
                            double magnitude = 20.0D;

                            par2World.func_72869_a("flame", (double) x + 0.5D + rx * magnitude, (double) y + 0.5D + ry * magnitude, (double) z + 0.5D + rz * magnitude, -rx, -ry, -rz);
                        }
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public boolean func_77644_a(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {
        boolean result = super.func_77644_a(par1ItemStack, par2EntityLiving, par3EntityLiving);

        if (result && !par2EntityLiving.func_70045_F()) {
            if (par2EntityLiving.field_70170_p.field_72995_K) {
                for (int i = 0; i < 20; ++i) {
                    double d0 = ItemTFFieryPick.field_77697_d.nextGaussian() * 0.02D;
                    double d1 = ItemTFFieryPick.field_77697_d.nextGaussian() * 0.02D;
                    double d2 = ItemTFFieryPick.field_77697_d.nextGaussian() * 0.02D;
                    double d3 = 10.0D;

                    par2EntityLiving.field_70170_p.func_72869_a("flame", par2EntityLiving.field_70165_t + (double) (ItemTFFieryPick.field_77697_d.nextFloat() * par2EntityLiving.field_70130_N * 2.0F) - (double) par2EntityLiving.field_70130_N - d0 * d3, par2EntityLiving.field_70163_u + (double) (ItemTFFieryPick.field_77697_d.nextFloat() * par2EntityLiving.field_70131_O) - d1 * d3, par2EntityLiving.field_70161_v + (double) (ItemTFFieryPick.field_77697_d.nextFloat() * par2EntityLiving.field_70130_N * 2.0F) - (double) par2EntityLiving.field_70130_N - d2 * d3, d0, d1, d2);
                }
            } else {
                par2EntityLiving.func_70015_d(15);
            }
        }

        return result;
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.func_77624_a(par1ItemStack, par2EntityPlayer, par3List, par4);
        par3List.add(StatCollector.func_74838_a(this.func_77658_a() + ".tooltip"));
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == TFItems.fieryIngot ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    public boolean func_150897_b(Block par1Block) {
        return par1Block == Blocks.field_150343_Z ? true : super.func_150897_b(par1Block);
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }
}
