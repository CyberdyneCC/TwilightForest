package twilightforest.entity.ai;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import twilightforest.entity.passive.EntityTFQuestRam;

public class EntityAITFFindLoose extends EntityAIBase {

    private EntityCreature temptedEntity;
    private Item temptID;
    private float pursueSpeed;
    private int delayTemptCounter;
    private EntityItem temptingItem;

    public EntityAITFFindLoose(EntityTFQuestRam entityTFQuestRam, float speed, Item blockID) {
        this.temptedEntity = entityTFQuestRam;
        this.pursueSpeed = speed;
        this.temptID = blockID;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        if (this.delayTemptCounter > 0) {
            --this.delayTemptCounter;
            return false;
        } else {
            this.temptingItem = null;
            List nearbyItems = this.temptedEntity.field_70170_p.func_72872_a(EntityItem.class, this.temptedEntity.field_70121_D.func_72314_b(16.0D, 4.0D, 16.0D));
            Iterator iterator = nearbyItems.iterator();

            while (iterator.hasNext()) {
                EntityItem itemNearby = (EntityItem) iterator.next();

                if (itemNearby.func_92059_d().func_77973_b() == this.temptID && itemNearby.func_70089_S()) {
                    this.temptingItem = itemNearby;
                    break;
                }
            }

            return this.temptingItem != null;
        }
    }

    public boolean func_75253_b() {
        return this.func_75250_a();
    }

    public void func_75249_e() {}

    public void func_75251_c() {
        this.temptingItem = null;
        this.temptedEntity.func_70661_as().func_75499_g();
        this.delayTemptCounter = 100;
    }

    public void func_75246_d() {
        this.temptedEntity.func_70671_ap().func_75651_a(this.temptingItem, 30.0F, (float) this.temptedEntity.func_70646_bf());
        if (this.temptedEntity.func_70068_e(this.temptingItem) < 6.25D) {
            this.temptedEntity.func_70661_as().func_75499_g();
        } else {
            this.temptedEntity.func_70661_as().func_75492_a(this.temptingItem.field_70165_t, this.temptingItem.field_70163_u, this.temptingItem.field_70161_v, (double) this.pursueSpeed);
        }

    }
}
