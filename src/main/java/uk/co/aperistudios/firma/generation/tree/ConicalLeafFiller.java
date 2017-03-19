package uk.co.aperistudios.firma.generation.tree;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ConicalLeafFiller extends LeafFiller {

	@Override
	public void fillLeaves(World w, BlockPos pos, Random r) {
		for(int x=-2; x<3; x++){
			for(int z=-2; z<3; z++){
				fill(w, pos.add(x, -3, z));
				fill(w, pos.add(x, -1, z));
			}
		}
		for(int x=-1; x<2; x++){
			for(int z=-1; z<2; z++){
				fill(w, pos.add(x,0,z));
				fill(w, pos.add(x,-2,z));
				fill(w, pos.add(x, 1, z));
			}
		}
		fill(w,pos.add(0,2,0));
		fill(w,pos.add(0,3,0));
		fill(w,pos.add(0,4,0));
		
	}
}
