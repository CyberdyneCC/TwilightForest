package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import twilightforest.entity.boss.EntityTFIceBomb;

public class ItemTFIceBomb extends ItemTF {

    private IIcon[] snowIcon = new IIcon[4];

    public ItemTFIceBomb() {
        this.func_77625_d(16);
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));

        for (int i = 0; i < 4; ++i) {
            this.snowIcon[i] = par1IconRegister.func_94245_a("TwilightForest:snow_" + i);
        }

    }

    public IIcon getSnowIcon(int i) {
        return this.snowIcon[i];
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par3EntityPlayer.field_71075_bZ.field_75098_d) {
            --par1ItemStack.field_77994_a;
        }

        par2World.func_72956_a(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (ItemTFIceBomb.field_77697_d.nextFloat() * 0.4F + 0.8F));
        if (!par2World.field_72995_K) {
            par2World.func_72838_d(new EntityTFIceBomb(par2World, par3EntityPlayer));
        }

        return par1ItemStack;
    }
}
