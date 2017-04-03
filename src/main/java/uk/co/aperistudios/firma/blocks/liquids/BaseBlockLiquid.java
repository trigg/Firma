package uk.co.aperistudios.firma.blocks.liquids;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BaseBlockLiquid extends BlockFluidClassic {

	public BaseBlockLiquid(Fluid fluid, Material material) {
		super(fluid, material);
	}
	
	@Override
	public boolean canBeReplacedByLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}
}
