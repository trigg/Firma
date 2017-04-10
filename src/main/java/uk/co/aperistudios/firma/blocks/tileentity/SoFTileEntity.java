package uk.co.aperistudios.firma.blocks.tileentity;

import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.items.PebbleItem;

public class SoFTileEntity extends TileEntity {

	private ItemStack itemstack;

	public ItemStack getItem() {
		return itemstack;
	}
	
	@Override
	public boolean canRenderBreaking() {
		return false;
	}
	
	@Override
	public boolean hasFastRenderer() {
		return true;
	}
	
	@Override
	public boolean shouldRefresh(World inWorld, BlockPos inPos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		String id = compound.getString("shitid");
		int meta = compound.getInteger("meta");
		itemstack = new ItemStack(Item.getByNameOrId(id),1,meta);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setString("shitid", itemstack.getItem().getRegistryName().toString());
		compound.setInteger("meta", itemstack.getItemDamage());
		return compound;
	}
	
	@Override
	public double getMaxRenderDistanceSquared() {
		return 1024;
	}
	
    @Override
	@Nullable
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
    }

    @Override
	public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

	public void setItem(ItemStack itemForArea) {
		itemstack = itemForArea;
	}

}
