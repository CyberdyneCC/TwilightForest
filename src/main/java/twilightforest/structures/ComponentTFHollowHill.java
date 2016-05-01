package twilightforest.structures;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.TFTreasure;
import twilightforest.entity.TFCreatures;
import twilightforest.world.TFGenCaveStalactite;

public class ComponentTFHollowHill extends StructureTFComponent {

    int hillSize;
    int radius;

    public ComponentTFHollowHill() {}

    public ComponentTFHollowHill(World world, Random rand, int i, int size, int x, int y, int z) {
        super(i);
        this.setCoordBaseMode(0);
        this.hillSize = size;
        this.radius = (this.hillSize * 2 + 1) * 8 - 6;
        this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, -this.radius, -3, -this.radius, this.radius * 2, 10, this.radius * 2, 0);
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("hillSize", this.hillSize);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.hillSize = par1NBTTagCompound.func_74762_e("hillSize");
        this.radius = (this.hillSize * 2 + 1) * 8 - 6;
    }

    public void func_74861_a(StructureComponent structurecomponent, List list, Random random) {}

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        int[] sna = new int[] { 0, 128, 256, 512};
        int sn = sna[this.hillSize];
        int[] mga = new int[] { 0, 1, 4, 9};
        int mg = mga[this.hillSize];
        int[] tca = new int[] { 0, 2, 6, 12};
        int tc = tca[this.hillSize];

        int i;
        int[] dest;

        for (i = 0; i < mg; ++i) {
            dest = this.getCoordsInHill2D(rand);
            String mobID = this.getMobID(rand);

            this.placeSpawnerAtCurrentPosition(world, rand, dest[0], rand.nextInt(4), dest[1], mobID, sbb);
        }

        for (i = 0; i < tc; ++i) {
            dest = this.getCoordsInHill2D(rand);
            this.generateTreasureChest(world, dest[0], 0, dest[1], sbb);
        }

        for (i = 0; i < sn; ++i) {
            dest = this.getCoordsInHill2D(rand);
            this.generateOreStalactite(world, dest[0], 1, dest[1], sbb);
        }

        for (i = 0; i < sn; ++i) {
            dest = this.getCoordsInHill2D(rand);
            this.generateBlockStalactite(world, Blocks.field_150348_b, 1.0F, true, dest[0], 1, dest[1], sbb);
        }

        for (i = 0; i < sn; ++i) {
            dest = this.getCoordsInHill2D(rand);
            this.generateBlockStalactite(world, Blocks.field_150348_b, 0.9F, false, dest[0], 1, dest[1], sbb);
        }

        if (this.hillSize == 3) {
            ;
        }

        return true;
    }

    protected void generateTreasureChest(World world, int x, int y, int z, StructureBoundingBox sbb) {
        Random chestRNG = new Random(world.func_72905_C() + (long) (x * z));

        this.placeTreasureAtCurrentPosition(world, chestRNG, x, y, z, this.hillSize == 3 ? TFTreasure.hill3 : (this.hillSize == 2 ? TFTreasure.hill2 : TFTreasure.hill1), sbb);
        this.func_151554_b(world, Blocks.field_150347_e, 0, x, y - 1, z, sbb);
    }

    protected void generateOreStalactite(World world, int x, int y, int z, StructureBoundingBox sbb) {
        int dx = this.func_74865_a(x, z);
        int dy = this.func_74862_a(y);
        int dz = this.func_74873_b(x, z);

        if (sbb.func_78890_b(dx, dy, dz) && world.func_147439_a(dx, dy, dz) != Blocks.field_150474_ac) {
            Random stalRNG = new Random(world.func_72905_C() + (long) (dx * dz));
            TFGenCaveStalactite stalag = TFGenCaveStalactite.makeRandomOreStalactite(stalRNG, this.hillSize);

            stalag.func_76484_a(world, stalRNG, dx, dy, dz);
        }

    }

    protected void generateBlockStalactite(World world, Block blockToGenerate, float length, boolean up, int x, int y, int z, StructureBoundingBox sbb) {
        int dx = this.func_74865_a(x, z);
        int dy = this.func_74862_a(y);
        int dz = this.func_74873_b(x, z);

        if (sbb.func_78890_b(dx, dy, dz) && world.func_147439_a(dx, dy, dz) != Blocks.field_150474_ac) {
            Random stalRNG = new Random(world.func_72905_C() + (long) (dx * dz));

            if (this.hillSize == 1) {
                length *= 1.9F;
            }

            (new TFGenCaveStalactite(blockToGenerate, length, up)).func_76484_a(world, stalRNG, dx, dy, dz);
        }

    }

    boolean isInHill(int cx, int cz) {
        int dx = this.radius - cx;
        int dz = this.radius - cz;
        int dist = (int) Math.sqrt((double) (dx * dx + dz * dz));

        return dist < this.radius;
    }

    boolean isInHill(int mapX, int mapY, int mapZ) {
        int dx = this.field_74887_e.field_78897_a + this.radius - mapX;
        int dy = (this.field_74887_e.field_78895_b - mapY) * 2;
        int dz = this.field_74887_e.field_78896_c + this.radius - mapZ;
        int dist = dx * dx + dy * dy + dz * dz;

        return dist < this.radius * this.radius;
    }

    int[] getCoordsInHill2D(Random rand) {
        return this.getCoordsInHill2D(rand, this.radius);
    }

    int[] getCoordsInHill2D(Random rand, int rad) {
        int rx;
        int rz;

        do {
            rx = rand.nextInt(2 * rad);
            rz = rand.nextInt(2 * rad);
        } while (!this.isInHill(rx, rz));

        int[] coords = new int[] { rx, rz};

        return coords;
    }

    protected String getMobID(Random rand) {
        return this.getMobID(rand, this.hillSize);
    }

    protected String getMobID(Random rand, int level) {
        return level == 1 ? this.getLevel1Mob(rand) : (level == 2 ? this.getLevel2Mob(rand) : (level == 3 ? this.getLevel3Mob(rand) : "Spider"));
    }

    public String getLevel1Mob(Random rand) {
        switch (rand.nextInt(10)) {
        case 0:
        case 1:
        case 2:
            return TFCreatures.getSpawnerNameFor("Swarm Spider");

        case 3:
        case 4:
        case 5:
            return "Spider";

        case 6:
        case 7:
            return "Zombie";

        case 8:
            return "Silverfish";

        case 9:
            return TFCreatures.getSpawnerNameFor("Redcap");

        default:
            return TFCreatures.getSpawnerNameFor("Swarm Spider");
        }
    }

    public String getLevel2Mob(Random rand) {
        switch (rand.nextInt(10)) {
        case 0:
        case 1:
        case 2:
            return TFCreatures.getSpawnerNameFor("Redcap");

        case 3:
        case 4:
        case 5:
            return "Zombie";

        case 6:
        case 7:
            return "Skeleton";

        case 8:
            return TFCreatures.getSpawnerNameFor("Swarm Spider");

        case 9:
            return "CaveSpider";

        default:
            return TFCreatures.getSpawnerNameFor("Redcap");
        }
    }

    public String getLevel3Mob(Random rand) {
        switch (rand.nextInt(11)) {
        case 0:
            return TFCreatures.getSpawnerNameFor("Slime Beetle");

        case 1:
            return TFCreatures.getSpawnerNameFor("Fire Beetle");

        case 2:
            return TFCreatures.getSpawnerNameFor("Pinch Beetle");

        case 3:
        case 4:
        case 5:
            return "Skeleton";

        case 6:
        case 7:
        case 8:
            return "CaveSpider";

        case 9:
            return "Creeper";

        case 10:
        default:
            return TFCreatures.getSpawnerNameFor("Twilight Wraith");
        }
    }
}
