package uk.co.aperistudios.firma.generation.tree;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CedarFiller extends LeafFiller {

	@Override
	public void fillLeaves(World w, BlockPos pos, Random r) {
		fill(w,pos.add(-1, 0, 0));
		fill(w,pos.add(1, 0, 0));
		fill(w,pos.add(0, -1, 0));
		fill(w,pos.add(0, 1, 0));
		fill(w,pos.add(0, 0, -1));
		fill(w,pos.add(0, 0, 1));
	}

}
