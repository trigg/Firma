package uk.co.aperistudios.firma.blocks.recolour;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.Biome;
import uk.co.aperistudios.firma.generation.FirmaBiome;

public class GrassColor implements IBlockColor {
	@Override
	public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
		// return worldIn.getBiome(pos).getFoliageColorAtPos(pos);
	//	Mouse.setGrabbed(false);

		Biome b = worldIn.getBiome(pos);
		if(!(b instanceof FirmaBiome)){
		//	Mouse.setGrabbed(false);
			//throw new RuntimeException("Non-Firma Biome!");
		}
		int c = b.getGrassColorAtPos(pos);
		// TODO Mix in seasonal colours
		return c;
	}
}
