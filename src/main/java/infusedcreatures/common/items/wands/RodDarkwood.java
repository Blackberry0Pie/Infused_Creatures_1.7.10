package infusedcreatures.common.items.wands;

import infusedcreatures.common.config.ICConfigItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import thaumcraft.api.wands.WandRod;

public class RodDarkwood
  extends ModWandRod
{
  //static ResourceLocation res = new ResourceLocation("infusedcreatures:textures/model/roddarkwood.png");
  //private String unlocalizedName;
  
  public RodDarkwood() {
    //ModWandRod(String tag, int capacity, ItemStack item, int craftCost, boolean glow)
    super("darkwood", 150, new ItemStack(ICConfigItems.itemRodDarkwood), 10, false);
  }
  
//  public ItemRodDarkwood(String s)
//  {
//    super(s, 1000, new ItemStack(ICConfigItems.itemRodDarkwood, 1, 9), 10);
//    setGlowing(true);
//  }
  
//  public ResourceLocation getTexture()
//  {
//    return this.res;
//  }
}