package infusedcreatures.common.lib.events;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import infusedcreatures.common.entities.EntityChickenZombie;
import infusedcreatures.common.entities.EntityCowZombie;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class EventHandlerEntity {
    @SubscribeEvent
    public void entitySpawns(EntityJoinWorldEvent event) {
        if (event.entity instanceof EntityLiving) checkEditTask((EntityLiving) event.entity, event);
    }

    private void checkEditTask(EntityLiving entity, Event event) {
        if (entity instanceof EntityCowZombie || entity instanceof EntityChickenZombie) {
            for (Object a : entity.tasks.taskEntries.toArray()) {
                EntityAIBase ai = ((EntityAITaskEntry) a).action;
                if (ai instanceof EntityAITempt || ai instanceof EntityAIMate || ai instanceof EntityAIFollowParent) entity.tasks.removeTask(ai);

            }
            EntityCreature cow = (EntityCreature) entity;
            entity.tasks.addTask(3, new EntityAITempt(cow, 1.25D, Items.rotten_flesh, false));
        }
    }
}