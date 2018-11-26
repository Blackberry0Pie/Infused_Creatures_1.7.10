package infusedcreatures.common.tiles;

import java.awt.Color;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectSource;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.tiles.TileJar;

public class TileCreeperJar extends TileJar implements IAspectSource, IWandable, IEssentiaTransport {
    public Aspect aspect = null;
    public Aspect aspectFilter = null;
    public int amount = 0;
    public int maxAmount = 256;
    public ForgeDirection facing = ForgeDirection.DOWN;
    public float headFacing;
    public float headTilt;
    public boolean rotated = false;
    public boolean wasConnected = false;
    public boolean isInhibited = false;
    public boolean forgeLiquid = false;
    public int lid = 0;

    // public int unlabeledSuction = 12;
    // public int labeledSuction = 24;
    // public int emptySuction = 48;
    public int suction = 32;
    public int fullSuction = 0;

    public int getMinimumSuction() {
        // return this.aspectFilter != null ? labeledSuction : unlabeledSuction;
        return suction;
    }

    public int getSuctionAmount(ForgeDirection loc) {
        // if (this.amount < this.maxAmount) {
        // if (this.aspectFilter != null) {
        // return 64;
        // }
        // return 32;
        // }
        //
        // return 0;
        return (this.amount < this.maxAmount) ? suction : fullSuction;
    }

    public double playerRadiusCheck = 4;
    public double playerLookRadius = 10;
    public int ignition;
    public int ignitionMax = 1200;
    // public float random;

    float tr = 1.0F;
    float tri = 0.0F;
    float tg = 1.0F;
    float tgi = 0.0F;
    float tb = 1.0F;
    float tbi = 0.0F;
    public float cr = 1.0F;
    public float cg = 1.0F;
    public float cb = 1.0F;
    public Aspect displayAspect = null;

