package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemTF extends Item {

    private boolean isRare = false;

    protected ItemTF() {
        this.func_77637_a(TFItems.creativeTab);
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return this.isRare ? EnumRarity.rare : EnumRarity.uncommon;
    }

    public ItemTF makeRare() {
        this.isRare = true;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }
}
