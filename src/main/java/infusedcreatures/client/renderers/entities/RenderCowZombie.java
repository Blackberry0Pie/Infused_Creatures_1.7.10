package infusedcreatures.client.renderers.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import infusedcreatures.common.entities.EntityCowZombie;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderCowZombie extends RenderLiving {
    public RenderCowZombie(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    private static final ResourceLocation rl = new ResourceLocation("infusedcreatures", "textures/models/cowzombie.png");

    protected ResourceLocation getEntityTexture(Entity entity) {
        return rl;
    }

    public void renderCow(EntityCowZombie par1EntityCow, double par2, double par4, double par6, float par8, float par9) {
        super.doRender(par1EntityCow, par2, par4, par6, par8, par9);
    }

    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        renderCow((EntityCowZombie) par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        renderCow((EntityCowZombie) par1Entity, par2, par4, par6, par8, par9);
    }
}