package infusedcreatures.common.entities;

import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySpiderHouse extends EntityCreature {

    private static final int webTime = 1200; // in ticks. 2400 = 2 mins; 6000 =
                                             // 5 mins; 18000 = 15mins(1 mc day)

    public EntitySpiderHouse(World par1World) {
        super(par1World);
        this.experienceValue = 1;
        this.setSize(0.2F, 0.2F);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.4D));
        // this.tasks.addTask(2, new EntityAIMate(this, 0.5D));
        // this.tasks.addTask(3, new EntityAITempt(this, 0.5D, Items.fish,
        // false));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte) 0));
    }

    public boolean isAIEnabled() {
        return true;
    }

    protected String getLivingSound() {
        return null;
    }

    protected String getHurtSound() {
        return null;
    }

    protected void playStepSound(int par1, int par2, int par3, int par4) {
    }

    protected float getSoundVolume() {
        return 0.5F;
    }

    protected float getSoundPitch() {
        return 1.3F;
    }

    protected int getDropItemId() {
        return 0;
    }

    protected void dropFewItems(boolean par1, int par2) {
    }

    protected Entity findPlayerToAttack() {
        return null;
    }

    public boolean attackEntityAsMob(Entity par1Entity) {
        return false;
    }

    // removes chance of spider jockey
    public void initCreature() {
    }

    @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
    public float spiderScaleAmount() {
        return 0.2F;
    }

    public float getShadowSize() {
        return 0.2F;
    }

    private int formWeb = 0;

    public void onUpdate() {
        super.onUpdate();

        if (!this.worldObj.isRemote) {
            this.setBesideClimbableBlock(this.isCollidedHorizontally);
        }

        if (this.formWeb < webTime) {
            this.formWeb++;
        } else {
            this.formWeb();
        }
    }

    public void formWeb() {
        int x = MathHelper.floor_double(this.posX);
        int y = MathHelper.floor_double(this.boundingBox.minY);
        int z = MathHelper.floor_double(this.posZ);
        if (y < 5) return;
        int i = x - 2 + this.worldObj.rand.nextInt(5);
        int j = y - 1 + this.worldObj.rand.nextInt(3);
        int k = z - 2 + this.worldObj.rand.nextInt(5);
        if (this.worldObj.getBlock(i, j, k) == Blocks.air && this.worldObj.getBlock(i, j - 1, k) != Blocks.air && worldObj.getClosestPlayerToEntity(this, 6) == null
                && isCorner(i, j, k) && notWebby(i, j, k)) {
            this.worldObj.setBlock(i, j, k, Blocks.web);
            this.formWeb = 0;
            return;
        }
    }

    public boolean isCorner(int x, int j, int z) {
        int count = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int k = z - 1; k <= z + 1; k++) {
                if (this.worldObj.getBlock(i, j, k) != Blocks.air && this.worldObj.getBlock(i, j, k) != Blocks.web) {
                    count++;
                }
            }
        }
        return ((count > 2) ? true : false);
    }

    public boolean notWebby(int x, int y, int z) {
        int count = 0;
        for (int i = x - 8; i < x + 8; i++) {
            for (int k = z - 8; k < z + 8; k++) {
                for (int j = y - 8; j < y + 8; j++) {
                    if (this.worldObj.getBlock(i, j, k) == Blocks.web) {
                        count++;
                    }
                }
            }
        }
        return ((count < 5) ? true : false);
    }

    public boolean isOnLadder() {
        return this.isBesideClimbableBlock();
    }

    public boolean isBesideClimbableBlock() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean p_70839_1_) {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (p_70839_1_) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 &= -2;
        }

        this.dataWatcher.updateObject(16, Byte.valueOf(b0));
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    protected boolean canDespawn() {
        return false;
    }

    // public EntitySpiderHouse createChild(EntityAgeable entity) {
    // return new EntitySpiderHouse(this.worldObj);
    // }
    //
    // public boolean isBreedingItem(ItemStack itemstack) {
    // return itemstack != null && itemstack.getItem() == Items.fish;
    // }
}
