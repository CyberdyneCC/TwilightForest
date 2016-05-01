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
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;

public class EntityTFSquirrel extends EntityCreature implements IAnimals {

    public EntityTFSquirrel(World par1World) {
        super(par1World);
        this.func_70105_a(0.3F, 0.7F);
        this.field_70138_W = 1.0F;
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(1, new EntityAIPanic(this, 1.3799999952316284D));
        this.field_70714_bg.func_75776_a(2, new EntityAITempt(this, 1.0D, Items.field_151014_N, true));
        this.field_70714_bg.func_75776_a(3, new EntityAIAvoidEntity(this, EntityPlayer.class, 2.0F, 0.800000011920929D, 1.399999976158142D));
        this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 1.25D));
        this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
    }

    protected void func_70069_a(float par1) {}

    public boolean func_70650_aV() {
        return true;
    }

    public float func_70603_bj() {
        return 0.3F;
    }

    public float func_70783_a(int par1, int par2, int par3) {
        Material underMaterial = this.field_70170_p.func_147439_a(par1, par2 - 1, par3).func_149688_o();

        return underMaterial == Material.field_151584_j ? 12.0F : (underMaterial == Material.field_151575_d ? 15.0F : (underMaterial == Material.field_151577_b ? 10.0F : this.field_70170_p.func_72801_o(par1, par2, par3) - 0.5F));
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }
}
