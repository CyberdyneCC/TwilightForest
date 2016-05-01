package twilightforest.entity.boss;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import twilightforest.entity.EntityTFMinotaur;
import twilightforest.item.TFItems;

public class EntityTFMinoshroom extends EntityTFMinotaur {

    public EntityTFMinoshroom(World par1World) {
        super(par1World);
        this.func_70105_a(1.49F, 2.9F);
        this.field_70728_aV = 100;
        this.func_70062_b(0, new ItemStack(TFItems.minotaurAxe));
        this.field_82174_bp[0] = 0.0F;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(120.0D);
    }

    protected Item func_146068_u() {
        return TFItems.meefStroganoff;
    }

    protected void func_70628_a(boolean par1, int par2) {
        int numDrops = this.field_70146_Z.nextInt(4) + 2 + this.field_70146_Z.nextInt(1 + par2);

        for (int i = 0; i < numDrops; ++i) {
            this.func_145779_a(TFItems.meefStroganoff, 1);
        }

    }

    protected boolean func_70692_ba() {
        return false;
    }

    protected void func_82160_b(boolean par1, int par2) {
        super.func_82160_b(par1, par2);
        this.func_70099_a(new ItemStack(TFItems.minotaurAxe), 0.0F);
    }
}
