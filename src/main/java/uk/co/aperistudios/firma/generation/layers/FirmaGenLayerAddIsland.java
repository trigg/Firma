package uk.co.aperistudios.firma.generation.layers;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class FirmaGenLayerAddIsland extends FirmaGenLayer {

	public FirmaGenLayerAddIsland(long par1, GenLayer par3GenLayer)
	{
		super(par1);
		this.parent = par3GenLayer;
	}

	/**
	 * Returns an array of 0's/1's. 0 Becomes ocean, 1 becomes land
	 */
	@Override
	public int[] getInts(int x, int y, int w, int h)
	{
		int xminus1 = x - 1;
		int yminus1 = y - 1;
		int widthplus2 = w + 2;
		int heightplus2 = h + 2;
		int[] parentInts = this.parent.getInts(xminus1, yminus1, widthplus2, heightplus2);
		int[] var10 = IntCache.getIntCache(w * h);

		for (int cy = 0; cy < h; ++cy)
		{
			for (int cz = 0; cz < w; ++cz)
			{
				int var13 = parentInts[cz + 0 + (cy + 0) * widthplus2];
				int var14 = parentInts[cz + 2 + (cy + 0) * widthplus2];
				int var15 = parentInts[cz + 0 + (cy + 2) * widthplus2];
				int var16 = parentInts[cz + 2 + (cy + 2) * widthplus2];
				int var17 = parentInts[cz + 1 + (cy + 1) * widthplus2];
				this.initChunkSeed(cz + x, cy + y);

				if (var17 == 0 && (var13 != 0 || var14 != 0 || var15 != 0 || var16 != 0))
				{
					int var18 = 1;
					int var19 = 1;

					if (var13 != 0 && this.nextInt(var18++) == 0)
						var19 = var13;

					if (var14 != 0 && this.nextInt(var18++) == 0)
						var19 = var14;

					if (var15 != 0 && this.nextInt(var18++) == 0)
						var19 = var15;

					if (var16 != 0 && this.nextInt(var18++) == 0)
						var19 = var16;

					if (this.nextInt(3) == 0)
						var10[cz + cy * w] = var19;
					else
						var10[cz + cy * w] = 0;
				}
				else if (var17 > 0 && (var13 == 0 || var14 == 0 || var15 == 0 || var16 == 0))
				{
					if (this.nextInt(5) == 0)
						var10[cz + cy * w] = 0;
					else
						var10[cz + cy * w] = var17;
				}
				else
				{
					var10[cz + cy * w] = var17;
				}
			}
		}
		return var10;
	}


}
