package uk.co.aperistudios.firma.generation.tree;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class LeafFiller {
	
	protected IBlockState leaf;

	public abstract void fillLeaves(World w, BlockPos pos, Random r);
	
	public void fill(World w, BlockPos pos){
		IBlockState bs = w.getBlockState(pos);
		Block b = bs.getBlock();
		if(b.canBeReplacedByLeaves(bs, w, pos)){
			w.setBlockState(pos, leaf);
		}
	}
}
