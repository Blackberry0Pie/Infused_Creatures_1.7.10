package infusedcreatures.common.config;

import cpw.mods.fml.common.registry.GameRegistry;
import infusedcreatures.common.blocks.BlockCreeperJar;
import infusedcreatures.common.blocks.BlockCreeperJarItem;
import infusedcreatures.common.blocks.BlockCrystalEssence;
//import infusedcreatures.common.blocks.BlockCrystalEssenceItem;
import infusedcreatures.common.tiles.TileCreeperJar;
import infusedcreatures.common.tiles.TileCrystalEssence;
import net.minecraft.block.Block;
import thaumcraft.api.aspects.Aspect;

public class ICConfigBlocks {

    static Aspect[] displayAspects = (Aspect[]) Aspect.aspects.values().toArray(new Aspect[0]);
    public static Block blockCreeperJar;
    public static Block blockCrystalEssence;
    public static int blockCreeperJarRI = -1;
    public static int blockCrystalEssenceRI = -1;

    public static void init() {
        initializeBlocks();
        registerBlocks();
        registerTileEntities();
    }

    private static void initializeBlocks() {
        blockCreeperJar = new BlockCreeperJar().setBlockName("blockCreeperJar");
        blockCrystalEssence = new BlockCrystalEssence().setBlockName("blockCrystalEssence");
        // blockStairsArcaneStone = new BlockCosmeticStairs(blockCosmeticSolid,
        // 7).setBlockName("blockStairsArcaneStone");
    }

    private static void registerBlocks() {
        GameRegistry.registerBlock(blockCreeperJar, BlockCreeperJarItem.class, "blockCreeperJar");
        GameRegistry.registerBlock(blockCrystalEssence, "blockCrystalEssence");
        // GameRegistry.registerBlock(blockCrystalEssence,
        // BlockCrystalEssenceItem.class, "blockCrystalEssence");
        // GameRegistry.registerBlock(blockCosmeticSolid,
        // BlockCosmeticSolidItem.class, "blockCosmeticSolid");
    }

    private static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileCreeperJar.class, "TileCreeperJar");
        GameRegistry.registerTileEntity(TileCrystalEssence.class, "TileCrystalEssence");
    }
}
