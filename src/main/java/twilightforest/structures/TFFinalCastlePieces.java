package twilightforest.structures;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.TFFeature;
import twilightforest.biomes.TFBiomeBase;
import twilightforest.block.TFBlocks;
import twilightforest.structures.lichtower.ComponentTFTowerWing;

public class TFFinalCastlePieces {

    public static void registerFinalCastlePieces() {
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.Main.class, "TFFCMain");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.StairTower.class, "TFFCStTo");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.LargeTower.class, "TFFCLaTo");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.Foundation48.class, "TFFCToF48");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.Roof48Crenellated.class, "TFFCRo48Cr");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.BossGazebo.class, "TFFCBoGaz");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.MazeTower13.class, "TFFCSiTo");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.DungeonSteps.class, "TFFCDunSt");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.DungeonEntrance.class, "TFFCDunEn");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.DungeonRoom31.class, "TFFCDunR31");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.DungeonExit.class, "TFFCDunEx");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.DungeonBossRoom.class, "TFFCDunBoR");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.Roof9Crenellated.class, "TFFCRo9Cr");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.Roof13Crenellated.class, "TFFCRo13Cr");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.Roof13Conical.class, "TFFCRo13Con");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.Roof13Peaked.class, "TFFCRo13Pk");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.EntranceTower.class, "TFFCEnTo");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.EntranceSideTower.class, "TFFCEnSiTo");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.EntranceBottomTower.class, "TFFCEnBoTo");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.EntranceStairs.class, "TFFCEnSt");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.BellTower21.class, "TFFCBelTo");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.Bridge.class, "TFFCBri");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.Foundation13.class, "TFFCToF13");
        MapGenStructureIO.func_143031_a(TFFinalCastlePieces.BellFoundation21.class, "TFFCBeF21");
    }

    public static class BellFoundation21 extends TFFinalCastlePieces.Foundation13 {

        public BellFoundation21() {}

        public BellFoundation21(Random rand, int i, StructureTFComponent sideTower) {
            super(rand, i, sideTower);
            this.field_74887_e = new StructureBoundingBox(sideTower.func_74874_b().field_78897_a - 2, sideTower.func_74874_b().field_78894_e - 1, sideTower.func_74874_b().field_78896_c - 2, sideTower.func_74874_b().field_78893_d + 2, sideTower.func_74874_b().field_78894_e, sideTower.func_74874_b().field_78892_f + 2);
        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            if (this.groundLevel < 0) {
                this.groundLevel = this.getDeadrockLevel(world, sbb);
            }

            int i = this.field_74887_e.field_78894_e - this.groundLevel;
            byte mid = 16;
            byte low = 32;
            int size = this.field_74887_e.field_78893_d - this.field_74887_e.field_78897_a;

            for (int rotation = 0; rotation < 4; ++rotation) {
                this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 2, -1, 1, rotation, sbb);
                this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 2, -mid, 0, rotation, sbb);
                this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 1, -1, 2, rotation, sbb);
                this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 0, -mid, 2, rotation, sbb);
                this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 1, -low, 1, rotation, sbb);
                this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 0, -low, 1, rotation, sbb);
                this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 1, -low, 0, rotation, sbb);
                this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 0, -low, 0, rotation, sbb);

                for (int x = 6; x < size - 3; x += 4) {
                    this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, x, -1, 1, rotation, sbb);
                    this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, x, -mid, 0, rotation, sbb);
                }
            }

            return true;
        }
    }

    public static class Foundation13 extends StructureTFComponent {

        protected int groundLevel = -1;

        public Foundation13() {}

        public Foundation13(Random rand, int i, StructureTFComponent sideTower) {
            super(i);
            this.setCoordBaseMode(sideTower.getCoordBaseMode());
            this.field_74887_e = new StructureBoundingBox(sideTower.func_74874_b().field_78897_a - 2, sideTower.func_74874_b().field_78895_b - 1, sideTower.func_74874_b().field_78896_c - 2, sideTower.func_74874_b().field_78893_d + 2, sideTower.func_74874_b().field_78895_b, sideTower.func_74874_b().field_78892_f + 2);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            if (this.groundLevel < 0) {
                this.groundLevel = this.getDeadrockLevel(world, sbb);
                if (this.groundLevel < 0) {
                    return true;
                }
            }

            int height = this.field_74887_e.field_78894_e - this.groundLevel;
            int mid = height / 2;
            int size = this.field_74887_e.field_78893_d - this.field_74887_e.field_78897_a;

            for (int rotation = 0; rotation < 4; ++rotation) {
                this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 1, -1, 1, rotation, sbb);
                this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 2, -1, 1, rotation, sbb);
                this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 2, -mid, 0, rotation, sbb);
                this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 1, -1, 2, rotation, sbb);
                this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 0, -mid, 2, rotation, sbb);

                for (int x = 6; x < size - 3; x += 4) {
                    this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, x, -1, 1, rotation, sbb);
                    this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, x, -mid, 0, rotation, sbb);
                }
            }

            return true;
        }

        protected int getDeadrockLevel(World world, StructureBoundingBox sbb) {
            int groundLevel = 256;

            for (int y = 150; y > 0; --y) {
                int cx = sbb.func_78881_e();
                int cz = sbb.func_78891_g();
                Block block = world.func_147439_a(cx, y, cz);

                if (block == TFBlocks.deadrock) {
                    groundLevel = y;
                    break;
                }
            }

            return groundLevel;
        }
    }

    public static class Roof13Peaked extends StructureTFComponent {

        public Roof13Peaked() {}

        public Roof13Peaked(Random rand, int i, StructureTFComponent sideTower) {
            super(i);
            byte height = 18;

            this.setCoordBaseMode(sideTower.getCoordBaseMode());
            this.field_74887_e = new StructureBoundingBox(sideTower.func_74874_b().field_78897_a - 2, sideTower.func_74874_b().field_78894_e - 1, sideTower.func_74874_b().field_78896_c - 2, sideTower.func_74874_b().field_78893_d + 2, sideTower.func_74874_b().field_78894_e + height - 1, sideTower.func_74874_b().field_78892_f + 2);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            int rotation;

            for (rotation = 0; rotation < 3; ++rotation) {
                this.func_151556_a(world, sbb, 1, rotation, rotation, 15, rotation, rotation, this.deco.roofID, this.deco.roofMeta, this.deco.roofID, this.deco.roofMeta, false);
                this.func_151556_a(world, sbb, 1, rotation, 16 - rotation, 15, rotation, 16 - rotation, this.deco.roofID, this.deco.roofMeta, this.deco.roofID, this.deco.roofMeta, false);
            }

            int i;

            for (rotation = 0; rotation < 3; ++rotation) {
                i = 3 + rotation;
                this.func_151556_a(world, sbb, 2, 5 + (rotation - 1) * 2, i, 14, 4 + rotation * 2, i, this.deco.roofID, this.deco.roofMeta, this.deco.roofID, this.deco.roofMeta, false);
                this.func_151556_a(world, sbb, 1, 1, i, 1, 5 + (rotation - 1) * 2, i, this.deco.roofID, this.deco.roofMeta, this.deco.roofID, this.deco.roofMeta, false);
                this.func_151556_a(world, sbb, 1, 5 + (rotation - 1) * 2, i - 1, 1, 4 + rotation * 2, i, this.deco.blockID, this.deco.blockMeta, this.deco.blockID, this.deco.blockMeta, false);
                this.func_151556_a(world, sbb, 15, 1, i, 15, 5 + (rotation - 1) * 2, i, this.deco.roofID, this.deco.roofMeta, this.deco.roofID, this.deco.roofMeta, false);
                this.func_151556_a(world, sbb, 15, 5 + (rotation - 1) * 2, i - 1, 15, 4 + rotation * 2, i, this.deco.blockID, this.deco.blockMeta, this.deco.blockID, this.deco.blockMeta, false);
                i = 13 - rotation;
                this.func_151556_a(world, sbb, 2, 5 + (rotation - 1) * 2, i, 14, 4 + rotation * 2, i, this.deco.roofID, this.deco.roofMeta, this.deco.roofID, this.deco.roofMeta, false);
                this.func_151556_a(world, sbb, 1, 1, i, 1, 5 + (rotation - 1) * 2, i, this.deco.roofID, this.deco.roofMeta, this.deco.roofID, this.deco.roofMeta, false);
                this.func_151556_a(world, sbb, 1, 5 + (rotation - 1) * 2, i, 1, 4 + rotation * 2, i + 1, this.deco.blockID, this.deco.blockMeta, this.deco.blockID, this.deco.blockMeta, false);
                this.func_151556_a(world, sbb, 15, 1, i, 15, 5 + (rotation - 1) * 2, i, this.deco.roofID, this.deco.roofMeta, this.deco.roofID, this.deco.roofMeta, false);
                this.func_151556_a(world, sbb, 15, 5 + (rotation - 1) * 2, i, 15, 4 + rotation * 2, i + 1, this.deco.blockID, this.deco.blockMeta, this.deco.blockID, this.deco.blockMeta, false);
            }

            for (rotation = 0; rotation < 3; ++rotation) {
                i = 6 + rotation;
                this.func_151556_a(world, sbb, 2, 12 + (rotation - 1) * 3, i, 14, 11 + rotation * 3, i, this.deco.roofID, this.deco.roofMeta, this.deco.roofID, this.deco.roofMeta, false);
                this.func_151556_a(world, sbb, 1, 1, i, 1, 12 + (rotation - 1) * 3, i, this.deco.roofID, this.deco.roofMeta, this.deco.roofID, this.deco.roofMeta, false);
                this.func_151556_a(world, sbb, 1, 12 + (rotation - 1) * 3, i - 1, 1, 11 + rotation * 3, i, this.deco.blockID, this.deco.blockMeta, this.deco.blockID, this.deco.blockMeta, false);
                this.func_151556_a(world, sbb, 15, 1, i, 15, 12 + (rotation - 1) * 3, i, this.deco.roofID, this.deco.roofMeta, this.deco.roofID, this.deco.roofMeta, false);
                this.func_151556_a(world, sbb, 15, 12 + (rotation - 1) * 3, i - 1, 15, 11 + rotation * 3, i, this.deco.blockID, this.deco.blockMeta, this.deco.blockID, this.deco.blockMeta, false);
                i = 10 - rotation;
                this.func_151556_a(world, sbb, 2, 12 + (rotation - 1) * 3, i, 14, 11 + rotation * 3, i, this.deco.roofID, this.deco.roofMeta, this.deco.roofID, this.deco.roofMeta, false);
                this.func_151556_a(world, sbb, 1, 1, i, 1, 12 + (rotation - 1) * 3, i, this.deco.roofID, this.deco.roofMeta, this.deco.roofID, this.deco.roofMeta, false);
                this.func_151556_a(world, sbb, 1, 12 + (rotation - 1) * 3, i, 1, 11 + rotation * 3, i + 1, this.deco.blockID, this.deco.blockMeta, this.deco.blockID, this.deco.blockMeta, false);
                this.func_151556_a(world, sbb, 15, 1, i, 15, 12 + (rotation - 1) * 3, i, this.deco.roofID, this.deco.roofMeta, this.deco.roofID, this.deco.roofMeta, false);
                this.func_151556_a(world, sbb, 15, 12 + (rotation - 1) * 3, i, 15, 11 + rotation * 3, i + 1, this.deco.blockID, this.deco.blockMeta, this.deco.blockID, this.deco.blockMeta, false);
            }

            this.func_151556_a(world, sbb, 1, 18, 8, 5, 18, 8, this.deco.roofID, this.deco.roofMeta, this.deco.roofID, this.deco.roofMeta, false);
            this.func_151556_a(world, sbb, 11, 18, 8, 14, 18, 8, this.deco.roofID, this.deco.roofMeta, this.deco.roofID, this.deco.roofMeta, false);
            this.func_151556_a(world, sbb, 0, 17, 8, 1, 19, 8, this.deco.roofID, this.deco.roofMeta, this.deco.roofID, this.deco.roofMeta, false);
            this.func_151556_a(world, sbb, 15, 17, 8, 16, 19, 8, this.deco.roofID, this.deco.roofMeta, this.deco.roofID, this.deco.roofMeta, false);

            for (rotation = 1; rotation < 4; rotation += 2) {
                this.fillBlocksRotated(world, sbb, 4, 0, 1, 12, 1, 1, this.deco.blockID, this.deco.blockMeta, rotation);

                for (i = 3; i < 13; i += 2) {
                    this.fillBlocksRotated(world, sbb, i, -1, 1, i, 2, 1, this.deco.blockID, this.deco.blockMeta, rotation);
                }
            }

            for (rotation = 0; rotation < 4; ++rotation) {
                this.fillBlocksRotated(world, sbb, 0, -1, 0, 3, 2, 3, this.deco.blockID, this.deco.blockMeta, rotation);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 1, -2, 2, rotation, sbb);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 1, -2, 1, rotation, sbb);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 2, -2, 1, rotation, sbb);
            }

            return true;
        }
    }

    public static class Roof13Crenellated extends StructureTFComponent {

        public Roof13Crenellated() {}

        public Roof13Crenellated(Random rand, int i, StructureTFComponent sideTower) {
            super(i);
            byte height = 5;

            this.setCoordBaseMode(sideTower.getCoordBaseMode());
            this.field_74887_e = new StructureBoundingBox(sideTower.func_74874_b().field_78897_a - 2, sideTower.func_74874_b().field_78894_e - 1, sideTower.func_74874_b().field_78896_c - 2, sideTower.func_74874_b().field_78893_d + 2, sideTower.func_74874_b().field_78894_e + height - 1, sideTower.func_74874_b().field_78892_f + 2);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            int size = this.field_74887_e.field_78893_d - this.field_74887_e.field_78897_a;

            for (int rotation = 0; rotation < 4; ++rotation) {
                this.fillBlocksRotated(world, sbb, 0, -1, 0, 3, 3, 3, this.deco.blockID, this.deco.blockMeta, rotation);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 1, -2, 2, rotation, sbb);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 1, -2, 1, rotation, sbb);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 2, -2, 1, rotation, sbb);
                this.fillBlocksRotated(world, sbb, 4, 0, 1, size - 4, 1, 1, this.deco.blockID, this.deco.blockMeta, rotation);

                for (int x = 5; x < size - 5; x += 4) {
                    this.fillBlocksRotated(world, sbb, x, 0, 0, x + 2, 3, 2, this.deco.blockID, this.deco.blockMeta, rotation);
                    this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, x + 1, -1, 1, rotation, sbb);
                    this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, x + 1, -2, 1, rotation, sbb);
                }
            }

            return true;
        }
    }

    public static class Roof13Conical extends StructureTFComponent {

        public int slope;

        public Roof13Conical() {}

        public Roof13Conical(Random rand, int i, StructureTFComponent sideTower) {
            super(i);
            this.slope = 2 + rand.nextInt(3) + rand.nextInt(3);
            int height = this.slope * 4;

            this.setCoordBaseMode(sideTower.getCoordBaseMode());
            this.field_74887_e = new StructureBoundingBox(sideTower.func_74874_b().field_78897_a - 2, sideTower.func_74874_b().field_78894_e - 1, sideTower.func_74874_b().field_78896_c - 2, sideTower.func_74874_b().field_78893_d + 2, sideTower.func_74874_b().field_78894_e + height - 1, sideTower.func_74874_b().field_78892_f + 2);
        }

        protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
            super.func_143012_a(par1NBTTagCompound);
            par1NBTTagCompound.func_74768_a("slope", this.slope);
        }

        protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
            super.func_143011_b(par1NBTTagCompound);
            this.slope = par1NBTTagCompound.func_74762_e("slope");
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            for (int rotation = 0; rotation < 4; ++rotation) {
                this.fillBlocksRotated(world, sbb, 0, -1, 0, 3, 2, 3, this.deco.blockID, this.deco.blockMeta, rotation);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 1, -2, 2, rotation, sbb);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 1, -2, 1, rotation, sbb);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 2, -2, 1, rotation, sbb);
                this.fillBlocksRotated(world, sbb, 4, 0, 1, 12, 1, 1, this.deco.blockID, this.deco.blockMeta, rotation);

                int i;

                for (i = 3; i < 13; i += 2) {
                    this.fillBlocksRotated(world, sbb, i, -1, 1, i, 2, 1, this.deco.blockID, this.deco.blockMeta, rotation);
                }

                for (i = 2; i < 9; ++i) {
                    int base = 2 - this.slope;

                    if (i < 7) {
                        this.fillBlocksRotated(world, sbb, i - 1, (i - 1) * this.slope + base, i - 1, i, i * this.slope + base - 1, i, this.deco.blockID, this.deco.blockMeta, rotation);
                    } else {
                        this.fillBlocksRotated(world, sbb, 16 - i, (i - 1) * this.slope + base, i, 16 - i, i * this.slope + base - 1, i, this.deco.roofID, this.deco.roofMeta, rotation);
                    }

                    this.fillBlocksRotated(world, sbb, i + 1, (i - 1) * this.slope + base, i, 15 - i, i * this.slope + base - 1, i, this.deco.roofID, this.deco.roofMeta, rotation);
                }

                this.fillBlocksRotated(world, sbb, 8, this.slope * 6 + 2, 8, 8, this.slope * 7 + 2, 8, this.deco.roofID, this.deco.roofMeta, rotation);
            }

            return true;
        }
    }

    public static class Roof9Crenellated extends StructureTFComponent {

        public Roof9Crenellated() {}

        public Roof9Crenellated(Random rand, int i, StructureTFComponent sideTower) {
            super(i);
            byte height = 5;

            this.setCoordBaseMode(sideTower.getCoordBaseMode());
            this.field_74887_e = new StructureBoundingBox(sideTower.func_74874_b().field_78897_a - 2, sideTower.func_74874_b().field_78894_e - 1, sideTower.func_74874_b().field_78896_c - 2, sideTower.func_74874_b().field_78893_d + 2, sideTower.func_74874_b().field_78894_e + height - 1, sideTower.func_74874_b().field_78892_f + 2);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            for (int rotation = 0; rotation < 4; ++rotation) {
                this.fillBlocksRotated(world, sbb, 0, -1, 0, 2, 3, 2, this.deco.blockID, this.deco.blockMeta, rotation);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 1, -2, 2, rotation, sbb);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 1, -2, 1, rotation, sbb);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 2, -2, 1, rotation, sbb);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 3, 0, 1, rotation, sbb);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 3, 1, 1, rotation, sbb);
                this.fillBlocksRotated(world, sbb, 4, 0, 0, 5, 3, 2, this.deco.blockID, this.deco.blockMeta, rotation);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 6, 0, 1, rotation, sbb);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 6, 1, 1, rotation, sbb);
                this.fillBlocksRotated(world, sbb, 7, 0, 0, 8, 3, 2, this.deco.blockID, this.deco.blockMeta, rotation);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 9, 0, 1, rotation, sbb);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 9, 1, 1, rotation, sbb);
            }

            return true;
        }
    }

    public static class Bridge extends StructureTFComponent {

        public Bridge() {}

        public Bridge(int i, int x, int y, int z, int length, int direction) {
            this.setCoordBaseMode(direction);
            this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox2(x, y, z, 0, -1, -3, length - 1, 5, 6, direction);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            int length = this.field_74885_f != 0 && this.field_74885_f != 2 ? this.field_74887_e.field_78892_f - this.field_74887_e.field_78896_c : this.field_74887_e.field_78893_d - this.field_74887_e.field_78897_a;

            this.func_74882_a(world, sbb, 0, 0, 0, length, 1, 6, false, rand, this.deco.randomBlocks);
            this.func_74882_a(world, sbb, 0, 1, 0, length, 2, 0, false, rand, this.deco.randomBlocks);
            this.func_74882_a(world, sbb, 0, 1, 6, length, 2, 6, false, rand, this.deco.randomBlocks);
            int l3 = length / 3;

            for (int i = 0; i < l3; ++i) {
                int sl = l3 - (int) (MathHelper.func_76134_b((float) (l3 - i) / (float) l3 * 1.6F) * (float) l3);

                this.func_74882_a(world, sbb, i, -sl, 0, i, 0, 0, false, rand, this.deco.randomBlocks);
                this.func_74882_a(world, sbb, i, -sl, 6, i, 0, 6, false, rand, this.deco.randomBlocks);
                this.func_74882_a(world, sbb, length - i, -sl, 0, length - i, 0, 0, false, rand, this.deco.randomBlocks);
                this.func_74882_a(world, sbb, length - i, -sl, 6, length - i, 0, 6, false, rand, this.deco.randomBlocks);
            }

            this.func_151556_a(world, sbb, 0, 2, 1, 0, 7, 1, this.deco.pillarID, this.deco.pillarMeta, this.deco.pillarID, this.deco.pillarMeta, false);
            this.func_151556_a(world, sbb, 0, 2, 5, 0, 7, 5, this.deco.pillarID, this.deco.pillarMeta, this.deco.pillarID, this.deco.pillarMeta, false);
            this.func_151556_a(world, sbb, 0, 6, 2, 0, 6, 4, this.deco.accentID, this.deco.accentMeta, this.deco.accentID, this.deco.accentMeta, false);
            this.func_151550_a(world, this.deco.pillarID, this.deco.pillarMeta, 0, 7, 3, sbb);
            this.func_151556_a(world, sbb, length, 2, 1, length, 7, 1, this.deco.pillarID, this.deco.pillarMeta, this.deco.pillarID, this.deco.pillarMeta, false);
            this.func_151556_a(world, sbb, length, 2, 5, length, 7, 5, this.deco.pillarID, this.deco.pillarMeta, this.deco.pillarID, this.deco.pillarMeta, false);
            this.func_151556_a(world, sbb, length, 6, 2, length, 6, 4, this.deco.accentID, this.deco.accentMeta, this.deco.accentID, this.deco.accentMeta, false);
            this.func_151550_a(world, this.deco.pillarID, this.deco.pillarMeta, length, 7, 3, sbb);
            return true;
        }
    }

    public static class BellTower21 extends TFFinalCastlePieces.MazeTower13 {

        private static final int FLOORS = 8;

        public BellTower21() {}

        public BellTower21(Random rand, int i, int x, int y, int z, int direction) {
            super(rand, i, x, y, z, 8, 1, 1, direction);
            this.size = 21;
            byte floors = 8;

            this.height = floors * 8 + 1;
            this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox2(x, y, z, -6, -8, -this.size / 2, this.size - 1, this.height, this.size - 1, direction);
            this.openings.clear();
            this.addOpening(0, 9, this.size / 2, 2);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

            TFFinalCastlePieces.BellFoundation21 foundation = new TFFinalCastlePieces.BellFoundation21(rand, 4, this);

            list.add(foundation);
            foundation.func_74861_a(this, list, rand);
            TFFinalCastlePieces.Roof13Crenellated roof = new TFFinalCastlePieces.Roof13Crenellated(rand, 4, this);

            list.add(roof);
            roof.func_74861_a(this, list, rand);
        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            super.func_74875_a(world, rand, sbb);
            Block fieldBlock = TFBlocks.forceField;
            byte fieldMeta = 4;

            for (int rotation = 0; rotation < 4; ++rotation) {
                byte y = 48;

                int x;

                for (x = 5; x < this.size - 4; x += 2) {
                    this.fillBlocksRotated(world, sbb, x, y, 0, x, y + 14, 0, fieldBlock, fieldMeta, rotation);
                }

                y = 24;

                for (x = 1; x < this.size - 1; x += 8) {
                    this.fillBlocksRotated(world, sbb, x, y, 0, x, y + 14, 0, fieldBlock, fieldMeta, rotation);
                    this.fillBlocksRotated(world, sbb, x + 2, y, 0, x + 2, y + 14, 0, fieldBlock, fieldMeta, rotation);
                }
            }

            this.placeSignAtCurrentPosition(world, 7, 9, 8, "Parkour area 2", "mini-boss 1", sbb);
            return true;
        }
    }

    public static class EntranceStairs extends StructureTFComponent {

        public EntranceStairs() {}

        public EntranceStairs(int index, int x, int y, int z, int direction) {
            this.setCoordBaseMode(direction);
            this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox2(x, y, z, 0, -1, -5, 12, 0, 12, direction);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            byte size = 13;

            for (int x = 1; x < size; ++x) {
                this.placeStairs(world, sbb, x, 1 - x, 5, 2);

                for (int z = 0; z <= x; ++z) {
                    if (z > 0 && z <= size / 2) {
                        this.placeStairs(world, sbb, x, 1 - x, 5 - z, 2);
                        this.placeStairs(world, sbb, x, 1 - x, 5 + z, 2);
                    }

                    if (x <= size / 2) {
                        this.placeStairs(world, sbb, z, 1 - x, 5 - x, 1);
                        this.placeStairs(world, sbb, z, 1 - x, 5 + x, 3);
                    }
                }
            }

            this.func_151554_b(world, this.deco.blockID, this.deco.blockMeta, 0, 0, 5, sbb);
            return true;
        }

        private void placeStairs(World world, StructureBoundingBox sbb, int x, int y, int z, int stairMeta) {
            if (this.func_151548_a(world, x, y, z, sbb).isReplaceable(world, x, y, z)) {
                this.func_151550_a(world, this.deco.stairID, this.getStairMeta(stairMeta), x, y, z, sbb);
                this.func_151554_b(world, this.deco.blockID, this.deco.blockMeta, x, y - 1, z, sbb);
            }

        }
    }

    public static class EntranceBottomTower extends TFFinalCastlePieces.MazeTower13 {

        public EntranceBottomTower() {}

        public EntranceBottomTower(Random rand, int i, int x, int y, int z, int floors, int entranceFloor, int direction) {
            super(rand, i, x, y, z, floors, entranceFloor, 0, direction);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

            this.addStairs(list, rand, this.func_74877_c() + 1, this.size - 1, 1, this.size / 2, 0);
            this.addStairs(list, rand, this.func_74877_c() + 1, 0, 1, this.size / 2, 2);
            this.addStairs(list, rand, this.func_74877_c() + 1, this.size / 2, 1, 0, 3);
            this.addStairs(list, rand, this.func_74877_c() + 1, this.size / 2, 1, this.size - 1, 1);
        }

        private boolean addStairs(List list, Random rand, int index, int x, int y, int z, int rotation) {
            this.addOpening(x, y, z, rotation);
            int direction = (this.getCoordBaseMode() + rotation) % 4;
            ChunkCoordinates dx = this.offsetTowerCCoords(x, y, z, 0, direction);
            TFFinalCastlePieces.EntranceStairs stairs = new TFFinalCastlePieces.EntranceStairs(index, dx.field_71574_a, dx.field_71572_b, dx.field_71573_c, direction);

            list.add(stairs);
            stairs.func_74861_a((StructureComponent) list.get(0), list, rand);
            return true;
        }

        protected boolean hasAccessibleRoof() {
            return false;
        }
    }

    public static class EntranceSideTower extends TFFinalCastlePieces.MazeTower13 {

        public EntranceSideTower() {}

        public EntranceSideTower(Random rand, int i, int x, int y, int z, int floors, int entranceFloor, int direction) {
            super(rand, i, x, y, z, floors, entranceFloor, 0, direction);
            this.addOpening(0, 1, this.size / 2, 2);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

            TFFinalCastlePieces.Foundation13 foundation = new TFFinalCastlePieces.Foundation13(rand, 4, this);

            list.add(foundation);
            foundation.func_74861_a(this, list, rand);
            TFFinalCastlePieces.Roof13Peaked roof = new TFFinalCastlePieces.Roof13Peaked(rand, 4, this);

            list.add(roof);
            roof.func_74861_a(this, list, rand);
        }
    }

    public static class EntranceTower extends TFFinalCastlePieces.MazeTower13 {

        public EntranceTower() {}

        public EntranceTower(Random rand, int i, int x, int y, int z, int direction) {
            super(rand, i, x, y, z, 3, 2, 0, direction);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

            TFFinalCastlePieces.Foundation13 foundation = new TFFinalCastlePieces.Foundation13(rand, 4, this);

            list.add(foundation);
            foundation.func_74861_a(this, list, rand);
            TFFinalCastlePieces.Roof13Peaked roof = new TFFinalCastlePieces.Roof13Peaked(rand, 4, this);

            list.add(roof);
            roof.func_74861_a(this, list, rand);
            int missingFloors = (this.field_74887_e.field_78895_b - 127) / 8;
            int bottomFloors = missingFloors / 2;
            int middleFloors = missingFloors - bottomFloors;
            byte direction = 1;
            byte howFar = 20;

            if (!this.buildSideTower(list, rand, middleFloors + 1, direction, howFar)) {
                direction = 3;
                if (!this.buildSideTower(list, rand, middleFloors + 1, direction, howFar)) {
                    direction = 0;
                    if (!this.buildSideTower(list, rand, middleFloors + 1, direction, howFar)) {
                        ;
                    }
                }
            }

            int brDirection = (direction + this.field_74885_f) % 4;
            TFFinalCastlePieces.EntranceBottomTower eTower = new TFFinalCastlePieces.EntranceBottomTower(rand, this.func_74877_c() + 1, this.field_74887_e.field_78897_a + 6, this.field_74887_e.field_78895_b - middleFloors * 8, this.field_74887_e.field_78896_c + 6, bottomFloors + 1, bottomFloors, (brDirection + 2) % 4);

            list.add(eTower);
            eTower.func_74861_a(this, list, rand);
            ChunkCoordinates opening = this.getValidOpeningCC(rand, direction);

            opening.field_71572_b -= middleFloors * 8;
            ChunkCoordinates bc = this.offsetTowerCCoords(opening.field_71574_a, opening.field_71572_b, opening.field_71573_c, 1, brDirection);
            TFFinalCastlePieces.Bridge bridge = new TFFinalCastlePieces.Bridge(this.func_74877_c() + 1, bc.field_71574_a, bc.field_71572_b, bc.field_71573_c, howFar - 7, brDirection);

            list.add(bridge);
            bridge.func_74861_a(this, list, rand);
        }

        private boolean buildSideTower(List list, Random rand, int middleFloors, int direction, int howFar) {
            ChunkCoordinates opening = this.getValidOpeningCC(rand, direction);

            direction += this.field_74885_f;
            direction %= 4;
            ChunkCoordinates tc = this.offsetTowerCCoords(opening.field_71574_a, opening.field_71572_b, opening.field_71573_c, howFar, direction);
            TFFinalCastlePieces.EntranceSideTower eTower = new TFFinalCastlePieces.EntranceSideTower(rand, this.func_74877_c() + 1, tc.field_71574_a, tc.field_71572_b, tc.field_71573_c, middleFloors, middleFloors - 1, direction);
            StructureBoundingBox largerBB = new StructureBoundingBox(eTower.func_74874_b());

            largerBB.field_78897_a -= 6;
            largerBB.field_78896_c -= 6;
            largerBB.field_78893_d += 6;
            largerBB.field_78892_f += 6;
            StructureComponent intersect = StructureComponent.func_74883_a(list, largerBB);

            if (intersect == null) {
                list.add(eTower);
                eTower.func_74861_a(this, list, rand);
                ChunkCoordinates bc = this.offsetTowerCCoords(opening.field_71574_a, opening.field_71572_b, opening.field_71573_c, 1, direction);
                TFFinalCastlePieces.Bridge bridge = new TFFinalCastlePieces.Bridge(this.func_74877_c() + 1, bc.field_71574_a, bc.field_71572_b, bc.field_71573_c, howFar - 7, direction);

                list.add(bridge);
                bridge.func_74861_a(this, list, rand);
                this.addOpening(opening.field_71574_a, opening.field_71572_b + 1, opening.field_71573_c, direction);
                return true;
            } else {
                System.out.println("side entrance tower blocked");
                return false;
            }
        }

        public ChunkCoordinates getValidOpeningCC(Random rand, int direction) {
            byte ry;

            if (direction != 0 && direction != 2) {
                if (direction != 1 && direction != 3) {
                    return new ChunkCoordinates(0, 0, 0);
                } else {
                    byte rx1 = 6;
                    int rz1 = direction == 1 ? 12 : 0;

                    ry = 0;
                    return new ChunkCoordinates(rx1, ry, rz1);
                }
            } else {
                int rx = direction == 0 ? 12 : 0;
                byte rz = 6;

                ry = 0;
                return new ChunkCoordinates(rx, ry, rz);
            }
        }
    }

    public static class MazeTower13 extends ComponentTFTowerWing {

        public static final int LOWEST_DOOR = 144;
        public static final int HIGHEST_DOOR = 222;
        public int type;

        public MazeTower13() {}

        public MazeTower13(Random rand, int i, int x, int y, int z, int type, int direction) {
            super(i);
            this.setCoordBaseMode(direction);
            this.type = type;
            this.size = 13;
            int floors = rand.nextInt(3) + 2;

            this.height = floors * 8 + 1;
            int entranceFloor = rand.nextInt(floors);

            if (y - entranceFloor * 8 < 144) {
                entranceFloor = 0;
            }

            if (y + (floors - entranceFloor) * 8 > 222) {
                entranceFloor = floors - 1;
            }

            this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, -6, 0 - entranceFloor * 8, -6, this.size - 1, this.height, this.size - 1, 0);
            this.addOpening(0, entranceFloor * 8 + 1, this.size / 2, 2);
        }

        public MazeTower13(Random rand, int i, int x, int y, int z, int floors, int entranceFloor, int type, int direction) {
            super(i);
            this.setCoordBaseMode(direction);
            this.type = type;
            this.size = 13;
            this.height = floors * 8 + 1;
            this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, -6, 0 - entranceFloor * 8, -6, this.size - 1, this.height, this.size - 1, 0);
            this.addOpening(0, entranceFloor * 8 + 1, this.size / 2, 2);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

            TFFinalCastlePieces.Foundation13 foundation = new TFFinalCastlePieces.Foundation13(rand, 4, this);

            list.add(foundation);
            foundation.func_74861_a(this, list, rand);
            Object roof = rand.nextBoolean() ? new TFFinalCastlePieces.Roof13Conical(rand, 4, this) : new TFFinalCastlePieces.Roof13Crenellated(rand, 4, this);

            list.add(roof);
            ((StructureTFComponent) roof).func_74861_a(this, list, rand);
        }

        public void buildTowards(StructureComponent parent, List list, Random rand, ChunkCoordinates dest) {
            this.func_74861_a(parent, list, rand);
            if (this.func_74877_c() < 15) {
                if (this.isWithinRange(dest.field_71574_a, dest.field_71573_c, this.field_74887_e.field_78897_a + 6, this.field_74887_e.field_78896_c + 6, 30)) {
                    byte howFar = 20;

                    if (!this.buildEndTowerTowards(list, rand, dest, this.findBestDirectionTowards(dest), howFar) && !this.buildEndTowerTowards(list, rand, dest, this.findSecondDirectionTowards(dest), howFar) && !this.buildEndTowerTowards(list, rand, dest, this.findThirdDirectionTowards(dest), howFar)) {
                        ;
                    }
                } else {
                    int howFar1 = 14 + rand.nextInt(24);
                    int direction = this.findBestDirectionTowards(dest);

                    if (direction == 0 || !this.buildTowerTowards(list, rand, dest, direction, howFar1)) {
                        direction = this.findSecondDirectionTowards(dest);
                        if (direction == 0 || !this.buildTowerTowards(list, rand, dest, direction, howFar1)) {
                            direction = this.findThirdDirectionTowards(dest);
                            if ((direction == 0 || !this.buildTowerTowards(list, rand, dest, direction, howFar1)) && !this.buildTowerTowards(list, rand, dest, 0, howFar1)) {
                                ;
                            }
                        }
                    }
                }
            }

        }

        private int findBestDirectionTowards(ChunkCoordinates dest) {
            int cx = this.field_74887_e.field_78897_a + 6;
            int cz = this.field_74887_e.field_78896_c + 6;
            int dx = cx - dest.field_71574_a;
            int dz = cz - dest.field_71573_c;
            boolean absoluteDir = false;
            int absoluteDir1;

            if (Math.abs(dx) > Math.abs(dz)) {
                absoluteDir1 = dx >= 0 ? 2 : 0;
            } else {
                absoluteDir1 = dz >= 0 ? 3 : 1;
            }

            int relativeDir = (absoluteDir1 + 4 - this.field_74885_f) % 4;

            return relativeDir;
        }

        private int findSecondDirectionTowards(ChunkCoordinates dest) {
            int cx = this.field_74887_e.field_78897_a + 6;
            int cz = this.field_74887_e.field_78896_c + 6;
            int dx = cx - dest.field_71574_a;
            int dz = cz - dest.field_71573_c;
            boolean absoluteDir = false;
            int absoluteDir1;

            if (Math.abs(dx) < Math.abs(dz)) {
                absoluteDir1 = dx >= 0 ? 2 : 0;
            } else {
                absoluteDir1 = dz >= 0 ? 3 : 1;
            }

            int relativeDir = (absoluteDir1 + 4 - this.field_74885_f) % 4;

            return relativeDir;
        }

        private int findThirdDirectionTowards(ChunkCoordinates dest) {
            int first = this.findBestDirectionTowards(dest);
            int second = this.findSecondDirectionTowards(dest);

            return first == 0 && second == 1 ? 3 : (first == 1 && second == 3 ? 0 : 1);
        }

        private boolean buildTowerTowards(List list, Random rand, ChunkCoordinates dest, int direction, int howFar) {
            ChunkCoordinates opening = this.getValidOpeningCC(rand, direction);
            byte adjustmentRange = 60;

            if (this.isWithinRange(dest.field_71574_a, dest.field_71573_c, this.field_74887_e.field_78897_a + 6, this.field_74887_e.field_78896_c + 6, adjustmentRange)) {
                opening.field_71572_b = this.adjustOpening(opening.field_71572_b, dest);
            }

            direction += this.field_74885_f;
            direction %= 4;
            ChunkCoordinates tc = this.offsetTowerCCoords(opening.field_71574_a, opening.field_71572_b, opening.field_71573_c, howFar, direction);
            StructureComponent start = (StructureComponent) list.get(0);
            int centerX = start.func_74874_b().field_78897_a + 128 >> 8 << 8;
            int centerZ = start.func_74874_b().field_78896_c + 128 >> 8 << 8;

            if (this.isWithinRange(centerX, centerZ, tc.field_71574_a, tc.field_71573_c, 128)) {
                TFFinalCastlePieces.MazeTower13 sTower = new TFFinalCastlePieces.MazeTower13(rand, this.func_74877_c() + 1, tc.field_71574_a, tc.field_71572_b, tc.field_71573_c, this.type, direction);
                StructureBoundingBox largerBB = new StructureBoundingBox(sTower.func_74874_b());

                largerBB.field_78897_a -= 6;
                largerBB.field_78896_c -= 6;
                largerBB.field_78893_d += 6;
                largerBB.field_78892_f += 6;
                largerBB.field_78895_b = 0;
                largerBB.field_78894_e = 255;
                StructureComponent intersect = StructureComponent.func_74883_a(list, largerBB);

                if (intersect == null) {
                    list.add(sTower);
                    sTower.buildTowards(this, list, rand, dest);
                    ChunkCoordinates bc = this.offsetTowerCCoords(opening.field_71574_a, opening.field_71572_b, opening.field_71573_c, 1, direction);
                    TFFinalCastlePieces.Bridge bridge = new TFFinalCastlePieces.Bridge(this.func_74877_c() + 1, bc.field_71574_a, bc.field_71572_b, bc.field_71573_c, howFar - 7, direction);

                    list.add(bridge);
                    bridge.func_74861_a(this, list, rand);
                    this.addOpening(opening.field_71574_a, opening.field_71572_b + 1, opening.field_71573_c, direction);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        private int adjustOpening(int posY, ChunkCoordinates dest) {
            int openY = posY;
            int realOpeningY = this.func_74862_a(posY);

            if (realOpeningY - dest.field_71572_b < 12) {
                openY = this.height - 9;
            } else if (dest.field_71572_b - realOpeningY < 12) {
                openY = 0;
            }

            return openY;
        }

        private boolean buildEndTowerTowards(List list, Random rand, ChunkCoordinates dest, int direction, int howFar) {
            ChunkCoordinates opening = this.getValidOpeningCC(rand, direction);

            opening.field_71572_b = this.adjustOpening(opening.field_71572_b, dest);
            direction += this.field_74885_f;
            direction %= 4;
            ChunkCoordinates tc = this.offsetTowerCCoords(opening.field_71574_a, opening.field_71572_b, opening.field_71573_c, howFar, direction);
            Object eTower;

            if (this.type == 0) {
                eTower = new TFFinalCastlePieces.EntranceTower(rand, this.func_74877_c() + 1, tc.field_71574_a, tc.field_71572_b, tc.field_71573_c, direction);
            } else {
                eTower = new TFFinalCastlePieces.BellTower21(rand, this.func_74877_c() + 1, tc.field_71574_a, tc.field_71572_b, tc.field_71573_c, direction);
            }

            StructureBoundingBox largerBB = new StructureBoundingBox(((TFFinalCastlePieces.MazeTower13) eTower).func_74874_b());

            largerBB.field_78897_a -= 6;
            largerBB.field_78896_c -= 6;
            largerBB.field_78893_d += 6;
            largerBB.field_78892_f += 6;
            StructureComponent intersect = StructureComponent.func_74883_a(list, largerBB);

            if (intersect == null) {
                list.add(eTower);
                ((TFFinalCastlePieces.MazeTower13) eTower).func_74861_a(this, list, rand);
                ChunkCoordinates bc = this.offsetTowerCCoords(opening.field_71574_a, opening.field_71572_b, opening.field_71573_c, 1, direction);
                TFFinalCastlePieces.Bridge bridge = new TFFinalCastlePieces.Bridge(this.func_74877_c() + 1, bc.field_71574_a, bc.field_71572_b, bc.field_71573_c, howFar - 7, direction);

                list.add(bridge);
                bridge.func_74861_a(this, list, rand);
                this.addOpening(opening.field_71574_a, opening.field_71572_b + 1, opening.field_71573_c, direction);
                return true;
            } else {
                return false;
            }
        }

        private boolean isWithinRange(int centerX, int centerZ, int posX, int posZ, int range) {
            boolean inRange = Math.abs(centerX - posX) < range && Math.abs(centerZ - posZ) < range;

            if (!inRange) {
                ;
            }

            return inRange;
        }

        public ChunkCoordinates getValidOpeningCC(Random rand, int direction) {
            int floors = this.height / 8;
            int ry;

            if (direction != 0 && direction != 2) {
                if (direction != 1 && direction != 3) {
                    return new ChunkCoordinates(0, 0, 0);
                } else {
                    byte rx1 = 6;
                    int rz1 = direction == 1 ? 12 : 0;

                    ry = rand.nextInt(floors) * 8;
                    return new ChunkCoordinates(rx1, ry, rz1);
                }
            } else {
                int rx = direction == 0 ? 12 : 0;
                byte rz = 6;

                ry = rand.nextInt(floors) * 8;
                return new ChunkCoordinates(rx, ry, rz);
            }
        }

        protected ChunkCoordinates offsetTowerCCoords(int x, int y, int z, int howFar, int direction) {
            int dx = this.func_74865_a(x, z);
            int dy = this.func_74862_a(y);
            int dz = this.func_74873_b(x, z);

            switch (direction) {
            case 0:
                dx += howFar;
                break;

            case 1:
                dz += howFar;
                break;

            case 2:
                dx -= howFar;
                break;

            case 3:
                dz -= howFar;
            }

            return new ChunkCoordinates(dx, dy, dz);
        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            this.func_74882_a(world, sbb, 0, 0, 0, this.size - 1, this.height - 1, this.size - 1, false, rand, this.deco.randomBlocks);

            for (int x = 0; x < this.size; ++x) {
                for (int z = 0; z < this.size; ++z) {
                    this.func_151554_b(world, this.deco.blockID, this.deco.blockMeta, x, -1, z, sbb);
                }
            }

            this.addFloors(world, rand, sbb);
            this.makeOpenings(world, sbb);
            return true;
        }

        private void addFloors(World world, Random rand, StructureBoundingBox sbb) {
            int floors = this.highestOpening / 8 + 1;

            for (int i = 1; i < floors; ++i) {
                this.func_151556_a(world, sbb, 1, i * 8, 1, 11, i * 8, 11, this.deco.blockID, this.deco.blockMeta, this.deco.blockID, this.deco.blockMeta, false);
                this.addStairsDown(world, sbb, (i + 2) % 4, i * 8);
            }

            if (this.hasAccessibleRoof()) {
                this.addStairsDown(world, sbb, (floors + 2) % 4, this.height - 1);
            }

        }

        protected boolean hasAccessibleRoof() {
            return this.height - this.highestOpening < 9;
        }

        private void addStairsDown(World world, StructureBoundingBox sbb, int rotation, int y) {
            int i;
            int sy;

            for (i = 0; i < 4; ++i) {
                int sx = 8 - i;

                sy = y - i;
                byte sz = 9;

                this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(0 + rotation), sx, sy, sz, rotation, sbb);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, sx, sy - 1, sz, rotation, sbb);
                this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(0 + rotation), sx, sy, sz - 1, rotation, sbb);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, sx, sy - 1, sz - 1, rotation, sbb);
                this.fillAirRotated(world, sbb, sx, sy + 1, sz - 1, sx, sy + 3, sz, rotation);
            }

            this.fillBlocksRotated(world, sbb, 3, y - 4, 8, 4, y - 4, 9, this.deco.blockID, this.deco.blockMeta, rotation);

            for (i = 0; i < 4; ++i) {
                byte b0 = 4;

                sy = y - i - 4;
                int i = 7 - i;

                this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(1 + rotation), b0, sy, i, rotation, sbb);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, b0, sy - 1, i, rotation, sbb);
                this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(1 + rotation), b0 - 1, sy, i, rotation, sbb);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, b0 - 1, sy - 1, i, rotation, sbb);
                this.fillAirRotated(world, sbb, b0, sy + 1, i, b0 - 1, sy + 3, i, rotation);
            }

        }

        protected void makeDoorOpening(World world, int dx, int dy, int dz, StructureBoundingBox sbb) {
            if (dx == 0 || dx == this.size - 1) {
                this.func_151556_a(world, sbb, dx, dy - 1, dz - 2, dx, dy + 4, dz + 2, this.deco.accentID, this.deco.accentMeta, Blocks.field_150350_a, 0, false);
                this.func_151556_a(world, sbb, dx, dy, dz - 1, dx, dy + 3, dz + 1, TFBlocks.towerDevice, 0, Blocks.field_150350_a, 0, false);
            }

            if (dz == 0 || dz == this.size - 1) {
                this.func_151556_a(world, sbb, dx - 2, dy - 1, dz, dx + 2, dy + 4, dz, this.deco.accentID, this.deco.accentMeta, Blocks.field_150350_a, 0, false);
                this.func_151556_a(world, sbb, dx - 1, dy, dz, dx + 1, dy + 3, dz, TFBlocks.towerDevice, 0, Blocks.field_150350_a, 0, false);
            }

        }
    }

    public static class LargeTower extends StructureTFComponent {

        public LargeTower() {}

        public LargeTower(Random rand, int i, int x, int y, int z, int rotation) {
            this.setCoordBaseMode(rotation);
            this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, -6, 0, -6, 12, 60, 12, 0);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

            TFFinalCastlePieces.Roof9Crenellated roof = new TFFinalCastlePieces.Roof9Crenellated(rand, 4, this);

            list.add(roof);
            roof.func_74861_a(this, list, rand);
        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            this.func_74882_a(world, sbb, 0, 0, 0, 12, 59, 12, false, rand, this.deco.randomBlocks);

            for (int i = 1; i < 4; ++i) {
                this.func_74882_a(world, sbb, i, 0 - i * 2, i, 8 - i, 1 - i * 2, 8 - i, false, rand, this.deco.randomBlocks);
            }

            this.func_151550_a(world, this.deco.blockID, this.deco.blockMeta, 4, -7, 4, sbb);
            this.func_151556_a(world, sbb, 0, 1, 1, 0, 4, 3, TFBlocks.towerDevice, 0, Blocks.field_150350_a, 0, false);
            this.placeSignAtCurrentPosition(world, 6, 1, 6, "Parkour area 1", "Unique monster?", sbb);
            return true;
        }
    }

    public static class StairTower extends StructureTFComponent {

        public StairTower() {}

        public StairTower(Random rand, int i, int x, int y, int z, int rotation) {
            this.setCoordBaseMode(rotation);
            this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, -4, 0, -4, 8, 50, 8, 0);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

            TFFinalCastlePieces.Roof9Crenellated roof = new TFFinalCastlePieces.Roof9Crenellated(rand, 4, this);

            list.add(roof);
            roof.func_74861_a(this, list, rand);
        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            this.func_74882_a(world, sbb, 0, 0, 0, 8, 49, 8, false, rand, this.deco.randomBlocks);

            int f;

            for (f = 1; f < 4; ++f) {
                this.func_74882_a(world, sbb, f, 0 - f * 2, f, 8 - f, 1 - f * 2, 8 - f, false, rand, this.deco.randomBlocks);
            }

            this.func_151550_a(world, this.deco.blockID, this.deco.blockMeta, 4, -7, 4, sbb);
            this.func_151556_a(world, sbb, 0, 1, 1, 0, 3, 2, TFBlocks.towerDevice, 0, Blocks.field_150350_a, 0, false);

            int rotation;
            int y;
            int i;
            int sx;
            int sy;
            byte sz;

            for (f = 0; f < 5; ++f) {
                rotation = (f + 2) % 4;
                y = f * 3 + 1;

                for (i = 0; i < 3; ++i) {
                    sx = 3 + i;
                    sy = y + i;
                    sz = 1;
                    this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(0 + rotation), sx, sy, sz, rotation, sbb);
                    this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, sx, sy - 1, sz, rotation, sbb);
                    this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(0 + rotation), sx, sy, sz + 1, rotation, sbb);
                    this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, sx, sy - 1, sz + 1, rotation, sbb);
                }

                this.fillBlocksRotated(world, sbb, 6, y + 2, 1, 7, y + 2, 2, this.deco.blockID, this.deco.blockMeta, rotation);
            }

            this.func_151556_a(world, sbb, 1, 18, 0, 2, 20, 0, TFBlocks.towerDevice, 0, Blocks.field_150350_a, 0, false);
            this.func_151556_a(world, sbb, 1, 17, 1, 3, 17, 3, this.deco.blockID, this.deco.blockMeta, this.deco.blockID, this.deco.blockMeta, false);
            this.func_151556_a(world, sbb, 1, 17, 4, 2, 17, 4, this.deco.stairID, this.getStairMeta(3), this.deco.stairID, this.getStairMeta(3), false);
            this.func_151556_a(world, sbb, 1, 16, 4, 2, 16, 4, this.deco.blockID, this.deco.blockMeta, this.deco.blockID, this.deco.blockMeta, false);
            this.func_151556_a(world, sbb, 1, 16, 5, 2, 16, 5, this.deco.stairID, this.getStairMeta(3), this.deco.stairID, this.getStairMeta(3), false);
            this.func_151556_a(world, sbb, 1, 15, 5, 2, 15, 5, this.deco.blockID, this.deco.blockMeta, this.deco.blockID, this.deco.blockMeta, false);
            this.func_151556_a(world, sbb, 1, 39, 0, 2, 41, 0, TFBlocks.towerDevice, 0, Blocks.field_150350_a, 0, false);

            for (f = 0; f < 7; ++f) {
                rotation = (f + 0) % 4;
                y = f * 3 + 18;

                for (i = 0; i < 3; ++i) {
                    sx = 3 + i;
                    sy = y + i;
                    sz = 1;
                    this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(0 + rotation), sx, sy, sz, rotation, sbb);
                    this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, sx, sy - 1, sz, rotation, sbb);
                    this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(0 + rotation), sx, sy, sz + 1, rotation, sbb);
                    this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, sx, sy - 1, sz + 1, rotation, sbb);
                }

                this.fillBlocksRotated(world, sbb, 6, y + 2, 1, 7, y + 2, 2, this.deco.blockID, this.deco.blockMeta, rotation);
            }

            this.func_151556_a(world, sbb, 1, 38, 1, 3, 38, 5, this.deco.blockID, this.deco.blockMeta, this.deco.blockID, this.deco.blockMeta, false);
            this.func_151556_a(world, sbb, 3, 39, 1, 3, 39, 5, this.deco.fenceID, this.deco.fenceMeta, this.deco.fenceID, this.deco.fenceMeta, false);
            return true;
        }
    }

    public static class DungeonBossRoom extends StructureTFComponent {

        public DungeonBossRoom() {}

        public DungeonBossRoom(Random rand, int i, int x, int y, int z, int direction) {
            super(i);
            this.setCoordBaseMode(direction);
            this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox2(x, y, z, -15, 0, -15, 50, 30, 50, direction);
        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            this.func_74878_a(world, sbb, 0, 0, 0, 50, 30, 50);
            this.placeSignAtCurrentPosition(world, 25, 0, 25, "Mini-boss 2", "Gives talisman", sbb);
            return true;
        }
    }

    public static class DungeonRoom31 extends ComponentTFTowerWing {

        public int level;

        public DungeonRoom31() {}

        public DungeonRoom31(Random rand, int i, int x, int y, int z, int direction, int level) {
            super(i);
            this.setCoordBaseMode(direction);
            this.size = 31;
            this.height = 7;
            this.level = level;
            this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, -15, 0, -15, this.size - 1, this.height - 1, this.size - 1, 0);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

            int mySpread = this.func_74877_c() - parent.func_74877_c();
            int maxSpread = this.level == 1 ? 2 : 3;
            int direction;
            int i;

            if (mySpread == maxSpread && !this.isExitBuildForLevel(parent)) {
                direction = rand.nextInt(4);

                for (i = 0; i < 8 && !this.isExitBuildForLevel(parent); ++i) {
                    direction = (direction + i) % 4;
                    if (this.addDungeonExit(parent, list, rand, direction)) {
                        this.setExitBuiltForLevel(parent, true);
                    }
                }
            }

            if (mySpread < maxSpread) {
                direction = rand.nextInt(4);

                for (i = 0; i < 12; ++i) {
                    direction = (direction + i) % 4;
                    this.addDungeonRoom(parent, list, rand, direction, this.level);
                }
            }

        }

        private boolean isExitBuildForLevel(StructureComponent parent) {
            return parent instanceof TFFinalCastlePieces.DungeonEntrance ? ((TFFinalCastlePieces.DungeonEntrance) parent).hasExit : false;
        }

        private void setExitBuiltForLevel(StructureComponent parent, boolean exit) {
            if (parent instanceof TFFinalCastlePieces.DungeonEntrance) {
                ((TFFinalCastlePieces.DungeonEntrance) parent).hasExit = exit;
            }

        }

        protected boolean addDungeonRoom(StructureComponent parent, List list, Random rand, int rotation, int level) {
            rotation = (this.field_74885_f + rotation) % 4;
            ChunkCoordinates rc = this.getNewRoomCoords(rand, rotation);
            TFFinalCastlePieces.DungeonRoom31 dRoom = new TFFinalCastlePieces.DungeonRoom31(rand, this.field_74886_g + 1, rc.field_71574_a, rc.field_71572_b, rc.field_71573_c, rotation, level);
            StructureBoundingBox largerBB = new StructureBoundingBox(dRoom.func_74874_b());
            byte expand = 0;

            largerBB.field_78897_a -= expand;
            largerBB.field_78896_c -= expand;
            largerBB.field_78893_d += expand;
            largerBB.field_78892_f += expand;
            StructureComponent intersect = StructureTFComponent.findIntersectingExcluding(list, largerBB, this);

            if (intersect == null) {
                list.add(dRoom);
                dRoom.func_74861_a(parent, list, rand);
                return true;
            } else {
                return false;
            }
        }

        protected boolean addDungeonExit(StructureComponent parent, List list, Random rand, int rotation) {
            rotation = (this.field_74885_f + rotation) % 4;
            ChunkCoordinates rc = this.getNewRoomCoords(rand, rotation);
            TFFinalCastlePieces.DungeonExit dRoom = new TFFinalCastlePieces.DungeonExit(rand, this.field_74886_g + 1, rc.field_71574_a, rc.field_71572_b, rc.field_71573_c, rotation, this.level);
            StructureComponent intersect = StructureTFComponent.findIntersectingExcluding(list, dRoom.func_74874_b(), this);

            if (intersect == null) {
                list.add(dRoom);
                dRoom.func_74861_a(this, list, rand);
                return true;
            } else {
                return false;
            }
        }

        private ChunkCoordinates getNewRoomCoords(Random rand, int rotation) {
            int offset = rand.nextInt(15) - 9;

            if (rand.nextBoolean()) {
                offset += this.size;
            }

            switch (rotation) {
            case 0:
            default:
                return new ChunkCoordinates(this.field_74887_e.field_78893_d + 9, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + offset);

            case 1:
                return new ChunkCoordinates(this.field_74887_e.field_78897_a + offset, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f + 9);

            case 2:
                return new ChunkCoordinates(this.field_74887_e.field_78897_a - 9, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + offset);

            case 3:
                return new ChunkCoordinates(this.field_74887_e.field_78897_a + offset, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c - 9);
            }
        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            if (this.isBoundingBoxOutOfPlateau(world, sbb)) {
                return false;
            } else {
                Random decoRNG = new Random(world.func_72905_C() + (long) (this.field_74887_e.field_78897_a * 321534781) ^ (long) (this.field_74887_e.field_78896_c * 756839));

                this.func_74878_a(world, sbb, 0, 0, 0, this.size - 1, this.height - 1, this.size - 1);
                int forceFieldMeta = this.getForceFieldMeta(decoRNG);
                int runeMeta = this.getRuneMeta(forceFieldMeta);

                for (int rotation = 0; rotation < 4; ++rotation) {
                    byte cs = 7;

                    this.fillBlocksRotated(world, sbb, cs, 0, cs + 1, cs, this.height - 1, this.size - 2 - cs, TFBlocks.forceField, forceFieldMeta, rotation);

                    for (int z = cs; z < this.size - 1 - cs; z += 4) {
                        this.fillBlocksRotated(world, sbb, cs, 0, z, cs, this.height - 1, z, TFBlocks.castleMagic, runeMeta, rotation);
                        int y = (z - cs) % 8 == 0 ? decoRNG.nextInt(3) + 0 : decoRNG.nextInt(3) + 4;

                        this.fillBlocksRotated(world, sbb, cs, y, z + 1, cs, y, z + 3, TFBlocks.castleMagic, runeMeta, rotation);
                    }
                }

                return true;
            }
        }

        private boolean isBoundingBoxOutOfPlateau(World world, StructureBoundingBox sbb) {
            int minX = this.field_74887_e.field_78897_a - 1;
            int minZ = this.field_74887_e.field_78896_c - 1;
            int maxX = this.field_74887_e.field_78893_d + 1;
            int maxZ = this.field_74887_e.field_78892_f + 1;

            for (int x = minX; x <= maxX; ++x) {
                for (int z = minZ; z <= maxZ; ++z) {
                    if (world.func_72807_a(x, z) != TFBiomeBase.highlandsCenter && world.func_72807_a(x, z) != TFBiomeBase.thornlands) {
                        return true;
                    }
                }
            }

            return false;
        }

        protected int getRuneMeta(int forceFieldMeta) {
            return forceFieldMeta == 4 ? 1 : 2;
        }

        protected int getForceFieldMeta(Random decoRNG) {
            return decoRNG.nextInt(2) + 3;
        }
    }

    public static class DungeonExit extends TFFinalCastlePieces.DungeonRoom31 {

        public DungeonExit() {}

        public DungeonExit(Random rand, int i, int x, int y, int z, int direction, int level) {
            super(rand, i, x, y, z, direction, level);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

            int bestDir = this.findStairDirectionTowards(parent.func_74874_b().field_78897_a, parent.func_74874_b().field_78896_c);
            TFFinalCastlePieces.DungeonSteps steps0 = new TFFinalCastlePieces.DungeonSteps(rand, 5, this.field_74887_e.field_78897_a + 15, this.field_74887_e.field_78895_b + 0, this.field_74887_e.field_78896_c + 15, bestDir);

            list.add(steps0);
            steps0.func_74861_a(this, list, rand);
            if (this.level == 1) {
                steps0.buildLevelUnder(parent, list, rand, this.level + 1);
            } else {
                steps0.buildBossRoomUnder(parent, list, rand);
            }

        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            super.func_74875_a(world, rand, sbb);
            this.func_151556_a(world, sbb, 7, 0, 16, 7, 3, 18, TFBlocks.towerDevice, 0, Blocks.field_150350_a, 0, false);
            this.func_151556_a(world, sbb, 7, 4, 16, 7, 4, 18, this.deco.blockID, this.deco.blockMeta, this.deco.blockID, this.deco.blockMeta, false);
            return true;
        }

        public int findStairDirectionTowards(int x, int z) {
            int cx = this.field_74887_e.func_78881_e();
            int cz = this.field_74887_e.func_78891_g();
            int dx = cx - x;
            int dz = cz - z;
            int absoluteDir;

            if (Math.abs(dz) >= Math.abs(dx)) {
                absoluteDir = dz >= 0 ? 2 : 0;
            } else {
                absoluteDir = dx >= 0 ? 3 : 1;
            }

            return absoluteDir;
        }

        protected int getForceFieldMeta(Random decoRNG) {
            return 1;
        }

        protected int getRuneMeta(int fieldMeta) {
            return 0;
        }
    }

    public static class DungeonEntrance extends TFFinalCastlePieces.DungeonRoom31 {

        public boolean hasExit = false;

        public DungeonEntrance() {}

        public DungeonEntrance(Random rand, int i, int x, int y, int z, int direction, int level) {
            super(rand, i, x, y, z, direction, level);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            this.deco = new StructureTFDecoratorCastle();
            this.deco.blockID = TFBlocks.castleMagic;
            this.deco.blockMeta = 0;
            this.deco.fenceID = TFBlocks.forceField;
            this.deco.fenceMeta = 1;
            super.func_74861_a(this, list, rand);
        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            super.func_74875_a(world, rand, sbb);

            for (int y = 0; y <= this.height; ++y) {
                int x = this.size / 2 - 2;
                int z = this.size / 2 - y + 2;

                this.func_151556_a(world, sbb, x, y, z, x + 4, y, z, this.deco.stairID, this.getStairMeta(3), this.deco.stairID, this.getStairMeta(3), false);
                this.func_151556_a(world, sbb, x, 0, z, x + 4, y - 1, z, TFBlocks.deadrock, 0, TFBlocks.deadrock, 0, false);
            }

            this.func_151556_a(world, sbb, 23, 0, 12, 23, 3, 14, TFBlocks.towerDevice, 0, Blocks.field_150350_a, 0, false);
            this.func_151556_a(world, sbb, 23, 4, 12, 23, 4, 14, this.deco.blockID, this.deco.blockMeta, this.deco.blockID, this.deco.blockMeta, false);
            return true;
        }

        protected int getForceFieldMeta(Random decoRNG) {
            return 1;
        }

        protected int getRuneMeta(int fieldMeta) {
            return 0;
        }
    }

    public static class DungeonSteps extends StructureTFComponent {

        public DungeonSteps() {}

        public DungeonSteps(Random rand, int i, int x, int y, int z, int rotation) {
            this.setCoordBaseMode(rotation);
            this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox2(x, y, z, -2, -15, -3, 5, 15, 20, rotation);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

        }

        public TFFinalCastlePieces.DungeonSteps buildMoreStepsTowards(StructureComponent parent, List list, Random rand, int rotation) {
            int direction = (rotation + this.field_74885_f) % 4;
            int sx = 2;
            byte sy = 0;
            int sz = 17;

            switch (rotation) {
            case 0:
                sz -= 5;
                break;

            case 1:
                sx -= 5;
                break;

            case 2:
                sz += 5;
                break;

            case 3:
                sx += 6;
            }

            int dx = this.func_74865_a(sx, sz);
            int dy = this.func_74862_a(sy);
            int dz = this.func_74873_b(sx, sz);
            TFFinalCastlePieces.DungeonSteps steps = new TFFinalCastlePieces.DungeonSteps(rand, this.field_74886_g + 1, dx, dy, dz, direction);

            list.add(steps);
            steps.func_74861_a(this, list, rand);
            return steps;
        }

        public TFFinalCastlePieces.DungeonEntrance buildLevelUnder(StructureComponent parent, List list, Random rand, int level) {
            int dx = this.func_74865_a(2, 19);
            int dy = this.func_74862_a(-7);
            int dz = this.func_74873_b(2, 19);
            TFFinalCastlePieces.DungeonEntrance room = new TFFinalCastlePieces.DungeonEntrance(rand, 8, dx, dy, dz, this.field_74885_f, level);

            list.add(room);
            room.func_74861_a(this, list, rand);
            return room;
        }

        public TFFinalCastlePieces.DungeonBossRoom buildBossRoomUnder(StructureComponent parent, List list, Random rand) {
            int dx = this.func_74865_a(2, 19);
            int dy = this.func_74862_a(-31);
            int dz = this.func_74873_b(2, 19);
            TFFinalCastlePieces.DungeonBossRoom room = new TFFinalCastlePieces.DungeonBossRoom(rand, 8, dx, dy, dz, this.field_74885_f);

            list.add(room);
            room.func_74861_a(this, list, rand);
            return room;
        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            for (int z = 0; z < 15; ++z) {
                int y = 14 - z;

                this.func_151556_a(world, sbb, 0, y, z, 4, y, z, this.deco.stairID, this.getStairMeta(3), this.deco.stairID, this.getStairMeta(3), false);
                this.func_74878_a(world, sbb, 0, y + 1, z, 4, y + 6, z);
            }

            this.func_74878_a(world, sbb, 0, 0, 15, 4, 5, 19);
            return true;
        }
    }

    public static class Foundation48 extends StructureTFComponent {

        private int groundLevel = -1;

        public Foundation48() {}

        public Foundation48(Random rand, int i, StructureTFComponent sideTower) {
            super(i);
            this.setCoordBaseMode(sideTower.getCoordBaseMode());
            this.field_74887_e = new StructureBoundingBox(sideTower.func_74874_b().field_78897_a, sideTower.func_74874_b().field_78895_b, sideTower.func_74874_b().field_78896_c, sideTower.func_74874_b().field_78893_d, sideTower.func_74874_b().field_78895_b - 1, sideTower.func_74874_b().field_78892_f);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            int rotation;

            for (int mid = 4; mid < 45; ++mid) {
                for (rotation = 4; rotation < 45; ++rotation) {
                    this.func_151554_b(world, this.deco.blockID, this.deco.blockMeta, mid, -1, rotation, sbb);
                }
            }

            byte b0 = 16;

            for (rotation = 0; rotation < 4; ++rotation) {
                this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 3, -2, 3, rotation, sbb);
                this.fillBlocksRotated(world, sbb, 2, -2, 1, 46, -1, 1, this.deco.blockID, this.deco.blockMeta, rotation);
                this.fillBlocksRotated(world, sbb, 2, -4, 2, 45, -1, 2, this.deco.blockID, this.deco.blockMeta, rotation);
                this.fillBlocksRotated(world, sbb, 4, -6, 3, 44, -1, 3, this.deco.blockID, this.deco.blockMeta, rotation);

                for (int i = 9; i < 45; i += 6) {
                    this.makePiling(world, sbb, b0, rotation, i);
                }

                this.makePiling(world, sbb, b0, rotation, 4);
                this.makePiling(world, sbb, b0, rotation, 44);
            }

            this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 21, -2, 0, 1, sbb);
            this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 21, -4, 1, 1, sbb);
            this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 21, -6, 2, 1, sbb);
            this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 27, -2, 0, 1, sbb);
            this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 27, -4, 1, 1, sbb);
            this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, 27, -6, 2, 1, sbb);
            return true;
        }

        private void makePiling(World world, StructureBoundingBox sbb, int mid, int rotation, int i) {
            this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, i, -7, 3, rotation, sbb);
            this.fillToGroundRotated(world, this.deco.blockID, this.deco.blockMeta, i, -mid, 2, rotation, sbb);
            this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, i, -1, 0, rotation, sbb);
            this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, i, -3, 1, rotation, sbb);
            this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, i, -5, 2, rotation, sbb);
        }
    }

    public static class BossGazebo extends StructureTFComponent {

        public BossGazebo() {}

        public BossGazebo(Random rand, int i, StructureTFComponent keep) {
            super(i);
            this.setCoordBaseMode(keep.getCoordBaseMode());
            this.field_74887_e = new StructureBoundingBox(keep.func_74874_b().field_78897_a + 14, keep.func_74874_b().field_78894_e + 2, keep.func_74874_b().field_78896_c + 14, keep.func_74874_b().field_78893_d - 14, keep.func_74874_b().field_78894_e + 13, keep.func_74874_b().field_78892_f - 14);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            this.deco = new StructureTFDecoratorCastle();
            this.deco.blockID = TFBlocks.castleMagic;
            this.deco.blockMeta = 1;
            this.deco.fenceID = TFBlocks.forceField;
            this.deco.fenceMeta = 10;
        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            for (int rotation = 0; rotation < 4; ++rotation) {
                this.fillBlocksRotated(world, sbb, 0, 0, 0, 0, 10, 20, this.deco.fenceID, this.deco.fenceMeta, rotation);
            }

            this.func_151556_a(world, sbb, 0, 11, 0, 20, 11, 20, this.deco.fenceID, this.deco.fenceMeta, this.deco.fenceID, this.deco.fenceMeta, false);
            this.placeSignAtCurrentPosition(world, 10, 0, 10, "Final Boss Here", "You win!", sbb);
            return true;
        }
    }

    public static class Roof48Crenellated extends StructureTFComponent {

        public Roof48Crenellated() {}

        public Roof48Crenellated(Random rand, int i, StructureTFComponent keep) {
            super(i);
            byte height = 5;

            this.setCoordBaseMode(keep.getCoordBaseMode());
            this.field_74887_e = new StructureBoundingBox(keep.func_74874_b().field_78897_a - 2, keep.func_74874_b().field_78894_e - 1, keep.func_74874_b().field_78896_c - 2, keep.func_74874_b().field_78893_d + 2, keep.func_74874_b().field_78894_e + height - 1, keep.func_74874_b().field_78892_f + 2);
        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            if (parent != null && parent instanceof StructureTFComponent) {
                this.deco = ((StructureTFComponent) parent).deco;
            }

        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            this.func_151556_a(world, sbb, 2, 2, 2, 50, 2, 50, TFBlocks.castleMagic, 3, TFBlocks.castleMagic, 3, false);

            for (int rotation = 0; rotation < 4; ++rotation) {
                this.fillBlocksRotated(world, sbb, 3, 1, 1, 45, 3, 1, this.deco.blockID, this.deco.blockMeta, rotation);

                for (int i = 10; i < 41; i += 5) {
                    this.fillBlocksRotated(world, sbb, i, 1, 0, i + 2, 5, 2, this.deco.blockID, this.deco.blockMeta, rotation);
                    this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, i + 1, 0, 1, rotation, sbb);
                }
            }

            return true;
        }
    }

    public static class Main extends StructureTFComponent {

        public Main() {}

        public Main(World world, Random rand, int i, int x, int y, int z) {
            this.setCoordBaseMode(0);
            x = x + 127 >> 8 << 8;
            z = z + 127 >> 8 << 8;
            this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, -24, 120, -24, 48, 40, 48, 0);
            ChunkCoordinates cc = TFFeature.getNearestCenterXYZ(x >> 4, z >> 4, world);
            int cx = x >> 8 << 8;
            int cz = z >> 8 << 8;

            System.out.println("Making castle at " + x + ", " + z + ". center is " + cc.field_71574_a + ", " + cc.field_71573_c);
            System.out.println("Natural center at " + cx + ", " + cz);
            if (this.deco == null) {
                this.deco = new StructureTFDecoratorCastle();
            }

        }

        public void func_74861_a(StructureComponent parent, List list, Random rand) {
            TFFinalCastlePieces.Foundation48 foundation = new TFFinalCastlePieces.Foundation48(rand, 4, this);

            list.add(foundation);
            foundation.func_74861_a(this, list, rand);
            TFFinalCastlePieces.Roof48Crenellated roof = new TFFinalCastlePieces.Roof48Crenellated(rand, 4, this);

            list.add(roof);
            roof.func_74861_a(this, list, rand);
            TFFinalCastlePieces.BossGazebo gazebo = new TFFinalCastlePieces.BossGazebo(rand, 5, this);

            list.add(gazebo);
            gazebo.func_74861_a(this, list, rand);
            TFFinalCastlePieces.StairTower tower0 = new TFFinalCastlePieces.StairTower(rand, 3, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b + 3, this.field_74887_e.field_78896_c, 2);

            list.add(tower0);
            tower0.func_74861_a(this, list, rand);
            TFFinalCastlePieces.LargeTower tower1 = new TFFinalCastlePieces.LargeTower(rand, 3, this.field_74887_e.field_78893_d, this.field_74887_e.field_78895_b + 3, this.field_74887_e.field_78896_c, 3);

            list.add(tower1);
            tower1.func_74861_a(this, list, rand);
            TFFinalCastlePieces.StairTower tower2 = new TFFinalCastlePieces.StairTower(rand, 3, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b + 3, this.field_74887_e.field_78892_f, 1);

            list.add(tower2);
            tower2.func_74861_a(this, list, rand);
            TFFinalCastlePieces.StairTower tower3 = new TFFinalCastlePieces.StairTower(rand, 3, this.field_74887_e.field_78893_d, this.field_74887_e.field_78895_b + 3, this.field_74887_e.field_78892_f, 0);

            list.add(tower3);
            tower3.func_74861_a(this, list, rand);
            ChunkCoordinates dest = new ChunkCoordinates(this.field_74887_e.field_78897_a - 4, this.field_74887_e.field_78894_e, this.field_74887_e.field_78896_c - 24);

            this.buildTowerMaze(list, rand, 48, 0, 24, 60, 0, 0, dest);
            dest = new ChunkCoordinates(this.field_74887_e.field_78893_d + 4, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f + 24);
            this.buildTowerMaze(list, rand, 0, 30, 24, 60, 2, 1, dest);
            TFFinalCastlePieces.DungeonSteps steps0 = new TFFinalCastlePieces.DungeonSteps(rand, 5, this.field_74887_e.field_78897_a + 18, this.field_74887_e.field_78895_b + 1, this.field_74887_e.field_78896_c + 18, 0);

            list.add(steps0);
            steps0.func_74861_a(this, list, rand);
            TFFinalCastlePieces.DungeonSteps steps1 = steps0.buildMoreStepsTowards(parent, list, rand, 3);
            TFFinalCastlePieces.DungeonSteps steps2 = steps1.buildMoreStepsTowards(parent, list, rand, 3);
            TFFinalCastlePieces.DungeonSteps steps3 = steps2.buildMoreStepsTowards(parent, list, rand, 3);

            steps3.buildLevelUnder(parent, list, rand, 1);
        }

        private void buildTowerMaze(List list, Random rand, int x, int y, int z, int howFar, int direction, int type, ChunkCoordinates dest) {
            ChunkCoordinates tc = this.offsetTowerCCoords(x, y, z, howFar, direction);
            TFFinalCastlePieces.MazeTower13 sTower = new TFFinalCastlePieces.MazeTower13(rand, 3, tc.field_71574_a, tc.field_71572_b, tc.field_71573_c, type, direction);
            ChunkCoordinates bc = this.offsetTowerCCoords(x, y, z, 1, direction);
            TFFinalCastlePieces.Bridge bridge = new TFFinalCastlePieces.Bridge(this.func_74877_c() + 1, bc.field_71574_a, bc.field_71572_b, bc.field_71573_c, howFar - 7, direction);

            list.add(bridge);
            bridge.func_74861_a(this, list, rand);
            list.add(sTower);
            sTower.buildTowards(this, list, rand, dest);
        }

        protected ChunkCoordinates offsetTowerCCoords(int x, int y, int z, int howFar, int direction) {
            int dx = this.func_74865_a(x, z);
            int dy = this.func_74862_a(y);
            int dz = this.func_74873_b(x, z);

            switch (direction) {
            case 0:
                dx += howFar;
                break;

            case 1:
                dz += howFar;
                break;

            case 2:
                dx -= howFar;
                break;

            case 3:
                dz -= howFar;
            }

            return new ChunkCoordinates(dx, dy, dz);
        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
            this.func_74882_a(world, sbb, 0, 0, 0, 48, 40, 48, false, rand, this.deco.randomBlocks);
            this.func_74882_a(world, sbb, 13, 30, 1, 47, 30, 12, false, rand, this.deco.randomBlocks);
            this.func_151549_a(world, sbb, 13, 31, 12, 36, 31, 12, this.deco.fenceID, this.deco.fenceID, false);
            this.func_74882_a(world, sbb, 13, 30, 36, 47, 30, 47, false, rand, this.deco.randomBlocks);
            this.func_151549_a(world, sbb, 13, 31, 36, 36, 31, 36, this.deco.fenceID, this.deco.fenceID, false);
            this.func_74882_a(world, sbb, 1, 30, 1, 12, 30, 47, false, rand, this.deco.randomBlocks);
            this.func_151549_a(world, sbb, 12, 31, 12, 12, 31, 36, this.deco.fenceID, this.deco.fenceID, false);
            this.func_74882_a(world, sbb, 38, 25, 13, 47, 25, 35, false, rand, this.deco.randomBlocks);

            int fieldBlock;
            int fieldMeta;

            for (fieldBlock = 0; fieldBlock < 5; ++fieldBlock) {
                fieldMeta = 30 - fieldBlock;
                this.makeMezzTopStairs(world, sbb, fieldMeta, 10 + fieldBlock, 3);
                this.makeMezzTopStairs(world, sbb, fieldMeta, 38 - fieldBlock, 1);
                fieldMeta = 25 - fieldBlock;
                int x = 37 - fieldBlock;

                this.func_151556_a(world, sbb, x, fieldMeta, 14, x, fieldMeta, 22, this.deco.stairID, this.getStairMeta(0), this.deco.stairID, this.getStairMeta(0), false);
                this.func_151556_a(world, sbb, x, fieldMeta - 1, 14, x, fieldMeta - 1, 22, this.deco.blockID, this.deco.blockMeta, this.deco.blockID, this.deco.blockMeta, false);
                this.func_151556_a(world, sbb, x, fieldMeta, 26, x, fieldMeta, 34, this.deco.stairID, this.getStairMeta(0), this.deco.stairID, this.getStairMeta(0), false);
                this.func_151556_a(world, sbb, x, fieldMeta - 1, 26, x, fieldMeta - 1, 34, this.deco.blockID, this.deco.blockMeta, this.deco.blockID, this.deco.blockMeta, false);
            }

            for (fieldBlock = 11; fieldBlock < 47; fieldBlock += 12) {
                for (fieldMeta = 11; fieldMeta < 47; fieldMeta += 12) {
                    this.func_151556_a(world, sbb, fieldBlock, 1, fieldMeta, fieldBlock + 2, 40, fieldMeta + 2, this.deco.pillarID, this.deco.pillarMeta, this.deco.blockID, this.deco.blockMeta, false);
                    this.makePillarBase(world, sbb, fieldBlock, fieldMeta, 1, 0);
                    this.makePillarBase(world, sbb, fieldBlock, fieldMeta, 19, 4);
                    this.makePillarBase(world, sbb, fieldBlock, fieldMeta, 21, 0);
                    this.makePillarBase(world, sbb, fieldBlock, fieldMeta, 39, 4);
                }
            }

            for (fieldBlock = 0; fieldBlock < 4; ++fieldBlock) {
                for (fieldMeta = 11; fieldMeta < 47; fieldMeta += 12) {
                    if (fieldMeta != 23 || fieldBlock != 0 && fieldBlock != 2) {
                        this.fillBlocksRotated(world, sbb, 1, 1, fieldMeta, 1, 40, fieldMeta + 2, this.deco.pillarID, this.deco.pillarMeta, fieldBlock);
                        this.makeHalfPillarBase(world, sbb, fieldBlock, 1, fieldMeta, 0);
                        this.makeHalfPillarBase(world, sbb, fieldBlock, 19, fieldMeta, 4);
                        this.makeHalfPillarBase(world, sbb, fieldBlock, 21, fieldMeta, 0);
                        this.makeHalfPillarBase(world, sbb, fieldBlock, 39, fieldMeta, 4);
                    }
                }
            }

            this.func_74882_a(world, sbb, 1, 20, 1, 47, 20, 47, false, rand, this.deco.randomBlocks);
            Block block = TFBlocks.forceField;
            byte b0 = 6;

            this.func_151556_a(world, sbb, 12, 1, 12, 24, 10, 12, block, b0, block, b0, false);
            this.func_151556_a(world, sbb, 12, 1, 12, 12, 10, 24, block, b0, block, b0, false);
            this.func_151556_a(world, sbb, 24, 1, 12, 24, 10, 24, block, b0, block, b0, false);
            this.func_151556_a(world, sbb, 12, 1, 24, 24, 10, 24, block, b0, block, b0, false);
            this.func_151556_a(world, sbb, 12, 10, 12, 24, 10, 24, block, b0, block, b0, false);
            this.func_151556_a(world, sbb, 17, 1, 12, 19, 4, 12, TFBlocks.towerDevice, 0, Blocks.field_150350_a, 0, false);
            this.func_151556_a(world, sbb, 17, 1, 24, 19, 4, 24, TFBlocks.towerDevice, 0, Blocks.field_150350_a, 0, false);
            this.makeSmallTowerStairs(world, sbb, 0);
            this.makeSmallTowerStairs(world, sbb, 1);
            this.makeSmallTowerStairs(world, sbb, 3);
            this.makeLargeTowerStairs(world, sbb, 2);
            this.func_151556_a(world, sbb, 48, 1, 23, 48, 4, 25, TFBlocks.towerDevice, 0, Blocks.field_150350_a, 0, false);
            this.func_151556_a(world, sbb, 0, 31, 23, 0, 34, 25, TFBlocks.towerDevice, 0, Blocks.field_150350_a, 0, false);
            return true;
        }

        private void makeSmallTowerStairs(World world, StructureBoundingBox sbb, int rotation) {
            for (int y = 1; y < 4; ++y) {
                int z = 40 + y;

                this.fillBlocksRotated(world, sbb, 1, 1, z, 4, y, z, this.deco.blockID, this.deco.blockMeta, rotation);
                this.fillBlocksRotated(world, sbb, 2, y, z, 3, y, z, this.deco.stairID, this.getStairMeta(1 + rotation), rotation);
            }

        }

        private void makeLargeTowerStairs(World world, StructureBoundingBox sbb, int rotation) {
            for (int y = 1; y < 4; ++y) {
                int z = 38 + y;

                this.fillBlocksRotated(world, sbb, 2, 1, z, 6, y, z, this.deco.blockID, this.deco.blockMeta, rotation);
                this.fillBlocksRotated(world, sbb, 3, y, z, 5, y, z, this.deco.stairID, this.getStairMeta(1 + rotation), rotation);
            }

        }

        private void makeMezzTopStairs(World world, StructureBoundingBox sbb, int y, int z, int stairMeta) {
            this.func_151556_a(world, sbb, 38, y, z, 46, y, z, this.deco.stairID, this.getStairMeta(stairMeta), this.deco.stairID, this.getStairMeta(stairMeta), false);
            this.func_151556_a(world, sbb, 38, y - 1, z, 46, y - 1, z, this.deco.blockID, this.deco.blockMeta, this.deco.blockID, this.deco.blockMeta, false);
            this.func_74878_a(world, sbb, 38, y + 1, z, 46, y + 3, z);
        }

        private void makeHalfPillarBase(World world, StructureBoundingBox sbb, int rotation, int y, int z, int metaBit) {
            this.fillBlocksRotated(world, sbb, 2, y, z - 1, 2, y, z + 3, this.deco.stairID, this.getStairMeta(2 + rotation) | metaBit, rotation);
            this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(1 + rotation) | metaBit, 1, y, z - 1, rotation, sbb);
            this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation) | metaBit, 1, y, z + 3, rotation, sbb);
        }

        private void makePillarBase(World world, StructureBoundingBox sbb, int x, int z, int y, int metaBit) {
            this.func_151556_a(world, sbb, x + 0, y, z + 3, x + 3, y, z + 3, this.deco.stairID, this.getStairMeta(3) | metaBit, Blocks.field_150350_a, 0, false);
            this.func_151556_a(world, sbb, x - 1, y, z - 1, x + 2, y, z - 1, this.deco.stairID, this.getStairMeta(1) | metaBit, Blocks.field_150350_a, 0, false);
            this.func_151556_a(world, sbb, x + 3, y, z - 1, x + 3, y, z + 2, this.deco.stairID, this.getStairMeta(2) | metaBit, Blocks.field_150350_a, 0, false);
            this.func_151556_a(world, sbb, x - 1, y, z + 0, x - 1, y, z + 3, this.deco.stairID, this.getStairMeta(0) | metaBit, Blocks.field_150350_a, 0, false);
        }
    }
}
