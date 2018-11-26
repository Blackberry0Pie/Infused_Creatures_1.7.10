package infusedcreatures.common.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import infusedcreatures.common.config.ICConfigBlocks;
import infusedcreatures.common.config.ICConfigItems;
import infusedcreatures.common.items.ItemCrystalEssence;
import infusedcreatures.common.tiles.TileCrystalEssence;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class BlockCrystalEssence extends Block {
    public BlockCrystalEssence() {
        super(Material.rock);
        setResistance(10.0F);
        setHardness(2.0F);
        setStepSound(soundTypeStone);
    }

    public static IIcon icon;

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        this.icon = ir.registerIcon("thaumcraft:pedestal_top");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        return this.icon;
    }

    // @SideOnly(Side.CLIENT)
    // public int colorMultiplier(IBlockAccess access, int x, int y, int z){
    // //return 16777215;
    // //return -22016;
    // //Block block = access.getBlock(x, y, z);
    // return 0xffaaaaff;
    // }

    public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
        if (worldObj.getBlock(x, y, z) != this) return false;
        return true;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return ICConfigBlocks.blockCrystalEssenceRI;
    }

    static HashMap<WorldCoordinates, Aspect> st = new HashMap();

    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if ((tile != null) && ((tile instanceof TileCrystalEssence)) && (((TileCrystalEssence) tile).aspect != null)) {
            st.put(new WorldCoordinates(x, y, z, world.provider.dimensionId), ((TileCrystalEssence) tile).aspect);
        }
        super.breakBlock(world, x, y, z, block, meta);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> dropped = new ArrayList();

        Aspect aspect = Aspect.PLANT;
        if (st.containsKey(new WorldCoordinates(x, y, z, world.provider.dimensionId))) {
            aspect = (Aspect) st.get(new WorldCoordinates(x, y, z, world.provider.dimensionId));
        } else {
            TileEntity tile = world.getTileEntity(x, y, z);
            if ((tile != null) && ((tile instanceof TileCrystalEssence)) && (((TileCrystalEssence) tile).aspect != null)) {
                aspect = ((TileCrystalEssence) tile).aspect;
            }
        }

        st.remove(new WorldCoordinates(x, y, z, world.provider.dimensionId));

        ItemStack is = new ItemStack(ICConfigItems.itemCrystalEssence);
        ((ItemCrystalEssence) is.getItem()).setAspects(is, new AspectList().add(aspect, 8));
        dropped.add(is);
        return dropped;
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return ICConfigItems.itemCrystalEssence;
    }

    public Item getItemDropped(int par1, Random par2Random, int par3) {
        // return Item.getItemById(0);
        return ICConfigItems.itemCrystalEssence;
    }

    public boolean getBlocksMovement(IBlockAccess par1iBlockAccess, int par2, int par3, int par4) {
        return true;
    }

    public boolean hasTileEntity(int metadata) {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileCrystalEssence();
    }
}
