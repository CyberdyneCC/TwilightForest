package twilightforest.entity.ai;

import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import twilightforest.entity.EntityTFRedcap;

public class EntityAITFRedcapLightTNT extends EntityAITFRedcapBase {

    private float pursueSpeed;
    private int delayTemptCounter;
    private int tntX;
    private int tntY;
    private int tntZ;

    public EntityAITFRedcapLightTNT(EntityTFRedcap hostEntity, float speed) {
        this.entityObj = hostEntity;
        this.pursueSpeed = speed;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        ChunkCoordinates nearbyTNT = this.findBlockTNTNearby(8);

        if (this.delayTemptCounter > 0) {
            --this.delayTemptCounter;
            return false;
        } else if (nearbyTNT != null) {
            this.tntX = nearbyTNT.field_71574_a;
            this.tntY = nearbyTNT.field_71572_b;
            this.tntZ = nearbyTNT.field_71573_c;
            return true;
        } else {
            return false;
        }
    }

    public boolean func_75253_b() {
        return this.entityObj.field_70170_p.func_147439_a(this.tntX, this.tntY, this.tntZ) == Blocks.field_150335_W;
    }

    public void func_75249_e() {
        this.entityObj.func_70062_b(0, EntityTFRedcap.heldFlint);
    }

    public void func_75251_c() {
        this.entityObj.func_70661_as().func_75499_g();
        this.entityObj.func_70062_b(0, this.entityObj.getPick());
        this.delayTemptCounter = 20;
    }

    public void func_75246_d() {
        this.entityObj.func_70671_ap().func_75650_a((double) this.tntX, (double) this.tntY, (double) this.tntZ, 30.0F, (float) this.entityObj.func_70646_bf());
        if (this.entityObj.func_70011_f((double) this.tntX, (double) this.tntY, (double) this.tntZ) < 2.4D) {
            this.entityObj.func_70642_aH();
            Blocks.field_150335_W.func_149664_b(this.entityObj.field_70170_p, this.tntX, this.tntY, this.tntZ, 1);
            this.entityObj.field_70170_p.func_147465_d(this.tntX, this.tntY, this.tntZ, Blocks.field_150350_a, 0, 2);
            this.entityObj.func_70661_as().func_75499_g();
        } else {
            this.entityObj.func_70661_as().func_75492_a((double) this.tntX, (double) this.tntY, (double) this.tntZ, (double) this.pursueSpeed);
        }

    }
}
