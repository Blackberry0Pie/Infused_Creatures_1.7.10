package infusedcreatures.client.renderers.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelClam extends ModelBase {
    // fields
    ModelRenderer TopShellTop;
    ModelRenderer TopShellLeft;
    ModelRenderer TopShellRight;
    ModelRenderer TopShellFront;
    ModelRenderer TopShellBack;
    ModelRenderer BottomShellBottom;
    ModelRenderer BottomShellLeft;
    ModelRenderer BottomShellRight;
    ModelRenderer BottomShellFront;
    ModelRenderer BottomShellBack;
    ModelRenderer BottomLiving;
    ModelRenderer Pearl;

    public ModelClam() {
        textureWidth = 64;
        textureHeight = 32;

        TopShellTop = new ModelRenderer(this, 14, 0);
        TopShellTop.addBox(1F, -2F, -1.5F, 4, 1, 3);
        TopShellTop.setRotationPoint(-3F, 22F, 0F);
        TopShellTop.setTextureSize(64, 32);
        // TopShellTop.mirror = true;
        setRotation(TopShellTop, 0F, 0F, -0.5759587F);
        TopShellLeft = new ModelRenderer(this, 0, 4);
        TopShellLeft.addBox(0F, -2F, -2.5F, 6, 2, 1);
        TopShellLeft.setRotationPoint(-3F, 22F, 0F);
        TopShellLeft.setTextureSize(64, 32);
        // TopShellLeft.mirror = true;
        setRotation(TopShellLeft, 0F, 0F, -0.5759587F);
        TopShellRight = new ModelRenderer(this, 28, 4);
        TopShellRight.addBox(0F, -2F, 1.5F, 6, 2, 1);
        TopShellRight.setRotationPoint(-3F, 22F, 0F);
        TopShellRight.setTextureSize(64, 32);
        // TopShellRight.mirror = true;
        setRotation(TopShellRight, 0F, 0F, -0.5759587F);
        TopShellFront = new ModelRenderer(this, 6, 7);
        TopShellFront.addBox(5F, -2F, -1.5F, 1, 2, 3);
        TopShellFront.setRotationPoint(-3F, 22F, 0F);
        TopShellFront.setTextureSize(64, 32);
        // TopShellFront.mirror = true;
        setRotation(TopShellFront, 0F, 0F, -0.5759587F);
        TopShellBack = new ModelRenderer(this, 28, 7);
        TopShellBack.addBox(0F, -2F, -1.5F, 1, 2, 3);
        TopShellBack.setRotationPoint(-3F, 22F, 0F);
        TopShellBack.setTextureSize(64, 32);
        // TopShellBack.mirror = true;
        setRotation(TopShellBack, 0F, 0F, -0.5759587F);
        BottomShellBottom = new ModelRenderer(this, 14, 12);
        BottomShellBottom.addBox(1F, 1F, -1.5F, 4, 1, 3);
        BottomShellBottom.setRotationPoint(-3F, 22F, 0F);
        BottomShellBottom.setTextureSize(64, 32);
        // BottomShellBottom.mirror = true;
        setRotation(BottomShellBottom, 0F, 0F, 0F);
        BottomShellLeft = new ModelRenderer(this, 0, 16);
        BottomShellLeft.addBox(0F, 0F, -2.5F, 6, 2, 1);
        BottomShellLeft.setRotationPoint(-3F, 22F, 0F);
        BottomShellLeft.setTextureSize(64, 32);
        // BottomShellLeft.mirror = true;
        setRotation(BottomShellLeft, 0.0174533F, 0F, 0F);
        BottomShellRight = new ModelRenderer(this, 28, 16);
        BottomShellRight.addBox(0F, 0F, 1.5F, 6, 2, 1);
        BottomShellRight.setRotationPoint(-3F, 22F, 0F);
        BottomShellRight.setTextureSize(64, 32);
        // BottomShellRight.mirror = true;
        setRotation(BottomShellRight, 0F, 0F, 0F);
        BottomShellFront = new ModelRenderer(this, 6, 19);
        BottomShellFront.addBox(5F, 0F, -1.5F, 1, 2, 3);
        BottomShellFront.setRotationPoint(-3F, 22F, 0F);
        BottomShellFront.setTextureSize(64, 32);
        // BottomShellFront.mirror = true;
        setRotation(BottomShellFront, 0F, 0F, 0F);
        BottomShellBack = new ModelRenderer(this, 28, 19);
        BottomShellBack.addBox(0F, 0F, -1.5F, 1, 2, 3);
        BottomShellBack.setRotationPoint(-3F, 22F, 0F);
        BottomShellBack.setTextureSize(64, 32);
        // BottomShellBack.mirror = true;
        setRotation(BottomShellBack, 0F, 0F, 0F);
        BottomLiving = new ModelRenderer(this, 15, 24);
        BottomLiving.addBox(1F, 0F, -1.5F, 3, 1, 3);
        BottomLiving.setRotationPoint(-3F, 22F, 0F);
        BottomLiving.setTextureSize(64, 32);
        // BottomLiving.mirror = true;
        setRotation(BottomLiving, 0F, 0F, 0F);
        Pearl = new ModelRenderer(this, 19, 19);
        Pearl.addBox(2F, -1F, -0.5F, 1, 1, 1);
        Pearl.setRotationPoint(-3F, 22F, 0F);
        Pearl.setTextureSize(64, 32);
        // Pearl.mirror = true;
        setRotation(Pearl, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        TopShellTop.render(f5);
        TopShellLeft.render(f5);
        TopShellRight.render(f5);
        TopShellFront.render(f5);
        TopShellBack.render(f5);
        BottomShellBottom.render(f5);
        BottomShellLeft.render(f5);
        BottomShellRight.render(f5);
        BottomShellFront.render(f5);
        BottomShellBack.render(f5);
        BottomLiving.render(f5);
        Pearl.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.TopShellTop.rotateAngleZ = 0.25F * MathHelper.cos(f2) - 0.576F;
        this.TopShellLeft.rotateAngleZ = this.TopShellTop.rotateAngleZ;
        this.TopShellRight.rotateAngleZ = this.TopShellTop.rotateAngleZ;
        this.TopShellFront.rotateAngleZ = this.TopShellTop.rotateAngleZ;
        this.TopShellBack.rotateAngleZ = this.TopShellTop.rotateAngleZ;
    }

}
