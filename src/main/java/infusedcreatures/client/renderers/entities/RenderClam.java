package infusedcreatures.client.renderers.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import infusedcreatures.common.entities.EntityClam;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderClam extends RenderLiving {
    public RenderClam(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    private static final ResourceLocation nopearl = new ResourceLocation("infusedcreatures", "textures/models/clam.png");
    private static final ResourceLocation pearl = new ResourceLocation("infusedcreatures", "textures/models/clampearl.png");
    private static final ResourceLocation epearl = new ResourceLocation("infusedcreatures", "textures/models/clamenderpearl.png");

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return getClamPerlTexture((EntityClam) entity);
    }

    protected ResourceLocation getClamPerlTexture(EntityClam entity) {
        int pearlVal = entity.getPearl();
        return pearlVal == 1 ? pearl : pearlVal == 2 ? epearl : nopearl;
    }

    public void renderClam(EntityClam entity, double par2, double par4, double par6, float par8, float par9) {
        super.doRender(entity, par2, par4, par6, par8, par9);
    }

    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        renderClam((EntityClam) par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        renderClam((EntityClam) par1Entity, par2, par4, par6, par8, par9);
    }
}
