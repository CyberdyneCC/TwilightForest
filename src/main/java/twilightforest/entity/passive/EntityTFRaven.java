package twilightforest.entity.passive;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import twilightforest.entity.ai.EntityTFRavenLookHelper;
import twilightforest.item.TFItems;

public class EntityTFRaven extends EntityTFTinyBird {

    EntityTFRavenLookHelper ravenLook = new EntityTFRavenLookHelper(this);

    public EntityTFRaven(World par1World) {
        super(par1World);
        this.func_70105_a(0.3F, 0.7F);
        this.field_70138_W = 1.0F;
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(1, new EntityAIPanic(this, 1.5D));
        this.field_70714_bg.func_75776_a(2, new EntityAITempt(this, 0.8500000238418579D, Items.field_151014_N, true));
        this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.field_70714_bg.func_75776_a(7, new EntityAILookIdle(this));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.20000001192092895D);
    }

    protected void func_70619_bc() {
        super.func_70619_bc();
        this.ravenLook.func_75649_a();
    }

    public EntityLookHelper func_70671_ap() {
        return this.ravenLook;
    }

    protected String func_70639_aQ() {
        return "TwilightForest:mob.raven.caw";
    }

    protected String func_70621_aR() {
        return "TwilightForest:mob.raven.squawk";
    }

    protected String func_70673_aS() {
        return "TwilightForest:mob.raven.squawk";
    }

    protected Item func_146068_u() {
        return TFItems.feather;
    }

    public float func_70603_bj() {
        return 0.3F;
    }

    public boolean isSpooked() {
        return this.field_70737_aN > 0;
    }
}
