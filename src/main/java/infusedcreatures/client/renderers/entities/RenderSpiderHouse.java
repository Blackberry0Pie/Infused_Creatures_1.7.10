package infusedcreatures.client.renderers.entities;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import infusedcreatures.common.entities.EntitySpiderHouse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderSpiderHouse extends RenderLiving {
    public RenderSpiderHouse() {
        super(new ModelSpider(), 0.2F);
        setRenderPassModel(new ModelSpider());
    }

    protected float setSpiderDeathMaxRotation(EntityLiving par1EntitySpider) {
        return 180.0F;
    }

    private static final ResourceLocation rl = new ResourceLocation("infusedcreatures", "textures/models/spiderhouse.png");
    private static final ResourceLocation eyes = new ResourceLocation("infusedcreatures", "textures/models/spiderhouse_eyes.png");

    protected ResourceLocation getEntityTexture(Entity entity) {
        return rl;
    }

    protected int setSpiderEyeBrightness(EntityLiving par1EntitySpider, int par2, float par3) {
        if (par2 != 0) {
            return -1;
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(eyes);
        float f1 = 1.0F;
        GL11.glEnable(3042);
        GL11.glDisable(3008);
        GL11.glBlendFunc(1, 1);

        if (par1EntitySpider.isInvisible()) {
            GL11.glDepthMask(false);
        } else {
            GL11.glDepthMask(true);
        }

        char c0 = 61680;
        int j = c0 % 65536;
        int k = c0 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j / 1.0F, k / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
        return 1;
    }

    protected void scaleSpider(EntitySpiderHouse par1EntitySpider, float par2) {
        float f1 = par1EntitySpider.spiderScaleAmount();
        GL11.glScalef(f1, f1 * 1.25F, f1);
    }

    protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2) {
        scaleSpider((EntitySpiderHouse) par1EntityLiving, par2);
    }

    protected float getDeathMaxRotation(EntityLivingBase par1EntityLiving) {
        return setSpiderDeathMaxRotation((EntityLiving) par1EntityLiving);
    }

    protected int shouldRenderPass(EntityLivingBase par1EntityLiving, int par2, float par3) {
        return setSpiderEyeBrightness((EntityLiving) par1EntityLiving, par2, par3);
    }
}
