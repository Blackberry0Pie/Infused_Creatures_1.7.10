package infusedcreatures.common.entities;

import java.util.ArrayList;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class EntityCowZombie extends EntityCow implements IShearable {
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
        // getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.5D);
    }

    public EntityCowZombie(World par1World) {
        super(par1World);
        // removed wheat tempt in entity spawn event
        // added flesh tempt in entity spawn event
    }

    // protected void playStepSound(int par1, int par2, int par3, int par4) {
    // this.worldObj.playSoundAtEntity(this, "mob.cow.step", 0.15F, 1.0F);
    // }

    protected void dropFewItems(boolean par1, int par2) {
        // 0:1+loot
        int j = this.rand.nextInt(2) + this.rand.nextInt(1 + par2);
        int k;

        for (k = 0; k < j; ++k) {
            this.dropItem(Items.leather, 1);
        }
        // 1:2+loot
        j = this.rand.nextInt(2) + 1 + this.rand.nextInt(1 + par2);

        for (k = 0; k < j; ++k) {
            this.dropItem(Items.rotten_flesh, 1);
        }
    }

    public boolean interact(EntityPlayer par1EntityPlayer) {
        ItemStack currentstack = par1EntityPlayer.getHeldItem();
        if (currentstack != null) {
            if (currentstack.getItem() == Items.rotten_flesh && this.getHealth() < this.getMaxHealth()) {
                if (!par1EntityPlayer.capabilities.isCreativeMode) {
                    par1EntityPlayer.inventory.consumeInventoryItem(Items.rotten_flesh);
                }
                this.playSound("random.eat", 0.5F + 0.5F * (float) this.rand.nextInt(2), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.85F);
                this.playSound("mob.zombie.death", 0.5F + 0.5F * (float) this.rand.nextInt(2), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.85F);
                heal(6);
                return true;
            } else if (currentstack.getItem() == Items.shears && this.getHealth() <= 8 && this.worldObj.isRemote) {
                par1EntityPlayer.addChatMessage(new ChatComponentTranslation("This cow looks too weak to shear."));
            }
            return false;
        } else {
            return super.interact(par1EntityPlayer);
        }
    }

    public EntityCow createChild(EntityAgeable var1) {
        return null;
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int X, int Y, int Z) {
        return this.getHealth() > 8;
    }

    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int X, int Y, int Z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        // set to randomly give an extra leather normally
        for (int j = 0; j < 1 + rand.nextInt(2) + rand.nextInt(1 + fortune); j++)
            ret.add(new ItemStack(Items.leather));
        if (rand.nextInt(30) == 0) ret.add(new ItemStack(Items.bone));
        if (rand.nextInt(20) == 0) ret.add(new ItemStack(Items.rotten_flesh));
        this.worldObj.playSoundAtEntity(this, "mob.sheep.shear", 1.0F, 1.0F);
        this.attackEntityFrom(DamageSource.starve, 8);
        return ret;
    }

    public int getTotalArmorValue() {
        return (2);
    }
}