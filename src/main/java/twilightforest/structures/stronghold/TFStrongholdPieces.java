package twilightforest.structures.stronghold;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;

public class TFStrongholdPieces {

    private static final TFStrongholdPieceWeight[] pieceWeightArray = new TFStrongholdPieceWeight[] { new TFStrongholdPieceWeight(ComponentTFStrongholdSmallHallway.class, 40, 0), new TFStrongholdPieceWeight(ComponentTFStrongholdLeftTurn.class, 20, 0), new TFStrongholdPieceWeight(ComponentTFStrongholdCrossing.class, 10, 4), new TFStrongholdPieceWeight(ComponentTFStrongholdRightTurn.class, 20, 0), new TFStrongholdPieceWeight(ComponentTFStrongholdDeadEnd.class, 5, 0), new TFStrongholdPieceWeight(ComponentTFStrongholdBalconyRoom.class, 10, 3, 2), new TFStrongholdPieceWeight(ComponentTFStrongholdTrainingRoom.class, 10, 2), new TFStrongholdPieceWeight(ComponentTFStrongholdSmallStairs.class, 10, 0), new TFStrongholdPieceWeight(ComponentTFStrongholdTreasureCorridor.class, 5, 0), new TFStrongholdPieceWeight(ComponentTFStrongholdAtrium.class, 5, 2, 3), new TFStrongholdPieceWeight(ComponentTFStrongholdFoundry.class, 5, 1, 4), new TFStrongholdPieceWeight(ComponentTFStrongholdTreasureRoom.class, 5, 1, 4), new TFStrongholdPieceWeight(ComponentTFStrongholdBossRoom.class, 10, 1, 4)};
    private List pieceList;
    static int totalWeight = 0;
    private static Class lastPieceMade;

    public static void registerPieces() {
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdSmallHallway.class, "TFSSH");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdLeftTurn.class, "TFSLT");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdCrossing.class, "TFSCr");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdRightTurn.class, "TFSRT");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdDeadEnd.class, "TFSDE");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdBalconyRoom.class, "TFSBR");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdTrainingRoom.class, "TFSTR");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdSmallStairs.class, "TFSSS");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdTreasureCorridor.class, "TFSTC");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdAtrium.class, "TFSAt");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdFoundry.class, "TFSFo");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdTreasureRoom.class, "TFTreaR");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdBossRoom.class, "TFSBR");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdAccessChamber.class, "TFSAC");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdEntrance.class, "TFSEnter");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdUpperAscender.class, "TFSUA");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdUpperLeftTurn.class, "TFSULT");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdUpperRightTurn.class, "TFSURT");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdUpperCorridor.class, "TFSUCo");
        MapGenStructureIO.func_143031_a(ComponentTFStrongholdUpperTIntersection.class, "TFSUTI");
        MapGenStructureIO.func_143031_a(StructureTFStrongholdShield.class, "TFSShield");
    }

    public void prepareStructurePieces() {
        this.pieceList = new ArrayList();
        TFStrongholdPieceWeight[] atfstrongholdpieceweight = TFStrongholdPieces.pieceWeightArray;
        int i = atfstrongholdpieceweight.length;

        for (int j = 0; j < i; ++j) {
            TFStrongholdPieceWeight piece = atfstrongholdpieceweight[j];

            piece.instancesSpawned = 0;
            this.pieceList.add(piece);
        }

    }

    public void markBossRoomUsed() {
        this.pieceList.remove(this.pieceList.size() - 1);
    }

    private boolean hasMoreLimitedPieces() {
        boolean flag = false;

        TFStrongholdPieces.totalWeight = 0;
        Iterator iterator = this.pieceList.iterator();

        while (iterator.hasNext()) {
            TFStrongholdPieceWeight piece = (TFStrongholdPieceWeight) iterator.next();

            TFStrongholdPieces.totalWeight += piece.pieceWeight;
            if (piece.instancesLimit > 0 && piece.instancesSpawned < piece.instancesLimit) {
                flag = true;
            }
        }

        return flag;
    }

    public StructureTFStrongholdComponent getNextComponent(StructureComponent parent, List list, Random random, int index, int facing, int x, int y, int z) {
        if (!this.hasMoreLimitedPieces()) {
            return null;
        } else {
            int deadEnd = 0;

            while (deadEnd < 5) {
                int counter = random.nextInt(TFStrongholdPieces.totalWeight);
                Iterator iterator = this.pieceList.iterator();

                while (true) {
                    if (iterator.hasNext()) {
                        TFStrongholdPieceWeight piece = (TFStrongholdPieceWeight) iterator.next();

                        counter -= piece.pieceWeight;
                        if (counter >= 0) {
                            continue;
                        }

                        if (piece.isDeepEnough(index) && piece.pieceClass != TFStrongholdPieces.lastPieceMade) {
                            StructureTFStrongholdComponent component = instantiateComponent(piece.pieceClass, index, facing, x, y, z);

                            if (StructureComponent.func_74883_a(list, component.func_74874_b()) == null) {
                                ++piece.instancesSpawned;
                                if (!piece.canSpawnMoreStructures()) {
                                    this.pieceList.remove(piece);
                                }

                                TFStrongholdPieces.lastPieceMade = piece.pieceClass;
                                return component;
                            }
                            continue;
                        }
                    }

                    ++deadEnd;
                    break;
                }
            }

            ComponentTFStrongholdDeadEnd componenttfstrongholddeadend = new ComponentTFStrongholdDeadEnd(index, facing, x, y, z);

            if (StructureComponent.func_74883_a(list, componenttfstrongholddeadend.func_74874_b()) == null) {
                return componenttfstrongholddeadend;
            } else {
                return null;
            }
        }
    }

    private static StructureTFStrongholdComponent instantiateComponent(Class pieceClass, int index, int facing, int x, int y, int z) {
        try {
            return (StructureTFStrongholdComponent) pieceClass.getConstructor(new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}).newInstance(new Object[] { Integer.valueOf(index), Integer.valueOf(facing), Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z)});
        } catch (InstantiationException instantiationexception) {
            instantiationexception.printStackTrace();
        } catch (IllegalAccessException illegalaccessexception) {
            illegalaccessexception.printStackTrace();
        } catch (IllegalArgumentException illegalargumentexception) {
            illegalargumentexception.printStackTrace();
        } catch (InvocationTargetException invocationtargetexception) {
            invocationtargetexception.printStackTrace();
        } catch (NoSuchMethodException nosuchmethodexception) {
            nosuchmethodexception.printStackTrace();
        } catch (SecurityException securityexception) {
            securityexception.printStackTrace();
        }

        return null;
    }
}
