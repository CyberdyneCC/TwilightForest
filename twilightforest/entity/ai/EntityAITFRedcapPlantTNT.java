package twilightforest.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import twilightforest.entity.EntityTFRedcap;

public class EntityAITFRedcapPlantTNT extends EntityAITFRedcapBase {

    public EntityAITFRedcapPlantTNT(EntityTFRedcap entityTFRedcap) {
        this.entityObj = entityTFRedcap;
    }

    public boolean func_75250_a() {
        EntityLivingBase attackTarget = this.entityObj.func_70638_az();

        return attackTarget != null && this.entityObj.getTntLeft() > 0 && this.entityObj.func_70068_e(attackTarget) < 25.0D && !this.isTargetLookingAtMe(attackTarget) && !this.isLitTNTNearby(8) && this.findBlockTNTNearby(5) == null;
    }

    public void func_75249_e() {
        int entityPosX = MathHelper.func_76128_c(this.entityObj.field_70165_t);
        int entityPosY = MathHelper.func_76128_c(this.entityObj.field_70163_u);
        int entityPosZ = MathHelper.func_76128_c(this.entityObj.field_70161_v);

        this.entityObj.func_70062_b(0, EntityTFRedcap.heldTNT);
        if (this.entityObj.field_70170_p.func_147437_c(entityPosX, entityPosY, entityPosZ)) {
            this.entityObj.setTntLeft(this.entityObj.getTntLeft() - 1);
            this.entityObj.func_70642_aH();
            this.entityObj.field_70170_p.func_147465_d(entityPosX, entityPosY, entityPosZ, Blocks.field_150335_W, 0, 3);
        }

    }

    public void func_75251_c() {
        this.entityObj.func_70062_b(0, this.entityObj.getPick());
    }
}
