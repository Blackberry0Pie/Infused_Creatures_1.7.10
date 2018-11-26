package infusedcreatures.client.renderers.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelCreeperJar extends ModelBase {
    ModelRenderer head;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer leg1;
    ModelRenderer leg2;

    public ModelCreeperJar() {
        textureWidth = 64;
        textureHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, 0F, 6F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, -1.570796F, 0F, -1.570796F);
        leg3 = new ModelRenderer(this, 0, 16);
        leg3.addBox(-2F, 0F, -2F, 4, 6, 4);
        leg3.setRotationPoint(-4F, 2F, 6F);
        leg3.setTextureSize(64, 32);
        leg3.mirror = true;
        setRotation(leg3, -1.570796F, 0F, -1.570796F);
        leg4 = new ModelRenderer(this, 0, 16);
        leg4.addBox(-2F, 0F, -2F, 4, 6, 4);
        leg4.setRotationPoint(-4F, -2F, 6F);
        leg4.setTextureSize(64, 32);
        leg4.mirror = true;
        setRotation(leg4, -1.570796F, 0F, -1.570796F);
        leg1 = new ModelRenderer(this, 0, 16);
        leg1.addBox(-2F, 0F, -2F, 4, 6, 4);// fixed
        leg1.setRotationPoint(4F, 2F, 6F);// fixed
        leg1.setTextureSize(64, 32);
        leg1.mirror = true;
        setRotation(leg1, -1.570796F, 0F, 1.570796F);
        leg2 = new ModelRenderer(this, 0, 16);
        leg2.addBox(-2F, 0F, -2F, 4, 6, 4);
        leg2.setRotationPoint(4F, -2F, 6F);
        leg2.setTextureSize(64, 32);
        leg2.mirror = true;
        setRotation(leg2, -1.570796F, 0F, 1.570796F);
    }

    public void render(boolean connected, float headFacing,
            float headTilt/* , float random */) {
        // super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(connected, headFacing, headTilt/* , random */);
        float f5 = 0.0625F;
        head.render(f5);
        leg3.render(f5);
        leg4.render(f5);
        leg1.render(f5);
        leg2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(boolean connected, float headFacing,
            float headTilt/* , float random */) {
        // super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        if (connected) {
            setRotation(leg1, 0.3F - 1.570796F, 0F, 0.5F + 1.570796F);
            setRotation(leg2, 0.3F - 1.570796F, 0F, -.5F + 1.570796F);
            setRotation(leg3, 0.3F - 1.570796F, 0F, -.5F - 1.570796F);
            setRotation(leg4, 0.3F - 1.570796F, 0F, 0.5F - 1.570796F);
        } else {
            // MathHelper.clamp_float(headTilt, -1.570796F / 2.5F, 1.570796F /
            // 4);

            setRotation(leg1, -.3F - 1.570796F/* - random */, 0F, 0.5F + 1.570796F);
            setRotation(leg2, -.3F - 1.570796F, 0F, -.5F + 1.570796F);
            setRotation(leg3, -.3F - 1.570796F, 0F, -.5F - 1.570796F);
            setRotation(leg4, -.3F - 1.570796F, 0F, 0.5F - 1.570796F);
        }
        setRotation(head, headTilt, 0F, headFacing);
    }

}
