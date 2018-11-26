package infusedcreatures.common.config;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapedOreRecipe;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemCrystalEssence;
import thaumcraft.common.lib.crafting.ArcaneSceptreRecipe;
import thaumcraft.common.lib.crafting.ArcaneWandRecipe;

public class ICConfigRecipes {
    public static void init() {
        initializeSmelting();
        initializeNormalRecipes();
        initializeArcaneRecipes();
        initializeInfusionRecipes();
        initializeAlchemyRecipes();
    }

    private static void initializeNormalRecipes() {
        // CraftingManager.getInstance().addRecipe(new
        // ItemStack(ICConfigItems.itemPearlForceps), new Object[] { " A ", "A
        // A", "A A", 'A', Items.iron_ingot });
        ICConfigResearch.recipes.put("PearlForceps", oreDictRecipe(new ItemStack(ICConfigItems.itemPearlForceps), new Object[] { " A ", "A A", "A A", 'A', Items.iron_ingot }));

        // for (int a = 0; a < 48; a++) {
        // ConfigResearch.recipes.put("CrystalizedSlab" + a, oreDictRecipe(new
        // ItemStack(ICConfigBlocks.blockCrystalStab, 1, a), new Object[] {
        // "SSS", "S S", "SSS", Character.valueOf('S'), new
        // ItemStack(ConfigItems.itemShard, 1, a) }));
        // }
    }

