package infusedcreatures.common.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.world.World;

public class EntityClamEnder extends EntityClam {

    public EntityClamEnder(World par1World) {
        super(par1World);
    }

    public void onEntityUpdate() {
        super.onEntityUpdate();
        if (this.formPearl > 0 && this.formPearl % 40 == 0) {
            if (this.formPearl % 1200 == 0) {
                this.playSound("mob.endermen.portal", 0.25F/* volume */, 2.0F/* pitch */);
            }
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            this.worldObj.spawnParticle("portal", this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width,
                    this.posY + 1 + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1,
                    d2);
        }
        if (this.formPearl == 0 && this.getPearl() != 2) {
            this.setPearl(2);
            this.playSound("mob.endermen.portal", 0.5F/* volume */, 1.5F/* pitch */);
        }
    }

    public EntityClamEnder createChild(EntityAgeable entity) {
        return new EntityClamEnder(this.worldObj);
    }

    protected boolean canDespawn() {
        return false;
    }
}
