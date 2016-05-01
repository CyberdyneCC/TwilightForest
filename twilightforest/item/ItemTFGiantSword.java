package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.IIcon;

public class ItemTFGiantSword extends ItemSword {

    private GiantItemIcon giantIcon;

    public ItemTFGiantSword(ToolMaterial par2EnumToolMaterial) {
        super(par2EnumToolMaterial);
        this.func_77637_a(TFItems.creativeTab);
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == TFItems.ironwoodIngot ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = Items.field_151052_q.func_77617_a(0);
        this.giantIcon = new GiantItemIcon(this.field_77791_bV, 0.1875F, 0.3125F);
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return (IIcon) (pass == -1 ? this.giantIcon : super.getIcon(stack, pass));
    }
}
