package twilightforest.entity.ai;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.Vec3;

public class EntityAITFFlockToSameKind extends EntityAIBase {

    private static final double MAX_DIST = 256.0D;
    private static final double MIN_DIST = 25.0D;
    EntityLiving flockCreature;
    Vec3 flockPosition;
    double speed;
    private int moveTimer;

    public EntityAITFFlockToSameKind(EntityLiving par1EntityLiving, double par2) {
        this.flockCreature = par1EntityLiving;
        this.speed = par2;
    }

    public boolean func_75250_a() {
        if (this.flockCreature.func_70681_au().nextInt(40) != 0) {
            return false;
        } else {
            List flockList = this.flockCreature.field_70170_p.func_72872_a(this.flockCreature.getClass(), this.flockCreature.field_70121_D.func_72314_b(16.0D, 4.0D, 16.0D));
            int flocknum = 0;
            double flockX = 0.0D;
            double flockY = 0.0D;
            double flockZ = 0.0D;

            EntityLiving flocker;

            for (Iterator iterator = flockList.iterator(); iterator.hasNext(); flockZ += flocker.field_70161_v) {
                flocker = (EntityLiving) iterator.next();
                ++flocknum;
                flockX += flocker.field_70165_t;
                flockY += flocker.field_70163_u;
            }

            flockX /= (double) flocknum;
            flockY /= (double) flocknum;
            flockZ /= (double) flocknum;
            if (this.flockCreature.func_70092_e(flockX, flockY, flockZ) < 25.0D) {
                return false;
            } else {
                this.flockPosition = Vec3.func_72443_a(flockX, flockY, flockZ);
                return true;
            }
        }
    }

    public boolean func_75253_b() {
        if (this.flockPosition == null) {
            return false;
        } else {
            double distance = this.flockCreature.func_70092_e(this.flockPosition.field_72450_a, this.flockPosition.field_72448_b, this.flockPosition.field_72449_c);

            return distance >= 25.0D && distance <= 256.0D;
        }
    }

    public void func_75249_e() {
        this.moveTimer = 0;
    }

    public void func_75251_c() {
        this.flockPosition = null;
    }

    public void func_75246_d() {
        if (--this.moveTimer <= 0) {
            this.moveTimer = 10;
            this.flockCreature.func_70661_as().func_75492_a(this.flockPosition.field_72450_a, this.flockPosition.field_72448_b, this.flockPosition.field_72449_c, this.speed);
        }

    }
}
