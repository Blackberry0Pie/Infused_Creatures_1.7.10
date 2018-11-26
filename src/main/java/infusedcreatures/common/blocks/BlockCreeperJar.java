package infusedcreatures.common.blocks;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import infusedcreatures.common.InfusedCreatures;
import infusedcreatures.common.config.Config;
import infusedcreatures.common.config.ICConfigBlocks;
import infusedcreatures.common.config.ICConfigItems;
import infusedcreatures.common.tiles.TileCreeperJar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCreeperJar extends BlockContainer {
    public BlockCreeperJar() {
        super(net.minecraft.block.material.Material.rock);
        setHardness(2.0F);
        setResistance(17.0F);
        setStepSound(new thaumcraft.common.lib.CustomSoundType("gore", 0.15F, 0.8F));
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        setCreativeTab(InfusedCreatures.tabIC);
    }

    public IIcon icon = null;
    public IIcon iconInhibited = null;
    // public IIcon iconLiquid = null;
    public int explosionSize = 2;

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        this.icon = ir.registerIcon("infusedcreatures:creeperjaritem");
        this.iconInhibited = ir.registerIcon("infusedcreatures:creeperjariteminhibited");
        // this.iconLiquid = ir.registerIcon("thaumcraft:animatedglow");
    }

    public IIcon getIcon(int i, int md) {
        if (md == 1) {
            return this.iconInhibited;
        }
        return this.icon;
    }

    public IIcon getIcon(IBlockAccess iblockaccess, int i, int j, int k, int side) {
        return this.icon;
    }

    public int getRenderType() {
        return ICConfigBlocks.blockCreeperJarRI;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderBlockPass() {
        return 1;
    }

    public int damageDropped(int metadata) {
        return metadata;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if (metadata == 0) return (TileEntity) new TileCreeperJar(); // check
                                                                     // this
        return super.createTileEntity(world, metadata);
    }

    public TileEntity createNewTileEntity(World var1, int md) {
        return null;
    }

    public boolean hasComparatorInputOverride() {
        return true;
    }

    public int getComparatorInputOverride(World world, int x, int y, int z, int rs) {
        TileEntity te = world.getTileEntity(x, y, z);
        if ((te != null) && ((te instanceof TileCreeperJar))) {
            TileCreeperJar tcj = ((TileCreeperJar) te);
            float r = tcj.amount / tcj.maxAmount;
            return MathHelper.floor_float(r * 14.0F) + (tcj.amount > 0 ? 1 : 0);
        }
        return 0;
    }

    public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {
        TileEntity te = world.getTileEntity(x, y, z);
        if ((te != null) && ((te instanceof TileCreeperJar))) {
            TileCreeperJar tcj = ((TileCreeperJar) te);
            if (!tcj.isInhibited) {
                boolean mobGriefing = world.getGameRules().getGameRuleBooleanValue("mobGriefing");
                world.createExplosion(null, x + 0.5D, y + 0.5D, z + 0.5D, explosionSize * (1 + tcj.amount / tcj.maxAmount), mobGriefing && Config.creeperJarExplodify);
            }
        }
        super.breakBlock(world, x, y, z, par5, par6);
    }

    public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
        dropBlockAsItem(par1World, par2, par3, par4, par5, 0);
        super.onBlockHarvested(par1World, par2, par3, par4, par5, par6EntityPlayer);
    }

    // public boolean isUseableByPlayer(EntityPlayer player) {
    // return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this &&
    // player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
    // }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float f1, float f2, float f3) {
        TileEntity te = world.getTileEntity(x, y, z);
        if ((te != null) && (te instanceof TileCreeperJar)) {
            TileCreeperJar tcj = ((TileCreeperJar) te);
            if (player != null && player.getHeldItem() != null && player.getHeldItem().getItem() != null && player.getHeldItem().getItem() == ICConfigItems.itemCreeperInhibitor) {
                ItemStack held = player.getHeldItem();
                if (held.stackSize > 0 && tcj.isInhibited == false) {
                    tcj.inhibit();
                    if (!player.capabilities.isCreativeMode) {
                        held.stackSize -= 1;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList();
        TileEntity te = world.getTileEntity(x, y, z);
        if ((te != null) && ((te instanceof TileCreeperJar))) {
            TileCreeperJar tcj = ((TileCreeperJar) te);
            ItemStack drop = new ItemStack(ICConfigBlocks.blockCreeperJar);
            if (tcj.isInhibited) {
                // drop = new ItemStack(ICConfigBlocks.blockCreeperJar, 1, 1);
                ItemStack ci = new ItemStack(ICConfigItems.itemCreeperInhibitor);
                drops.add(ci);
            }
            drops.add(drop);
        }
        return drops;
    }

    // public boolean onBlockActivated(World world, int x, int y, int z,
    // EntityPlayer player, int side, float what, float these, float are)
    // {
    // TileEntity te = world.getTileEntity(x, y, z);
    //
    // if ((te != null) && ((te instanceof TileCreeperJar)) &&
    // (player.isSneaking()) && (((TileCreeperJar)te).aspectFilter != null) &&
    // (side == ((TileCreeperJar)te).facing))
    // {
    // ((TileCreeperJar)te).aspectFilter = null;
    // if (world.isRemote) {
    // world.playSound(x + 0.5F, y + 0.5F, z + 0.5F, "thaumcraft:page", 1.0F,
    // 1.0F, false);
    // } else {
    // ForgeDirection fd = ForgeDirection.getOrientation(side);
    // world.spawnEntityInWorld(new net.minecraft.entity.item.EntityItem(world,
    // x + 0.5F + fd.offsetX / 3.0F, y + 0.5F, z + 0.5F + fd.offsetZ / 3.0F, new
    // ItemStack(ConfigItems.itemResource, 1, 13)));
    // }
    //
    //
    // }
    // else if ((te != null) && ((te instanceof TileCreeperJar)) &&
    // (player.isSneaking()) && (player.getHeldItem() == null)) {
    // ((TileCreeperJar)te).amount = 0;
    // if (((TileCreeperJar)te).aspectFilter == null)
    // ((TileCreeperJar)te).aspect = null;
    // if (world.isRemote) {
    // world.playSound(x + 0.5F, y + 0.5F, z + 0.5F, "thaumcraft:jar", 0.4F,
    // 1.0F, false);
    // world.playSound(x + 0.5F, y + 0.5F, z + 0.5F, "game.neutral.swim", 0.5F,
    // 1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F, false);
    // }
    //
    // }
    // else if ((te != null) && ((te instanceof TileCreeperJar)) &&
    // (player.getHeldItem() != null) && (((TileCreeperJar)te).aspectFilter ==
    // null) && (player.getHeldItem().getItem() == ConfigItems.itemResource) &&
    // (player.getHeldItem().getItemDamage() == 13))
    // {
    //
    //
    //
    // if ((((TileCreeperJar)te).amount == 0) &&
    // (((IEssentiaContainerItem)player.getHeldItem().getItem()).getAspects(player.getHeldItem())
    // == null)) {
    // return true;
    // }
    //
    // if ((((TileCreeperJar)te).amount == 0) &&
    // (((IEssentiaContainerItem)player.getHeldItem().getItem()).getAspects(player.getHeldItem())
    // != null)) {
    // ((TileCreeperJar)te).aspect =
    // ((IEssentiaContainerItem)(IEssentiaContainerItem)player.getHeldItem().getItem()).getAspects(player.getHeldItem()).getAspects()[0];
    // }
    //
    // player.getHeldItem().stackSize -= 1;
    // onBlockPlacedBy(world, x, y, z, player, null);
    // ((TileCreeperJar)te).aspectFilter = ((TileCreeperJar)te).aspect;
    // if (world.isRemote) {
    // world.playSound(x + 0.5F, y + 0.5F, z + 0.5F, "thaumcraft:jar", 0.4F,
    // 1.0F, false);
    // }
    // }
    //
    //
    // return true;
    // }
}
