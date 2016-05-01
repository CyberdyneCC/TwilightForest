package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import twilightforest.entity.EntityTFHedgeSpider;
import twilightforest.entity.EntityTFHostileWolf;
import twilightforest.entity.EntityTFMinotaur;
import twilightforest.entity.EntityTFRedcap;
import twilightforest.entity.EntityTFSkeletonDruid;
import twilightforest.entity.EntityTFSwarmSpider;
import twilightforest.entity.EntityTFWraith;
import twilightforest.entity.passive.EntityTFBighorn;
import twilightforest.entity.passive.EntityTFBoar;
import twilightforest.entity.passive.EntityTFDeer;
import twilightforest.entity.passive.EntityTFPenguin;
import twilightforest.entity.passive.EntityTFRaven;

public class ItemTFTransformPowder extends ItemTF {

    HashMap transformMap;

    protected ItemTFTransformPowder() {
        this.field_77777_bU = 64;
        this.func_77637_a(TFItems.creativeTab);
        this.transformMap = new HashMap();
        this.addTwoWayTransformation(EntityTFMinotaur.class, EntityPigZombie.class);
        this.addTwoWayTransformation(EntityTFDeer.class, EntityCow.class);
        this.addTwoWayTransformation(EntityTFBighorn.class, EntitySheep.class);
        this.addTwoWayTransformation(EntityTFBoar.class, EntityPig.class);
        this.addTwoWayTransformation(EntityTFRaven.class, EntityBat.class);
        this.addTwoWayTransformation(EntityTFHostileWolf.class, EntityWolf.class);
        this.addTwoWayTransformation(EntityTFPenguin.class, EntityChicken.class);
        this.addTwoWayTransformation(EntityTFHedgeSpider.class, EntitySpider.class);
        this.addTwoWayTransformation(EntityTFSwarmSpider.class, EntityCaveSpider.class);
        this.addTwoWayTransformation(EntityTFWraith.class, EntityBlaze.class);
        this.addTwoWayTransformation(EntityTFRedcap.class, EntityVillager.class);
        this.addTwoWayTransformation(EntityTFSkeletonDruid.class, EntityWitch.class);
    }

    public void addTwoWayTransformation(Class class1, Class class2) {
        this.transformMap.put(class1, class2);
        this.transformMap.put(class2, class1);
    }

    public boolean func_111207_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, EntityLivingBase target) {
        Class transformClass = this.getMonsterTransform(target.getClass());

        if (transformClass != null) {
            if (target.field_70170_p.field_72995_K) {
                if (target instanceof EntityLiving) {
                    ((EntityLiving) target).func_70656_aK();
                    ((EntityLiving) target).func_70656_aK();
                }

                target.field_70170_p.func_72980_b(target.field_70165_t + 0.5D, target.field_70163_u + 0.5D, target.field_70161_v + 0.5D, "mob.zombie.remedy", 1.0F + ItemTFTransformPowder.field_77697_d.nextFloat(), ItemTFTransformPowder.field_77697_d.nextFloat() * 0.7F + 0.3F, false);
            } else {
                EntityLivingBase newMonster = null;

                try {
                    newMonster = (EntityLivingBase) transformClass.getConstructor(new Class[] { World.class}).newInstance(new Object[] { target.field_70170_p});
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                if (newMonster == null) {
                    return false;
                }

                newMonster.func_70080_a(target.field_70165_t, target.field_70163_u, target.field_70161_v, target.field_70177_z, target.field_70125_A);
                target.field_70170_p.func_72838_d(newMonster);
                target.func_70106_y();
            }

            --par1ItemStack.field_77994_a;
            return true;
        } else {
            return false;
        }
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World world, EntityPlayer player) {
        if (world.field_72995_K) {
            AxisAlignedBB fanBox = this.getEffectAABB(world, player);

            for (int i = 0; i < 30; ++i) {
                world.func_72869_a("magicCrit", fanBox.field_72340_a + (double) world.field_73012_v.nextFloat() * (fanBox.field_72336_d - fanBox.field_72340_a), fanBox.field_72338_b + (double) world.field_73012_v.nextFloat() * (fanBox.field_72337_e - fanBox.field_72338_b), fanBox.field_72339_c + (double) world.field_73012_v.nextFloat() * (fanBox.field_72334_f - fanBox.field_72339_c), 0.0D, 0.0D, 0.0D);
            }
        }

        return par1ItemStack;
    }

    private AxisAlignedBB getEffectAABB(World world, EntityPlayer player) {
        double range = 2.0D;
        double radius = 1.0D;
        Vec3 srcVec = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + (double) player.func_70047_e(), player.field_70161_v);
        Vec3 lookVec = player.func_70040_Z();
        Vec3 destVec = srcVec.func_72441_c(lookVec.field_72450_a * range, lookVec.field_72448_b * range, lookVec.field_72449_c * range);

        return AxisAlignedBB.func_72330_a(destVec.field_72450_a - radius, destVec.field_72448_b - radius, destVec.field_72449_c - radius, destVec.field_72450_a + radius, destVec.field_72448_b + radius, destVec.field_72449_c + radius);
    }

    public Class getMonsterTransform(Class originalMonster) {
        return (Class) this.transformMap.get(originalMonster);
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }
}
