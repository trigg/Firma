package uk.co.aperistudios.firma.generation.tree;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TreeCedar extends FirmaTree {

	public TreeCedar(LeafFiller elmFiller) {
		filler = elmFiller;
	}

	@Override
	public boolean generateTree(World worldIn, Random rand, BlockPos pos) {
		filler.leaf = leaf;
		if(worldIn.getLight(pos)<7){
			return false;
		}
		// Check height of trunk
		int h = rand.nextInt(4) + 4;
		for(int y = 0; y<h; y++){
			if(!checkBlockAt(worldIn,pos.add(0,h,0))){
				return false;
			}
		}
		// Place central trunk
		for(int y = 0; y<h; y++){
			fill(worldIn, pos.add(0, y, 0));
			if(y>1){
				filler.fillLeaves(worldIn, pos.add(0,y,0), rand);				
			}
		}
		return true;
	}

}
