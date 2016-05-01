package twilightforest.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.item.TFItems;

public class EntityTFDeer extends EntityCow {

    public EntityTFDeer(World world) {
        super(world);
        this.func_70105_a(0.7F, 2.3F);
    }

    public EntityTFDeer(World world, double x, double y, double z) {
        this(world);
        this.func_70107_b(x, y, z);
    }

    protected String func_70639_aQ() {
        return null;
    }

    protected void func_145780_a(int par1, int par2, int par3, Block par4) {}

    public boolean func_70085_c(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.field_71071_by.func_70448_g();

        return itemstack != null && itemstack.func_77973_b() == Items.field_151133_ar ? false : super.func_70085_c(entityplayer);
    }

    protected void func_70628_a(boolean par1, int par2) {
        int i = this.field_70146_Z.nextInt(3) + this.field_70146_Z.nextInt(1 + par2);

        int j;

        for (j = 0; j < i; ++j) {
            this.func_145779_a(Items.field_151116_aA, 1);
        }

        i = this.field_70146_Z.nextInt(3) + 1 + this.field_70146_Z.nextInt(1 + par2);

        for (j = 0; j < i; ++j) {
            if (this.func_70027_ad()) {
                this.func_145779_a(TFItems.venisonCooked, 1);
            } else {
                this.func_145779_a(TFItems.venisonRaw, 1);
            }
        }

    }

    public EntityCow func_90011_a(EntityAgeable entityanimal) {
        return new EntityTFDeer(this.field_70170_p);
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }
}
