package uk.co.aperistudios.firma.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.google.common.collect.Lists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import uk.co.aperistudios.firma.generation.layers.FirmaGenLayer;

public class FirmaBiomeProvider extends BiomeProvider {
	private GenLayer genBiomes;
	private GenLayer biomeIndexLayer;
	private BiomeCache biomeCache;
	private ArrayList<Biome> biomesToSpawnIn;

	public FirmaBiomeProvider(long seed, WorldType worldTypeIn, String options) {
		this.biomeCache = new BiomeCache(this);
		FirmaBiome[] allowedBiomesFirma = { FirmaBiome.PLAINS, FirmaBiome.HILLS ,FirmaBiome.SWAMP };
		this.biomesToSpawnIn = Lists.newArrayList(allowedBiomesFirma);
		GenLayer[] agenlayer = FirmaGenLayer.initialize(seed, worldTypeIn);
		this.genBiomes = agenlayer[0];
		this.biomeIndexLayer = agenlayer[1];
	}

	@Override
	public boolean areBiomesViable(int x, int z, int radius, List<Biome> allowed) {
		IntCache.resetIntCache();
		int var5 = x - radius >> 2;
		int var6 = x - radius >> 2;
		int var7 = x + radius >> 2;
		int var8 = z + radius >> 2;
		int var9 = var7 - var5 + 1;
		int var10 = var8 - var6 + 1;
		int[] var11 = this.genBiomes.getInts(var5, var6, var9, var10);

		for (int var12 = 0; var12 < var9 * var10; ++var12) {
			FirmaBiome var13 = FirmaBiome.getBiomeList()[var11[var12]];
			if (!allowed.contains(var13)) {
				throw new RuntimeException("Non-viable biome");
				// return false;
			}
		}
		return true;
	}

	@Override
	public Biome[] getBiomesForGeneration(Biome[] biome, int x, int y, int width, int height) {
		IntCache.resetIntCache();

		if (biome == null || biome.length < width * height)
			biome = new FirmaBiome[width * height];

		int[] var6 = this.genBiomes.getInts(x, y, width, height);
		for (int var7 = 0; var7 < width * height; ++var7) {
			int index = Math.max(var6[var7], 0);
			biome[var7] = FirmaBiome.getBiome(index);
		}

		return biome;
	}

	public Biome[] loadBlockGeneratorData(Biome[] par1, int par2, int par3, int par4, int par5) {
		return this.getBiomes(par1, par2, par3, par4, par5, true);
	}

	@Override
	public Biome[] getBiomes(Biome[] oldBiomeList, int x, int z, int width, int depth) {
		return this.getBiomes(oldBiomeList, x, z, width, depth, true);
	}

	@Override
	public Biome[] getBiomes(Biome[] biome, int x, int z, int width, int height, boolean useCache) {
		IntCache.resetIntCache();

		if (biome == null || biome.length < width * height)
			biome = new FirmaBiome[width * height];

		if (useCache && width == 16 && height == 16 && (x & 15) == 0 && (z & 15) == 0) {
			Biome[] var9 = this.biomeCache.getCachedBiomes(x, z);
			System.arraycopy(var9, 0, biome, 0, width * height);
			return biome;
		}
		int[] var7 = this.biomeIndexLayer.getInts(x, z, width, height);
		for (int zCoord = 0; zCoord < width; ++zCoord) {
			for (int xCoord = 0; xCoord < height; ++xCoord) {
				int id = var7[zCoord * width + xCoord] != -1 ? var7[zCoord * width + xCoord] : 0;
				biome[zCoord * width + xCoord] = FirmaBiome.getBiome(id);
			}
		}
		return biome;
	}

	@Override
	public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
		IntCache.resetIntCache();
		int i = x - range >> 2;
		int j = z - range >> 2;
		int k = x + range >> 2;
		int l = z + range >> 2;
		int i1 = k - i + 1;
		int j1 = l - j + 1;
		int[] aint = this.genBiomes.getInts(i, j, i1, j1);
		BlockPos blockpos = null;
		int k1 = 0;

		for (int l1 = 0; l1 < i1 * j1; ++l1) {
			int i2 = i + l1 % i1 << 2;
			int j2 = j + l1 / i1 << 2;
			FirmaBiome biome = (FirmaBiome) Biome.getBiome(aint[l1]);

			if (biomes.contains(biome) && (blockpos == null || random.nextInt(k1 + 1) == 0)) {
				blockpos = new BlockPos(i2, 0, j2);
				System.out.println("Placing spawn in "+biome.getBiomeName());
				//return blockpos;
				++k1;
			}
		}

		return blockpos;
	}

	@Override
	public List<Biome> getBiomesToSpawnIn() {
		return this.biomesToSpawnIn;
	}

	@Override
	public Biome getBiome(BlockPos pos) {
		return this.getBiome(pos,null);
	}

	@Override
	public Biome getBiome(BlockPos pos, Biome defaultBiome) {
		return this.biomeCache.getBiome(pos.getX(), pos.getZ(), defaultBiome);
	}
	
    @Override
	public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original)
    {
        return null;
    }
    
    @Override
	public void cleanupCache()
    {
    	super.cleanupCache();
        this.biomeCache.cleanupCache();
    }
    
    @Override
    public boolean isFixedBiome() {
    	return false;
    }
}
