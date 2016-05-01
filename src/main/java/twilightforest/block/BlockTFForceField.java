package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import twilightforest.item.TFItems;

public class BlockTFForceField extends BlockPane {

    public static String[] names = new String[] { "purple", "pink", "orange", "green", "blue"};
    public static IIcon[] sides;
    public static IIcon top;

    protected BlockTFForceField() {
        super("glass", "glass_pane_top", Material.field_151577_b, false);
        this.func_149715_a(0.13333334F);
        this.func_149647_a(TFItems.creativeTab);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return BlockTFForceField.sides[meta % BlockTFForceField.names.length];
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_150097_e() {
        return BlockTFForceField.top;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister register) {
        super.func_149651_a(register);
        BlockTFForceField.sides = new IIcon[BlockTFForceField.names.length];

        for (int i = 0; i < BlockTFForceField.names.length; ++i) {
            BlockTFForceField.sides[i] = register.func_94245_a("TwilightForest:forcefield_" + BlockTFForceField.names[i]);
        }

        BlockTFForceField.top = register.func_94245_a("TwilightForest:forcefield_top");
    }

    @SideOnly(Side.CLIENT)
    public int func_149677_c(IBlockAccess world, int x, int y, int z) {
        return 15728880;
    }

    public int func_149692_a(int meta) {
        return meta % BlockTFForceField.names.length;
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < BlockTFForceField.names.length; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }

    }

    @SideOnly(Side.CLIENT)
    public int func_149701_w() {
        return 1;
    }

    public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity) {
        super.func_149743_a(world, x, y, z, aabb, list, entity);
        boolean north = this.canPaneConnectTo(world, x, y, z - 1, ForgeDirection.NORTH);
        boolean south = this.canPaneConnectTo(world, x, y, z + 1, ForgeDirection.SOUTH);
        boolean east = this.canPaneConnectTo(world, x - 1, y, z, ForgeDirection.WEST);
        boolean west = this.canPaneConnectTo(world, x + 1, y, z, ForgeDirection.EAST);

        if (north && south && east && west) {
            this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

        AxisAlignedBB myAABB = this.func_149668_a(world, x, y, z);

        if (myAABB != null && aabb.func_72326_a(myAABB)) {
            list.add(myAABB);
        }

    }
}
