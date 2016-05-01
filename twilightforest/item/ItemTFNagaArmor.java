package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;

public class ItemTFNagaArmor extends ItemArmor {

    public ItemTFNagaArmor(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
        this.func_77637_a(TFItems.creativeTab);
    }

    public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String layer) {
        return itemstack.func_77973_b() == TFItems.plateNaga ? "twilightforest:textures/armor/naga_scale_1.png" : (itemstack.func_77973_b() == TFItems.legsNaga ? "twilightforest:textures/armor/naga_scale_2.png" : "twilightforest:textures/armor/naga_scale_1.png");
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == TFItems.nagaScale ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }
}
