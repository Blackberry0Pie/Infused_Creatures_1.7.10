package infusedcreatures.client.renderers.item;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import thaumcraft.client.renderers.models.ModelCube;

// -----------------------------------

// CURRENTLY NOT USED

// NOT REGISTERED IN CLIENT PROXY

// ------------------------------------

public class UNUSED_ItemCrystalEssenceRenderer implements IItemRenderer {

    ModelCube cube;

    public UNUSED_ItemCrystalEssenceRenderer() {
        cube = new ModelCube();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        if (item.stackTagCompound.getString("aspect") == null) {
            return false;
        }
        if (type == type.ENTITY || type == type.INVENTORY) {
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("thaumcraft:pedestal_top"));
        switch (type) {
        // case EQUIPPED:
        // case EQUIPPED_FIRST_PERSON:
        case ENTITY:
        case INVENTORY:
            GL11.glRotatef(0F, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(-5F, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(-150F, 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(-0.8F, -0.7F, -0.1F);
            cube.render((Entity) data[0], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            break;
        default:
            break;
        }
        GL11.glPopMatrix();
    }

}