    public boolean canUpdate() {
        return true;
    }

    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.aspect = Aspect.getAspect(nbttagcompound.getString("Aspect"));
        this.aspectFilter = Aspect.getAspect(nbttagcompound.getString("AspectFilter"));
        this.amount = nbttagcompound.getShort("Amount");
        this.facing = ForgeDirection.getOrientation(nbttagcompound.getByte("face"));
        this.wasConnected = nbttagcompound.getBoolean("wasConnected");
        this.isInhibited = nbttagcompound.getBoolean("isInhibited");
    }

    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        if (this.aspect != null) nbttagcompound.setString("Aspect", this.aspect.getTag());
        if (this.aspectFilter != null) nbttagcompound.setString("AspectFilter", this.aspectFilter.getTag());
        nbttagcompound.setShort("Amount", (short) this.amount);
        nbttagcompound.setByte("face", (byte) this.facing.ordinal());
        nbttagcompound.setBoolean("wasConnected", this.wasConnected);
        nbttagcompound.setBoolean("isInhibited", this.isInhibited);
    }

    public AspectList getAspects() {
        AspectList al = new AspectList();
        if ((this.aspect != null) && (this.amount > 0)) al.add(this.aspect, this.amount);
        return al;
    }

    public void setAspects(AspectList aspects) {
    }

    public int addToContainer(Aspect tt, int am) {
        if (am == 0) return am;
        if (((this.amount < this.maxAmount) && (tt == this.aspect)) || (this.amount == 0)) {
            this.aspect = tt;
            int added = Math.min(am, this.maxAmount - this.amount);
            this.amount += added;
            am -= added;
        }
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        markDirty();
        return am;
    }

    public boolean takeFromContainer(Aspect tt, int am) {
        if ((this.amount >= am) && (tt == this.aspect)) {
            this.amount -= am;
            if (this.amount <= 0) {
                this.aspect = null;
                this.amount = 0;
            }
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            markDirty();
            return true;
        }
        return false;
    }

    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    public boolean doesContainerContainAmount(Aspect tag, int amt) {
        if ((this.amount >= amt) && (tag == this.aspect)) return true;
        return false;
    }

    public boolean doesContainerContain(AspectList ot) {
        for (Aspect tt : ot.getAspects()) {
            if ((this.amount > 0) && (tt == this.aspect)) return true;
        }
        return false;
    }

    public int containerContains(Aspect tag) {
        return 0;
    }

    public boolean doesContainerAccept(Aspect tag) {
        return this.aspectFilter != null ? tag.equals(this.aspectFilter) : true;
    }

    public boolean isConnectable(ForgeDirection face) {
        return face == this.facing;
    }

    public boolean canInputFrom(ForgeDirection face) {
        return face == this.facing;
    }

    public boolean canOutputTo(ForgeDirection face) {
        return face == this.facing;
    }

    public boolean isConnected() {
        TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.facing);
        if (te != null) {
            return true;
        }
        return false;
    }

    public void setSuction(Aspect aspect, int amount) {
    }

    public boolean renderExtendedTube() {
        return true;
    }

    public Aspect getSuctionType(ForgeDirection loc) {
        return this.aspectFilter != null ? this.aspectFilter : this.aspect;
    }

    public Aspect getEssentiaType(ForgeDirection loc) {
        return (loc == ForgeDirection.UNKNOWN) ? this.aspect : null;
    }

    public int getEssentiaAmount(ForgeDirection loc) {
        return this.amount;
    }

    public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return (canOutputTo(face)) && (takeFromContainer(aspect, amount)) ? amount : 0;
    }

    public int addEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return canInputFrom(face) ? amount - addToContainer(aspect, amount) : 0;
    }

    int count = 0;

    public void updateEntity() {
        super.updateEntity();
        if (!this.worldObj.isRemote) {
            if ((++this.count % 5 == 0) && (this.amount < this.maxAmount)) {
                fillJar();
            }
        } else {
            EntityPlayer entityplayer = this.worldObj.getClosestPlayer((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D, playerLookRadius);
            if (entityplayer != null) {
                double d0 = entityplayer.posX - (double) ((float) this.xCoord + 0.5F);
                double d1 = entityplayer.posZ - (double) ((float) this.zCoord + 0.5F);
                double d2 = entityplayer.posY - (double) ((float) this.yCoord + 0.5F);
                if (facing == ForgeDirection.WEST) {
                    this.headFacing = (float) Math.atan2(d1, d2);
                    this.headTilt = (float) -d0;
                } else if (facing == ForgeDirection.EAST) {
                    this.headFacing = (float) Math.atan2(d1, -d2);
                    this.headTilt = (float) d0;
                } else if (facing == ForgeDirection.NORTH) {
                    this.headFacing = (float) Math.atan2(d2, d0);
                    this.headTilt = (float) -d1;
                } else if (facing == ForgeDirection.SOUTH) {
                    this.headFacing = (float) Math.atan2(d2, -d0);
                    this.headTilt = (float) d1;
                } else if (facing == ForgeDirection.DOWN) {
                    this.headFacing = (float) Math.atan2(d1, -d0);
                    this.headTilt = (float) -d2;
                } else if (facing == ForgeDirection.UP) {
                    this.headFacing = (float) Math.atan2(d1, d0);
                    this.headTilt = (float) d2;
                }
            } else {
                this.headFacing = 0F;
                this.headTilt = 0F;
            }
            headTilt = MathHelper.clamp_float(headTilt, -1.570796F / 2.5F, 1.570796F / 4);
            if (this.worldObj.rand.nextInt(512 - this.amount) == 0) {
                if (this.worldObj.getClosestPlayer((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D, playerRadiusCheck) != null) {
                    if (ignition == 0 && !this.isInhibited) {
                        this.worldObj.playSound(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, "creeper.primed", 0.4F, 5F + this.worldObj.rand.nextFloat() * 0.2F,
                                false);
                        ignition = ignitionMax;
                    }
                }
            }
            if (this.amount > 0) {
                if (this.count % 20 == 0) {
                    this.displayAspect = this.aspect;
                    Color c = new Color(this.displayAspect.getColor());
                    this.tr = (c.getRed() / 255.0F);
                    this.tg = (c.getGreen() / 255.0F);
                    this.tb = (c.getBlue() / 255.0F);
                    this.tri = ((this.cr - this.tr) / 20.0F);
                    this.tgi = ((this.cg - this.tg) / 20.0F);
                    this.tbi = ((this.cb - this.tb) / 20.0F);
                }

                if (this.displayAspect == null) {
                    this.tr = (this.tg = this.tb = 1.0F);
                    this.tri = (this.tgi = this.tbi = 0.0F);
                } else {
                    this.cr -= this.tri;
                    this.cg -= this.tgi;
                    this.cb -= this.tbi;
                }
            }
            if (this.isConnected()) {
                if (!this.wasConnected || this.rotated) {
                    this.worldObj.playSound(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, "mob.creeper.say", 0.4F, 5F + this.worldObj.rand.nextFloat() * 0.2F, false);
                }
                this.wasConnected = true;
            } else {
                this.wasConnected = false;
            }
            this.rotated = false;
            /*
             * if (this.count % 60 == 0) { this.random =
             * this.worldObj.rand.nextFloat(); }
             */
        }
        if (ignition > 0) {
            ignition--;
        }
    }

    void fillJar() {
        TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.facing);
        if (te != null) {
            IEssentiaTransport ic = (IEssentiaTransport) te;
            if (!ic.canOutputTo(this.facing.getOpposite())) {
                return;
            }
            Aspect ta = null;
            if (this.aspectFilter != null) {
                ta = this.aspectFilter;
            } else if ((this.aspect != null) && (this.amount > 0)) {
                ta = this.aspect;
            } else if ((ic.getEssentiaAmount(this.facing.getOpposite()) > 0) && (ic.getSuctionAmount(this.facing.getOpposite()) < getSuctionAmount(this.facing))
                    && (getSuctionAmount(this.facing) >= ic.getMinimumSuction())) {
                ta = ic.getEssentiaType(this.facing.getOpposite());
            }

            if ((ta != null) && (ic.getSuctionAmount(this.facing.getOpposite()) < getSuctionAmount(this.facing))) {
                addToContainer(ta, ic.takeEssentia(ta, 1, this.facing.getOpposite()));
            }
        }
    }

    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        ForgeDirection oldFacing = this.facing;
        if (player.isSneaking()) {
            this.facing = ForgeDirection.getOrientation(side);
        } else {
            this.facing = ForgeDirection.getOrientation(side).getOpposite();
        }
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        player.swingItem();
        if (!oldFacing.equals(this.facing)) {
            this.rotated = true;
        }
        markDirty();
        return 0;
    }

    public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
        return null;
    }

    public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {
    }

    public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {
    }

    public void inhibit() {
        this.isInhibited = true;
        markDirty();
    }
}