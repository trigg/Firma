package uk.co.aperistudios.firma.generation.layers;

import net.minecraft.world.gen.layer.GenLayer;

public class FirmaGenLayerFuzzyZoom extends FirmaGenLayer {
	public FirmaGenLayerFuzzyZoom(long par1, GenLayer par3GenLayer)
	{
		super(par1);
		super.parent = par3GenLayer;
	}

	@Override
	public int[] getInts(int x, int y, int w, int h)
	{
		int var5 = x >> 1;
		int var6 = y >> 1;
		int var7 = (w >> 1) + 3;
		int var8 = (h >> 1) + 3;
		int[] var9 = this.parent.getInts(var5, var6, var7, var8);
		int[] var10 = new int[var7 * 2 * var8 * 2];
		int var11 = var7 << 1;
		int var13;

		for (int var12 = 0; var12 < var8 - 1; ++var12)
		{
			var13 = var12 << 1;
			int var14 = var13 * var11;
			int var15 = var9[0 + (var12 + 0) * var7];
			int var16 = var9[0 + (var12 + 1) * var7];

			for (int var17 = 0; var17 < var7 - 1; ++var17)
			{
				this.initChunkSeed(var17 + var5 << 1, var12 + var6 << 1);
				int var18 = var9[var17 + 1 + (var12 + 0) * var7];
				int var19 = var9[var17 + 1 + (var12 + 1) * var7];
				var10[var14] = var15;
				var10[var14++ + var11] = this.choose(var15, var16);
				var10[var14] = this.choose(var15, var18);
				var10[var14++ + var11] = this.choose(var15, var18, var16, var19);
				var15 = var18;
				var16 = var19;
			}
		}

		int[] var20 = new int[w * h];

		for (var13 = 0; var13 < h; ++var13)
			System.arraycopy(var10, (var13 + (y & 1)) * (var7 << 1) + (x & 1), var20, var13 * w, w); //NOPMD

		return var20;
	}

	/**
	 * randomly choose between the two args
	 */
	protected int choose(int par1, int par2)
	{
		return this.nextInt(2) == 0 ? par1 : par2;
	}

	/**
	 * randomly choose between the four args
	 */
	protected int choose(int par1, int par2, int par3, int par4)
	{
		int var5 = this.nextInt(4);
		return var5 == 0 ? par1 : var5 == 1 ? par2 : var5 == 2 ? par3 : par4;
	}
}
