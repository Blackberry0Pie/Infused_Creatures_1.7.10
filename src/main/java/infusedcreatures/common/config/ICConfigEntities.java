package infusedcreatures.common.config;

import cpw.mods.fml.common.registry.EntityRegistry;
import infusedcreatures.common.InfusedCreatures;
import infusedcreatures.common.entities.EntityChickenZombie;
import infusedcreatures.common.entities.EntityClam;
import infusedcreatures.common.entities.EntityClamEnder;
import infusedcreatures.common.entities.EntityCowZombie;
import infusedcreatures.common.entities.EntitySpiderHouse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.common.BiomeDictionary;

public class ICConfigEntities {
    public static int zombieChickenID, zombieCowID, enderClamID, houseSpiderID, clamID;

    public static void init() {
        int id = 0;
        zombieChickenID = registerEntity(EntityChickenZombie.class, "infusedcreatures.chickenzombie", id++, InfusedCreatures.instance, 64, 3, true, 0xFE2E2E, 0x868F6B);
        zombieCowID = registerEntity(EntityCowZombie.class, "infusedcreatures.cowzombie", id++, InfusedCreatures.instance, 64, 3, true, 0x515838, 0x868F6B);
        enderClamID = registerEntity(EntityClamEnder.class, "infusedcreatures.clamender", id++, InfusedCreatures.instance, 64, 3, true, 0x646C7A, 0x9D8BA1);
        clamID = registerEntity(EntityClam.class, "infusedcreatures.clam", id++, InfusedCreatures.instance, 64, 3, true, 0x646C7A, 0x9D8BA1);
        houseSpiderID = registerEntity(EntitySpiderHouse.class, "infusedcreatures.spiderhouse", id++, InfusedCreatures.instance, 64, 3, true, 0xCC9933, 0x996600);

    }

    private static int registerEntity(Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency,
            boolean sendsVelocityUpdates, int color1, int color2) {
        int globalID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entityClass, entityName, globalID, color1, color2);
        EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
        return globalID;
    }

    public static int getZombieChickenID() {
        return zombieChickenID;
    }

    public static int getZombieCowID() {
        return zombieCowID;
    }

    public static int getEnderClamID() {
        return enderClamID;
    }

    public static int getClamID() {
        return clamID;
    }

    public static int getHouseSpiderID() {
        return houseSpiderID;
    }

    public static void initEntitySpawns() {
        // WATER is RIVER and OCEAN, so it doesnt work
        EntityRegistry.addSpawn(EntityClam.class, Config.clamOceanWeight, 3, 6, EnumCreatureType.waterCreature, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.OCEAN));
        EntityRegistry.addSpawn(EntityClam.class, Config.clamBeachWeight, 3, 6, EnumCreatureType.waterCreature, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.BEACH));
    }
}
