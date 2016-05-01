package twilightforest.entity.boss;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityTFIceCrystal extends EntityMob {

    private int crystalAge;
    public int maxCrystalAge;

    public EntityTFIceCrystal(World par1World) {
        super(par1World);
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.field_70714_bg.func_75776_a(2, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(3, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.func_70105_a(0.6F, 1.8F);
        this.maxCrystalAge = -1;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23000000417232513D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected Item func_146068_u() {
        return Items.field_151126_ay;
    }

    public int func_70641_bl() {
        return 8;
    }

    protected String func_70639_aQ() {
        return "TwilightForest:mob.ice.noise";
    }

    protected String func_70621_aR() {
        return "TwilightForest:mob.ice.hurt";
    }

    protected String func_70673_aS() {
        return "TwilightForest:mob.ice.death";
    }

    public void setToDieIn30Seconds() {
        this.maxCrystalAge = 600;
    }

    public void func_70636_d() {
        super.func_70636_d();
        ++this.crystalAge;
        if (this.maxCrystalAge > 0 && this.crystalAge >= this.maxCrystalAge && !this.field_70170_p.field_72995_K) {
            this.func_70106_y();
        }

    }
}
