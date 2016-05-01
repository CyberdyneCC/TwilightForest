package twilightforest.entity;

import net.minecraft.entity.SharedMonsterAttributes;
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
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import twilightforest.entity.ai.EntityAITFGiantAttackOnCollide;
import twilightforest.item.TFItems;

public class EntityTFGiantMiner extends EntityMob {

    public EntityTFGiantMiner(World par1World) {
        super(par1World);
        this.func_70105_a(this.field_70130_N * 4.0F, this.field_70131_O * 4.0F);
        this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(4, new EntityAITFGiantAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.func_70062_b(0, new ItemStack(Items.field_151050_s));

        for (int i = 0; i < this.field_82174_bp.length; ++i) {
            this.field_82174_bp[i] = 0.0F;
        }

    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(80.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(10.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected Item func_146068_u() {
        return TFItems.giantPick;
    }

    protected void func_70628_a(boolean par1, int par2) {
        Item item = this.func_146068_u();

        if (item != null && par1) {
            this.func_145779_a(item, 1);
        }

    }

    public void makeNonDespawning() {
        this.func_110163_bv();
    }
}
