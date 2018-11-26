package infusedcreatures.common.blocks;

import infusedcreatures.common.tiles.TileCreeperJar;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCreeperJarItem extends ItemBlock {
    public BlockCreeperJarItem(Block par1) {
        super(par1);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    public int getMetadata(int par1) {
        return par1;
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        boolean placed = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
        if (placed) {
            try {
                TileCreeperJar ts = (TileCreeperJar) world.getTileEntity(x, y, z);
                ts.facing = ForgeDirection.getOrientation(side).getOpposite();
                // if(metadata == 1){
                // ts.inhibit();
                // }
                ts.markDirty();
                world.markBlockForUpdate(x, y, z);
            } catch (Exception e) {
            }
        }
        return placed;
    }

    // @SideOnly(Side.CLIENT)
    // public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List
    // par3List) {
    // ItemStack i = new ItemStack(this, 1, 0);
    // ItemStack j = new ItemStack(this, 1, 1);
    // par3List.add(i);
    // par3List.add(j);
    // }
}
