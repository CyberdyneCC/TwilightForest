package twilightforest.structures.trollcave;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.block.TFBlocks;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFTrollCaveConnect extends ComponentTFTrollCaveMain {

    protected boolean[] openingTowards = new boolean[] { false, false, true, false};

    public ComponentTFTrollCaveConnect() {}

    public ComponentTFTrollCaveConnect(int index, int x, int y, int z, int caveSize, int caveHeight, int direction) {
        super(index);
        this.size = caveSize;
        this.height = caveHeight;
        this.setCoordBaseMode(direction);
        this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, this.size - 1, this.height - 1, this.size - 1, direction);
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("openingTowards0", this.openingTowards[0]);
        par1NBTTagCompound.func_74757_a("openingTowards1", this.openingTowards[1]);
        par1NBTTagCompound.func_74757_a("openingTowards2", this.openingTowards[2]);
        par1NBTTagCompound.func_74757_a("openingTowards3", this.openingTowards[3]);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.openingTowards[0] = par1NBTTagCompound.func_74767_n("openingTowards0");
        this.openingTowards[1] = par1NBTTagCompound.func_74767_n("openingTowards1");
        this.openingTowards[2] = par1NBTTagCompound.func_74767_n("openingTowards2");
        this.openingTowards[3] = par1NBTTagCompound.func_74767_n("openingTowards3");
    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        if (this.func_74877_c() < 3) {
            for (int i = 0; i < 4; ++i) {
                ChunkCoordinates dest = this.getValidOpening(rand, 2, i);

                if (rand.nextBoolean() || !this.makeGardenCave(list, rand, this.func_74877_c() + 1, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c, 30, 15, i)) {
                    this.makeSmallerCave(list, rand, this.func_74877_c() + 1, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c, 20, 15, i);
                }
            }
        }

    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        if (this.isBoundingBoxOutOfHighlands(world, sbb)) {
            return false;
        } else {
            this.hollowCaveMiddle(world, sbb, rand, 0, 0, 0, this.size - 1, this.height - 1, this.size - 1);
            Random decoRNG = new Random(world.func_72905_C() + (long) (this.field_74887_e.field_78897_a * 321534781) ^ (long) (this.field_74887_e.field_78896_c * 756839));

            int i;

            for (i = 0; i < 4; ++i) {
                if (!this.openingTowards[i]) {
                    this.decorateWall(world, sbb, decoRNG, i);
                }
            }

            decoRNG.setSeed(world.func_72905_C() + (long) (this.field_74887_e.field_78897_a * 321534781) ^ (long) (this.field_74887_e.field_78896_c * 756839));

            ChunkCoordinates dest;

            for (i = 0; i < 32; ++i) {
                dest = this.getCoordsInCave(decoRNG);
                this.generateBlockStalactite(world, decoRNG, Blocks.field_150348_b, 0.5F, true, dest.field_71574_a, 3, dest.field_71573_c, sbb);
            }

            for (i = 0; i < 8; ++i) {
                dest = this.getCoordsInCave(decoRNG);
                this.generateBlockStalactite(world, decoRNG, Blocks.field_150348_b, 0.5F, false, dest.field_71574_a, 3, dest.field_71573_c, sbb);
            }

            decoRNG.setSeed(world.func_72905_C() + (long) (this.field_74887_e.field_78897_a * 321534781) ^ (long) (this.field_74887_e.field_78896_c * 756839));
            if (this.countExits() == 1 && decoRNG.nextInt(3) == 0) {
                this.makeTreasureCrate(world, decoRNG, sbb);
            } else if (decoRNG.nextInt(3) == 0) {
                this.makeMonolith(world, decoRNG, sbb);
            }

            return true;
        }
    }

    protected void makeMonolith(World world, Random rand, StructureBoundingBox sbb) {
        int mid = this.size / 2;
        int height = 7 + rand.nextInt(8);
        int rotation = rand.nextInt(4);

        this.fillBlocksRotated(world, sbb, mid - 1, 0, mid - 1, mid - 1, height, mid - 1, Blocks.field_150343_Z, 0, rotation);
        this.fillBlocksRotated(world, sbb, mid + 0, 0, mid - 1, mid + 0, height - 2, mid - 1, Blocks.field_150343_Z, 0, rotation);
        this.fillBlocksRotated(world, sbb, mid - 1, 0, mid + 0, mid - 1, height - 2, mid + 0, Blocks.field_150343_Z, 0, rotation);
        this.fillBlocksRotated(world, sbb, mid + 0, 0, mid + 0, mid + 0, height - 4, mid + 0, Blocks.field_150343_Z, 0, rotation);
    }

    private int countExits() {
        int count = 0;

        for (int i = 0; i < this.openingTowards.length; ++i) {
            if (this.openingTowards[i]) {
                ++count;
            }
        }

        return count;
    }

    private void decorateWall(World world, StructureBoundingBox sbb, Random decoRNG, int rotation) {
        if (decoRNG.nextBoolean()) {
            this.decorateBracketMushrooms(world, sbb, decoRNG, rotation);
        } else if (decoRNG.nextBoolean()) {
            this.decorateStoneFormation(world, sbb, decoRNG, rotation);
            this.decorateStoneFormation(world, sbb, decoRNG, rotation);
        } else {
            this.decorateStoneProjection(world, sbb, decoRNG, rotation);
        }

    }

    private void decorateStoneFormation(World world, StructureBoundingBox sbb, Random decoRNG, int rotation) {
        int z = 5 + decoRNG.nextInt(7);
        int startY = 1 + decoRNG.nextInt(2);

        for (int y = startY; y < this.height; y += 2) {
            byte width = 1;
            int depth = 1 + (decoRNG.nextInt(3) == 0 ? 1 : 0);

            this.makeSingleStoneFormation(world, sbb, decoRNG, rotation, z, y, width, depth);
            z += decoRNG.nextInt(4) - decoRNG.nextInt(4);
            if (z < 5 || z > this.size - 5) {
                z = 5 + decoRNG.nextInt(7);
            }
        }

    }

    private void makeSingleStoneFormation(World world, StructureBoundingBox sbb, Random decoRNG, int rotation, int z, int y, int width, int depth) {
        if (decoRNG.nextInt(8) == 0) {
            this.fillBlocksRotated(world, sbb, this.size - (depth + 1), y - width, z - width, this.size - 1, y + width, z + width, Blocks.field_150343_Z, 0, rotation);
        } else if (decoRNG.nextInt(4) == 0) {
            this.fillBlocksRotated(world, sbb, this.size - (depth + 1), y - width, z - width, this.size - 1, y + width, z + width, TFBlocks.trollSteinn, 0, rotation);
        } else {
            this.fillBlocksRotated(world, sbb, this.size - (depth + 1), y - width, z - width, this.size - 1, y + width, z + width, Blocks.field_150348_b, 0, rotation);
        }

    }

    private void decorateStoneProjection(World world, StructureBoundingBox sbb, Random decoRNG, int rotation) {
        int z = 7 + decoRNG.nextInt(3) - decoRNG.nextInt(3);
        int y = 7 + decoRNG.nextInt(3) - decoRNG.nextInt(3);

        this.randomlyFillBlocksRotated(world, sbb, decoRNG, 0.25F, this.size - 9, y, z, this.size - 2, y + 3, z + 3, TFBlocks.trollSteinn, 0, Blocks.field_150348_b, 0, rotation);
        if (decoRNG.nextBoolean()) {
            this.randomlyFillBlocksRotated(world, sbb, decoRNG, 0.25F, this.size - 9, 1, z, this.size - 6, y - 1, z + 3, TFBlocks.trollSteinn, 0, Blocks.field_150348_b, 0, rotation);
        } else {
            this.randomlyFillBlocksRotated(world, sbb, decoRNG, 0.25F, this.size - 9, y + 4, z, this.size - 6, this.height - 2, z + 3, TFBlocks.trollSteinn, 0, Blocks.field_150348_b, 0, rotation);
        }

    }

    private void decorateBracketMushrooms(World world, StructureBoundingBox sbb, Random decoRNG, int rotation) {
        int z = 5 + decoRNG.nextInt(7);
        int startY = 1 + decoRNG.nextInt(4);

        for (int y = startY; y < this.height; y += 2) {
            int width = 1 + decoRNG.nextInt(2) + decoRNG.nextInt(2);
            int depth = 1 + decoRNG.nextInt(2) + decoRNG.nextInt(2);
            Block mushBlock = decoRNG.nextInt(3) == 0 ? TFBlocks.hugeGloomBlock : (decoRNG.nextBoolean() ? Blocks.field_150420_aW : Blocks.field_150419_aX);

            this.makeSingleBracketMushroom(world, sbb, rotation, z, y, width, depth, mushBlock);
            z += decoRNG.nextInt(4) - decoRNG.nextInt(4);
            if (z < 5 || z > this.size - 5) {
                z = 5 + decoRNG.nextInt(7);
            }
        }

    }

    private void makeSingleBracketMushroom(World world, StructureBoundingBox sbb, int rotation, int z, int y, int width, int depth, Block mushBlock) {
        this.fillBlocksRotated(world, sbb, this.size - depth, y, z - (width - 1), this.size - 2, y, z + (width - 1), mushBlock, 5, rotation);
        this.fillBlocksRotated(world, sbb, this.size - (depth + 1), y, z - (width - 1), this.size - (depth + 1), y, z + (width - 1), mushBlock, this.getMushroomMetaFor(4, rotation), rotation);

        int d;

        for (d = 0; d < depth - 1; ++d) {
            this.placeBlockRotated(world, mushBlock, this.getMushroomMetaFor(2, rotation), this.size - (2 + d), y, z - width, rotation, sbb);
        }

        this.placeBlockRotated(world, mushBlock, this.getMushroomMetaFor(1, rotation), this.size - (depth + 1), y, z - width, rotation, sbb);

        for (d = 0; d < depth - 1; ++d) {
            this.placeBlockRotated(world, mushBlock, this.getMushroomMetaFor(8, rotation), this.size - (2 + d), y, z + width, rotation, sbb);
        }

        this.placeBlockRotated(world, mushBlock, this.getMushroomMetaFor(7, rotation), this.size - (depth + 1), y, z + width, rotation, sbb);
    }

    private int getMushroomMetaFor(int meta, int rotation) {
        if (meta > 0 && meta < 10) {
            int totalRot = (this.field_74885_f + rotation) % 4;

            switch (totalRot) {
            case 0:
                return meta;

            case 1:
                switch (meta) {
                case 1:
                    return 3;

                case 2:
                    return 6;

                case 3:
                case 5:
                case 6:
                default:
                    break;

                case 4:
                    return 2;

                case 7:
                    return 1;

                case 8:
                    return 4;
                }

            case 2:
                return 10 - meta % 10;

            case 3:
                switch (meta) {
                case 1:
                    return 7;

                case 2:
                    return 4;

                case 3:
                case 5:
                case 6:
                default:
                    break;

                case 4:
                    return 8;

                case 7:
                    return 9;

                case 8:
                    return 6;
                }

            default:
                return 15;
            }
        } else {
            return meta;
        }
    }

    protected boolean makeGardenCave(List list, Random rand, int index, int x, int y, int z, int caveSize, int caveHeight, int rotation) {
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        ChunkCoordinates dest = this.offsetTowerCCoords(x, y, z, caveSize, direction);
        ComponentTFTrollCaveGarden cave = new ComponentTFTrollCaveGarden(index, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c, caveSize, caveHeight, direction);
        StructureComponent intersect = StructureComponent.func_74883_a(list, cave.func_74874_b());
        StructureComponent otherGarden = this.findNearbyGarden(list, cave.func_74874_b());

        if ((intersect == null || intersect == this) && otherGarden == null) {
            list.add(cave);
            cave.func_74861_a((StructureComponent) list.get(0), list, rand);
            this.openingTowards[rotation] = true;
            return true;
        } else {
            return false;
        }
    }

    private StructureComponent findNearbyGarden(List list, StructureBoundingBox boundingBox) {
        StructureBoundingBox largeBox = new StructureBoundingBox(boundingBox);

        largeBox.field_78897_a -= 30;
        largeBox.field_78895_b -= 30;
        largeBox.field_78896_c -= 30;
        largeBox.field_78893_d += 30;
        largeBox.field_78894_e += 30;
        largeBox.field_78892_f += 30;
        Iterator iterator = list.iterator();

        StructureComponent component;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            component = (StructureComponent) iterator.next();
        } while (!(component instanceof ComponentTFTrollCaveGarden) || !component.func_74874_b().func_78884_a(largeBox));

        return component;
    }

    protected boolean makeSmallerCave(List list, Random rand, int index, int x, int y, int z, int caveSize, int caveHeight, int rotation) {
        if (super.makeSmallerCave(list, rand, index, x, y, z, caveSize, caveHeight, rotation)) {
            this.openingTowards[rotation] = true;
            return true;
        } else {
            return false;
        }
    }
}
