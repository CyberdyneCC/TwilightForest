package twilightforest.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityTFLoyalZombie extends EntityTameable {

    public EntityTFLoyalZombie(World par1World) {
        super(par1World);
        this.func_70105_a(0.6F, 1.8F);
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(3, new EntityAILeapAtTarget(this, 0.4F));
        this.field_70714_bg.func_75776_a(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.field_70714_bg.func_75776_a(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(9, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIOwnerHurtByTarget(this));
        this.field_70715_bh.func_75776_a(2, new EntityAIOwnerHurtTarget(this));
        this.field_70715_bh.func_75776_a(3, new EntityAIHurtByTarget(this, true));
        this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true));
    }

    public EntityAnimal createChild(EntityAgeable entityanimal) {
        return null;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(40.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
    }

    public boolean func_70652_k(Entity par1Entity) {
        byte attackpower = 7;
        boolean success = par1Entity.func_70097_a(DamageSource.func_76358_a(this), (float) attackpower);

        if (success) {
            par1Entity.field_70181_x += 0.2000000059604645D;
        }

        return success;
    }

    public void func_70636_d() {
        if (!this.field_70170_p.field_72995_K && this.func_70660_b(Potion.field_76420_g) == null) {
            this.func_70015_d(100);
        }

        super.func_70636_d();
    }

    protected boolean func_70692_ba() {
        return !this.func_70909_n();
    }

    public int func_70658_aO() {
        return 3;
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected String func_70639_aQ() {
        return "mob.zombie.say";
    }

    protected String func_70621_aR() {
        return "mob.zombie.hurt";
    }

    protected String func_70673_aS() {
        return "mob.zombie.death";
    }

    protected void func_145780_a(int par1, int par2, int par3, Block par4) {
        this.field_70170_p.func_72956_a(this, "mob.zombie.step", 0.15F, 1.0F);
    }

    protected Item func_146068_u() {
        return Item.func_150899_d(0);
    }

    public EnumCreatureAttribute func_70668_bt() {
        return EnumCreatureAttribute.UNDEAD;
    }
}
