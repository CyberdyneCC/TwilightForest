package twilightforest.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import twilightforest.TFAchievementPage;
import twilightforest.entity.ai.EntityAITFBirdFly;

public class EntityTFTinyBird extends EntityTFBird {

    private static final int DATA_BIRDTYPE = 16;
    private static final int DATA_BIRDFLAGS = 17;
    private ChunkCoordinates currentFlightTarget;
    private int currentFlightTime;

    public EntityTFTinyBird(World par1World) {
        super(par1World);
        this.func_70105_a(0.5F, 0.9F);
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(0, new EntityAITFBirdFly(this));
        this.field_70714_bg.func_75776_a(1, new EntityAITempt(this, 1.0D, Items.field_151014_N, true));
        this.field_70714_bg.func_75776_a(2, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.field_70714_bg.func_75776_a(4, new EntityAILookIdle(this));
        this.setBirdType(this.field_70146_Z.nextInt(4));
        this.setIsBirdLanded(true);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, Byte.valueOf((byte) 0));
        this.field_70180_af.func_75682_a(17, Byte.valueOf((byte) 0));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.20000001192092895D);
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("BirdType", this.getBirdType());
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.setBirdType(par1NBTTagCompound.func_74762_e("BirdType"));
    }

    public int getBirdType() {
        return this.field_70180_af.func_75683_a(16);
    }

    public void setBirdType(int par1) {
        this.field_70180_af.func_75692_b(16, Byte.valueOf((byte) par1));
    }

    protected String func_70639_aQ() {
        return "TwilightForest:mob.tinybird.chirp";
    }

    protected String func_70621_aR() {
        return "TwilightForest:mob.tinybird.hurt";
    }

    protected String func_70673_aS() {
        return "TwilightForest:mob.tinybird.hurt";
    }

    public float func_70603_bj() {
        return 0.3F;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public float func_70783_a(int par1, int par2, int par3) {
        Material underMaterial = this.field_70170_p.func_147439_a(par1, par2 - 1, par3).func_149688_o();

        return underMaterial == Material.field_151584_j ? 200.0F : (underMaterial == Material.field_151575_d ? 15.0F : (underMaterial == Material.field_151577_b ? 9.0F : this.field_70170_p.func_72801_o(par1, par2, par3) - 0.5F));
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.isBirdLanded()) {
            this.field_70181_x *= 0.6000000238418579D;
        }

    }

    protected void func_70619_bc() {
        super.func_70619_bc();
        if (this.isBirdLanded()) {
            this.currentFlightTime = 0;
            if (this.field_70146_Z.nextInt(200) == 0 && !this.isLandableBlock(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u - 1.0D), MathHelper.func_76128_c(this.field_70161_v))) {
                this.setIsBirdLanded(false);
                this.field_70170_p.func_72889_a((EntityPlayer) null, 1015, (int) this.field_70165_t, (int) this.field_70163_u, (int) this.field_70161_v, 0);
                this.field_70181_x = 0.4D;
            } else if (this.isSpooked()) {
                this.setIsBirdLanded(false);
                this.field_70181_x = 0.4D;
                this.field_70170_p.func_72889_a((EntityPlayer) null, 1015, (int) this.field_70165_t, (int) this.field_70163_u, (int) this.field_70161_v, 0);
            }
        } else {
            ++this.currentFlightTime;
            if (this.currentFlightTarget != null && (!this.field_70170_p.func_147437_c(this.currentFlightTarget.field_71574_a, this.currentFlightTarget.field_71572_b, this.currentFlightTarget.field_71573_c) || this.currentFlightTarget.field_71572_b < 1)) {
                this.currentFlightTarget = null;
            }

            if (this.currentFlightTarget == null || this.field_70146_Z.nextInt(30) == 0 || this.currentFlightTarget.func_71569_e((int) this.field_70165_t, (int) this.field_70163_u, (int) this.field_70161_v) < 4.0F) {
                int d0 = this.currentFlightTime < 100 ? 2 : 4;

                this.currentFlightTarget = new ChunkCoordinates((int) this.field_70165_t + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7), (int) this.field_70163_u + this.field_70146_Z.nextInt(6) - d0, (int) this.field_70161_v + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7));
            }

            double d0 = (double) this.currentFlightTarget.field_71574_a + 0.5D - this.field_70165_t;
            double d1 = (double) this.currentFlightTarget.field_71572_b + 0.1D - this.field_70163_u;
            double d2 = (double) this.currentFlightTarget.field_71573_c + 0.5D - this.field_70161_v;

            this.field_70159_w += (Math.signum(d0) * 0.5D - this.field_70159_w) * 0.10000000149011612D;
            this.field_70181_x += (Math.signum(d1) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
            this.field_70179_y += (Math.signum(d2) * 0.5D - this.field_70179_y) * 0.10000000149011612D;
            float f = (float) (Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / 3.141592653589793D) - 90.0F;
            float f1 = MathHelper.func_76142_g(f - this.field_70177_z);

            this.field_70701_bs = 0.5F;
            this.field_70177_z += f1;
            if (this.field_70146_Z.nextInt(10) == 0 && this.isLandableBlock(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u - 1.0D), MathHelper.func_76128_c(this.field_70161_v))) {
                this.setIsBirdLanded(true);
                this.field_70181_x = 0.0D;
            }
        }

    }

    public boolean isSpooked() {
        EntityPlayer closestPlayer = this.field_70170_p.func_72890_a(this, 4.0D);

        return this.field_70737_aN > 0 || closestPlayer != null && (closestPlayer.field_71071_by.func_70448_g() == null || closestPlayer.field_71071_by.func_70448_g().func_77973_b() != Items.field_151014_N);
    }

    public boolean isLandableBlock(int x, int y, int z) {
        Block block = this.field_70170_p.func_147439_a(x, y, z);

        return block == Blocks.field_150350_a ? false : block.isLeaves(this.field_70170_p, x, y, z) || block.isSideSolid(this.field_70170_p, x, y, z, ForgeDirection.UP);
    }

    public boolean isBirdLanded() {
        return (this.field_70180_af.func_75683_a(17) & 1) != 0;
    }

    public void setIsBirdLanded(boolean par1) {
        byte b0 = this.field_70180_af.func_75683_a(17);

        if (par1) {
            this.field_70180_af.func_75692_b(17, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.field_70180_af.func_75692_b(17, Byte.valueOf((byte) (b0 & -2)));
        }

    }

    public boolean func_70104_M() {
        return false;
    }

    protected void func_82167_n(Entity par1Entity) {}

    protected void func_85033_bc() {}
}
