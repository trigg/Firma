package uk.co.aperistudios.firma.generation.tree;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;

public abstract class FirmaTree extends WorldGenerator {
	protected IBlockState bs, leaf;
	protected LeafFiller filler;

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		return generateTree(worldIn, rand, position);
	}

	public abstract boolean generateTree(World worldIn, Random rand, BlockPos position);

	public void set(IBlockState log, IBlockState leaf) {
		this.bs=log;this.leaf=leaf;
	}
	
	public void fill(World w, BlockPos p){
		this.setBlockAndNotifyAdequately(w, p, bs);
	}
}
