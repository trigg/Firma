package uk.co.aperistudios.firma.generation.layers;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.IntCache;
import uk.co.aperistudios.firma.generation.FirmaBiome;

public class GenLayerDeepOcean extends FirmaGenLayer {
	public GenLayerDeepOcean(long seed, FirmaGenLayer parent)
	{
		super(seed);
		this.parent = parent;
	}

	@Override
	public int[] getInts(int parX, int parZ, int parXSize, int parZSize)
	{
		int xSize = parXSize + 2;
		int zSize = parZSize + 2;
		int thisID;
		int[] parentIDs = this.parent.getInts(parX - 1, parZ - 1, xSize, zSize);
		int[] outCache = IntCache.getIntCache(parXSize * parZSize);

		for (int z = 0; z < parZSize; ++z)
		{
			for (int x = 0; x < parXSize; ++x)
			{
				int northID = parentIDs[x + 1 + z * xSize];
				int rightID = parentIDs[x + 2 + (z + 1) * xSize];
				int leftID = parentIDs[x + (z + 1) * xSize];
				int southID = parentIDs[x + 1 + (z + 2) * xSize];
				thisID = parentIDs[x + 1 + (z + 1) * xSize];
				int oceanCount = 0;
				int outIndex = x + z * parXSize;

				if (isOceanic(northID))
				{
					++oceanCount;
				}

				if (isOceanic(rightID))
				{
					++oceanCount;
				}

				if (isOceanic(leftID))
				{
					++oceanCount;
				}

				if (isOceanic(southID))
				{
					++oceanCount;
				}

				if (isOceanic(thisID) && oceanCount > 3)
				{
					outCache[outIndex] = Biome.getIdForBiome(FirmaBiome.DEEPOCEAN);
				}
				else
				{
					outCache[outIndex] = thisID;
				}
			}
		}

		return outCache;
	}

}
