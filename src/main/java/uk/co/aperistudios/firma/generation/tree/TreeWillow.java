package uk.co.aperistudios.firma.generation.tree;

import java.util.Random;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.Display;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TreeWillow extends FirmaTree {

	public TreeWillow(WillowFiller willowFiller) {
		filler = willowFiller;
	}

	@Override
	public boolean generateTree(World w, Random rand, BlockPos pos) {
		filler.leaf=leaf;
		for(int y = 0; y < 5; y++){
			fill(w,pos.add(0,y,0));
		}
		for(int branch = 0; branch < 5; branch++){
			float incx = rand.nextFloat()*2f-1f;
			float incz = rand.nextFloat()*2f-1f;
			float incy = rand.nextFloat()/4f;
			float x=0,y=0,z=0;
			for(int bc = 0; bc < 5; bc++){
				x+=incx; y+=incy; z+=incz;
				BlockPos p = pos.add(x, y+5, z);
				Mouse.setGrabbed(false);
				fill(w,p);
				filler.fillLeaves(w, p, rand);
			}
		}
		return false;
	}

}
