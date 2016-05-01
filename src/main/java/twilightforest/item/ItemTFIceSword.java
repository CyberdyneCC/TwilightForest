package twilightforest.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import twilightforest.TwilightForestMod;

public class ItemTFIceSword extends ItemSword {

    public ItemTFIceSword(ToolMaterial par2EnumToolMaterial) {
        super(par2EnumToolMaterial);
        this.func_77637_a(TFItems.creativeTab);
        this.func_111206_d("TwilightForest:iceSword");
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == Item.func_150898_a(Blocks.field_150403_cj) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    public boolean func_77644_a(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {
        boolean result = super.func_77644_a(par1ItemStack, par2EntityLiving, par3EntityLiving);

        if (result) {
            byte chillLevel = 2;

            par2EntityLiving.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 200, chillLevel, true));
        }

        return result;
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (player.field_70170_p.field_72995_K) {
            for (int i = 0; i < 20; ++i) {
                double px = entity.field_70165_t + (double) (ItemTFIceSword.field_77697_d.nextFloat() * entity.field_70130_N * 2.0F) - (double) entity.field_70130_N;
                double py = entity.field_70163_u + (double) (ItemTFIceSword.field_77697_d.nextFloat() * entity.field_70131_O);
                double pz = entity.field_70161_v + (double) (ItemTFIceSword.field_77697_d.nextFloat() * entity.field_70130_N * 2.0F) - (double) entity.field_70130_N;

                TwilightForestMod.proxy.spawnParticle(entity.field_70170_p, "snowstuff", px, py, pz, 0.0D, 0.0D, 0.0D);
            }
        }

        return false;
    }
}
