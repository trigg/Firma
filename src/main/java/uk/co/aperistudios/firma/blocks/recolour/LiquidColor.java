package uk.co.aperistudios.firma.blocks.recolour;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class LiquidColor implements IBlockColor {
	private int colour;

	public LiquidColor(int colour) {
		this.colour = colour;
	}

	@Override
	public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
		/*
		 * if(worldIn == null){return 0xffffff;} if(pos==null){return 0xffffff;}
		 * TileEntity te = worldIn.getTileEntity(pos); if(te!=null && te
		 * instanceof MyTileEntity){
		 * 
		 * 
		 * }
		 */
		return colour;
	}

}
