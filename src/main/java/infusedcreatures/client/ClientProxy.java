package infusedcreatures.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import infusedcreatures.client.renderers.block.BlockCreeperJarRenderer;
import infusedcreatures.client.renderers.block.BlockCrystalEssenceRenderer;
import infusedcreatures.client.renderers.entities.RenderChickenZombie;
import infusedcreatures.client.renderers.entities.RenderClam;
import infusedcreatures.client.renderers.entities.RenderCowZombie;
import infusedcreatures.client.renderers.entities.RenderSpiderHouse;
import infusedcreatures.client.renderers.models.entities.ModelClam;
import infusedcreatures.client.renderers.tile.TileCreeperJarRenderer;
import infusedcreatures.common.CommonProxy;
import infusedcreatures.common.config.ICConfigBlocks;
import infusedcreatures.common.entities.EntityChickenZombie;
import infusedcreatures.common.entities.EntityClam;
import infusedcreatures.common.entities.EntityClamEnder;
import infusedcreatures.common.entities.EntityCowZombie;
import infusedcreatures.common.entities.EntitySpiderHouse;
import infusedcreatures.common.tiles.TileCreeperJar;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class ClientProxy extends CommonProxy {

    // public void registerHandlers(){
    // //FMLCommonHandler.instance().bus().register(new ClientTickEventsFML());
    // //MinecraftForge.EVENT_BUS.register(InfusedCreatures.instance.renderEventHandler);
    //
    // //MinecraftForge.EVENT_BUS.register(ConfigBlocks.blockTube);
    // //MinecraftForge.EVENT_BUS.register(ParticleEngine.instance);
    // //FMLCommonHandler.instance().bus().register(ParticleEngine.instance);
    // }

    public void registerDisplayInformation() {
        setupItemRenderers();
        setupEntityRenderers();
        setupBlockRenderers();
        setupTileRenderers();
    }

    private void setupItemRenderers() {
        // MinecraftForgeClient.registerItemRenderer(ConfigItems.itemCreeperJarFilled,
        // new ItemCreeperJarFilledRenderer());
        // MinecraftForgeClient.registerItemRenderer(ICConfigItems.itemCrystalEssence,
        // new ItemCrystalEssenceRenderer());
    }

    private void setupEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityCowZombie.class, new RenderCowZombie(new ModelCow(), 0.9F));
        RenderingRegistry.registerEntityRenderingHandler(EntityChickenZombie.class, new RenderChickenZombie(new ModelChicken(), 0.3F));
        RenderingRegistry.registerEntityRenderingHandler(EntityClamEnder.class, new RenderClam(new ModelClam(), 0.3F));
        RenderingRegistry.registerEntityRenderingHandler(EntityClam.class, new RenderClam(new ModelClam(), 0.3F));
        RenderingRegistry.registerEntityRenderingHandler(EntitySpiderHouse.class, new RenderSpiderHouse());

    }

    void setupTileRenderers() {
        registerTileEntitySpecialRenderer(TileCreeperJar.class, new TileCreeperJarRenderer());
    }

    void setupBlockRenderers() {
        ICConfigBlocks.blockCreeperJarRI = RenderingRegistry.getNextAvailableRenderId();
        registerBlockRenderer(new BlockCreeperJarRenderer());
        ICConfigBlocks.blockCrystalEssenceRI = RenderingRegistry.getNextAvailableRenderId();
        registerBlockRenderer(new BlockCrystalEssenceRenderer());
    }

    public void registerTileEntitySpecialRenderer(Class tile, TileEntitySpecialRenderer renderer) {
        ClientRegistry.bindTileEntitySpecialRenderer(tile, renderer);
    }

    public void registerBlockRenderer(ISimpleBlockRenderingHandler renderer) {
        RenderingRegistry.registerBlockHandler(renderer);
    }

    // public World getClientWorld() {
    // return FMLClientHandler.instance().getClient().theWorld;
    // }
}
