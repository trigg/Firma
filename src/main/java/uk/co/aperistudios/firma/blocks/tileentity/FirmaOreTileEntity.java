package uk.co.aperistudios.firma.blocks.tileentity;

import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.Util;
import uk.co.aperistudios.firma.types.OresEnum;
import uk.co.aperistudios.firma.types.RockEnum;
import uk.co.aperistudios.firma.types.RockEnum2;
import uk.co.aperistudios.firma.types.RocksEnum;

public class FirmaOreTileEntity extends TileEntity {

	public int grade = 0;
	public OresEnum ore;
	public RocksEnum rock;

	@Override
	public boolean shouldRefresh(World inWorld, BlockPos inPos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	public void setState(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in);
		if (Util.isRockEnum1(in)) {
			rock = RocksEnum.get(RockEnum.getName(meta));
		} else {
			rock = RocksEnum.get(RockEnum2.getName(meta));
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		ore = OresEnum.values()[compound.getInteger("ore")];
		rock = RocksEnum.values()[compound.getInteger("rock")];
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("ore", OresEnum.indexOf(ore));
		compound.setInteger("rock", RocksEnum.indexOf(rock));
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
}
