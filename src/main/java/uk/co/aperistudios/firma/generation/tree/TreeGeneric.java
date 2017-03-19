package uk.co.aperistudios.firma.generation.tree;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;

public class TreeGeneric extends FirmaTree {
	
	private int width;
	private int height;
	private int heightVar;

	public TreeGeneric(int height, int width, LeafFiller filler, int heightVar) {
		this.height=height;this.width=width;this.filler=filler; this.heightVar = heightVar;
	}

	@Override
	public boolean generateTree(World worldIn, Random rand, BlockPos pos) {
		filler.leaf = leaf;
		// Check light levels
		if(worldIn.getLight(pos)<7){
			return false;
		}
		// Check height of trunk
		int h = rand.nextInt(heightVar) + height;
		for(int y = pos.getY(); y<pos.getY()+h; y++){
			if(!checkBlockAt(worldIn,pos.getX(),y,pos.getZ())){
				return false;
			}
		}
		// Place central trunk
		for(int y = 0; y<h; y++){
			fill(worldIn, pos.add(0, y, 0));
		}
		filler.fillLeaves(worldIn, pos.add(0,h-1,0), rand);
		
		return false;
	}
	
	public boolean checkBlockAt(World w, int x,int y,int z){
		BlockPos pos = new BlockPos(x,y,z);
		IBlockState s = w.getBlockState(pos);
		Block b = s.getBlock();
		return b.canBeReplacedByLeaves(s, w, pos);
	}

}