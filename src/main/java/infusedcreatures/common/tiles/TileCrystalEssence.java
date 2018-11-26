package infusedcreatures.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;

public class TileCrystalEssence extends TileThaumcraft implements IAspectContainer {
    public Aspect aspect = null;

    public boolean canUpdate() {
        return false;
    }

    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.aspect = Aspect.getAspect(nbttagcompound.getString("aspect"));
    }

    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        if (this.aspect != null) nbttagcompound.setString("aspect", this.aspect.getTag());
    }

    public AspectList getAspects() {
        return (this.aspect != null) ? new AspectList().add(this.aspect, 8) : null;
    }

    public void setAspects(AspectList aspects) {
    }

    public boolean doesContainerAccept(Aspect tag) {
        return false;
    }

    public int addToContainer(Aspect tag, int amount) {
        return 0;
    }

    public boolean takeFromContainer(Aspect tag, int amount) {
        return false;
    }

    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    public boolean doesContainerContainAmount(Aspect tag, int amount) {
        return false;
    }

    public boolean doesContainerContain(AspectList ot) {
        return false;
    }

    public int containerContains(Aspect tag) {
        return 0;
    }
}
