package twilightforest.entity.ai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;

public class EntityAITFFlockTarget extends EntityAITarget {

    EntityLivingBase flockCreature;
    EntityLivingBase flockTarget;

    public EntityAITFFlockTarget(EntityCreature par1EntityLiving, boolean b) {
        super(par1EntityLiving, false);
        this.flockCreature = par1EntityLiving;
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        List flockList = this.flockCreature.field_70170_p.func_72872_a(this.flockCreature.getClass(), this.flockCreature.field_70121_D.func_72314_b(16.0D, 4.0D, 16.0D));
        ArrayList targetList = new ArrayList();
        Iterator randomTarget = flockList.iterator();

        while (randomTarget.hasNext()) {
            EntityLivingBase flocker = (EntityLivingBase) randomTarget.next();

            if (flocker.func_70643_av() != null) {
                targetList.add(flocker.func_70643_av());
            }
        }

        if (targetList.isEmpty()) {
            return false;
        } else {
            EntityLivingBase randomTarget1 = (EntityLivingBase) targetList.get(this.flockCreature.field_70170_p.field_73012_v.nextInt(targetList.size()));

            System.out.println("randomTarget = " + randomTarget1);
            this.flockTarget = randomTarget1;
            return this.func_75296_a(this.flockTarget, true);
        }
    }

    public void func_75249_e() {
        this.field_75299_d.func_70624_b(this.flockTarget);
        super.func_75249_e();
    }
}
