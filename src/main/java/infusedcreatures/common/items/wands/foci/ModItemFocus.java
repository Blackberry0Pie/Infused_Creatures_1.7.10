package infusedcreatures.common.items.wands.foci;

import cpw.mods.fml.common.registry.GameRegistry;
import infusedcreatures.common.InfusedCreatures;
import net.minecraft.client.renderer.texture.IIconRegister;
import thaumcraft.api.wands.ItemFocusBasic;


public abstract class ModItemFocus
  extends ItemFocusBasic
{
  public ModItemFocus(String name)
  {
    setUnlocalizedName("infusedcreatures_" + name);
    setTextureName("infusedcreatures:" + name);
    if (InfusedCreatures.tabIC != null) {
      setCreativeTab(InfusedCreatures.tabIC);
    }
    
    GameRegistry.registerItem(this, name);
  }
  
  public void registerIcons(IIconRegister ir)
  {
    super.registerIcons(ir);
    this.icon = this.itemIcon;
  }
}
