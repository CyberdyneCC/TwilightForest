package twilightforest.item;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import twilightforest.entity.EntityTFLoyalZombie;

public class ItemTFZombieWand extends ItemTF {

    protected ItemTFZombieWand() {
        this.field_77777_bU = 1;
        this.func_77656_e(9);
        this.func_77637_a(TFItems.creativeTab);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World worldObj, EntityPlayer player) {
        if (par1ItemStack.func_77960_j() < this.func_77612_l()) {
            player.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
            if (!worldObj.field_72995_K) {
                MovingObjectPosition mop = this.getPlayerPointVec(worldObj, player, 20.0F);

                if (mop != null) {
                    EntityTFLoyalZombie zombie = new EntityTFLoyalZombie(worldObj);

                    zombie.func_70080_a(mop.field_72307_f.field_72450_a, mop.field_72307_f.field_72448_b, mop.field_72307_f.field_72449_c, 1.0F, 1.0F);
                    zombie.func_70903_f(true);

                    try {
                        zombie.func_152115_b(player.func_110124_au().toString());
                    } catch (NoSuchMethodError nosuchmethoderror) {
                        FMLLog.warning("[TwilightForest] Could not determine player name for loyal zombie, ignoring error.", new Object[0]);
                    }

                    zombie.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, 1200, 1));
                    worldObj.func_72838_d(zombie);
                    par1ItemStack.func_77972_a(1, player);
                }
            }
        } else {
            player.func_71034_by();
        }

        return par1ItemStack;
    }

    private MovingObjectPosition getPlayerPointVec(World worldObj, EntityPlayer player, float range) {
        Vec3 position = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + (double) player.func_70047_e(), player.field_70161_v);
        Vec3 look = player.func_70676_i(1.0F);
        Vec3 dest = position.func_72441_c(look.field_72450_a * (double) range, look.field_72448_b * (double) range, look.field_72449_c * (double) range);

        return worldObj.func_72933_a(position, dest);
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 30;
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add(par1ItemStack.func_77958_k() - par1ItemStack.func_77960_j() + " charges left");
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }
}
