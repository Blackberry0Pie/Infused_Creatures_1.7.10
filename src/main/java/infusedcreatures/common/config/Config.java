package infusedcreatures.common.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config {
    public static Configuration config;
    public static int clamBeachWeight = 500;
    public static int clamOceanWeight = 500;
    public static boolean creeperJarExplodify = true;

    public static void initialize(File file) {
        config = new Configuration(file);
        config.addCustomCategoryComment("Clam_Spawn_Weights", "For beach and ocean biomes.");
        config.addCustomCategoryComment("Creeper_Jar_Explodify", "Turn this off if you are a wuss.");

        config.load();

        Property clamBeachWeightP = config.get("Clam_Spawn_Weights", "clamBeachWeight", 500);
        clamBeachWeightP.comment = "higher values increases the weight of clam spawns for the beach biome. If you are using biome addon mods you probably want to increase this weight quite a bit";
        clamBeachWeight = clamBeachWeightP.getInt();

        Property clamOceanWeightP = config.get("Clam_Spawn_Weights", "clamOceanWeight", 500);
        clamOceanWeightP.comment = "higher values increases the weight of clam spawns for the ocean biome. If you are using biome addon mods you probably want to increase this weight quite a bit";
        clamOceanWeight = clamOceanWeightP.getInt();

        Property creeperJarExplodifyP = config.get("Creeper_Jar_Explodify", "creeperJarExplodify", creeperJarExplodify);
        creeperJarExplodifyP.comment = "Can Creeper Jars explode on harvest.";
        creeperJarExplodify = creeperJarExplodifyP.getBoolean(true);

        config.save();
    }

    public static void save() {
        config.save();
    }
}
