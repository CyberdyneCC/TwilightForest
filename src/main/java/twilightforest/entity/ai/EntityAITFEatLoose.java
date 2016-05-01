package twilightforest.entity.ai;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import twilightforest.entity.passive.EntityTFQuestRam;

public class EntityAITFEatLoose extends EntityAIBase {

    private EntityTFQuestRam temptedQuestRam;
    private Item temptID;
    private int delayTemptCounter;
    private EntityItem temptingItem;

    public EntityAITFEatLoose(EntityTFQuestRam entityTFQuestRam, Item blockID) {
        this.temptedQuestRam = entityTFQuestRam;
        this.temptID = blockID;
        this.func_75248_a(0);
    }

    public boolean func_75250_a() {
        if (this.delayTemptCounter > 0) {
            --this.delayTemptCounter;
            return false;
        } else {
            this.temptingItem = null;
            List nearbyItems = this.temptedQuestRam.field_70170_p.func_72872_a(EntityItem.class, this.temptedQuestRam.field_70121_D.func_72314_b(2.0D, 2.0D, 2.0D));
            Iterator iterator = nearbyItems.iterator();

            while (iterator.hasNext()) {
                EntityItem itemNearby = (EntityItem) iterator.next();

                if (itemNearby.func_92059_d().func_77973_b() == this.temptID && !this.temptedQuestRam.isColorPresent(itemNearby.func_92059_d().func_77960_j()) && itemNearby.func_70089_S()) {
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
        this.temptedQuestRam.func_70661_as().func_75499_g();
        this.delayTemptCounter = 100;
    }

    public void func_75246_d() {
        this.temptedQuestRam.func_70671_ap().func_75651_a(this.temptingItem, 30.0F, (float) this.temptedQuestRam.func_70646_bf());
        if (this.temptedQuestRam.func_70068_e(this.temptingItem) < 6.25D && !this.temptedQuestRam.isColorPresent(this.temptingItem.func_92059_d().func_77960_j())) {
            this.temptingItem.func_70106_y();
            this.temptedQuestRam.func_70642_aH();
            this.temptedQuestRam.setColorPresent(this.temptingItem.func_92059_d().func_77960_j());
            this.temptedQuestRam.animateAddColor(this.temptingItem.func_92059_d().func_77960_j(), 50);
        }

    }
}
