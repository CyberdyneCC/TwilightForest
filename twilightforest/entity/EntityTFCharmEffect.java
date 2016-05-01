package twilightforest.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import twilightforest.item.TFItems;

public class EntityTFCharmEffect extends Entity {

    private static final int DATA_OWNER = 17;
    private static final int DATA_ITEMID = 16;
    private static final double DISTANCE = 1.75D;
    private EntityLivingBase orbiting;
    private double newPosX;
    private double newPosY;
    private double newPosZ;
    private double newRotationYaw;
    private double newRotationPitch;
    private int newPosRotationIncrements;
    public float offset;

    public EntityTFCharmEffect(World par1World) {
        super(par1World);
        this.func_70105_a(0.25F, 0.25F);
        this.setItemID(TFItems.charmOfLife1);
    }

    public EntityTFCharmEffect(World par1World, EntityLivingBase par2EntityLiving, Item item) {
        super(par1World);
        this.func_70105_a(0.25F, 0.25F);
        this.orbiting = par2EntityLiving;
        this.setItemID(item);
        Vec3 look = Vec3.func_72443_a(1.75D, 0.0D, 0.0D);

        this.func_70012_b(par2EntityLiving.field_70165_t, par2EntityLiving.field_70163_u + (double) par2EntityLiving.func_70047_e(), par2EntityLiving.field_70161_v, par2EntityLiving.field_70177_z, par2EntityLiving.field_70125_A);
        this.field_70165_t += look.field_72450_a * 1.75D;
        this.field_70161_v += look.field_72449_c * 1.75D;
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70129_M = 0.0F;
    }

    public void func_70071_h_() {
        this.field_70142_S = this.field_70165_t;
        this.field_70137_T = this.field_70163_u;
        this.field_70136_U = this.field_70161_v;
        super.func_70071_h_();
        double dx;
        double dy;
        double dz;

        if (this.newPosRotationIncrements > 0) {
            double rotation = this.field_70165_t + (this.newPosX - this.field_70165_t) / (double) this.newPosRotationIncrements;

            dx = this.field_70163_u + (this.newPosY - this.field_70163_u) / (double) this.newPosRotationIncrements;
            dy = this.field_70161_v + (this.newPosZ - this.field_70161_v) / (double) this.newPosRotationIncrements;
            dz = MathHelper.func_76138_g(this.newRotationYaw - (double) this.field_70177_z);
            this.field_70177_z = (float) ((double) this.field_70177_z + dz / (double) this.newPosRotationIncrements);
            this.field_70125_A = (float) ((double) this.field_70125_A + (this.newRotationPitch - (double) this.field_70125_A) / (double) this.newPosRotationIncrements);
            --this.newPosRotationIncrements;
            this.func_70107_b(rotation, dx, dy);
            this.func_70101_b(this.field_70177_z, this.field_70125_A);
        }

        float f = (float) this.field_70173_aa / 5.0F + this.offset;

        if (this.orbiting == null) {
            this.orbiting = this.getOwner();
        }

        if (this.orbiting != null && !this.field_70170_p.field_72995_K) {
            this.func_70012_b(this.orbiting.field_70165_t, this.orbiting.field_70163_u + (double) this.orbiting.func_70047_e(), this.orbiting.field_70161_v, this.orbiting.field_70177_z, this.orbiting.field_70125_A);
            Vec3 i = Vec3.func_72443_a(1.75D, 0.0D, 0.0D);

            i.func_72442_b(f);
            this.field_70165_t += i.field_72450_a;
            this.field_70161_v += i.field_72449_c;
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        }

        if (this.getItemID() > 0) {
            for (int i = 0; i < 3; ++i) {
                dx = this.field_70165_t + 0.5D * (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble());
                dy = this.field_70163_u + 0.5D * (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble());
                dz = this.field_70161_v + 0.5D * (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble());
                this.field_70170_p.func_72869_a("iconcrack_" + this.getItemID(), dx, dy, dz, 0.0D, 0.2D, 0.0D);
            }
        }

        if (this.field_70173_aa > 200 || this.orbiting == null || this.orbiting.field_70128_L) {
            this.func_70106_y();
        }

    }

    public void func_70056_a(double par1, double par3, double par5, float par7, float par8, int par9) {
        this.field_70129_M = 0.0F;
        this.newPosX = par1;
        this.newPosY = par3;
        this.newPosZ = par5;
        this.newRotationYaw = (double) par7;
        this.newRotationPitch = (double) par8;
        this.newPosRotationIncrements = par9;
    }

    protected void func_70088_a() {
        this.field_70180_af.func_75682_a(16, Integer.valueOf(0));
        this.field_70180_af.func_75682_a(17, "");
    }

    public String getOwnerName() {
        return this.field_70180_af.func_75681_e(17);
    }

    public void setOwner(String par1Str) {
        this.field_70180_af.func_75692_b(17, par1Str);
    }

    public EntityLivingBase getOwner() {
        return this.field_70170_p.func_72924_a(this.getOwnerName());
    }

    public int getItemID() {
        return this.field_70180_af.func_75679_c(16);
    }

    public void setItemID(Item charmOfLife1) {}

    protected void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.func_74778_a("Owner", this.getOwnerName());
        par1NBTTagCompound.func_74777_a("ItemID", (short) this.getItemID());
    }

    protected void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        this.setOwner(par1NBTTagCompound.func_74779_i("Owner"));
    }
}
