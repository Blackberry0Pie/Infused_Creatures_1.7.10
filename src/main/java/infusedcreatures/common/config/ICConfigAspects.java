package infusedcreatures.common.config;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class ICConfigAspects {
    public static void init() {
        registerItemAspects();
        registerEntityAspects();
    }

    private static void registerEntityAspects() {
        ThaumcraftApi.registerEntityTag("infusedcreatures.chickenzombie", new AspectList().add(Aspect.UNDEAD, 1).add(Aspect.BEAST, 2).add(Aspect.FLIGHT, 2),
                new ThaumcraftApi.EntityTagsNBT[0]);

        ThaumcraftApi.registerEntityTag("infusedcreatures.cowzombie", new AspectList().add(Aspect.UNDEAD, 2).add(Aspect.BEAST, 3).add(Aspect.EARTH, 3),
                new ThaumcraftApi.EntityTagsNBT[0]);

        ThaumcraftApi.registerEntityTag("infusedcreatures.spiderhouse", new AspectList().add(Aspect.TRAP, 2).add(Aspect.MAN, 1).add(Aspect.BEAST, 1).add(Aspect.DARKNESS, 1),
                new ThaumcraftApi.EntityTagsNBT[0]);

        ThaumcraftApi.registerEntityTag("infusedcreatures.clam", new AspectList().add(Aspect.WATER, 2).add(Aspect.BEAST, 1), new ThaumcraftApi.EntityTagsNBT[0]);

        ThaumcraftApi.registerEntityTag("infusedcreatures.clamender", new AspectList().add(Aspect.WATER, 2).add(Aspect.BEAST, 1).add(Aspect.ELDRITCH, 4),
                new ThaumcraftApi.EntityTagsNBT[0]);
    }

    private static void registerItemAspects() {
        ThaumcraftApi.registerObjectTag(new ItemStack(ICConfigItems.itemSoulStone, 1, 0),
                new AspectList().add(Aspect.BEAST, 4).add(Aspect.SOUL, 4).add(Aspect.TRAP, 4).add(Aspect.HUNGER, 4).add(Aspect.GREED, 4).add(Aspect.CRYSTAL, 4));

        ThaumcraftApi.registerObjectTag(new ItemStack(ICConfigItems.itemSoulStone, 1, 1),
                new AspectList().add(Aspect.BEAST, 6).add(Aspect.SOUL, 6).add(Aspect.TRAP, 4).add(Aspect.HUNGER, 4).add(Aspect.GREED, 4).add(Aspect.CRYSTAL, 4));

        for (int i = 2; i < 7; i++) {
            ThaumcraftApi.registerObjectTag(new ItemStack(ICConfigItems.itemSoulStone, 1, i),
                    new AspectList().add(Aspect.BEAST, 8).add(Aspect.SOUL, 8).add(Aspect.TRAP, 4).add(Aspect.HUNGER, 4).add(Aspect.GREED, 4).add(Aspect.CRYSTAL, 4));
        }

        ThaumcraftApi.registerObjectTag(new ItemStack(ICConfigItems.itemPearl), new AspectList().add(Aspect.WATER, 1).add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1));

        ThaumcraftApi.registerObjectTag(new ItemStack(ICConfigItems.itemPearlForceps), new AspectList().add(Aspect.METAL, 5).add(Aspect.TOOL, 1));

        ThaumcraftApi.registerObjectTag(new ItemStack(ICConfigBlocks.blockCreeperJar),
                new AspectList().add(Aspect.BEAST, 4).add(Aspect.ELDRITCH, 4).add(Aspect.MAGIC, 4).add(Aspect.VOID, 4));

        ThaumcraftApi.registerObjectTag(new ItemStack(ICConfigItems.itemSquinkWell),
                new AspectList().add(Aspect.DARKNESS, 4).add(Aspect.TOOL, 8).add(Aspect.BEAST, 1).add(Aspect.SLIME, 4).add(Aspect.SENSES, 4));

        ThaumcraftApi.registerObjectTag(new ItemStack(ICConfigItems.itemClamShell), new AspectList().add(Aspect.WATER, 2).add(Aspect.EARTH, 4).add(Aspect.BEAST, 1));
    }
}
