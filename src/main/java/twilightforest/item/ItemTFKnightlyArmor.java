package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import twilightforest.TwilightForestMod;

public class ItemTFKnightlyArmor extends ItemArmor {

    public ItemTFKnightlyArmor(ArmorMaterial par2EnumArmorMaterial, int renderIndex, int armorType) {
        super(par2EnumArmorMaterial, renderIndex, armorType);
        this.func_77637_a(TFItems.creativeTab);
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }

    public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String layer) {
        return slot == 2 ? "twilightforest:textures/armor/knightly_2.png" : "twilightforest:textures/armor/knightly_1.png";
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        ItemStack istack = new ItemStack(par1, 1, 0);

        par3List.add(istack);
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == TFItems.knightMetal ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }

    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        return TwilightForestMod.proxy.getKnightlyArmorModel(armorSlot);
    }
}
