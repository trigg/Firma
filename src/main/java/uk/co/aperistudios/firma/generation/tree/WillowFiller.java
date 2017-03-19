package uk.co.aperistudios.firma.generation.tree;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WillowFiller extends LeafFiller {

	@Override
	public void fillLeaves(World w, BlockPos pos, Random r) {
		for(int x = -1; x<2;x++){
			for(int z = -1; z<2; z++){
				fill(w,pos.add(0,1,0));
				for(int y = 0; y > -1 - (r.nextInt(3)); y--){
					fill(w,pos.add(x,y,z));
				}
			}
		}
	}

}
