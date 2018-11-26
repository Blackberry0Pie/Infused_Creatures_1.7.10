package infusedcreatures.client.renderers.tile;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import infusedcreatures.client.renderers.models.entities.ModelCreeperJar;
import infusedcreatures.common.tiles.TileCreeperJar;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class TileCreeperJarRenderer extends TileEntitySpecialRenderer {
    // private IModelCustom model;
    // private static final ResourceLocation RELAY = new
    // ResourceLocation("thaumcraft", "textures/models/reservoir.obj");
    // public TileCreeperJarRenderer() { this.model =
    // AdvancedModelLoader.loadModel(RELAY); }

    private final ModelCreeperJar creeperModel = new ModelCreeperJar();
    private static final ResourceLocation tex = new ResourceLocation("infusedcreatures", "textures/models/creeperjar.png");
    private static final ResourceLocation tex2 = new ResourceLocation("infusedcreatures", "textures/models/creeperjarinhibited.png");

    public void renderTileEntityAt(TileCreeperJar tile, double par2, double par4, double par6, float par8) {
        int facing = tile.facing.ordinal();
        int facingTrans = 0;
        if (facing == 0) facingTrans = 3;
        if (facing == 1) facingTrans = 1;
        if (facing == 2) facingTrans = 1;
        if (facing == 3) facingTrans = 1;
        if (facing == 4) facingTrans = 2;
        if (facing == 5) facingTrans = 0;
        GL11.glPushMatrix();
        translateFromOrientation(par2, par4, par6, facing);
        // GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (tile.isInhibited) {
            this.bindTexture(tex2);
        } else {
            this.bindTexture(tex);
        }
        this.creeperModel.render(tile.isConnected(), tile.headFacing + facingTrans * 1.570796F,
                tile.headTilt - 1.570796F/* , tile.random */);
        GL11.glPopMatrix();

        // GL11.glPushMatrix();
        // GL11.glTranslated(par2, par4 - 0.5D, par6);
        // GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        // renderLiquid(tile, par2, par4, par6, par8);
        // GL11.glPopMatrix();
    }

    public void renderLiquid(TileCreeperJar te, double x, double y, double z, float f) {
        // if ((this.field_147501_a.field_147553_e == null) || (te.displayAspect
        // == null) || (te.amount == 0)) { return;
        // }
        // GL11.glPushMatrix();
        // GL11.glEnable(3042);
        // GL11.glBlendFunc(770, 771);
        // World world = te.getWorldObj();
        // RenderBlocks renderBlocks = new RenderBlocks();
        //
        // GL11.glDisable(2896);
        //
        // float level = te.amount / te.maxAmount;
        //
        // Tessellator t = Tessellator.instance;
        //
        // renderBlocks.setRenderBounds(BlockRenderer.W3, BlockRenderer.W3,
        // BlockRenderer.W3, BlockRenderer.W13, BlockRenderer.W3 +
        // BlockRenderer.W10 * level, BlockRenderer.W13);
        //
        //
        //
        // t.startDrawingQuads();
        //
        // t.setColorRGBA_F(te.cr, te.cg, te.cb, 0.9F);
        //
        // int bright = 200;
        //
        // t.setBrightness(200);
        //
        // IIcon icon =
        // ((BlockCreeperJar)ConfigBlocks.blockCreeperJar).iconLiquid;
        //
        // this.field_147501_a.field_147553_e.bindTexture(TextureMap.locationBlocksTexture);
        //
        // renderBlocks.renderFaceYNeg(ConfigBlocks.blockCreeperJar, 0.0D, 0.5D,
        // 0.0D, icon);
        // renderBlocks.renderFaceYPos(ConfigBlocks.blockCreeperJar, 0.0D, 0.5D,
        // 0.0D, icon);
        // renderBlocks.renderFaceZNeg(ConfigBlocks.blockCreeperJar, 0.0D, 0.5D,
        // 0.0D, icon);
        // renderBlocks.renderFaceZPos(ConfigBlocks.blockCreeperJar, 0.0D, 0.5D,
        // 0.0D, icon);
        // renderBlocks.renderFaceXNeg(ConfigBlocks.blockCreeperJar, 0.0D, 0.5D,
        // 0.0D, icon);
        // renderBlocks.renderFaceXPos(ConfigBlocks.blockCreeperJar, 0.0D, 0.5D,
        // 0.0D, icon);
        //
        // t.draw();
        //
        //
        // GL11.glEnable(2896);
        // GL11.glDisable(3042);
        // GL11.glPopMatrix();
    }

    private void translateFromOrientation(double x, double y, double z, int orientation) {
        GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
        if (orientation == 0) {
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        } else if (orientation == 1) {
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        } else if (orientation != 2) {
            if (orientation == 3) {
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            } else if (orientation == 4) {
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            } else if (orientation == 5) GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        }
        GL11.glTranslated(0.0D, 0.0D, -0.5D);
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        renderTileEntityAt((TileCreeperJar) par1TileEntity, par2, par4, par6, par8);
    }
}
