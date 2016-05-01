package twilightforest.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;
import twilightforest.entity.ai.EntityAITFRedcapPlantTNT;
import twilightforest.item.TFItems;

public class EntityTFRedcapSapper extends EntityTFRedcap {

    public EntityTFRedcapSapper(World world) {
        super(world);
        this.field_70714_bg.func_75776_a(4, new EntityAITFRedcapPlantTNT(this));
        this.setTntLeft(3);
        this.func_70062_b(1, new ItemStack(TFItems.ironwoodBoots));
        this.func_70062_b(0, new ItemStack(TFItems.ironwoodPick, 1));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
    }

    public int func_70658_aO() {
        int i = super.func_70658_aO() + 2;

        if (i > 20) {
            i = 20;
        }

        return i;
    }

    public ItemStack getPick() {
        return new ItemStack(TFItems.ironwoodPick);
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
            int chunkX = MathHelper.func_76128_c(this.field_70165_t) >> 4;
            int chunkZ = MathHelper.func_76128_c(this.field_70161_v) >> 4;

            if (TFFeature.getNearestFeature(chunkX, chunkZ, this.field_70170_p) == TFFeature.hill2) {
                ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHill2);
            }
        }

    }
}
