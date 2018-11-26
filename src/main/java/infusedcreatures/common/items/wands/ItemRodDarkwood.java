package infusedcreatures.common.items.wands;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import infusedcreatures.common.InfusedCreatures;
import infusedcreatures.common.items.ModItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ItemRodDarkwood extends Item {

    public ItemRodDarkwood(/*String name*/) {
        //super(name);
        setMaxStackSize(64);
        setCreativeTab(InfusedCreatures.tabIC);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon icon;

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.icon = ir.registerIcon("infusedcreatures:rod_darkwood");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return this.icon;
    }
}
