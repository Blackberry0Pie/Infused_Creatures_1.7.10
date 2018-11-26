package infusedcreatures.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import infusedcreatures.common.InfusedCreatures;
import net.minecraft.item.Item;

public abstract class ModItem extends Item
{
  public ModItem(String name)
  {
    setUnlocalizedName("infusedcreatures_" + name);
    setTextureName("infusedcreatures:" + name);
    
    if (InfusedCreatures.tabIC != null) {
      setCreativeTab(InfusedCreatures.tabIC);
    }
    
    GameRegistry.registerItem(this, name);
  }
}
