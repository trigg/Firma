package uk.co.aperistudios.firma.generation;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.Util;
import uk.co.aperistudios.firma.generation.layers.FirmaGenLayer;

public class FirmaChunkGen implements IChunkGenerator {
	private IBlockState STONE = FirmaMod.rock.getStateFromMeta(1);
	private IBlockState OCEAN = FirmaMod.saltwater.getBlock().getDefaultState();
	private IBlockState RIVER = FirmaMod.freshwater.getBlock().getDefaultState();

	private final Random rand;
	public VoronoiNoise rockStrataNoise;
	private final World world;
	private final boolean mapFeaturesEnabled;
	private ChunkProviderSettings settings;
	private FirmaGenCaves caveGenerator = new FirmaGenCaves();
	private FirmaGenRavine ravineGenerator = new FirmaGenRavine();
	Biome[] biomesForTerrainGen = null;
	Biome[] biomesForBiomeGen = null;

	private NoiseGeneratorOctaves minLimitPerlinNoise;
	private NoiseGeneratorOctaves maxLimitPerlinNoise;
	private NoiseGeneratorOctaves mainPerlinNoise;
	private NoiseGeneratorOctaves depthNoise;
	double[] mainNoiseRegion;
	double[] minLimitRegion;
	double[] maxLimitRegion;
	double[] depthRegion;
	private float[] biomeWeights;
	private double[] heightMap;
	public FirmaChunkGen(World worldIn, boolean mapFeaturesEnabledIn) {
		this.world = worldIn;
		this.mapFeaturesEnabled = mapFeaturesEnabledIn;
		this.rand = new Random(world.getSeed());
		this.rockStrataNoise = new VoronoiNoise(world.getSeed(), (short) 0);

		String customSetting = worldIn.getWorldInfo().getGeneratorOptions();
		if(customSetting.length()==0){
			customSetting = "{\"baseSize\":20.0,\"heightScale\":2.0,\"seaLevel\":145}";
		}
		this.settings = ChunkProviderSettings.Factory.jsonToFactory(customSetting).build();
		this.OCEAN = this.settings.useLavaOceans ? FirmaMod.lava.getBlock().getDefaultState() : OCEAN;
		this.heightMap = new double[825];

		this.biomeWeights = new float[25];

		for (int i = -2; i <= 2; ++i) {
			for (int j = -2; j <= 2; ++j) {
				float f = 10.0F / MathHelper.sqrt(i * i + j * j + 0.2F);
				this.biomeWeights[i + 2 + (j + 2) * 5] = f;
			}
		}

		this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
		this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
		this.mainPerlinNoise = new NoiseGeneratorOctaves(this.rand, 8);
		this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
	}

	private Biome[] replaceBiomeBlocks(int x, int z, ChunkPrimer primer) {
		this.biomesForBiomeGen = ((FirmaBiomeProvider) this.world.getBiomeProvider()).getBiomes(this.biomesForBiomeGen, x * 16 - 1, z * 16 - 1, 18, 18);

		// Prep Rock Strata
		IBlockState topRock = Util.getRockStrata(rockStrataNoise.noise(x, 0, z, 0.02), 0);

		IBlockState dirt;
		IBlockState grass;
		IBlockState midRock = Util.getRockStrata(rockStrataNoise.noise(x, -30, z, 0.02), 1);
		IBlockState botRock = Util.getRockStrata(rockStrataNoise.noise(x, -60, z, 0.02), 2);
		IBlockState bedRock = Blocks.BEDROCK.getDefaultState();
		if (topRock == null) {
			topRock = Blocks.AIR.getDefaultState();
		}
		if (midRock == null) {
			midRock = Blocks.AIR.getDefaultState();
		}
		if (botRock == null) {
			botRock = Blocks.AIR.getDefaultState();
		}
		Block b = null;
		int cx, cy, cz, top, lt, lb, lm;
		for (cx = 0; cx < 16; cx++) {
			for (cz = 0; cz < 16; cz++) {
				FirmaBiome biome = (FirmaBiome) biomesForBiomeGen[cz + 1 + (cx + 1) * 18];
				if (isDirtBiome(biome)) {
					dirt = Util.getDirt(topRock);

					grass = Util.getGrass(topRock);
				} else if (isSandBiome(biome)) {
					dirt = Util.getSand(topRock);
					grass = dirt;
				} else if (isGravelBiome(biome)) {
					dirt = Util.getGravel(topRock);
					grass = dirt;
				} else if (isClayBiome(biome)) {
					dirt = Util.getClay(topRock);
					grass = dirt;
				} else {
					dirt = null;
					grass = null;
					throw new RuntimeException("Biome " + biome.getRegistryName() + " is invalid for FirmaWorld");
				}
				top = 255;
				for (top = 255; top > 0; top--) {
					b=primer.getBlockState(cx, top, cz).getBlock();
					if (b == STONE.getBlock() || b==OCEAN.getBlock()) {
						break;
					}
				}
				lt = top - 3;
				lb = lt / 3;
				lm = lb * 2;
				int bid = Biome.getIdForBiome(biome);
				for (cy = 0; cy <= top; cy++) {
					b = primer.getBlockState(cx, cy, cz).getBlock();
					if (b == STONE.getBlock()) {
						if (cy == 0) {
							primer.setBlockState(cx, cy, cz, bedRock);
						} else if (cy < lb) {
							primer.setBlockState(cx, cy, cz, botRock);
						} else if (cy < lm) {
							primer.setBlockState(cx, cy, cz, midRock);
						} else if (cy < lt) {
							primer.setBlockState(cx, cy, cz, topRock);
						} else if (cy == top) {
							primer.setBlockState(cx, cy, cz, grass);
						} else {
							primer.setBlockState(cx, cy, cz, dirt);
						}
					} else if (b == OCEAN.getBlock()) {
						if (!(FirmaGenLayer.isOceanic(bid) || FirmaGenLayer.isBeach(bid))) {
							// TODO Should swamp be fresh water or make new
							// brackish?
							primer.setBlockState(cx, cy, cz, this.RIVER);
						}
					}
				}
			}
		}
		return biomesForBiomeGen;
	}

