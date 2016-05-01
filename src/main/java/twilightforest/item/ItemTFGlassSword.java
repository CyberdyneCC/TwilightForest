package twilightforest.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;

public class ItemTFGlassSword extends ItemSword {

    public ItemTFGlassSword(ToolMaterial par2EnumToolMaterial) {
        super(par2EnumToolMaterial);
        this.func_77637_a(TFItems.creativeTab);
        this.func_111206_d("TwilightForest:glassSword");
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return false;
    }

    public boolean func_77644_a(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {
        boolean result = super.func_77644_a(par1ItemStack, par2EntityLiving, par3EntityLiving);

        if (result) {
            par1ItemStack.func_77972_a(1000, par3EntityLiving);
        }

        return result;
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (player.field_70170_p.field_72995_K) {
            for (int i = 0; i < 20; ++i) {
                double px = entity.field_70165_t + (double) (ItemTFGlassSword.field_77697_d.nextFloat() * entity.field_70130_N * 2.0F) - (double) entity.field_70130_N;
                double py = entity.field_70163_u + (double) (ItemTFGlassSword.field_77697_d.nextFloat() * entity.field_70131_O);
                double pz = entity.field_70161_v + (double) (ItemTFGlassSword.field_77697_d.nextFloat() * entity.field_70130_N * 2.0F) - (double) entity.field_70130_N;

                entity.field_70170_p.func_72869_a("blockcrack_" + Block.func_149682_b(Blocks.field_150399_cn) + "_" + 0, px, py, pz, 0.0D, 0.0D, 0.0D);
            }

            player.func_85030_a(Blocks.field_150359_w.field_149762_H.func_150495_a(), 1.0F, 0.5F);
        }

        return false;
    }
}
