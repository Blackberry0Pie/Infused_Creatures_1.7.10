package infusedcreatures.common.lib;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import infusedcreatures.common.config.ICConfigItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public final class CreativeTabInfusedCreatures extends CreativeTabs
{
  public CreativeTabInfusedCreatures(int par1, String par2Str)
  {
    super(par1, par2Str);
  }

  @SideOnly(Side.CLIENT)
  public Item getTabIconItem()
  {
    return ICConfigItems.itemSoulStone;
  }
}