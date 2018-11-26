package infusedcreatures.common.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import infusedcreatures.common.InfusedCreatures;
import infusedcreatures.common.config.ICConfigItems;
import infusedcreatures.common.lib.utils.ShardUtils;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import thaumcraft.common.Thaumcraft;

public class SoulStone extends Item {
    @SideOnly(Side.CLIENT)
    public IIcon iconEmpty;
    @SideOnly(Side.CLIENT)
    public IIcon iconInc;
    @SideOnly(Side.CLIENT)
    public IIcon iconFull;

    public SoulStone() {
        setMaxStackSize(1);
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(InfusedCreatures.tabIC);
    }

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.iconEmpty = ir.registerIcon("infusedcreatures:soulstoneempty");
        this.iconInc = ir.registerIcon("infusedcreatures:soulstoneinc");
        this.iconFull = ir.registerIcon("infusedcreatures:soulstonefull");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int dmg) {
        return dmg == 1 ? this.iconInc : dmg == 0 ? this.iconEmpty : this.iconFull;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        switch (stack.getItemDamage()) {
        case 1:
            return "item.infusedcreatures_soulstone.1";
        case 2:
            return "item.infusedcreatures_soulstone.2";
        case 3:
            return "item.infusedcreatures_soulstone.3";
        case 4:
            return "item.infusedcreatures_soulstone.4";
        case 5:
            return "item.infusedcreatures_soulstone.5";
        case 6:
            return "item.infusedcreatures_soulstone.6";
        case 7:
            return "item.infusedcreatures_soulstone.7";
        default:
            return "item.infusedcreatures_soulstone.0";
        }
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean isAdvanced) {
        if (stack.hasTagCompound() && ShardUtils.isShardBound(stack)) {
            tooltip.add(StatCollector.translateToLocal("tooltip.infusedcreatures." + ShardUtils.getShardBoundEnt(stack) + ".desc"));
            if (ShardUtils.getShardKillCount(stack) >= 0) {
                tooltip.add("Souls: " + ShardUtils.getShardKillCount(stack));
            }
        } else {
            tooltip.add(StatCollector.translateToLocal("tooltip.infusedcreatures.nullsoul.desc"));
        }
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List itemList) {
        ItemStack itemStack = new ItemStack(item);
        itemStack.setItemDamage(0);
        itemList.add(itemStack);
        // for (int i = 2; i < 2 + InfusedCreatures.VALID_ENTITIES.length; i++)
        // {
        // itemStack = new ItemStack(item);
        // itemStack.setItemDamage(i);
        // itemList.add(itemStack);
        // }
        ItemStack chickenStone = new ItemStack(ICConfigItems.itemSoulStone, 1, 2);
        chickenStone.setTagCompound(new NBTTagCompound());
        chickenStone.stackTagCompound.setShort("KillCount", (short) 10);
        chickenStone.stackTagCompound.setString("Entity", "Chicken");
        itemList.add(chickenStone);

        ItemStack cowStone = new ItemStack(ICConfigItems.itemSoulStone, 1, 3);
        cowStone.setTagCompound(new NBTTagCompound());
        cowStone.stackTagCompound.setShort("KillCount", (short) 10);
        cowStone.stackTagCompound.setString("Entity", "Cow");
        itemList.add(cowStone);

        ItemStack spiderStone = new ItemStack(ICConfigItems.itemSoulStone, 1, 4);
        spiderStone.setTagCompound(new NBTTagCompound());
        spiderStone.stackTagCompound.setShort("KillCount", (short) 10);
        spiderStone.stackTagCompound.setString("Entity", "Spider");
        itemList.add(spiderStone);

        ItemStack clamStone = new ItemStack(ICConfigItems.itemSoulStone, 1, 5);
        clamStone.setTagCompound(new NBTTagCompound());
        clamStone.stackTagCompound.setShort("KillCount", (short) 10);
        clamStone.stackTagCompound.setString("Entity", "infusedcreatures.clam");
        itemList.add(clamStone);

        ItemStack creeperStone = new ItemStack(ICConfigItems.itemSoulStone, 1, 6);
        creeperStone.setTagCompound(new NBTTagCompound());
        creeperStone.stackTagCompound.setShort("KillCount", (short) 10);
        creeperStone.stackTagCompound.setString("Entity", "Creeper");
        itemList.add(creeperStone);

        ItemStack squidStone = new ItemStack(ICConfigItems.itemSoulStone, 1, 7);
        squidStone.setTagCompound(new NBTTagCompound());
        squidStone.stackTagCompound.setShort("KillCount", (short) 10);
        squidStone.stackTagCompound.setString("Entity", "Squid");
        itemList.add(squidStone);
    }
}
