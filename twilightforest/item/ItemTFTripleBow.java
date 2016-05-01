package twilightforest.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

public class ItemTFTripleBow extends ItemTFBowBase {

    public ItemTFTripleBow() {
        this.func_111206_d("TwilightForest:triplebow");
        this.func_77637_a(TFItems.creativeTab);
    }

    public void func_77615_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {
        int j = this.func_77626_a(par1ItemStack) - par4;
        ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);

        MinecraftForge.EVENT_BUS.post(event);
        if (!event.isCanceled()) {
            j = event.charge;
            boolean flag = par3EntityPlayer.field_71075_bZ.field_75098_d || EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, par1ItemStack) > 0;

            if (flag || par3EntityPlayer.field_71071_by.func_146028_b(Items.field_151032_g)) {
                float f = (float) j / 20.0F;

                f = (f * f + f * 2.0F) / 3.0F;
                if ((double) f < 0.1D) {
                    return;
                }

                if (f > 1.0F) {
                    f = 1.0F;
                }

                EntityArrow entityarrow = new EntityArrow(par2World, par3EntityPlayer, f * 2.0F);
                EntityArrow entityarrow1 = new EntityArrow(par2World, par3EntityPlayer, f * 2.0F);

                entityarrow1.field_70181_x += 0.14999999664723873D;
                entityarrow1.field_70163_u += 0.02500000037252903D;
                EntityArrow entityarrow2 = new EntityArrow(par2World, par3EntityPlayer, f * 2.0F);

                entityarrow2.field_70181_x -= 0.14999999664723873D;
                entityarrow2.field_70163_u -= 0.02500000037252903D;
                if (f == 1.0F) {
                    entityarrow.func_70243_d(true);
                    entityarrow1.func_70243_d(true);
                    entityarrow2.func_70243_d(true);
                }

                int k = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, par1ItemStack);

                if (k > 0) {
                    entityarrow.func_70239_b(entityarrow.func_70242_d() + (double) k * 0.5D + 0.5D);
                    entityarrow1.func_70239_b(entityarrow.func_70242_d() + (double) k * 0.5D + 0.5D);
                    entityarrow2.func_70239_b(entityarrow.func_70242_d() + (double) k * 0.5D + 0.5D);
                }

                int l = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, par1ItemStack);

                if (l > 0) {
                    entityarrow.func_70240_a(l);
                    entityarrow1.func_70240_a(l);
                    entityarrow2.func_70240_a(l);
                }

                if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, par1ItemStack) > 0) {
                    entityarrow.func_70015_d(100);
                    entityarrow1.func_70015_d(100);
                    entityarrow2.func_70015_d(100);
                }

                par1ItemStack.func_77972_a(1, par3EntityPlayer);
                par2World.func_72956_a(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (ItemTFTripleBow.field_77697_d.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                if (flag) {
                    entityarrow.field_70251_a = 2;
                } else {
                    par3EntityPlayer.field_71071_by.func_146026_a(Items.field_151032_g);
                }

                entityarrow1.field_70251_a = 2;
                entityarrow2.field_70251_a = 2;
                if (!par2World.field_72995_K) {
                    par2World.func_72838_d(entityarrow);
                    par2World.func_72838_d(entityarrow1);
                    par2World.func_72838_d(entityarrow2);
                }
            }

        }
    }
}
