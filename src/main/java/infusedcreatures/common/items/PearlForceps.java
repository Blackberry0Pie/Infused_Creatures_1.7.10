package infusedcreatures.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import infusedcreatures.common.InfusedCreatures;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class PearlForceps extends Item {
    @SideOnly(Side.CLIENT)
    public IIcon icon;

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.icon = ir.registerIcon("infusedcreatures:pearlforceps");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return this.icon;
    }

    private final static Item.ToolMaterial material = Item.ToolMaterial.IRON;

    public PearlForceps() {
        // super(material);
        setCreativeTab(InfusedCreatures.tabIC);

        // Enchantment.addToBookList(enchantment);
        // EnumHelper.addEnchantmentType("looting");
    }

    // damage vs entities
    public float func_150931_i() {
        return 1.0F;
    }

    // block destroy speed
    public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_) {
        return 1.0F;
    }

    public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
        return true;
    }

    public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return false;
    }

    // public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_,
    // EntityPlayer p_77659_3_)

    // web check
    public boolean func_150897_b(Block p_150897_1_) {
        return false;
    }

    public EnumAction getItemUseAction(ItemStack is) {
        return EnumAction.none;
    }

    // public Multimap getItemAttributeModifiers(){
}
