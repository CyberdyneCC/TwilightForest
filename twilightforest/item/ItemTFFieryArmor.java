package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.StatCollector;
import twilightforest.TwilightForestMod;

public class ItemTFFieryArmor extends ItemArmor {

    public ItemTFFieryArmor(ArmorMaterial par2EnumArmorMaterial, int renderIndex, int armorType) {
        super(par2EnumArmorMaterial, renderIndex, armorType);
        this.func_77637_a(TFItems.creativeTab);
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.epic;
    }

    public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String layer) {
        return itemstack.func_77973_b() != TFItems.fieryPlate && itemstack.func_77973_b() != TFItems.fieryHelm && itemstack.func_77973_b() != TFItems.fieryBoots ? (itemstack.func_77973_b() == TFItems.fieryLegs ? "twilightforest:textures/armor/fiery_2.png" : "twilightforest:textures/armor/fiery_1.png") : "twilightforest:textures/armor/fiery_1.png";
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        ItemStack istack = new ItemStack(par1, 1, 0);

        par3List.add(istack);
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == TFItems.fieryIngot ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.func_77624_a(par1ItemStack, par2EntityPlayer, par3List, par4);
        par3List.add(StatCollector.func_74838_a(this.func_77658_a() + ".tooltip"));
    }

    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        return TwilightForestMod.proxy.getFieryArmorModel(armorSlot);
    }
}
