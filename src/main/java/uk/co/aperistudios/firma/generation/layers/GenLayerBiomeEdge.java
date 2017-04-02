package uk.co.aperistudios.firma.generation.layers;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomeEdge extends FirmaGenLayer {
	public GenLayerBiomeEdge(long par1, GenLayer par3GenLayer) {
		super(par1);
		this.parent = par3GenLayer;
	}

	/**
	 * Switches biomes so that there's less alias. Where non-mountainous touch mountainous replaces with mountain edge etcetc
	 */
	@Override
	public int[] getInts(int par1, int par2, int xSize, int zSize) {
		int[] inCache = this.parent.getInts(par1 - 1, par2 - 1, xSize + 2, zSize + 2);
		validateIntArray(inCache, xSize + 2, zSize + 2);
		int[] outCache = IntCache.getIntCache(xSize * zSize);
		int var10;
		int var11;
		int var12;
		int var13;

		for (int z = 0; z < zSize; ++z) {
			for (int x = 0; x < xSize; ++x) {
				this.initChunkSeed(x + par1, z + par2);
				int thisID = inCache[x + 1 + (z + 1) * (xSize + 2)];
				var10 = inCache[x + 1 + (z + 1 - 1) * (xSize + 2)];
				var11 = inCache[x + 1 + 1 + (z + 1) * (xSize + 2)];
				var12 = inCache[x + 1 - 1 + (z + 1) * (xSize + 2)];
				var13 = inCache[x + 1 + (z + 1 + 1) * (xSize + 2)];

				if (thisID == Layer.EXHILLS) {
					if(!(isMountain(var10) && isMountain(var11) && isMountain(var12) && isMountain(var13))){
						outCache[x + z * xSize] = Layer.EXHILLSEDGE;
					}
				} else if (thisID == Layer.HILLS) {
					if (!(isMountain(var10) && isMountain(var11) && isMountain(var12) && isMountain(var13))){
						outCache[x + z * xSize] = Layer.HILLSEDGE;
					}
				} else {
					outCache[x + z * xSize] = thisID;
				}
			}
		}
		validateIntArray(outCache, xSize, zSize);

		return outCache;
	}
}
