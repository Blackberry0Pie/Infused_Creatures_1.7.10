//-----------------------------------------------------------------
//This code is taken from Soul Shards Reborn(SSR)
//Which is an abandoned mod that was released open source, by Moze_Intel
//http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/wip-mods/1445947-1-7-10-soul-shards-reborn-original-soul-shards
//The GitHub source files for this mod have been taken down.
//
//SSR was previously taken from the abandoned mod Soul Shards 2(SS2), by ShadwDrgn
//http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/1285901-1-6-4-forgeirc-v1-0-18-soul-shards-v2-0-15-and
//
//The current(as of Dec 2015) version of SSR has been moved to 'Soul Shards: The Old Ways', by TheSgtPunishment
//Which is released under a proprietary "Open Source, non-commercial, give-credit" license
//http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/2329877-soul-shards-the-old-ways-rc9-update
//https://github.com/SgtPunishment/Soul-Shards-The-Old-Ways
//-----------------------------------------------------------------

package infusedcreatures.common.lib.utils;

import infusedcreatures.common.config.ICConfigItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;

public final class ShardUtils {
    public static ItemStack getShardFromInv(EntityPlayer player, String entity) {
        ItemStack lastResort = null;

        for (int i = 0; i <= 8; i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);

            if ((stack != null) && (stack.getItem() == ICConfigItems.itemSoulStone) && (!hasMaxedKills(stack))) {
                if ((!isShardBound(stack)) && (lastResort == null)) {
                    lastResort = stack;
                } else if (getShardBoundEnt(stack).equals(entity)) {
                    return stack;
                }
            }
        }

        return lastResort;
    }

    public static short getShardKillCount(ItemStack shard) {
        if (!shard.hasTagCompound()) {
            return 0;
        }

        return (short) MathHelper.clamp_int(shard.stackTagCompound.getShort("KillCount"), 0, 10);
    }

    public static void increaseShardKillCount(ItemStack shard, short amount) {
        if ((!shard.hasTagCompound()) || (hasMaxedKills(shard))) {
            return;
        }

        setShardKillCount(shard, getClampedKillCount(getShardKillCount(shard) + amount));
    }

    public static void setShardKillCount(ItemStack shard, short value) {
        if (!shard.hasTagCompound()) {
            shard.setTagCompound(new NBTTagCompound());
        }

        shard.stackTagCompound.setShort("KillCount", value);
        if (value > 0 && value < 10) {
            shard.setItemDamage(1);
        } else if (value == 10) {
            String entName = shard.stackTagCompound.getString("Entity");
            if (entName.equals("Chicken")) {
                shard.setItemDamage(2);
            } else if (entName.equals("Cow")) {
                shard.setItemDamage(3);
            } else if (entName.equals("Spider")) {
                shard.setItemDamage(4);
            } else if (entName.equals("infusedcreatures.clam")) {
                shard.setItemDamage(5);
            } else if (entName.equals("Creeper")) {
                shard.setItemDamage(6);
            } else if (entName.equals("Squid")) {
                shard.setItemDamage(7);
            }
        }
    }

    public static String getShardBoundEnt(ItemStack shard) {
        if (!shard.hasTagCompound()) {
            return "";
        }

        return shard.stackTagCompound.getString("Entity");
    }

    public static void setShardBoundEnt(ItemStack shard, String value) {
        if (!shard.hasTagCompound()) {
            shard.setTagCompound(new NBTTagCompound());
        }

        shard.stackTagCompound.setString("Entity", value);
    }

    public static boolean isShardBound(ItemStack shard) {
        return !getShardBoundEnt(shard).isEmpty();
    }

    public static boolean hasMaxedKills(ItemStack shard) {
        return (isShardBound(shard)) && (getShardKillCount(shard) >= 10);
    }

    public static String getEntityNameTransltated(String unlocalName) {
        if (unlocalName.equals("Wither Skeleton")) {
            return unlocalName;
        }

        String result = StatCollector.translateToLocal("entity." + unlocalName + ".name");

        if (result == null) {
            return unlocalName;
        }

        return result;
    }

    private static short getClampedKillCount(int amount) {
        int value = MathHelper.clamp_int(amount, 0, 10);

        return (short) value;
    }
}
