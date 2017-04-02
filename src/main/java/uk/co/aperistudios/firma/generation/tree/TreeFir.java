package uk.co.aperistudios.firma.generation.tree;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TreeFir extends FirmaTree {

	private int height;

	public TreeFir(int height, LeafFiller basicLeafFiller) {
		this.height=height;
		filler = basicLeafFiller;
	}

	@Override
	public boolean generateTree(World w, Random rand, BlockPos pos) {
		filler.leaf=leaf;
		// Check light levels
		if(w.getLight(pos)<7){
			return false;
		}
		// Check height of trunk
		int h = 3+rand.nextInt(height);
		for(int y = 0; y<h; y++){
			if(!checkBlockAt(w,pos.add(0,y,0))){
				return false;
			}
		}
		for(int y = 0; y < h; y++){
			fill(w,pos.add(0,y,0));
		}
		for(int branch = 0; branch < 5; branch++){
			float incx = rand.nextFloat()-.5f;
			float incz = rand.nextFloat()-.5f;
			float incy = rand.nextFloat()*0.5f + 0.5f;
			float x=0,y=0,z=0;
			for(int bc = 0; bc < 5; bc++){
				x+=incx; y+=incy; z+=incz;
				BlockPos p = pos.add(x, y+h, z);
				fill(w,p);
				filler.fillLeaves(w, p, rand);
			}
		}
		return true;
	}

}
