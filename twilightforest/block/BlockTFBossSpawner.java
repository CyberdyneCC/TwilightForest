package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import twilightforest.item.TFItems;
import twilightforest.tileentity.TileEntityTFHydraSpawner;
import twilightforest.tileentity.TileEntityTFKnightPhantomsSpawner;
import twilightforest.tileentity.TileEntityTFLichSpawner;
import twilightforest.tileentity.TileEntityTFNagaSpawner;
import twilightforest.tileentity.TileEntityTFSnowQueenSpawner;
import twilightforest.tileentity.TileEntityTFTowerBossSpawner;

public class BlockTFBossSpawner extends BlockContainer {

    protected BlockTFBossSpawner() {
        super(Material.field_151576_e);
        this.func_149711_c(20.0F);
        this.func_149647_a(TFItems.creativeTab);
    }

    public boolean hasTileEntity(int metadata) {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return (TileEntity) (metadata == 0 ? new TileEntityTFNagaSpawner() : (metadata == 1 ? new TileEntityTFLichSpawner() : (metadata == 2 ? new TileEntityTFHydraSpawner() : (metadata == 3 ? new TileEntityTFTowerBossSpawner() : (metadata == 4 ? new TileEntityTFKnightPhantomsSpawner() : (metadata == 5 ? new TileEntityTFSnowQueenSpawner() : null))))));
    }

    public TileEntity func_149915_a(World world, int i) {
        return this.createTileEntity(world, i);
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return null;
    }

    public int func_149745_a(Random random) {
        return 0;
    }

    public boolean func_149662_c() {
        return false;
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {}

    public IIcon func_149691_a(int side, int metadata) {
        return Blocks.field_150474_ac.func_149691_a(side, metadata);
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {}
}
