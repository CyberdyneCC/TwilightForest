package twilightforest.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import twilightforest.item.TFItems;

public class BlockTFUberousSoil extends Block implements IGrowable {

    protected BlockTFUberousSoil() {
        super(Material.field_151578_c);
        this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
        this.func_149713_g(255);
        this.func_149711_c(0.6F);
        this.func_149672_a(BlockTFUberousSoil.field_149767_g);
        this.func_149675_a(true);
        this.func_149658_d("TwilightForest:uberous_soil");
        this.func_149647_a(TFItems.creativeTab);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Blocks.field_150346_d.func_149650_a(0, p_149650_2_, p_149650_3_);
    }

    public void func_149674_a(World world, int x, int y, int z, Random rand) {
        Material aboveMaterial = world.func_147439_a(x, y + 1, z).func_149688_o();

        if (aboveMaterial.func_76220_a()) {
            world.func_147449_b(x, y, z, Blocks.field_150346_d);
        }

    }

    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
        EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);

        return plantType == EnumPlantType.Crop || plantType == EnumPlantType.Plains || plantType == EnumPlantType.Cave;
    }

    public void func_149695_a(World world, int x, int y, int z, Block neighbor) {
        Block above = world.func_147439_a(x, y + 1, z);
        Material aboveMaterial = above.func_149688_o();

        if (aboveMaterial.func_76220_a()) {
            world.func_147449_b(x, y, z, Blocks.field_150346_d);
        }

        if (above instanceof IPlantable) {
            IPlantable plant = (IPlantable) above;

            if (plant.getPlantType(world, x, y + 1, z) == EnumPlantType.Crop) {
                world.func_147465_d(x, y, z, Blocks.field_150458_ak, 2, 2);
            } else if (plant.getPlantType(world, x, y + 1, z) == EnumPlantType.Plains) {
                world.func_147449_b(x, y, z, Blocks.field_150349_c);
            } else {
                world.func_147449_b(x, y, z, Blocks.field_150346_d);
            }

            ItemDye.applyBonemeal(new ItemStack(Items.field_151100_aR), world, x, y + 1, z, (EntityPlayer) null);
            ItemDye.applyBonemeal(new ItemStack(Items.field_151100_aR), world, x, y + 1, z, (EntityPlayer) null);
            ItemDye.applyBonemeal(new ItemStack(Items.field_151100_aR), world, x, y + 1, z, (EntityPlayer) null);
            ItemDye.applyBonemeal(new ItemStack(Items.field_151100_aR), world, x, y + 1, z, (EntityPlayer) null);
            if (!world.field_72995_K) {
                world.func_72926_e(2005, x, y + 1, z, 0);
            }
        }

    }

    public boolean func_149851_a(World world, int x, int y, int z, boolean flag) {
        return true;
    }

    public boolean func_149852_a(World world, Random rand, int x, int y, int z) {
        return true;
    }

    public void func_149853_b(World world, Random rand, int x, int y, int z) {
        int gx = x;
        int gz = z;

        if (rand.nextBoolean()) {
            gx = x + (rand.nextBoolean() ? 1 : -1);
        } else {
            gz = z + (rand.nextBoolean() ? 1 : -1);
        }

        Block blockAt = world.func_147439_a(gx, y, gz);

        if (world.func_147437_c(gx, y + 1, gz) && (blockAt == Blocks.field_150346_d || blockAt == Blocks.field_150349_c || blockAt == Blocks.field_150458_ak)) {
            world.func_147449_b(gx, y, gz, this);
        }

    }
}
