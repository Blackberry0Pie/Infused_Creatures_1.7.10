package infusedcreatures.common.config;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class ICConfigResearch {
    public static HashMap<String, Object> recipes = new HashMap();
    public static ItemStack wand = null;

    public static void init() {
        // wand = new ItemStack(ConfigItems.itemWandCasting, 1, 0);
        // ((ItemWandCasting)wand.getItem()).setCap(wand,
        // ConfigItems.WAND_CAP_IRON);
        // ((ItemWandCasting)wand.getItem()).setRod(wand,
        // ConfigItems.WAND_ROD_WOOD);

        initCategories();
        initInfusedCreatureResearch();
    }

    private static void initCategories() {
        ResearchCategories.registerCategory("INFUSEDCREATURES", new ResourceLocation("infusedcreatures:textures/misc/tab-icon.png"),
                new ResourceLocation("infusedcreatures:textures/gui/researchback_INFUSEDCREATURES.png"));
    }

    private static void initInfusedCreatureResearch() {
        new ResearchItem("SANDDUPE", "INFUSEDCREATURES", new AspectList().add(Aspect.EARTH, 3).add(Aspect.EXCHANGE, 3), -2, -3, 0,
                new ResourceLocation("thaumcraft", "textures/misc/r_alchmult.png"))
                        .setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SANDDUPE.1"), new ResearchPage((CrucibleRecipe) recipes.get("AltSand")) }).setSecondary()
                        .setParents(new String[] { "ALCHEMICALDUPLICATION" }).registerResearchItem();

        ArrayList<IArcaneRecipe> rc = new ArrayList();
        for (int a = 0; a < Aspect.aspects.values().size(); a++) {
            rc.add((IArcaneRecipe) recipes.get("EssenceBlock" + a));
        }
        new ResearchItem("ESSENCEBLOCK", "INFUSEDCREATURES", new AspectList().add(Aspect.MAGIC, 3).add(Aspect.ORDER, 3), +2, -3, 0, new ItemStack(ICConfigItems.itemCrystalEssence))
                .setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ESSENCEBLOCK.1"), new ResearchPage((IArcaneRecipe[]) rc.toArray(new IArcaneRecipe[0])) })
                .setSecondary().setParents(new String[] { "ESSENTIACRYSTAL" }).registerResearchItem();

        new ResearchItem("SOULSTONE", "INFUSEDCREATURES", new AspectList().add(Aspect.SOUL, 3).add(Aspect.TRAP, 6).add(Aspect.HUNGER, 3), 0, 0, 2,
                new ItemStack(ICConfigItems.itemSoulStone))
                        .setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SOULSTONE.1"), new ResearchPage((InfusionRecipe) recipes.get("SoulStone")) }).setSpecial()
                        .setParents(new String[] { "INFUSION" }).registerResearchItem();
        ThaumcraftApi.addWarpToResearch("SOULSTONE", 1);
        ThaumcraftApi.addWarpToItem(new ItemStack(ICConfigItems.itemSoulStone), 1);

        new ResearchItem("ZOMBIECHICKEN", "INFUSEDCREATURES", new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.HUNGER, 3).add(Aspect.FLIGHT, 6), -3, 2, 1,
                new ItemStack(Items.spawn_egg, 1, ICConfigEntities.getZombieChickenID()))
                        .setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ZOMBIECHICKEN.1"), new ResearchPage((InfusionRecipe) recipes.get("ZombieChicken")) })
                        /*
                         * .setHidden().setEntityTriggers(new String[] {
                         * "Chicken" })
                         */.setParents(new String[] { "SOULSTONE" }).registerResearchItem();
        ThaumcraftApi.addWarpToResearch("ZOMBIECHICKEN", 1);

        new ResearchItem("ZOMBIECOW", "INFUSEDCREATURES", new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.HUNGER, 3).add(Aspect.EARTH, 6), -3, -2, 1,
                new ItemStack(Items.spawn_egg, 1, ICConfigEntities.getZombieCowID()))
                        .setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ZOMBIECOW.1"), new ResearchPage((InfusionRecipe) recipes.get("ZombieCow")) })
                        /*
                         * .setHidden().setEntityTriggers(new String[] { "Cow"
                         * })
                         */.setParents(new String[] { "SOULSTONE" }).registerResearchItem();
        ThaumcraftApi.addWarpToResearch("ZOMBIECOW", 1);

        new ResearchItem("HOUSESPIDER", "INFUSEDCREATURES", new AspectList().add(Aspect.MAN, 3).add(Aspect.DARKNESS, 3).add(Aspect.TRAP, 6), 0, -3, 1,
                new ItemStack(Items.spawn_egg, 1, ICConfigEntities.getHouseSpiderID()))
                        .setPages(new ResearchPage[] { new ResearchPage("tc.research_page.HOUSESPIDER.1"), new ResearchPage((InfusionRecipe) recipes.get("HouseSpider")) })
                        /*
                         * .setHidden().setEntityTriggers(new String[] {
                         * "Spider" })
                         */.setParents(new String[] { "SOULSTONE" }).registerResearchItem();

        new ResearchItem("ENDERCLAM", "INFUSEDCREATURES", new AspectList().add(Aspect.ELDRITCH, 3).add(Aspect.WATER, 3).add(Aspect.TRAVEL, 6), 0, 3, 1,
                new ItemStack(Items.spawn_egg, 1, ICConfigEntities.getEnderClamID()))
                        .setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ENDERCLAM.1"), new ResearchPage((InfusionRecipe) recipes.get("EnderClam")),
                                new ResearchPage("tc.research_page.ENDERCLAM.2"), new ResearchPage((IRecipe) recipes.get("PearlForceps")) })
                        /*
                         * .setHidden().setEntityTriggers(new String[] {
                         * "infusedcreatures.clam" })
                         */.setParents(new String[] { "SOULSTONE" }).registerResearchItem();

        rc = new ArrayList();
        for (int i = 0; i < 10; i++) {
            rc.add((IArcaneRecipe) recipes.get("PartlyChargeWithCreeperHead" + i));
        }
        new ResearchItem("CREEPERJAR", "INFUSEDCREATURES", new AspectList().add(Aspect.ELDRITCH, 3).add(Aspect.MAGIC, 3).add(Aspect.VOID, 6), +3, 2, 1,
                new ItemStack(ICConfigBlocks.blockCreeperJar))
                        .setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CREEPERJAR.1"), new ResearchPage((InfusionRecipe) recipes.get("CreeperJar")),
                                new ResearchPage((IArcaneRecipe[]) rc.toArray(new IArcaneRecipe[0])),
                                new ResearchPage((IArcaneRecipe) recipes.get("FullyChargeWithCreeperHeads")) })
                        /*
                         * .setHidden().setEntityTriggers(new String[] {
                         * "Creeper" })
                         */.setParents(new String[] { "SOULSTONE" }).registerResearchItem();

        new ResearchItem("CREEPERINHIBITOR", "INFUSEDCREATURES", new AspectList().add(Aspect.ORDER, 3).add(Aspect.VOID, 3).add(Aspect.ENERGY, 3).add(Aspect.MAGIC, 3), +4, 3, 1,
                new ItemStack(ICConfigItems.itemCreeperInhibitor))
                        .setPages(
                                new ResearchPage[] { new ResearchPage("tc.research_page.CREEPERINHIBITOR.1"), new ResearchPage((InfusionRecipe) recipes.get("CreeperInhibitor")) })
                        .setConcealed().setParents(new String[] { "CREEPERJAR" }).registerResearchItem();

        new ResearchItem("SQUINKWELL", "INFUSEDCREATURES", new AspectList().add(Aspect.TOOL, 3).add(Aspect.DARKNESS, 3).add(Aspect.SLIME, 6).add(Aspect.SENSES, 6), +3, -2, 1,
                new ItemStack(ICConfigItems.itemSquinkWell))
                        .setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SQUINKWELL.1"), new ResearchPage((InfusionRecipe) recipes.get("SquinkWell")) })
                        /*
                         * .setHidden().setEntityTriggers(new String[] { "Squid"
                         * })
                         */.setParents(new String[] { "SOULSTONE" }).registerResearchItem();
        
        new ResearchItem("ROD_DARKWOOD", "INFUSEDCREATURES", new AspectList().add(Aspect.TOOL, 3).add(Aspect.TAINT, 6).add(Aspect.MAGIC, 3), -3, -3, 1, new ItemStack(ICConfigItems.itemRodDarkwood)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_DARKWOOD.1")/*, new ResearchPage((IArcaneRecipe)recipes.get("WandRodDarkwood"))*/ }).setParents(new String[] { "ELDRITCHMAJOR" }).registerResearchItem();
    }
}