	private static boolean isGravelBiome(Biome biome) {
		return biome == FirmaBiome.GRAVELBEACH;
	}

	private static boolean isSandBiome(Biome biome) {
		return biome == FirmaBiome.BEACH || biome == FirmaBiome.OCEAN || biome == FirmaBiome.DEEPOCEAN;
	}

	private static boolean isDirtBiome(Biome biome) {
		return biome == FirmaBiome.EXHILLS || biome == FirmaBiome.EXHILLSEDGE || biome == FirmaBiome.FOREST || biome == FirmaBiome.SWAMP
				|| biome == FirmaBiome.HILLS || biome == FirmaBiome.HILLSEDGE || biome == FirmaBiome.PLAINS;
	}

	private static boolean isClayBiome(Biome biome) {
		return biome == FirmaBiome.LAKE || biome == FirmaBiome.RIVER;
	}

	@Override
	public Chunk provideChunk(int x, int z) {

		this.rand.setSeed(x * 341873128712L + z * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();
		this.setBlocksInChunk(x, z, chunkprimer);
		Biome[] biomesForGeneration = this.replaceBiomeBlocks(x, z, chunkprimer);
		this.caveGenerator.generate(this.world, x, z, chunkprimer);
		this.ravineGenerator.generate(world, x, z, chunkprimer);
		Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
		byte[] abyte = chunk.getBiomeArray();

		for (int i = 0; i < abyte.length; i++) {
			abyte[i] = (byte) Biome.getIdForBiome(biomesForGeneration[i + 19]);
		}
		chunk.setBiomeArray(abyte);
		chunk.generateSkylightMap();

		return chunk;
	}

	@Override
	public void populate(int x, int z) {
		BlockFalling.fallInstantly = true;
		//int i = x * 16;
		//int j = z * 16;
		//BlockPos blockpos = new BlockPos(i, 0, j);
		// Biome biome = this.world.getBiome(blockpos.add(16, 0, 16));
		this.rand.setSeed(this.world.getSeed());
		long k = this.rand.nextLong() / 2L * 2L + 1L;
		long l = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed(x * k + z * l ^ this.world.getSeed());
		// boolean flag = false;
		// ChunkPos chunkpos = new ChunkPos(x, z);

		// net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true,
		// this, this.world, this.rand, x, z, flag);

		if (this.mapFeaturesEnabled) {
			if (this.settings.useMineShafts) {
				// this.mineshaftGenerator.generateStructure(this.world,
				// this.rand, chunkpos);
			}

			if (this.settings.useVillages) {
				// flag = this.villageGenerator.generateStructure(this.world,
				// this.rand, chunkpos);
			}

			if (this.settings.useStrongholds) {
				// this.strongholdGenerator.generateStructure(this.world,
				// this.rand, chunkpos);
			}

			if (this.settings.useTemples) {
				// this.scatteredFeatureGenerator.generateStructure(this.world,
				// this.rand, chunkpos);
			}

			if (this.settings.useMonuments) {
				// this.oceanMonumentGenerator.generateStructure(this.world,
				// this.rand, chunkpos);
			}
		}

		
		net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, x, z, false);

		BlockFalling.fallInstantly = false;
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		boolean flag = false;

		if (this.settings.useMonuments && this.mapFeaturesEnabled && chunkIn.getInhabitedTime() < 3600L) {
			// flag |= this.oceanMonumentGenerator.generateStructure(this.world,
			// this.rand, new ChunkPos(x, z));
		}
		return flag;
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		Biome biome = this.world.getBiome(pos);

		/*
		 * if (this.mapFeaturesEnabled) { if (creatureType ==
		 * EnumCreatureType.MONSTER &&
		 * this.scatteredFeatureGenerator.isSwampHut(pos)) { return
		 * this.scatteredFeatureGenerator.getScatteredFeatureSpawnList(); }
		 * 
		 * if (creatureType == EnumCreatureType.MONSTER &&
		 * this.settings.useMonuments &&
		 * this.oceanMonumentGenerator.isPositionInStructure(this.world, pos)) {
		 * return this.oceanMonumentGenerator.getScatteredFeatureSpawnList(); }
		 * }
		 */

		return biome.getSpawnableList(creatureType);
	}

