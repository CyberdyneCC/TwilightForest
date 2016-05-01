package twilightforest.item;

import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.StatCollector;

public class ItemTFKnightlyPick extends ItemPickaxe {

    private static final int BONUS_DAMAGE = 2;
    private EntityPlayer bonusDamagePlayer;
    private Entity bonusDamageEntity;
    private float damageVsEntity;

    protected ItemTFKnightlyPick(ToolMaterial par2EnumToolMaterial) {
        super(par2EnumToolMaterial);
        this.func_77637_a(TFItems.creativeTab);
        this.damageVsEntity = 4.0F + par2EnumToolMaterial.func_78000_c();
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == TFItems.knightMetal ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (entity instanceof EntityLivingBase && ((EntityLivingBase) entity).func_70658_aO() > 0) {
            this.bonusDamageEntity = entity;
            this.bonusDamagePlayer = player;
        }

        return false;
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.func_77624_a(par1ItemStack, par2EntityPlayer, par3List, par4);
        par3List.add(StatCollector.func_74838_a(this.func_77658_a() + ".tooltip"));
    }

    public Multimap func_111205_h() {
        Multimap multimap = super.func_111205_h();

        multimap.removeAll(SharedMonsterAttributes.field_111264_e.func_111108_a());
        multimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(ItemTFKnightlyPick.field_111210_e, "Tool modifier", (double) this.damageVsEntity, 0));
        return multimap;
    }
}
