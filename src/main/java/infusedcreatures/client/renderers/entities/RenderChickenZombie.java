package infusedcreatures.client.renderers.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import infusedcreatures.common.entities.EntityChickenZombie;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderChickenZombie extends RenderLiving {
    public RenderChickenZombie(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    private static final ResourceLocation rl = new ResourceLocation("infusedcreatures", "textures/models/chickenzombie.png");

    protected ResourceLocation getEntityTexture(Entity entity) {
        return rl;
    }

    public void renderChicken(EntityChickenZombie par1EntityChicken, double par2, double par4, double par6, float par8, float par9) {
        super.doRender(par1EntityChicken, par2, par4, par6, par8, par9);
    }

    protected float getWingRotation(EntityChickenZombie chicken, float par2) {
        float var3 = chicken.prevRenderYawOffset + (chicken.renderYawOffset - chicken.prevRenderYawOffset) * par2;
        float var4 = (float) (chicken.prevDestPos + (chicken.destPos - chicken.prevDestPos) * par2);
        return (MathHelper.sin(var3) + 1.0F) * var4 * par2;
    }

    protected float handleRotationFloat(EntityLivingBase par1EntityLiving, float par2) {
        return getWingRotation((EntityChickenZombie) par1EntityLiving, par2);
    }

    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        renderChicken((EntityChickenZombie) par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        renderChicken((EntityChickenZombie) par1Entity, par2, par4, par6, par8, par9);
    }
}
