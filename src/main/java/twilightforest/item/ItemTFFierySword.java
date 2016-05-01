package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.StatCollector;

public class ItemTFFierySword extends ItemSword {

    public ItemTFFierySword(ToolMaterial par2EnumToolMaterial) {
        super(par2EnumToolMaterial);
        this.func_77637_a(TFItems.creativeTab);
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        ItemStack istack = new ItemStack(par1, 1, 0);

        par3List.add(istack);
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == TFItems.fieryIngot ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }

    public boolean func_77644_a(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {
        boolean result = super.func_77644_a(par1ItemStack, par2EntityLiving, par3EntityLiving);

        if (result && !par2EntityLiving.func_70045_F()) {
            if (par2EntityLiving.field_70170_p.field_72995_K) {
                for (int i = 0; i < 20; ++i) {
                    double d0 = ItemTFFierySword.field_77697_d.nextGaussian() * 0.02D;
                    double d1 = ItemTFFierySword.field_77697_d.nextGaussian() * 0.02D;
                    double d2 = ItemTFFierySword.field_77697_d.nextGaussian() * 0.02D;
                    double d3 = 10.0D;

                    par2EntityLiving.field_70170_p.func_72869_a("flame", par2EntityLiving.field_70165_t + (double) (ItemTFFierySword.field_77697_d.nextFloat() * par2EntityLiving.field_70130_N * 2.0F) - (double) par2EntityLiving.field_70130_N - d0 * d3, par2EntityLiving.field_70163_u + (double) (ItemTFFierySword.field_77697_d.nextFloat() * par2EntityLiving.field_70131_O) - d1 * d3, par2EntityLiving.field_70161_v + (double) (ItemTFFierySword.field_77697_d.nextFloat() * par2EntityLiving.field_70130_N * 2.0F) - (double) par2EntityLiving.field_70130_N - d2 * d3, d0, d1, d2);
                }
            } else {
                par2EntityLiving.func_70015_d(15);
            }
        }

        return result;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.func_77624_a(par1ItemStack, par2EntityPlayer, par3List, par4);
        par3List.add(StatCollector.func_74838_a(this.func_77658_a() + ".tooltip"));
    }
}
