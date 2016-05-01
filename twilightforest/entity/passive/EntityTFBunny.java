package twilightforest.entity.passive;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;

public class EntityTFBunny extends EntityCreature implements IAnimals {

    public EntityTFBunny(World par1World) {
        super(par1World);
        this.func_70105_a(0.3F, 0.7F);
        this.field_70138_W = 1.0F;
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(1, new EntityAIPanic(this, 2.0D));
        this.field_70714_bg.func_75776_a(2, new EntityAITempt(this, 1.0D, Items.field_151014_N, true));
        this.field_70714_bg.func_75776_a(3, new EntityAIAvoidEntity(this, EntityPlayer.class, 2.0F, 0.800000011920929D, 1.3300000429153442D));
        this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 0.800000011920929D));
        this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
        this.setBunnyType(this.field_70146_Z.nextInt(4));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(3.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, Byte.valueOf((byte) 0));
    }

    public boolean func_70650_aV() {
        return true;
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("BunnyType", this.getBunnyType());
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.setBunnyType(par1NBTTagCompound.func_74762_e("BunnyType"));
    }

    public int getBunnyType() {
        return this.field_70180_af.func_75683_a(16);
    }

    public void setBunnyType(int par1) {
        this.field_70180_af.func_75692_b(16, Byte.valueOf((byte) par1));
    }

    public float func_70603_bj() {
        return 0.3F;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public float func_70783_a(int par1, int par2, int par3) {
        Material underMaterial = this.field_70170_p.func_147439_a(par1, par2 - 1, par3).func_149688_o();

        return underMaterial == Material.field_151584_j ? -1.0F : (underMaterial == Material.field_151575_d ? -1.0F : (underMaterial == Material.field_151577_b ? 10.0F : this.field_70170_p.func_72801_o(par1, par2, par3) - 0.5F));
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }
}
