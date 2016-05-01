package twilightforest.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityTFTowerBroodling extends EntityTFSwarmSpider {

    public EntityTFTowerBroodling(World world) {
        this(world, true);
    }

    public EntityTFTowerBroodling(World world, boolean spawnMore) {
        super(world, spawnMore);
        this.field_70728_aV = 3;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(7.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
    }

    protected boolean spawnAnother() {
        EntityTFTowerBroodling another = new EntityTFTowerBroodling(this.field_70170_p, false);
        double sx = this.field_70165_t + (this.field_70146_Z.nextBoolean() ? 0.9D : -0.9D);
        double sy = this.field_70163_u;
        double sz = this.field_70161_v + (this.field_70146_Z.nextBoolean() ? 0.9D : -0.9D);

        another.func_70012_b(sx, sy, sz, this.field_70146_Z.nextFloat() * 360.0F, 0.0F);
        if (!another.func_70601_bi()) {
            another.func_70106_y();
            return false;
        } else {
            this.field_70170_p.func_72838_d(another);
            return true;
        }
    }
}
