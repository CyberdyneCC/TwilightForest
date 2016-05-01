package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemTFScepterLifeDrain extends ItemTF {

    protected ItemTFScepterLifeDrain() {
        this.field_77777_bU = 1;
        this.func_77656_e(99);
        this.func_77637_a(TFItems.creativeTab);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World worldObj, EntityPlayer player) {
        if (par1ItemStack.func_77960_j() < this.func_77612_l()) {
            player.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        } else {
            player.func_71034_by();
        }

        return par1ItemStack;
    }

    public static void animateTargetShatter(World worldObj, EntityLivingBase target) {
        for (int i = 0; i < 50; ++i) {
            double gaussX = ItemTFScepterLifeDrain.field_77697_d.nextGaussian() * 0.02D;
            double gaussY = ItemTFScepterLifeDrain.field_77697_d.nextGaussian() * 0.02D;
            double gaussZ = ItemTFScepterLifeDrain.field_77697_d.nextGaussian() * 0.02D;
            double gaussFactor = 10.0D;
            Item popItem = getTargetDropItemId(target) != null ? getTargetDropItemId(target) : Items.field_151078_bh;

            worldObj.func_72869_a("iconcrack_" + Item.func_150891_b(popItem), target.field_70165_t + (double) (ItemTFScepterLifeDrain.field_77697_d.nextFloat() * target.field_70130_N * 2.0F) - (double) target.field_70130_N - gaussX * gaussFactor, target.field_70163_u + (double) (ItemTFScepterLifeDrain.field_77697_d.nextFloat() * target.field_70131_O) - gaussY * gaussFactor, target.field_70161_v + (double) (ItemTFScepterLifeDrain.field_77697_d.nextFloat() * target.field_70130_N * 2.0F) - (double) target.field_70130_N - gaussZ * gaussFactor, gaussX, gaussY, gaussZ);
        }

    }

    public static Item getTargetDropItemId(EntityLivingBase target) {
        return Items.field_151078_bh;
    }

    private Entity getPlayerLookTarget(World worldObj, EntityPlayer player) {
        Entity pointedEntity = null;
        double range = 20.0D;
        Vec3 srcVec = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + (double) player.func_70047_e(), player.field_70161_v);
        Vec3 lookVec = player.func_70676_i(1.0F);
        Vec3 destVec = srcVec.func_72441_c(lookVec.field_72450_a * range, lookVec.field_72448_b * range, lookVec.field_72449_c * range);
        float f = 1.0F;
        List possibleList = worldObj.func_72839_b(player, player.field_70121_D.func_72321_a(lookVec.field_72450_a * range, lookVec.field_72448_b * range, lookVec.field_72449_c * range).func_72314_b((double) f, (double) f, (double) f));
        double hitDist = 0.0D;
        Iterator iterator = possibleList.iterator();

        while (iterator.hasNext()) {
            Entity possibleEntity = (Entity) iterator.next();

            if (possibleEntity.func_70067_L()) {
                float borderSize = possibleEntity.func_70111_Y();
                AxisAlignedBB collisionBB = possibleEntity.field_70121_D.func_72314_b((double) borderSize, (double) borderSize, (double) borderSize);
                MovingObjectPosition interceptPos = collisionBB.func_72327_a(srcVec, destVec);

                if (collisionBB.func_72318_a(srcVec)) {
                    if (0.0D < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = 0.0D;
                    }
                } else if (interceptPos != null) {
                    double possibleDist = srcVec.func_72438_d(interceptPos.field_72307_f);

                    if (possibleDist < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = possibleDist;
                    }
                }
            }
        }

        return pointedEntity;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        World worldObj = player.field_70170_p;

        if (stack.func_77960_j() >= this.func_77612_l()) {
            player.func_71034_by();
        } else {
            if (count % 5 == 0) {
                Entity pointedEntity = this.getPlayerLookTarget(worldObj, player);

                if (pointedEntity != null && pointedEntity instanceof EntityLivingBase) {
                    EntityLivingBase target = (EntityLivingBase) pointedEntity;

                    if (target.func_70660_b(Potion.field_76421_d) == null && target.func_110143_aJ() >= 1.0F) {
                        this.makeRedMagicTrail(worldObj, player.field_70165_t, player.field_70163_u + (double) player.func_70047_e(), player.field_70161_v, target.field_70165_t, target.field_70163_u + (double) target.func_70047_e(), target.field_70161_v);
                        worldObj.func_72956_a(player, "fire.ignite", 1.0F, (worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 0.2F + 1.0F);
                        if (!worldObj.field_72995_K) {
                            target.func_70097_a(DamageSource.func_76354_b(player, player), 1.0F);
                            if (this.getMaxHealth(target) <= this.getMaxHealth(player)) {
                                target.field_70159_w = 0.0D;
                                target.field_70181_x = 0.2D;
                                target.field_70179_y = 0.0D;
                            }

                            target.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 20, 2));
                        }
                    } else if (target.func_110143_aJ() <= 3.0F) {
                        this.makeRedMagicTrail(worldObj, player.field_70165_t, player.field_70163_u + (double) player.func_70047_e(), player.field_70161_v, target.field_70165_t, target.field_70163_u + (double) target.func_70047_e(), target.field_70161_v);
                        if (target instanceof EntityLiving) {
                            ((EntityLiving) target).func_70656_aK();
                        }

                        worldObj.func_72956_a(target, "game.player.hurt.fall.big", 1.0F, ((ItemTFScepterLifeDrain.field_77697_d.nextFloat() - ItemTFScepterLifeDrain.field_77697_d.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                        animateTargetShatter(worldObj, target);
                        if (!worldObj.field_72995_K) {
                            target.func_70106_y();
                            target.func_70645_a(DamageSource.func_76354_b(player, player));
                        }

                        player.func_71034_by();
                    } else if (!worldObj.field_72995_K) {
                        target.func_70097_a(DamageSource.func_76354_b(player, player), 3.0F);
                        if (this.getMaxHealth(target) <= this.getMaxHealth(player)) {
                            target.field_70159_w = 0.0D;
                            target.field_70181_x = 0.2D;
                            target.field_70179_y = 0.0D;
                        }

                        target.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 20, 2));
                        if (count % 10 == 0) {
                            player.func_70691_i(1.0F);
                            player.func_71024_bL().func_75122_a(1, 0.1F);
                        }
                    }

                    if (!worldObj.field_72995_K) {
                        stack.func_77972_a(1, player);
                    }
                }
            }

        }
    }

    private float getMaxHealth(EntityLivingBase target) {
        return (float) target.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b();
    }

    protected void makeRedMagicTrail(World worldObj, double srcX, double srcY, double srcZ, double destX, double destY, double destZ) {
        byte particles = 32;

        for (int i = 0; i < particles; ++i) {
            double trailFactor = (double) i / ((double) particles - 1.0D);
            float f = 1.0F;
            float f1 = 0.5F;
            float f2 = 0.5F;
            double tx = srcX + (destX - srcX) * trailFactor + worldObj.field_73012_v.nextGaussian() * 0.005D;
            double ty = srcY + (destY - srcY) * trailFactor + worldObj.field_73012_v.nextGaussian() * 0.005D;
            double tz = srcZ + (destZ - srcZ) * trailFactor + worldObj.field_73012_v.nextGaussian() * 0.005D;

            worldObj.func_72869_a("mobSpell", tx, ty, tz, (double) f, (double) f1, (double) f2);
        }

    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.func_77624_a(par1ItemStack, par2EntityPlayer, par3List, par4);
        par3List.add(par1ItemStack.func_77958_k() - par1ItemStack.func_77960_j() + " charges left");
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }
}
