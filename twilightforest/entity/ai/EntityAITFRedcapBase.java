package twilightforest.entity.ai;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import twilightforest.entity.EntityTFRedcap;

public abstract class EntityAITFRedcapBase extends EntityAIBase {

    protected EntityTFRedcap entityObj;

    public boolean isTargetLookingAtMe(EntityLivingBase attackTarget) {
        double dx = this.entityObj.field_70165_t - attackTarget.field_70165_t;
        double dz = this.entityObj.field_70161_v - attackTarget.field_70161_v;
        float angle = (float) (Math.atan2(dz, dx) * 180.0D / 3.141592653589793D) - 90.0F;
        float difference = MathHelper.func_76135_e((attackTarget.field_70177_z - angle) % 360.0F);

        return difference < 60.0F || difference > 300.0F;
    }

    public ChunkCoordinates findBlockTNTNearby(int range) {
        int entityPosX = MathHelper.func_76128_c(this.entityObj.field_70165_t);
        int entityPosY = MathHelper.func_76128_c(this.entityObj.field_70163_u);
        int entityPosZ = MathHelper.func_76128_c(this.entityObj.field_70161_v);

        for (int x = -range; x <= range; ++x) {
            for (int y = -range; y <= range; ++y) {
                for (int z = -range; z <= range; ++z) {
                    if (this.entityObj.field_70170_p.func_147439_a(entityPosX + x, entityPosY + y, entityPosZ + z) == Blocks.field_150335_W) {
                        return new ChunkCoordinates(entityPosX + x, entityPosY + y, entityPosZ + z);
                    }
                }
            }
        }

        return null;
    }

    public boolean isLitTNTNearby(int range) {
        AxisAlignedBB expandedBox = this.entityObj.field_70121_D.func_72314_b((double) range, (double) range, (double) range);
        List nearbyTNT = this.entityObj.field_70170_p.func_72872_a(EntityTNTPrimed.class, expandedBox);

        return nearbyTNT.size() > 0;
    }
}
