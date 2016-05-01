package twilightforest.item;

import com.google.common.collect.Sets;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import twilightforest.entity.EntityTFChainBlock;

public class ItemTFChainBlock extends ItemTool {

    private HashMap launchedBlocksMap = new HashMap();

    protected ItemTFChainBlock() {
        super(6.0F, TFItems.TOOL_KNIGHTLY, Sets.newHashSet(new Block[] { Blocks.field_150348_b}));
        this.field_77777_bU = 1;
        this.func_77656_e(99);
        this.func_77637_a(TFItems.creativeTab);
    }

    public ItemStack func_77659_a(ItemStack stack, World worldObj, EntityPlayer player) {
        player.func_71008_a(stack, this.func_77626_a(stack));
        if (!worldObj.field_72995_K && !this.hasLaunchedBlock(stack)) {
            worldObj.func_72956_a(player, "random.bow", 1.0F, 1.0F / (ItemTFChainBlock.field_77697_d.nextFloat() * 0.4F + 1.2F));
            EntityTFChainBlock launchedBlock = new EntityTFChainBlock(worldObj, player);

            worldObj.func_72838_d(launchedBlock);
            this.setLaunchedBlock(stack, launchedBlock);
            setChainAsThrown(stack);
            stack.func_77972_a(1, player);
        }

        return stack;
    }

    public static void setChainAsThrown(ItemStack stack) {
        if (stack.func_77978_p() == null) {
            stack.func_77982_d(new NBTTagCompound());
        }

        stack.func_77978_p().func_74757_a("thrown", true);
    }

    public static void setChainAsReturned(ItemStack stack) {
        if (stack.func_77978_p() == null) {
            stack.func_77982_d(new NBTTagCompound());
        }

        stack.func_77978_p().func_74757_a("thrown", false);
    }

    public static boolean doesChainHaveBlock(ItemStack stack) {
        return stack.func_77978_p() == null ? true : !stack.func_77978_p().func_74767_n("thrown");
    }

    public static void setChainAsReturned(EntityPlayer player) {
        if (player != null && player.func_71045_bC() != null && player.func_71045_bC().func_77973_b() == TFItems.chainBlock) {
            setChainAsReturned(player.func_71045_bC());
        }

    }

    public boolean hasLaunchedBlock(ItemStack stack) {
        Entity cube = (Entity) this.launchedBlocksMap.get(stack);

        return cube != null && !cube.field_70128_L;
    }

    public void setLaunchedBlock(ItemStack stack, EntityTFChainBlock launchedCube) {
        this.launchedBlocksMap.put(stack, launchedCube);
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }

    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        return doesChainHaveBlock(stack) ? this.field_77791_bV : TFItems.knightmetalRing.func_77650_f(stack);
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.block;
    }

    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        return false;
    }

    public int getHarvestLevel(ItemStack stack, String toolClass) {
        return toolClass != null && toolClass.equals("pickaxe") ? 2 : -1;
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == TFItems.knightMetal ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }
}
