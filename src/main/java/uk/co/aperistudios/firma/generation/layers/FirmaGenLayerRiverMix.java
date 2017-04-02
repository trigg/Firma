package uk.co.aperistudios.firma.generation.layers;

import net.minecraft.world.gen.layer.GenLayer;

public class FirmaGenLayerRiverMix extends FirmaGenLayer {

	private GenLayer biomePatternGeneratorChain;
	private GenLayer riverPatternGeneratorChain;
	private int[] layerBiomes;
	private int[] layerRivers;
	private int[] layerOut;
	private int xn;
	private int xp;
	private int zn;
	private int zp;

	public FirmaGenLayerRiverMix(long par1, GenLayer par3GenLayer, GenLayer par4GenLayer) {
		super(par1);
		this.biomePatternGeneratorChain = par3GenLayer;
		this.riverPatternGeneratorChain = par4GenLayer;
	}

	@Override
	public int[] getInts(int x, int z, int xSize, int zSize) {
		layerBiomes = this.biomePatternGeneratorChain.getInts(x, z, xSize, zSize);
		layerRivers = this.riverPatternGeneratorChain.getInts(x, z, xSize, zSize);
		layerOut = new int[xSize * zSize];

		for (int zElement = 0; zElement < zSize; ++zElement) {
			for (int xElement = 0; xElement < xSize; ++xElement) {
				int index = xElement + zElement * xSize;
				int b = layerBiomes[index];
				int r = layerRivers[index];

				xn = index - 1;
				xp = index + 1;
				zn = index - zSize;
				zp = index + zSize;

				if (isOceanic(b) || isMountain(b))
					layerOut[index] = b;
				else if (r > 0) {
					layerOut[index] = r;

					// Here we make sure that rivers dont run along ocean/beach
					// splits. We turn the river into oceans.
					if (isBeach(b)) {
						layerOut[index] = Layer.OCEAN;
						if (inBounds(xn, layerOut) && layerOut[xn] == Layer.RIVER) {
							layerOut[xn] = Layer.OCEAN;
						}
						if (inBounds(zn, layerOut) && layerOut[zn] == Layer.RIVER) {
							layerOut[zn] = Layer.OCEAN;
						}
						if (inBounds(zp, layerOut) && isOceanic(layerBiomes[zp]) && layerRivers[zp] == 0) {
							layerOut[index] = b;
						}
						if (inBounds(zn, layerOut) && isOceanic(layerBiomes[zn]) && layerRivers[zn] == 0) {
							layerOut[index] = b;
						}
						if (inBounds(xn, layerOut) && isOceanic(layerBiomes[xn]) && layerRivers[xn] == 0) {
							layerOut[index] = b;
						}
						if (inBounds(xp, layerOut) && isOceanic(layerBiomes[xp]) && layerRivers[xp] == 0) {
							layerOut[index] = b;
						}
					}
				} else {
					layerOut[index] = b;
				}

				// Similar to above, if we're near a lake, we turn the river
				// into lake.
				removeRiver(index, Layer.LAKE);
				removeRiver(index, Layer.HILLSEDGE);

				validateInt(layerOut, index);
			}
		}
		return layerOut.clone();
	}

	public void removeRiver(int index, int biomeToReplaceWith) {
		if (layerOut[index] == Layer.RIVER) {
			if (xn >= 0 && layerBiomes[xn] == biomeToReplaceWith) {
				layerOut[index] = biomeToReplaceWith;
			}
			if (zn >= 0 && layerBiomes[zn] == biomeToReplaceWith) {
				layerOut[index] = biomeToReplaceWith;
			}
			if (xp < layerBiomes.length && layerBiomes[xp] == biomeToReplaceWith) {
				layerOut[index] = biomeToReplaceWith;
			}
			if (zp < layerBiomes.length && layerBiomes[zp] == biomeToReplaceWith) {
				layerOut[index] = biomeToReplaceWith;
			}
		}
	}

	public boolean inBounds(int index, int[] array) {
		return index < array.length && index >= 0;
	}

	/**
	 * Initialize layer's local worldGenSeed based on its own baseSeed and the
	 * world's global seed (passed in as an argument).
	 */
	@Override
	public void initWorldGenSeed(long par1) {
		this.biomePatternGeneratorChain.initWorldGenSeed(par1);
		this.riverPatternGeneratorChain.initWorldGenSeed(par1);
		super.initWorldGenSeed(par1);
	}
}
