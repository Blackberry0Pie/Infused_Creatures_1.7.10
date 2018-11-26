package infusedcreatures.common.entities;

import infusedcreatures.common.config.ICConfigItems;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityClam extends EntityAnimal {

    public static final int perlTime = 6000; // in ticks. 2400 = 2 mins; 6000 =
                                             // 5 mins; 18000 = 15mins(1 mc day)

    public int formPearl = -1;

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.15D);
    }

    public EntityClam(World par1World) {
        super(par1World);
        this.setSize(0.3F, 0.3F);
        this.isImmuneToFire = false;
        this.tasks.addTask(2, new EntityAIMate(this, 0.5D));
        this.tasks.addTask(3, new EntityAITempt(this, 0.5D, Items.fish, false));
        this.tasks.addTask(6, new EntityAIWander(this, 0.1D));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    public boolean isAIEnabled() {
        return true;
    }

    protected boolean canDespawn() {
        return true;
    }

    public int getTotalArmorValue() {
        return 8;
    }

    protected String getLivingSound() {
        return null;
    }

    protected String getHurtSound() {
        return null;
    }

    protected String getDeathSound() {
        return null;
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    protected int getDropItemId() {
        return 0;
    }

    protected void dropFewItems(boolean player_killed, int looting) {
        int pearl = this.getPearl();
        this.dropItem(ICConfigItems.itemClamShell, rand.nextInt(1 + looting > 0 ? 1 : 0) + 1);
        if (player_killed) {
            if (pearl == 1) {
                dropPearl(pearl, looting);
            } else if (pearl == 2) {
                dropPearl(pearl, looting);
            } else {
                if (rand.nextInt(50) == 0) {
                    dropPearl(1, 0);
                }
            }
        }
    }

    public boolean interact(EntityPlayer player) {
        ItemStack currentstack = player.getHeldItem();
        if(currentstack != null && currentstack.getItem() != null) {
            Item currentitem = currentstack.getItem();
            //int damage = currentitem.getDamage(currentstack);
            if (currentitem == Item.getItemFromBlock(Blocks.sand) && this.formPearl < 0) {
                if (!player.capabilities.isCreativeMode) {
                    player.inventory.consumeInventoryItem(Item.getItemFromBlock(Blocks.sand));
                }
                this.formPearl = perlTime;
                return true;
                //handle damage check?
            } else if (currentitem == ICConfigItems.itemPearlForceps ) {
                int pearl = this.getPearl();
                int looting = EnchantmentHelper.getLootingModifier((EntityLivingBase) player);
                if (pearl == 1) {
                    dropPearl(pearl, looting);
                    this.setPearl(0);
                    this.formPearl = -1;
                    if (!player.capabilities.isCreativeMode)
                        currentstack.damageItem(1, player);
                    return true;
                } else if (pearl == 2) {
                    dropPearl(pearl, looting);
                    this.setPearl(0);
                    this.formPearl = -1;
                    if (!player.capabilities.isCreativeMode)
                        currentstack.damageItem(1, player);
                    return true;
                }
            }
        }
        return super.interact(player);
    }

    public boolean isInWater() {
        return this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0D, -0.6000000238418579D, 0.0D), Material.water, this);
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.boundingBox.minY);
        int k = MathHelper.floor_double(this.posZ);
        if (this.posY <= 45.0D || this.posY >= 63.0D) {
            return false;
        }
        boolean floorFlag = false;
        for (int y = j; y > 45; y--) {
            if (this.worldObj.getBlock(i, y, k) != Blocks.water) {
                floorFlag = true;
                break;
            }
        }
        return floorFlag && this.worldObj.checkNoEntityCollision(this.boundingBox);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte) 0));
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("pearl", (short) this.getPearl());
        par1NBTTagCompound.setShort("formPearl", (short) this.formPearl);
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setPearl(par1NBTTagCompound.getShort("pearl"));
        this.formPearl = par1NBTTagCompound.getShort("formPearl");
    }

    public int getPearl() {
        return this.dataWatcher.getWatchableObjectByte(16);
    }

    public void setPearl(int par1) {
        this.dataWatcher.updateObject(16, Byte.valueOf((byte) (par1 & 255)));
    }

    public void onEntityUpdate() {
        int a = this.getAir();
        super.onEntityUpdate();
        if (this.isEntityAlive() && !this.isInsideOfMaterial(Material.water)) {
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
            --a;
            this.setAir(a);

            if (this.getAir() == -20) {
                this.setAir(0);
                this.attackEntityFrom(DamageSource.drown, 2);
            }
        } else {
            this.setAir(300);
        }
        if (this.formPearl > 0) {
            --this.formPearl;
        }
        int pearl = this.getPearl();
        if (this.formPearl == 0 && pearl != 1 && pearl != 2) {
            this.setPearl(1);
        }
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    public EntityClam createChild(EntityAgeable entity) {
        return new EntityClam(this.worldObj);
    }

    public boolean isBreedingItem(ItemStack itemstack) {
        return itemstack != null && itemstack.getItem() == Items.fish;
    }

    public void dropPearl(int pearl, int looting) {
        if (!worldObj.isRemote) {
            if (pearl == 1) {
                this.dropItem(ICConfigItems.itemPearl, rand.nextInt(1 + looting) + 1);
            } else {
                this.dropItem(Items.ender_pearl, rand.nextInt(1 + looting) + 1);
            }
        }
    }
}
