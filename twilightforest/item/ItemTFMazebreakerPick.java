package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import twilightforest.block.TFBlocks;

public class ItemTFMazebreakerPick extends ItemPickaxe {

    protected ItemTFMazebreakerPick(ToolMaterial par2EnumToolMaterial) {
        super(par2EnumToolMaterial);
        this.func_77637_a(TFItems.creativeTab);
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        ItemStack istack = new ItemStack(par1, 1, 0);

        istack.func_77966_a(Enchantment.field_77349_p, 4);
        istack.func_77966_a(Enchantment.field_77347_r, 3);
        istack.func_77966_a(Enchantment.field_77346_s, 2);
        par3List.add(istack);
    }

    public float func_150893_a(ItemStack par1ItemStack, Block par2Block) {
        float strVsBlock = super.func_150893_a(par1ItemStack, par2Block);

        return par2Block == TFBlocks.mazestone ? strVsBlock * 16.0F : strVsBlock;
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }
}
