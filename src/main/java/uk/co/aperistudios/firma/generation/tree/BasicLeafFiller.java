package uk.co.aperistudios.firma.generation.tree;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BasicLeafFiller extends LeafFiller {

	@Override
	public void fillLeaves(World w, BlockPos pos, Random r) {
		for(int c =-1; c<2; c++){
			fill(w,pos.add(c,0,-2));
			fill(w,pos.add(c,0,2));
			fill(w,pos.add(-2,0,c));
			fill(w,pos.add(2,0,c));
		}
		
		for(int x=-1; x<2; x++){
			for(int z=-1; z<2; z++){
				fill(w, pos.add(x,0,z));
				fill(w, pos.add(x,1,z));
				fill(w, pos.add(x,-1,z));
			}
		}
		fill(w,pos.add(0,2,0));
		
	}

}
