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

package infusedcreatures.common.lib.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import infusedcreatures.common.InfusedCreatures;
import infusedcreatures.common.lib.utils.ShardUtils;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class PlayerKillEntityEvent {

    @SubscribeEvent
    public void onEntityKill(LivingDeathEvent event) {
        World world = event.entity.worldObj;

        if ((world.isRemote) || (!(event.entity instanceof EntityLiving)) || (!(event.source.getEntity() instanceof EntityPlayer))) {
            return;
        }

        EntityLiving dead = (EntityLiving) event.entity;
        EntityPlayer player = (EntityPlayer) event.source.getEntity();
        String entName = EntityList.getEntityString(dead);

        if ((entName == null) || (entName.isEmpty())) {
            return;
        }

        if (!isEntityValid(entName)) {
            return;
        }

        if (((dead instanceof EntitySkeleton)) && (((EntitySkeleton) dead).getSkeletonType() == 1)) {
            entName = "Wither Skeleton";
        }

        net.minecraft.item.ItemStack shard = ShardUtils.getShardFromInv(player, entName);

        if (shard != null) {
            if (!ShardUtils.isShardBound(shard)) {
                ShardUtils.setShardBoundEnt(shard, entName);
            }

            ShardUtils.increaseShardKillCount(shard, (short) (1));
        }
    }

    private boolean isEntityValid(String entName) {
        for (String name : InfusedCreatures.VALID_ENTITIES) {
            if (name.equals(entName)) {
                return true;
            }
        }
        return false;
    }
}
