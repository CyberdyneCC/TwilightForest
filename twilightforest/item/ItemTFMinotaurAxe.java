package twilightforest.item;

import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.StatCollector;

public class ItemTFMinotaurAxe extends ItemAxe {

    public static final int BONUS_CHARGING_DAMAGE = 7;
    private Entity bonusDamageEntity;
    private EntityPlayer bonusDamagePlayer;
    private float damageVsEntity;

    protected ItemTFMinotaurAxe(ToolMaterial par2EnumToolMaterial) {
        super(par2EnumToolMaterial);
        this.damageVsEntity = 4.0F + par2EnumToolMaterial.func_78000_c();
        this.func_77637_a(TFItems.creativeTab);
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        ItemStack istack = new ItemStack(par1, 1, 0);

        par3List.add(istack);
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (player.func_70051_ag()) {
            this.bonusDamageEntity = entity;
            this.bonusDamagePlayer = player;
        }

        return false;
    }

    public float getDamageVsEntity(Entity par1Entity, ItemStack itemStack) {
        if (this.bonusDamagePlayer != null && this.bonusDamageEntity != null && par1Entity == this.bonusDamageEntity) {
            this.bonusDamagePlayer.func_71047_c(par1Entity);
            this.bonusDamagePlayer = null;
            this.bonusDamageEntity = null;
            return this.damageVsEntity + 7.0F;
        } else {
            return this.damageVsEntity;
        }
    }

    public int func_77619_b() {
        return ToolMaterial.GOLD.func_77995_e();
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.func_77624_a(par1ItemStack, par2EntityPlayer, par3List, par4);
        par3List.add(StatCollector.func_74838_a(this.func_77658_a() + ".tooltip"));
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }

    public Multimap func_111205_h() {
        Multimap multimap = super.func_111205_h();

        multimap.removeAll(SharedMonsterAttributes.field_111264_e.func_111108_a());
        multimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(ItemTFMinotaurAxe.field_111210_e, "Tool modifier", (double) this.damageVsEntity, 0));
        return multimap;
    }
}
