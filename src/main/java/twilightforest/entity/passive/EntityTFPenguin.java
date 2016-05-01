package twilightforest.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;

public class EntityTFPenguin extends EntityTFBird {

    public EntityTFPenguin(World world) {
        super(world);
        this.func_70105_a(0.5F, 0.9F);
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(1, new EntityAIPanic(this, 1.75D));
        this.field_70714_bg.func_75776_a(2, new EntityAIMate(this, 1.0D));
        this.field_70714_bg.func_75776_a(3, new EntityAITempt(this, 0.75D, Items.field_151115_aP, false));
        this.field_70714_bg.func_75776_a(4, new EntityAIFollowParent(this, 1.149999976158142D));
        this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest2(this, EntityTFPenguin.class, 5.0F, 0.02F));
        this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
    }

    protected String func_70639_aQ() {
        return null;
    }

    public EntityAnimal createChild(EntityAgeable entityanimal) {
        return new EntityTFPenguin(this.field_70170_p);
    }

    public boolean func_70877_b(ItemStack par1ItemStack) {
        return par1ItemStack != null && par1ItemStack.func_77973_b() == Items.field_151115_aP;
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2D);
    }
}
