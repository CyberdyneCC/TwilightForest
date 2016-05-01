package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

public abstract class ItemTFBowBase extends ItemBow {

    private IIcon[] iconArray;

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a(this.func_111208_A() + "_standby");
        this.iconArray = new IIcon[ItemTFBowBase.field_94601_a.length];

        for (int i = 0; i < this.iconArray.length; ++i) {
            this.iconArray[i] = par1IconRegister.func_94245_a(this.func_111208_A() + "_" + ItemTFBowBase.field_94601_a[i]);
        }

    }

    @SideOnly(Side.CLIENT)
    public IIcon func_94599_c(int par1) {
        return this.iconArray[par1];
    }

    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (usingItem != null) {
            int j = usingItem.func_77988_m() - useRemaining;

            if (j >= 18) {
                return this.func_94599_c(2);
            }

            if (j > 13) {
                return this.func_94599_c(1);
            }

            if (j > 0) {
                return this.func_94599_c(0);
            }
        }

        return this.getIcon(stack, renderPass);
    }

    public void func_77615_a(ItemStack itemstack, World world, EntityPlayer entityPlayer, int itemInUseCount) {
        int charge = this.func_77626_a(itemstack) - itemInUseCount;
        ArrowLooseEvent event = new ArrowLooseEvent(entityPlayer, itemstack, charge);

        MinecraftForge.EVENT_BUS.post(event);
        if (!event.isCanceled()) {
            charge = event.charge;
            boolean isNoPickup = entityPlayer.field_71075_bZ.field_75098_d || EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, itemstack) > 0;

            if (isNoPickup || entityPlayer.field_71071_by.func_146028_b(Items.field_151032_g)) {
                float velocity = (float) charge / 20.0F;

                velocity = (velocity * velocity + velocity * 2.0F) / 3.0F;
                if ((double) velocity < 0.1D) {
                    return;
                }

                if (velocity > 1.0F) {
                    velocity = 1.0F;
                }

                EntityArrow entityarrow = this.getArrow(world, entityPlayer, velocity * 2.0F);

                if (velocity == 1.0F) {
                    entityarrow.func_70243_d(true);
                }

                int powerLevel = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, itemstack);

                if (powerLevel > 0) {
                    entityarrow.func_70239_b(entityarrow.func_70242_d() + (double) powerLevel * 0.5D + 0.5D);
                }

                int punchLevel = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, itemstack);

                if (punchLevel > 0) {
                    entityarrow.func_70240_a(punchLevel);
                }

                if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, itemstack) > 0) {
                    entityarrow.func_70015_d(100);
                }

                itemstack.func_77972_a(1, entityPlayer);
                world.func_72956_a(entityPlayer, "random.bow", 1.0F, 1.0F / (ItemTFBowBase.field_77697_d.nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);
                if (isNoPickup) {
                    entityarrow.field_70251_a = 2;
                } else {
                    entityPlayer.field_71071_by.func_146026_a(Items.field_151032_g);
                }

                if (!world.field_72995_K) {
                    world.func_72838_d(entityarrow);
                }
            }

        }
    }

    protected EntityArrow getArrow(World world, EntityPlayer entityPlayer, float velocity) {
        return new EntityArrow(world, entityPlayer, velocity);
    }
}
