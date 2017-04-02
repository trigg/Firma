package uk.co.aperistudios.firma.generation.layers;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.IntCache;
import uk.co.aperistudios.firma.generation.FirmaBiome;

public class FirmaGenLayerBiome extends FirmaGenLayer {

	/** this sets all the biomes that are allowed to appear in the overworld */
	private FirmaBiome[] allowedBiomes = new FirmaBiome[] {
			FirmaBiome.PLAINS,
			FirmaBiome.FOREST,
			FirmaBiome.HILLS,
			FirmaBiome.EXHILLS,
			FirmaBiome.BEACH,
			FirmaBiome.LAKE,
			FirmaBiome.SWAMP
	};

	public FirmaGenLayerBiome(long par1, FirmaGenLayer par3GenLayer, WorldType worldTypeIn)
	{
		super(par1);
		this.parent = par3GenLayer;
	}

	/**
	 * Takes Continent data (only 0's and 1's) and for anything not ocean it throws any of the allowed biomes back. Can't help but wonder if I could do better than "Any of allowed" and possibly have hills mostly in-land etc. Will have to see
	 */
	@Override
	public int[] getInts(int x, int y, int w, int h)
	{
		int[] parentInts = this.parent.getInts(x, y, w, h);
		int[] newInts = IntCache.getIntCache(w * h);

		for (int currentX = 0; currentX < h; ++currentX)
		{
			for (int currentY = 0; currentY < w; ++currentY)
			{
				this.initChunkSeed(currentY + x, currentX + y);
				int id = parentInts[currentY + currentX * w];
				if (id==0){
					newInts[currentY + currentX * w] = Layer.OCEAN;
				}else{ 
					newInts[currentY + currentX * w] = Biome.getIdForBiome(this.allowedBiomes[this.nextInt(this.allowedBiomes.length)]);
				}

				validateInt(newInts, currentY + currentX * w);
			}
		}
		validateIntArray(newInts, w, h);
		return newInts;
	}
}
