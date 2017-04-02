package uk.co.aperistudios.firma.generation.layers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import uk.co.aperistudios.firma.generation.FirmaBiome;

public abstract class FirmaGenLayer extends GenLayer {

	protected long worldGenSeed;
	// protected FirmaGenLayer parent;
	protected long chunkSeed;

	public static FirmaGenLayer[] initialize(long seed, WorldType worldTypeIn) {
		FirmaGenLayer continent = genContinent(0L, false);
		continent = new GenLayerDeepOcean(4L, continent);
		drawImage(512, continent, "8b Continents Done Deep Ocean");
		byte var4 = 4;

		// Create Biomes
		FirmaGenLayer continentCopy2 = FirmaGenLayerZoom.magnify(200L, continent, 0);
		FirmaGenLayer var17 = new FirmaGenLayerBiome(200L, continentCopy2, worldTypeIn);
		FirmaGenLayer lakes = new GenLayerLakes(200L, var17);
		continentCopy2 = FirmaGenLayerZoom.magnify(1000L, lakes, 2);
		FirmaGenLayer var18 = new GenLayerBiomeEdge(1000L, continentCopy2);
		for (int var7 = 0; var7 < var4; ++var7) {
			var18 = new FirmaGenLayerZoom(1000 + var7, var18);
			drawImage(512, var18, "18-" + var7 + " Zoom");
			if (var7 == 0)
				var18 = new FirmaGenLayerAddIsland(3L, var18);
			if (var7 == 1) {
				var18 = new FirmaGenLayerShore(1000L, var18);
				drawImage(512, var18, "18z Shore");
			}
		}

		// Create Rivers
		FirmaGenLayer riverCont = FirmaGenLayerZoom.magnify(1000L, continent, 2);
		riverCont = new FirmaGenLayerRiverInit(100L, riverCont);
		riverCont = FirmaGenLayerZoom.magnify(1000L, riverCont, 6);
		riverCont = new FirmaGenLayerRiver(1L, riverCont);
		riverCont = new FirmaGenLayerSmooth(1000L, riverCont);
		FirmaGenLayerSmoothBiome smoothContinent = new FirmaGenLayerSmoothBiome(1000L, var18);
		FirmaGenLayerRiverMix riverMix = new FirmaGenLayerRiverMix(100L, smoothContinent, riverCont);
		FirmaGenLayer finalCont = FirmaGenLayerZoom.magnify(1000L, riverMix, 2);
		finalCont = new FirmaGenLayerSmoothBiome(1001L, finalCont);
		riverMix.initWorldGenSeed(seed);
		finalCont.initWorldGenSeed(seed);
		drawImage(512, riverMix, "Biome 20");
		drawImage(512, finalCont, "Biome 21");
		return new FirmaGenLayer[] { riverMix, finalCont };
	}

	public static FirmaGenLayer genContinent(long seed, boolean oceanReduction) {
		FirmaGenLayer continentStart = new FirmaGenLayerIsland(1L + seed);
		FirmaGenLayerFuzzyZoom continentFuzzyZoom = new FirmaGenLayerFuzzyZoom(2000L, continentStart);
		FirmaGenLayer var10 = new FirmaGenLayerAddIsland(1L, continentFuzzyZoom);
		FirmaGenLayer var11 = new FirmaGenLayerZoom(2001L, var10);
		var10 = new FirmaGenLayerAddIsland(2L, var11);
		var11 = new FirmaGenLayerZoom(2002L, var10);
		var10 = new FirmaGenLayerAddIsland(3L, var11);
		var11 = new FirmaGenLayerZoom(2003L, var10);
		FirmaGenLayer continent = new FirmaGenLayerAddIsland(4L, var11);
		return continent;
	}

	public static void drawImage(int size, FirmaGenLayer genlayer, String name) {
		try {
			File outFile = new File(name + ".bmp");
			if (outFile.exists())
				return;
			int[] ints = genlayer.getInts(0, 0, size, size);
			BufferedImage outBitmap = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = (Graphics2D) outBitmap.getGraphics();
			graphics.clearRect(0, 0, size, size);
			for (int x = 0; x < size; x++) {
				for (int z = 0; z < size; z++) {
					if (ints[x * size + z] != -1 && FirmaBiome.getBiomeList()[ints[x * size + z]] != null) {
						graphics.setColor(Color.getColor("", ((FirmaBiome) Biome.getBiome(ints[x * size + z])).getBiomeColor()));
						graphics.drawRect(x, z, 1, 1);
					}
				}
			}
			System.out.println(outFile.getAbsolutePath());
			ImageIO.write(outBitmap, "BMP", outFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FirmaGenLayer(long par1) {
		super(par1);
		this.baseSeed = par1;
		this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
		this.baseSeed += par1;
		this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
		this.baseSeed += par1;
		this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
		this.baseSeed += par1;

	}

	/**
	 * Initialize layer's local worldGenSeed based on its own baseSeed and the
	 * world's global seed (passed in as an argument).
	 */
	@Override
	public void initWorldGenSeed(long par1) {
		worldGenSeed = par1;
		if (this.parent != null)
			parent.initWorldGenSeed(par1);

		worldGenSeed *= worldGenSeed * 6364136223846793005L + 1442695040888963407L;
		worldGenSeed += baseSeed;
		worldGenSeed *= worldGenSeed * 6364136223846793005L + 1442695040888963407L;
		worldGenSeed += baseSeed;
		worldGenSeed *= worldGenSeed * 6364136223846793005L + 1442695040888963407L;
		worldGenSeed += baseSeed;
	}

	/**
	 * Initialize layer's current chunkSeed based on the local worldGenSeed and
	 * the (x,z) chunk coordinates.
	 */
	@Override
	public void initChunkSeed(long par1, long par3) {
		chunkSeed = worldGenSeed;
		chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
		chunkSeed += par1;
		chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
		chunkSeed += par3;
		chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
		chunkSeed += par1;
		chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
		chunkSeed += par3;
	}

	/**
	 * returns a LCG pseudo random number from [0, x). Args: int x
	 */
	@Override
	protected int nextInt(int par1) {
		int var2 = (int) ((this.chunkSeed >> 24) % par1);
		if (var2 < 0)
			var2 += par1;
		chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
		chunkSeed += worldGenSeed;
		return var2;
	}

	@Override
	public abstract int[] getInts(int x, int y, int w, int h);

	public static int validateInt(int[] array, int index) {
		return array[index];
	}

	public void validateIntArray(int[] array, int xSize, int zSize) {
		for (int z = 0; z < zSize; z++) {
			for (int x = 0; x < xSize; x++) {
				int id = array[x + z * xSize];
				Biome b = Biome.getBiomeForId(id);
				if (!(b instanceof FirmaBiome)) {
					System.out.println(this.getClass() + " // Error Array garbage data: " + array[x + z * xSize]);
					throw new RuntimeException(id + " " + b.getBiomeName() + " Not Firma Biome");
					// return;
				}
			}
		}
	}

	public static boolean isOceanic(int id) {
		return id == Layer.OCEAN || id == Layer.DOCEAN;
	}

	public static boolean isMountain(int id) {
		return id == Layer.HILLS || id == Layer.HILLSEDGE || id == Layer.EXHILLS || id == Layer.EXHILLSEDGE;
	}

	public static boolean isBeach(int id) {
		return id == Layer.BEACH || id == Layer.GBEACH;
	}
}