    private static void initializeSmelting() {
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ICConfigItems.itemClamShell, 1), new ItemStack(Blocks.sand, 8), 0.0F);
    }

    private static void initializeArcaneRecipes() {
//        ArcaneWandRecipe wr = new ArcaneWandRecipe();
//        ThaumcraftApi.getCraftingRecipes().add(wr);
//        
//        ArcaneSceptreRecipe sr = new ArcaneSceptreRecipe();
//        ThaumcraftApi.getCraftingRecipes().add(sr);
        
        // creeper head, charge gem
        for (int i = 0; i < 10; i++) {
            ItemStack result = new ItemStack(ICConfigItems.itemSoulStone, 1, 0);
            result.setTagCompound(new NBTTagCompound());
            result.stackTagCompound.setShort("KillCount", (short) Math.min(i + 5, 10));
            result.stackTagCompound.setString("Entity", "Creeper");
            if (i < 5) {
                result.setItemDamage(1);
            } else {
                result.setItemDamage(6);
            }

            ItemStack in = new ItemStack(ICConfigItems.itemSoulStone, 1, 0);
            if (i > 0) {
                in.setTagCompound(new NBTTagCompound());
                in.stackTagCompound.setShort("KillCount", (short) i);
                in.stackTagCompound.setString("Entity", "Creeper");
                in.setItemDamage(1);
            }

            ICConfigResearch.recipes.put("PartlyChargeWithCreeperHead" + i, ThaumcraftApi.addShapelessArcaneCraftingRecipe("CREEPERJAR", result,
                    new AspectList().add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3).add(Aspect.ORDER, 3), new Object[] { new ItemStack(Items.skull, 1, 4), in }));
        }

        // 2x creeper head, fully charge gem
        ItemStack fullyChargedGem = new ItemStack(ICConfigItems.itemSoulStone, 1, 0);
        fullyChargedGem.setTagCompound(new NBTTagCompound());
        fullyChargedGem.stackTagCompound.setShort("KillCount", (short) 10);
        fullyChargedGem.stackTagCompound.setString("Entity", "Creeper");
        fullyChargedGem.setItemDamage(6);
        ICConfigResearch.recipes.put("FullyChargeWithCreeperHeads",
                ThaumcraftApi.addShapelessArcaneCraftingRecipe("CREEPERJAR", fullyChargedGem, new AspectList().add(Aspect.FIRE, 5).add(Aspect.ENTROPY, 5).add(Aspect.ORDER, 5),
                        new Object[] { new ItemStack(Items.skull, 1, 4), new ItemStack(Items.skull, 1, 4), new ItemStack(ICConfigItems.itemSoulStone, 1, 0) }));

        // Crystalized Essence Blocks
        int count = 0;
        for (Aspect aspect : Aspect.aspects.values()) {
            ItemStack essenceBlock = new ItemStack(ICConfigItems.itemCrystalEssence, 1, 0);
            ((IEssentiaContainerItem) essenceBlock.getItem()).setAspects(essenceBlock, new AspectList().add(aspect, 8));

            ItemStack crystal = new ItemStack(ConfigItems.itemCrystalEssence, 1, 0);
            ((IEssentiaContainerItem) crystal.getItem()).setAspects(crystal, new AspectList().add(aspect, 1));

            // (String research, ItemStack result, AspectList aspects, Object...
            // recipe)
            ICConfigResearch.recipes.put("EssenceBlock" + count, ThaumcraftApi.addArcaneCraftingRecipe("ESSENTIACRYSTAL", essenceBlock, new AspectList().add(Aspect.ORDER, 4),
                    new Object[] { "CCC", "C C", "CCC", Character.valueOf('C'), crystal }));
            count++;
        }
    }

    private static void initializeInfusionRecipes() {
        // addInfusionCraftingRecipe(String research, Object result, int
        // instability, AspectList aspects, ItemStack input, ItemStack[] recipe)
        ICConfigResearch.recipes.put("SoulStone",
                ThaumcraftApi.addInfusionCraftingRecipe("SOULSTONE", new ItemStack(ICConfigItems.itemSoulStone, 1), 6,
                        new AspectList().add(Aspect.VOID, 4).add(Aspect.SOUL, 4).add(Aspect.TRAP, 4).add(Aspect.HUNGER, 4), new ItemStack(Items.diamond),
                        new ItemStack[] { new ItemStack(Blocks.soul_sand), new ItemStack(Blocks.soul_sand) }));

        ItemStack chickenStone = new ItemStack(ICConfigItems.itemSoulStone, 1, 2);
        chickenStone.setTagCompound(new NBTTagCompound());
        chickenStone.stackTagCompound.setShort("KillCount", (short) 10);
        chickenStone.stackTagCompound.setString("Entity", "Chicken");
        ICConfigResearch.recipes.put("ZombieChicken",
                ThaumcraftApi.addInfusionCraftingRecipe("ZOMBIECHICKEN", new ItemStack(Items.spawn_egg, 4, ICConfigEntities.getZombieChickenID()), 4,
                        new AspectList().add(Aspect.UNDEAD, 4).add(Aspect.HUNGER, 8).add(Aspect.FLIGHT, 8).add(Aspect.DEATH, 8), chickenStone,
                        new ItemStack[] { new ItemStack(Items.chicken), new ItemStack(Items.feather), new ItemStack(Items.egg) }));

        ItemStack cowStone = new ItemStack(ICConfigItems.itemSoulStone, 1, 3);
        cowStone.setTagCompound(new NBTTagCompound());
        cowStone.stackTagCompound.setShort("KillCount", (short) 10);
        cowStone.stackTagCompound.setString("Entity", "Cow");
        ICConfigResearch.recipes.put("ZombieCow",
                ThaumcraftApi.addInfusionCraftingRecipe("ZOMBIECOW", new ItemStack(Items.spawn_egg, 4, ICConfigEntities.getZombieCowID()), 4,
                        new AspectList().add(Aspect.UNDEAD, 4).add(Aspect.HUNGER, 8).add(Aspect.CROP, 8).add(Aspect.DEATH, 8), cowStone,
                        new ItemStack[] { new ItemStack(Items.beef), new ItemStack(Items.leather), new ItemStack(Items.egg) }));

        ItemStack spiderStone = new ItemStack(ICConfigItems.itemSoulStone, 1, 4);
        spiderStone.setTagCompound(new NBTTagCompound());
        spiderStone.stackTagCompound.setShort("KillCount", (short) 10);
        spiderStone.stackTagCompound.setString("Entity", "Spider");
        ICConfigResearch.recipes.put("HouseSpider",
                ThaumcraftApi.addInfusionCraftingRecipe("HOUSESPIDER", new ItemStack(Items.spawn_egg, 4, ICConfigEntities.getHouseSpiderID()), 4,
                        new AspectList().add(Aspect.MAN, 4).add(Aspect.DARKNESS, 8).add(Aspect.TRAP, 8).add(Aspect.CLOTH, 8), spiderStone,
                        new ItemStack[] { new ItemStack(Items.string), new ItemStack(Items.spider_eye), new ItemStack(Items.egg) }));

        ItemStack clamStone = new ItemStack(ICConfigItems.itemSoulStone, 1, 5);
        clamStone.setTagCompound(new NBTTagCompound());
        clamStone.stackTagCompound.setShort("KillCount", (short) 10);
        clamStone.stackTagCompound.setString("Entity", "infusedcreatures.clam");
        ICConfigResearch.recipes.put("EnderClam",
                ThaumcraftApi.addInfusionCraftingRecipe("ENDERCLAM", new ItemStack(Items.spawn_egg, 4, ICConfigEntities.getEnderClamID()), 5,
                        new AspectList().add(Aspect.ELDRITCH, 4).add(Aspect.WATER, 8).add(Aspect.TRAVEL, 8).add(Aspect.BEAST, 8), clamStone,
                        new ItemStack[] { new ItemStack(ICConfigItems.itemClamShell), new ItemStack(ICConfigItems.itemPearl), new ItemStack(Items.egg) }));

        ItemStack orderShard = new ItemStack(ConfigItems.itemCrystalEssence, 1, 0);
        ((ItemCrystalEssence) orderShard.getItem()).setAspects(orderShard, new AspectList().add(Aspect.ORDER, 1));
        ItemStack creeperStone = new ItemStack(ICConfigItems.itemSoulStone, 1, 6);
        creeperStone.setTagCompound(new NBTTagCompound());
        creeperStone.stackTagCompound.setShort("KillCount", (short) 10);
        creeperStone.stackTagCompound.setString("Entity", "Creeper");
        ICConfigResearch.recipes.put("CreeperJar",
                ThaumcraftApi.addInfusionCraftingRecipe("CREEPERJAR", new ItemStack(ICConfigBlocks.blockCreeperJar, 8), 10,
                        new AspectList().add(Aspect.BEAST, 4).add(Aspect.ELDRITCH, 4).add(Aspect.MAGIC, 4).add(Aspect.VOID, 4), creeperStone,
                        /* [ConfigItems.itemResource, 1, 3] is quicksilver */
                        new ItemStack[] { new ItemStack(Blocks.glass), new ItemStack(ConfigItems.itemResource, 1, 3), new ItemStack(Blocks.glass),
                                new ItemStack(ConfigItems.itemResource, 1, 3), new ItemStack(Blocks.glass), new ItemStack(ConfigItems.itemResource, 1, 3),
                                new ItemStack(Blocks.glass), orderShard }));

        ICConfigResearch.recipes.put("CreeperInhibitor",
                ThaumcraftApi.addInfusionCraftingRecipe("CREEPERINHIBITOR", new ItemStack(ICConfigItems.itemCreeperInhibitor, 8), 5,
                        new AspectList().add(Aspect.ORDER, 4).add(Aspect.ENERGY, 4).add(Aspect.MAGIC, 4).add(Aspect.VOID, 4), new ItemStack(Blocks.wool),
                        /* [ConfigItems.itemResource, 1, 2] is thaumiumingot */
                        new ItemStack[] { orderShard, new ItemStack(ConfigItems.itemResource, 1, 2), orderShard, new ItemStack(ConfigItems.itemResource, 1, 2) }));

        ItemStack squidStone = new ItemStack(ICConfigItems.itemSoulStone, 1, 7);
        squidStone.setTagCompound(new NBTTagCompound());
        squidStone.stackTagCompound.setShort("KillCount", (short) 10);
        squidStone.stackTagCompound.setString("Entity", "Squid");
        ICConfigResearch.recipes.put("SquinkWell",
                ThaumcraftApi.addInfusionCraftingRecipe("SQUINKWELL", new ItemStack(ICConfigItems.itemSquinkWell), 2,
                        new AspectList().add(Aspect.TOOL, 4).add(Aspect.SENSES, 4).add(Aspect.DARKNESS, 4).add(Aspect.SLIME, 8), squidStone,
                        new ItemStack[] { new ItemStack(ConfigItems.itemInkwell), new ItemStack(Items.dye, 1, 0), new ItemStack(Items.dye, 1, 0) }));
    }

    static IRecipe oreDictRecipe(ItemStack res, Object[] params) {
        IRecipe rec = new ShapedOreRecipe(res, params);
        CraftingManager.getInstance().getRecipeList().add(rec);
        return rec;
    }

    private static void initializeAlchemyRecipes() {
        ICConfigResearch.recipes.put("AltSand", ThaumcraftApi.addCrucibleRecipe("SANDDUPE", new ItemStack(Blocks.sand, 2, 0), new ItemStack(Blocks.sand),
                new AspectList().merge(Aspect.EARTH, 1).merge(Aspect.ENTROPY, 1)));
    }
}