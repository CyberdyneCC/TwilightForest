package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemTFFood extends ItemFood {

    protected Item looksLike;
    protected boolean isSoup;

    public ItemTFFood(int par2) {
        super(par2, false);
        this.func_77637_a(TFItems.creativeTab);
        this.setSoup(true);
    }

    public ItemTFFood(int par2, float par3, boolean par4) {
        super(par2, par3, par4);
        this.func_77637_a(TFItems.creativeTab);
    }

    public Item getLooksLike() {
        return this.looksLike;
    }

    public ItemTFFood setLooksLike(Item itemLike) {
        this.looksLike = itemLike;
        return this;
    }

    public IIcon func_77617_a(int par1) {
        return this.looksLike != null ? this.looksLike.func_77617_a(0) : super.func_77617_a(par1);
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        if (this.looksLike == null) {
            this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
        }

    }

    public boolean isSoup() {
        return this.isSoup;
    }

    public void setSoup(boolean isSoup) {
        this.isSoup = isSoup;
        this.func_77625_d(1);
    }

    public ItemStack func_77654_b(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        super.func_77654_b(par1ItemStack, par2World, par3EntityPlayer);
        return this.isSoup ? new ItemStack(Items.field_151054_z) : par1ItemStack;
    }
}
