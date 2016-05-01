package twilightforest.item;

import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import twilightforest.block.BlockTFGiantBlock;
import twilightforest.block.TFBlocks;

public class ItemTFGiantPick extends ItemPickaxe {

    private float damageVsEntity;
    private GiantItemIcon giantIcon;

    protected ItemTFGiantPick(ToolMaterial par2EnumToolMaterial) {
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

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = Items.field_151050_s.func_77617_a(0);
        this.giantIcon = new GiantItemIcon(this.field_77791_bV, 0.4375F, 0.125F);
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return (IIcon) (pass == -1 ? this.giantIcon : super.getIcon(stack, pass));
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.func_77624_a(par1ItemStack, par2EntityPlayer, par3List, par4);
        par3List.add(StatCollector.func_74838_a(this.func_77658_a() + ".tooltip"));
    }

    public Multimap func_111205_h() {
        Multimap multimap = super.func_111205_h();

        multimap.removeAll(SharedMonsterAttributes.field_111264_e.func_111108_a());
        multimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(ItemTFGiantPick.field_111210_e, "Tool modifier", (double) this.damageVsEntity, 0));
        return multimap;
    }

    public float func_150893_a(ItemStack par1ItemStack, Block par2Block) {
        float strVsBlock = super.func_150893_a(par1ItemStack, par2Block);

        strVsBlock *= par2Block == TFBlocks.giantObsidian ? 64.0F : 1.0F;
        return this.isGiantBlock(par2Block) ? strVsBlock * 64.0F : strVsBlock;
    }

    private boolean isGiantBlock(Block block) {
        return block instanceof BlockTFGiantBlock;
    }
}
