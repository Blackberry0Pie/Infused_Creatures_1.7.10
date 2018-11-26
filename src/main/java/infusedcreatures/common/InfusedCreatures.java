package infusedcreatures.common;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import infusedcreatures.common.config.Config;
import infusedcreatures.common.config.ICConfigAspects;
import infusedcreatures.common.config.ICConfigBlocks;
import infusedcreatures.common.config.ICConfigEntities;
import infusedcreatures.common.config.ICConfigItems;
import infusedcreatures.common.config.ICConfigRecipes;
import infusedcreatures.common.config.ICConfigResearch;
import infusedcreatures.common.lib.CreativeTabInfusedCreatures;
import infusedcreatures.common.lib.events.EventHandlerEntity;
import infusedcreatures.common.lib.events.PlayerKillEntityEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = InfusedCreatures.MODID, version = InfusedCreatures.VERSION, name = InfusedCreatures.MODNAME, dependencies="required-after:Thaumcraft@[4.2.3.5,)")
public class InfusedCreatures {
    public static final String MODID = "infusedcreatures";
    public static final String MODNAME = "Infused Creatures";
    public static final String VERSION = "0.4.9";
    // dont forget soulstone and shardutils
    public static final String[] VALID_ENTITIES = { "Chicken", "Cow", "Spider", "infusedcreatures.clam", "Creeper", "Squid" };
    @SidedProxy(clientSide = "infusedcreatures.client.ClientProxy", serverSide = "infusedcreatures.common.CommonProxy")
    public static CommonProxy proxy;
    @Mod.Instance(MODID)
    public static InfusedCreatures instance;
    public EventHandlerEntity entityEventHandler;
    public File modDir;
    public static final Logger log = LogManager.getLogger("INFUSEDCREATURES");
    public static CreativeTabs tabIC = new CreativeTabInfusedCreatures(CreativeTabs.getNextID(), "infusedcreatures");

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        this.modDir = event.getModConfigurationDirectory();
        try {
            Config.initialize(event.getSuggestedConfigurationFile());
        } catch (Exception e) {
            log.error("InfusedCreatures had a problem loading its configuration file");
        } finally {
            if (Config.config != null) {
                Config.save();
            }
        }

        this.entityEventHandler = new EventHandlerEntity();
        MinecraftForge.EVENT_BUS.register(this.entityEventHandler);
        ICConfigItems.init();
        ICConfigBlocks.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent evt) {
        proxy.registerDisplayInformation();
        ICConfigEntities.init();
        MinecraftForge.EVENT_BUS.register(new PlayerKillEntityEvent());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent evt) {
        ICConfigEntities.initEntitySpawns();
        ICConfigRecipes.init();
        ICConfigAspects.init();
        ICConfigResearch.init();
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if (eventArgs.modID.equals("infusedcreatures")) {
            // Config.syncConfigurable();
            if ((Config.config != null) && (Config.config.hasChanged())) {
                Config.save();
            }
        }
    }
}
