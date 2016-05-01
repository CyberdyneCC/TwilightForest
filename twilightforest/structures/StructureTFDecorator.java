package twilightforest.structures;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.StructureComponent.BlockSelector;
import twilightforest.structures.darktower.StructureDecoratorDarkTower;
import twilightforest.structures.icetower.StructureDecoratorIceTower;
import twilightforest.structures.mushroomtower.StructureDecoratorMushroomTower;
import twilightforest.structures.stronghold.StructureTFDecoratorStronghold;

public class StructureTFDecorator {

    public Block blockID;
    public int blockMeta;
    public Block accentID;
    public int accentMeta;
    public Block stairID;
    public int stairMeta;
    public Block fenceID;
    public int fenceMeta;
    public Block pillarID;
    public int pillarMeta;
    public Block platformID;
    public int platformMeta;
    public Block floorID;
    public int floorMeta;
    public Block roofID;
    public int roofMeta;
    public BlockSelector randomBlocks;

    public StructureTFDecorator() {
        this.blockID = Blocks.field_150348_b;
        this.accentID = Blocks.field_150347_e;
        this.randomBlocks = new StructureTFStrongholdStones();
    }

    public static String getDecoString(StructureTFDecorator deco) {
        return deco instanceof StructureDecoratorDarkTower ? "DecoDarkTower" : (deco instanceof StructureDecoratorIceTower ? "DecoIceTower" : (deco instanceof StructureDecoratorMushroomTower ? "DecoMushroomTower" : (deco instanceof StructureTFDecoratorStronghold ? "DecoStronghold" : (deco instanceof StructureTFDecoratorCastle ? "DecoCastle" : ""))));
    }

    public static StructureTFDecorator getDecoFor(String decoString) {
        return (StructureTFDecorator) (decoString.equals("DecoDarkTower") ? new StructureDecoratorDarkTower() : (decoString.equals("DecoIceTower") ? new StructureDecoratorIceTower() : (decoString.equals("DecoMushroomTower") ? new StructureDecoratorMushroomTower() : (decoString.equals("DecoStronghold") ? new StructureTFDecoratorStronghold() : (decoString.equals("DecoCastle") ? new StructureTFDecoratorCastle() : new StructureTFDecorator())))));
    }
}
