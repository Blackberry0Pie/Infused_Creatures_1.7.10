package infusedcreatures.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import infusedcreatures.common.InfusedCreatures;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ItemCreeperInhibitor extends Item {
    @SideOnly(Side.CLIENT)
    public IIcon icon;

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.icon = ir.registerIcon("infusedcreatures:creeperinhibitor");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return this.icon;
    }

    public ItemCreeperInhibitor() {
        setMaxStackSize(64);
        setCreativeTab(InfusedCreatures.tabIC);
    }
}
