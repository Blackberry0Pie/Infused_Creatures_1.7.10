package infusedcreatures.common.config;

import cpw.mods.fml.common.registry.GameRegistry;
import infusedcreatures.common.InfusedCreatures;
import infusedcreatures.common.items.ItemPearl;
import infusedcreatures.common.items.ModItem;
import infusedcreatures.common.items.wands.ItemRodDarkwood;
import infusedcreatures.common.items.wands.ModWandRod;
import infusedcreatures.common.items.wands.RodDarkwood;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ICConfigItems {
    public static Item itemSoulStone;
    public static Item itemPearl;
    public static Item itemPearlForceps;
    public static Item itemClamShell;
    public static Item itemSquinkWell;
    public static Item itemCrystalEssence;
    public static Item itemCreeperInhibitor;
    public static Item itemRodDarkwood;
    public static WandRod WAND_ROD_DARKWOOD;

    // public static Item itemLightBeamFocus;

    public static void init() {
        initializeItems();
    }

    private static void initializeItems() {
        //itemPearl = new ItemPearl("pearl");
        itemPearl = new infusedcreatures.common.items.ItemPearl().setUnlocalizedName("infusedcreatures_pearl");
        GameRegistry.registerItem(itemPearl, "infusedcreatures_pearl");
        itemSoulStone = new infusedcreatures.common.items.SoulStone().setUnlocalizedName("infusedcreatures_soulstone");
        GameRegistry.registerItem(itemSoulStone, "infusedcreatures_soulstone");
        itemPearlForceps = new infusedcreatures.common.items.PearlForceps().setUnlocalizedName("infusedcreatures_pearlforceps");
        GameRegistry.registerItem(itemPearlForceps, "infusedcreatures_pearlforceps");
        itemClamShell = new infusedcreatures.common.items.ClamShell().setUnlocalizedName("infusedcreatures_clamshell");
        GameRegistry.registerItem(itemClamShell, "infusedcreatures_clamshell");
        itemSquinkWell = new infusedcreatures.common.items.SquinkWell().setUnlocalizedName("infusedcreatures_squinkwell");
        GameRegistry.registerItem(itemSquinkWell, "infusedcreatures_squinkwell");
        itemCrystalEssence = new infusedcreatures.common.items.ItemCrystalEssence().setUnlocalizedName("infusedcreatures_crystalessence");
        GameRegistry.registerItem(itemCrystalEssence, "infusedcreatures_crystalessence");
        itemCreeperInhibitor = new infusedcreatures.common.items.ItemCreeperInhibitor().setUnlocalizedName("infusedcreatures_creeperinhibitor");
        GameRegistry.registerItem(itemCreeperInhibitor, "infusedcreatures_creeperinhibitor");
        //itemRodDarkwood = new ItemRodDarkwood("rod_darkwood");
        itemRodDarkwood = new infusedcreatures.common.items.wands.ItemRodDarkwood().setUnlocalizedName("infusedcreatures_rod_darkwood");
        GameRegistry.registerItem(itemRodDarkwood, "infusedcreatures_rod_darkwood");
        WAND_ROD_DARKWOOD = new WandRod("darkwood", 150, new ItemStack(itemRodDarkwood), 12, new ResourceLocation("infusedcreatures", "textures/models/roddarkwood.png"));
        
        // itemLightBeamFocus = new
        // infusedcreatures.common.items.wands.foci.ItemLightBeam().setUnlocalizedName("infusedcreatures_lightbeamfocus");
        // GameRegistry.registerItem(itemLightBeamFocus,
        // "infusedcreatures_lightbeamfocus");
    }
}
