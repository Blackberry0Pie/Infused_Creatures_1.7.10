package infusedcreatures.common.items;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import infusedcreatures.common.InfusedCreatures;
import infusedcreatures.common.config.ICConfigBlocks;
import infusedcreatures.common.tiles.TileCrystalEssence;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.common.Thaumcraft;

public class ItemCrystalEssence extends Item implements IEssentiaContainerItem {
    public final int itemUseDuration;
    public IIcon icon;

    public ItemCrystalEssence() {
        super();
        this.itemUseDuration = 10;
        setMaxStackSize(64);
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(InfusedCreatures.tabIC);
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return this.itemUseDuration;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.icon = ir.registerIcon("infusedcreatures:crystalessence");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        return this.icon;
    }

    Random rand = new Random();
    static Aspect[] displayAspects = (Aspect[]) Aspect.aspects.values().toArray(new Aspect[0]);

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (Aspect tag : Aspect.aspects.values()) {
            ItemStack i = new ItemStack(this, 1, 0);
            setAspects(i, new AspectList().add(tag, 8));
            par3List.add(i);
        }
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        AspectList aspects = getAspects(stack);
        if ((aspects != null) && (aspects.size() > 0)) {
            for (Aspect tag : aspects.getAspectsSorted()) {
                if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.getCommandSenderName(), tag)) {
                    list.add(tag.getName() + " x" + aspects.getAmount(tag));
                } else {
                    list.add(net.minecraft.util.StatCollector.translateToLocal("tc.aspect.unknown"));
                }
            }
        }
        super.addInformation(stack, player, list, par4);
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int par2) {
        if (getAspects(stack) != null) {
            return getAspects(stack).getAspects()[0].getColor();
        }
        int idx = (int) (System.currentTimeMillis() / 500L % displayAspects.length);
        return displayAspects[idx].getColor();
    }

    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if ((!par2World.isRemote) && (!par1ItemStack.hasTagCompound())) {
            setAspects(par1ItemStack, new AspectList().add(displayAspects[this.rand.nextInt(displayAspects.length)], 8));
        }
        super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
    }

    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par1ItemStack.hasTagCompound()) {
            setAspects(par1ItemStack, new AspectList().add(displayAspects[this.rand.nextInt(displayAspects.length)], 8));
        }
    }

    public AspectList getAspects(ItemStack itemstack) {
        if (itemstack.hasTagCompound()) {
            AspectList aspects = new AspectList();
            aspects.readFromNBT(itemstack.getTagCompound());
            return aspects.size() > 0 ? aspects : null;
        }
        return null;
    }

    public void setAspects(ItemStack itemstack, AspectList aspects) {
        if (!itemstack.hasTagCompound()) itemstack.setTagCompound(new net.minecraft.nbt.NBTTagCompound());
        aspects.writeToNBT(itemstack.getTagCompound());
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int side, float par8, float par9, float par10) {
        if (!par2EntityPlayer.canPlayerEdit(x, y, z, side, par1ItemStack)) {
            return false;
        }
        if (side == 0) {
            y--;
        }
        if (side == 1) {
            y++;
        }
        if (side == 2) {
            z--;
        }
        if (side == 3) {
            z++;
        }
        if (side == 4) {
            x--;
        }
        if (side == 5) {
            x++;
        }
        if (par3World.isAirBlock(x, y, z)) {
            int k1 = ICConfigBlocks.blockCrystalEssence.onBlockPlaced(par3World, x, y, z, side, par8, par9, par10, 0);
            par3World.setBlock(x, y, z, ICConfigBlocks.blockCrystalEssence, k1, 2);

            TileEntity tile = par3World.getTileEntity(x, y, z);
            if ((tile != null) && ((tile instanceof TileCrystalEssence)) && (getAspects(par1ItemStack) != null) && (getAspects(par1ItemStack).size() > 0)) {

                ((TileCrystalEssence) tile).aspect = getAspects(par1ItemStack).getAspects()[0];
            }

            if (!par2EntityPlayer.capabilities.isCreativeMode) {
                par1ItemStack.stackSize -= 1;
            }
        }

        return true;
    }
}
