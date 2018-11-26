package infusedcreatures.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import infusedcreatures.common.InfusedCreatures;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.IScribeTools;
import thaumcraft.common.Thaumcraft;

public class SquinkWell extends Item implements IScribeTools {
    @SideOnly(Side.CLIENT)
    public IIcon icon;
    private int ticks = 0;

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.icon = ir.registerIcon("infusedcreatures:squinkwell");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return this.icon;
    }

    public SquinkWell() {
        setMaxStackSize(1);
        setMaxDamage(100);
        setCreativeTab(InfusedCreatures.tabIC);
    }

    public void setDamage(ItemStack stack, int damage) {
    }

    // public boolean isDamageable(){
    // return false;
    // }

    // public void onUpdate(ItemStack stack, World world, Entity entity, int
    // p_77663_4_, boolean p_77663_5_) {
    // super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
    // //if (!world.isRemote && stack.isItemDamaged() && ticks++ % 200 == 0){
    // if ((stack.isItemDamaged()) && (entity != null) && (entity.ticksExisted %
    // 200 == 0)) {
    // stack.damageItem(-1, (EntityLivingBase) entity);
    // //stack.setItemDamage(stack.getItemDamage() - 1);
    // }
    // }
}