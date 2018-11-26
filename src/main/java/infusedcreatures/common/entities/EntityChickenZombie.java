package infusedcreatures.common.entities;

import java.util.ArrayList;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class EntityChickenZombie extends EntityChicken implements IShearable {
    public float renderYawOffset;
    public float destPos;
    public float prevDestPos;
    public float prevRenderYawOffset;
    public float fallDistance = 1.0F;

    public EntityChickenZombie(World par1World) {
        super(par1World);
        // removed wheat tempt in entity spawn event
        // added flesh tempt in entity spawn event
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(9.0D);
        // this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
        // getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.5D);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.prevRenderYawOffset = this.renderYawOffset;
        this.prevDestPos = this.destPos;
        this.destPos = (float) ((double) this.destPos + (double) (this.onGround ? -1 : 4) * 0.3D);

        if (this.destPos < 0.0F) this.destPos = 0.0F;
        if (this.destPos > 1.0F) this.destPos = 1.0F;
        if (!this.onGround && this.fallDistance < 1.0F) this.fallDistance = 1.0F;

        this.fallDistance = (float) ((double) this.fallDistance * 0.9D);

        if (!this.onGround && this.motionY < 0.0D) this.motionY *= 0.6D;
        this.renderYawOffset += this.fallDistance * 2.0F;
    }

    protected void playStepSound(int par1, int par2, int par3, int par4) {
        this.worldObj.playSoundAtEntity(this, "mob.chicken.step", 0.15F, 1.0F);
    }

    protected void dropFewItems(boolean player_damaged, int looting) {
        // 0:1+loot
        int loot = this.rand.nextInt(2) + this.rand.nextInt(1 + looting);
        for (int i = 0; i < loot; i++)
            this.dropItem(Items.feather, 1);
        // 1:2+loot
        loot = this.rand.nextInt(2) + 1 + this.rand.nextInt(1 + looting);
        for (int i = 0; i < loot; i++)
            this.dropItem(Items.rotten_flesh, 1);
    }

    // add rotten flesh interaction
    public boolean interact(EntityPlayer player) {
        ItemStack currentstack = player.getHeldItem();

        if (currentstack != null) {
            if (currentstack.getItem() == Items.rotten_flesh && this.getHealth() < this.getMaxHealth()) {
                if (!player.capabilities.isCreativeMode) {
                    player.inventory.consumeInventoryItem(Items.rotten_flesh);
                }
                this.playSound("random.eat", 0.5F + 0.5F * (float) this.rand.nextInt(2), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.90F);
                this.playSound("mob.zombie.death", 0.5F + 0.5F * (float) this.rand.nextInt(2), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.90F);
                heal(6);
                return true;
            } else if (currentstack.getItem() == Items.shears && this.getHealth() <= 4 && this.worldObj.isRemote) {
                player.addChatMessage(new ChatComponentTranslation("This chicken looks too weak to shear."));
            }
            return false;
        } else {
            return super.interact(player);
        }
    }

    public EntityChicken createChild(EntityAgeable var1) {
        return null;
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int X, int Y, int Z) {
        return this.getHealth() > 4;
    }

    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int X, int Y, int Z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        // set to randomly give an extra feather normally
        for (int j = 0; j < 1 + rand.nextInt(2) + rand.nextInt(1 + fortune); j++)
            ret.add(new ItemStack(Items.feather));
        if (rand.nextInt(30) == 0) ret.add(new ItemStack(Items.bone));
        if (rand.nextInt(20) == 0) ret.add(new ItemStack(Items.rotten_flesh));
        this.worldObj.playSoundAtEntity(this, "mob.sheep.shear", 1.0F, 1.0F);
        this.attackEntityFrom(DamageSource.starve, 4);
        return ret;
    }

    public int getTotalArmorValue() {
        return (2);
    }
}
