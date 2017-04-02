package uk.co.aperistudios.firma.generation.layers;

import net.minecraft.world.biome.Biome;
import uk.co.aperistudios.firma.generation.FirmaBiome;

/***
 * While not slow, repeatedly calling Biome.getIdFor in chunk gen feels really
 * bad. Hardcoding them here seems like a great idea.
 * 
 * @author triggerhapp
 *
 */
public class Layer {
	public static int OCEAN, DOCEAN, LAKE, RIVER, PLAINS, HILLS, EXHILLS, HILLSEDGE, EXHILLSEDGE, SWAMP, BEACH, GBEACH, FOREST;

	public static void prep() {
		OCEAN = Biome.getIdForBiome(FirmaBiome.OCEAN);
		DOCEAN = Biome.getIdForBiome(FirmaBiome.DEEPOCEAN);
		LAKE = Biome.getIdForBiome(FirmaBiome.LAKE);
		RIVER = Biome.getIdForBiome(FirmaBiome.RIVER);
		PLAINS = Biome.getIdForBiome(FirmaBiome.PLAINS);
		HILLS = Biome.getIdForBiome(FirmaBiome.HILLS);
		EXHILLS = Biome.getIdForBiome(FirmaBiome.EXHILLS);
		HILLSEDGE = Biome.getIdForBiome(FirmaBiome.HILLSEDGE);
		EXHILLSEDGE = Biome.getIdForBiome(FirmaBiome.EXHILLSEDGE);
		SWAMP = Biome.getIdForBiome(FirmaBiome.SWAMP);
		BEACH = Biome.getIdForBiome(FirmaBiome.BEACH);
		GBEACH = Biome.getIdForBiome(FirmaBiome.GRAVELBEACH);
		FOREST = Biome.getIdForBiome(FirmaBiome.FOREST);

	}

}
