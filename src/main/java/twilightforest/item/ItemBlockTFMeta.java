package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockTFMeta extends ItemBlock {

    private final Block myBlock;

    public ItemBlockTFMeta(Block block) {
        super(block);
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.myBlock = block;
    }

    public int func_77647_b(int i) {
        return i;
    }

    public String func_77667_c(ItemStack itemstack) {
        int meta = itemstack.func_77960_j();

        return super.func_77658_a() + "." + meta;
    }

    public IIcon func_77617_a(int par1) {
        return this.myBlock.func_149691_a(2, par1);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.func_77624_a(par1ItemStack, par2EntityPlayer, par3List, par4);
        if (par1ItemStack.func_82833_r().contains("[WIP]")) {
            par3List.add("This block is a work in progress");
            par3List.add("and may have bugs or unintended");
            par3List.add("effects that may damage your world.");
            par3List.add("Use with caution.");
        }

        if (par1ItemStack.func_82833_r().contains("[NYI]")) {
            par3List.add("This block has effects");
            par3List.add("that are not yet implemented.");
        }

    }
}