	@Override
	@Nullable
	public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position, boolean p_180513_4_) {
		// Nothing to see here.

		/*
		 * return !this.mapFeaturesEnabled ? null :
		 * ("Stronghold".equals(structureName) && this.strongholdGenerator !=
		 * null ? this.strongholdGenerator.getClosestStrongholdPos(worldIn,
		 * position, p_180513_4_) : ("Monument".equals(structureName) &&
		 * this.oceanMonumentGenerator != null ?
		 * this.oceanMonumentGenerator.getClosestStrongholdPos(worldIn,
		 * position, p_180513_4_) : ("Village".equals(structureName) &&
		 * this.villageGenerator != null ?
		 * this.villageGenerator.getClosestStrongholdPos(worldIn, position,
		 * p_180513_4_) : ("Mineshaft".equals(structureName) &&
		 * this.mineshaftGenerator != null ?
		 * this.mineshaftGenerator.getClosestStrongholdPos(worldIn, position,
		 * p_180513_4_) : ("Temple".equals(structureName) &&
		 * this.scatteredFeatureGenerator != null ?
		 * this.scatteredFeatureGenerator.getClosestStrongholdPos(worldIn,
		 * position, p_180513_4_) : null)))));
		 */
		// long startTime = System.currentTimeMillis();
		// BlockPos r = !this.mapFeaturesEnabled ? null
		// : ("Village".equals(structureName) && this.villageGenerator != null
		// ? this.villageGenerator.getClosestStrongholdPos(worldIn, position,
		// p_180513_4_) : null);
		// long endTime = System.currentTimeMillis();
		// System.out.println("Made a chunk in "+(endTime-startTime)+" ms");
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		if (this.mapFeaturesEnabled) {
			if (this.settings.useMineShafts) {
				// this.mineshaftGenerator.generate(this.world, x, z,
				// (ChunkPrimer) null);
			}

			if (this.settings.useVillages) {
				// this.villageGenerator.generate(this.world, x, z,
				// (ChunkPrimer) null);
			}

			if (this.settings.useStrongholds) {
				// this.strongholdGenerator.generate(this.world, x, z,
				// (ChunkPrimer) null);
			}

			if (this.settings.useTemples) {
				// this.scatteredFeatureGenerator.generate(this.world, x, z,
				// (ChunkPrimer) null);
			}

			if (this.settings.useMonuments) {
				// this.oceanMonumentGenerator.generate(this.world, x, z,
				// (ChunkPrimer) null);
			}
		}
	}

	private void generateHeightmap(int x, int y, int z) {
		this.depthRegion = this.depthNoise.generateNoiseOctaves(this.depthRegion, x, z, 5, 5, this.settings.depthNoiseScaleX,
				 this.settings.depthNoiseScaleZ, this.settings.depthNoiseScaleExponent);
		float f = this.settings.coordinateScale;
		float f1 = this.settings.heightScale;
		this.mainNoiseRegion = this.mainPerlinNoise.generateNoiseOctaves(this.mainNoiseRegion, x, y, z, 5, 33, 5, f / this.settings.mainNoiseScaleX,
				f1 / this.settings.mainNoiseScaleY, f / this.settings.mainNoiseScaleZ);
		this.minLimitRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minLimitRegion, x, y, z, 5, 33, 5, f, f1, f);
		this.maxLimitRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxLimitRegion, x, y, z, 5, 33, 5, f, f1, f);
		int i = 0;
		int j = 0;

		for (int k = 0; k < 5; ++k) {
			for (int l = 0; l < 5; ++l) {
				float f2 = 0.0F;
				float f3 = 0.0F;
				float f4 = 0.0F;
				Biome biome = this.biomesForTerrainGen[k + 2 + (l + 2) * 10];

				for (int j1 = -2; j1 <= 2; ++j1) {
					for (int k1 = -2; k1 <= 2; ++k1) {
						Biome biome1 = this.biomesForTerrainGen[k + j1 + 2 + (l + k1 + 2) * 10];
						float f5 = this.settings.biomeDepthOffSet + biome1.getBaseHeight() * this.settings.biomeDepthWeight;
						float f6 = this.settings.biomeScaleOffset + biome1.getHeightVariation() * this.settings.biomeScaleWeight;

						float f7 = this.biomeWeights[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);

						if (biome1.getBaseHeight() > biome.getBaseHeight()) {
							f7 /= 2.0F;
						}

						f2 += f6 * f7;
						f3 += f5 * f7;
						f4 += f7;
					}
				}

				f2 = f2 / f4;
				f3 = f3 / f4;
				f2 = f2 * 0.9F + 0.1F;
				f3 = (f3 * 4.0F - 1.0F) / 8.0F;
				double d7 = this.depthRegion[j] / 8000.0D;

				if (d7 < 0.0D) {
					d7 = -d7 * 0.3D;
				}

				d7 = d7 * 3.0D - 2.0D;

				if (d7 < 0.0D) {
					d7 = d7 / 2.0D;

					if (d7 < -1.0D) {
						d7 = -1.0D;
					}

					d7 = d7 / 1.4D;
					d7 = d7 / 2.0D;
				} else {
					if (d7 > 1.0D) {
						d7 = 1.0D;
					}

					d7 = d7 / 8.0D;
				}

				++j;
				double d8 = f3;
				double d9 = f2;
				d8 = d8 + d7 * 0.2D;
				d8 = d8 * this.settings.baseSize / 8.0D;
				double d0 = this.settings.baseSize + d8 * 4.0D;

				for (int l1 = 0; l1 < 33; ++l1) {
					double d1 = (l1 - d0) * this.settings.stretchY * 128.0D / 256.0D / d9;

					if (d1 < 0.0D) {
						d1 *= 4.0D;
					}

					double d2 = this.minLimitRegion[i] / this.settings.lowerLimitScale;
					double d3 = this.maxLimitRegion[i] / this.settings.upperLimitScale;
					double d4 = (this.mainNoiseRegion[i] / 10.0D + 1.0D) / 2.0D;
					double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

					if (l1 > 29) {
						double d6 = (l1 - 29) / 3.0F;
						d5 = d5 * (1.0D - d6) + -10.0D * d6;
					}

					this.heightMap[i] = d5;
					++i;
				}
			}
		}
	}

	public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
		this.biomesForTerrainGen = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForTerrainGen, x * 4 - 2, z * 4 - 2, 10, 10);
		this.generateHeightmap(x * 4, 0, z * 4);

		for (int i = 0; i < 4; ++i) {
			int j = i * 5;
			int k = (i + 1) * 5;

			for (int l = 0; l < 4; ++l) {
				int i1 = (j + l) * 33;
				int j1 = (j + l + 1) * 33;
				int k1 = (k + l) * 33;
				int l1 = (k + l + 1) * 33;

				for (int i2 = 0; i2 < 32; ++i2) {
					double d1 = this.heightMap[i1 + i2];
					double d2 = this.heightMap[j1 + i2];
					double d3 = this.heightMap[k1 + i2];
					double d4 = this.heightMap[l1 + i2];
					double d5 = (this.heightMap[i1 + i2 + 1] - d1) * 0.125D;
					double d6 = (this.heightMap[j1 + i2 + 1] - d2) * 0.125D;
					double d7 = (this.heightMap[k1 + i2 + 1] - d3) * 0.125D;
					double d8 = (this.heightMap[l1 + i2 + 1] - d4) * 0.125D;

					for (int j2 = 0; j2 < 8; ++j2) {
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * 0.25D;
						double d13 = (d4 - d2) * 0.25D;

						for (int k2 = 0; k2 < 4; ++k2) {
							double d16 = (d11 - d10) * 0.25D;
							double lvt_45_1_ = d10 - d16;

							for (int l2 = 0; l2 < 4; ++l2) {
								if ((lvt_45_1_ += d16) > 0.0D) {
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, STONE);
								} else if (i2 * 8 + j2 < this.settings.seaLevel) {
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, this.OCEAN);
								}
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}
}
