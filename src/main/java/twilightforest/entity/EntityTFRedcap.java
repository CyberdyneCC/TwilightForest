package twilightforest.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;
import twilightforest.entity.ai.EntityAITFRedcapLightTNT;
import twilightforest.entity.ai.EntityAITFRedcapShy;

public class EntityTFRedcap extends EntityMob {

    public static ItemStack heldPick = new ItemStack(Items.field_151035_b, 1);
    public static ItemStack heldTNT = new ItemStack(Blocks.field_150335_W, 1);
    public static ItemStack heldFlint = new ItemStack(Items.field_151033_d, 1);
    private boolean shy;
    private int tntLeft;

    public EntityTFRedcap(World world) {
        super(world);
        this.tntLeft = 0;
        this.func_70105_a(0.9F, 1.4F);
        this.shy = true;
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(1, new EntityAIAvoidEntity(this, EntityTNTPrimed.class, 2.0F, 1.0D, 2.0D));
        this.field_70714_bg.func_75776_a(2, new EntityAITFRedcapShy(this, 1.0F));
        this.field_70714_bg.func_75776_a(3, new EntityAITFRedcapLightTNT(this, 1.0F));
        this.field_70714_bg.func_75776_a(5, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(7, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.func_70062_b(0, EntityTFRedcap.heldPick);
        this.func_70062_b(1, new ItemStack(Items.field_151167_ab));
        this.field_82174_bp[0] = 0.2F;
        this.field_82174_bp[1] = 0.2F;
    }

    public EntityTFRedcap(World world, double x, double y, double z) {
        this(world);
        this.func_70107_b(x, y, z);
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.28D);
    }

    protected String func_70639_aQ() {
        return "TwilightForest:mob.redcap.redcap";
    }

    protected String func_70621_aR() {
        return "TwilightForest:mob.redcap.hurt";
    }

    protected String func_70673_aS() {
        return "TwilightForest:mob.redcap.die";
    }

    protected Item func_146068_u() {
        return Items.field_151044_h;
    }

    public boolean isShy() {
        return this.shy && this.field_70718_bc <= 0;
    }

    public int getTntLeft() {
        return this.tntLeft;
    }

    public void setTntLeft(int tntLeft) {
        this.tntLeft = tntLeft;
    }

    public ItemStack getPick() {
        return EntityTFRedcap.heldPick;
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("TNTLeft", this.getTntLeft());
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.setTntLeft(par1NBTTagCompound.func_74762_e("TNTLeft"));
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
            int chunkX = MathHelper.func_76128_c(this.field_70165_t) >> 4;
            int chunkZ = MathHelper.func_76128_c(this.field_70161_v) >> 4;

            if (TFFeature.getNearestFeature(chunkX, chunkZ, this.field_70170_p) == TFFeature.hill1) {
                ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHill1);
            }
        }

    }
}
