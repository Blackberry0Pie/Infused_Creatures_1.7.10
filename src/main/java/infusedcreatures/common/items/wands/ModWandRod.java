package infusedcreatures.common.items.wands;

import infusedcreatures.common.config.ICConfigItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.wands.WandRod;

public abstract class ModWandRod extends WandRod {
    public ModWandRod(String tag, int capacity, ItemStack item, int craftCost, boolean glow) {
        super(tag.toUpperCase(), capacity, item, craftCost, new ResourceLocation("infusedcreatures:textures/model/rod_" + tag + ".png"));
        setGlowing(glow);
    }
}